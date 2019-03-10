package com.ReadEnjoyBack.controller.backend;

import com.ReadEnjoyBack.common.Const;
import com.ReadEnjoyBack.common.ResponseCode;
import com.ReadEnjoyBack.common.ServerResponse;
import com.ReadEnjoyBack.pojo.User;
import com.ReadEnjoyBack.service.IUserService;
import com.ReadEnjoyBack.util.MD5Util;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Author:HB
 * @Description: 用户后台登录管理
 * @Createdata:Created in  19:46  2018/5/23.
 */
@Controller
@RequestMapping("/manage/user")
public class UserManageController {
    // 注入iUserService
    @Autowired
    private IUserService iUserService;

    /*
        * @Author:HB
        * @Description: 系统管理员登录验证
        * @Data:16:37 2018/5/23
        * @param username
        * @param password
        * @param session
         returns:com.mmall.common.ServerResponse<com.mmall.pojo.User>
        */
    @RequestMapping(value = "login.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> login(String email, String password, HttpSession session,HttpServletRequest request,
                                      HttpServletResponse response){
        // 解决跨域
        response.addHeader("Access-Control-Allow-Origin",request.getHeader("Origin"));
        // 跨域的session 保证同一性
        response.addHeader("Access-Control-Allow-Credentials","true");
        String MD5Password = MD5Util.MD5EncodeUtf8(password);
        ServerResponse<User> loginResponse = iUserService.login(email,MD5Password);
        if (loginResponse.isSuccess()){
            User user = loginResponse.getData();
            if (user.getRole() == Const.Role.ROLE_ADMIN){
                // 权限为管理员
                session.setAttribute(Const.CURRENT_USER,user);
                return loginResponse;
            }else{
                return ServerResponse.createByErrorMessage("不是管理员，无法登录");
            }
        }
        return loginResponse;
    }
    /*
     * @Author:HB
     * @Description: 得到所有用户的列表
     * @Data:20:56 2018/6/14
     * @param pageSize pageNum
     returns:
    */
    @RequestMapping(value = "get_user_list.do")
    @ResponseBody
    ServerResponse<PageInfo> getUserList(HttpSession session, HttpServletRequest request, HttpServletResponse response,
                                               @RequestParam(value = "pageNum",defaultValue = "1") int pageNum, @RequestParam(value = "pageSize",
                                            defaultValue = "10") int pageSize ){
        // 解决跨域
        response.addHeader("Access-Control-Allow-Origin",request.getHeader("Origin"));
        // 跨域的session 保证同一性
        response.addHeader("Access-Control-Allow-Credentials","true");
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if (user == null){
         //return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，请进行管理员登录");
            return ServerResponse.createByErrorMessage("用户未登录，请进行管理员登录");
        }
        //判断权限
        if (iUserService.checkAdminRole(user).isSuccess()){
            return iUserService.getUserList(pageNum,pageSize);
        }else {
            return ServerResponse.createByErrorMessage("用户暂无管理员权限");
        }
    }
    /*
    * @Author:HB
    * @Description: 增加用户
    * @Data:20:56 2018/6/14
    * @param pageSize pageNum
    returns:
   */
    @RequestMapping(value = "add_user.do")
    @ResponseBody
    ServerResponse<String> addUser(HttpSession session, HttpServletRequest request, HttpServletResponse response,User user){
        // 解决跨域
        response.addHeader("Access-Control-Allow-Origin",request.getHeader("Origin"));
        // 跨域的session 保证同一性
        response.addHeader("Access-Control-Allow-Credentials","true");
        User adminUser = (User)session.getAttribute(Const.CURRENT_USER);
        if (adminUser == null){
            //return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，请进行管理员登录");
            return ServerResponse.createByErrorMessage("用户未登录，请进行管理员登录");
        }
        //判断权限
        if (iUserService.checkAdminRole(adminUser).isSuccess()){
            return iUserService.addUser(user);
        }else {
            return ServerResponse.createByErrorMessage("用户暂无管理员权限");
        }
    }
    /*
     * @Author:HB
     * @Description: 删除用户
     * @Data:21:21 2018/6/17
     * @param userId
     returns:
    */
    @RequestMapping(value = "delete_user.do")
    @ResponseBody
    ServerResponse<String> deleteUser(HttpSession session, HttpServletRequest request, HttpServletResponse response,Integer userId){
        // 解决跨域
        response.addHeader("Access-Control-Allow-Origin",request.getHeader("Origin"));
        // 跨域的session 保证同一性
        response.addHeader("Access-Control-Allow-Credentials","true");
        User adminUser = (User)session.getAttribute(Const.CURRENT_USER);
        if (adminUser == null){
            //return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，请进行管理员登录");
            return ServerResponse.createByErrorMessage("用户未登录，请进行管理员登录");
        }
        //判断权限
        if (iUserService.checkAdminRole(adminUser).isSuccess()){
            return iUserService.deleteUser(userId);
        }else {
            return ServerResponse.createByErrorMessage("用户暂无管理员权限");
        }
    }
     /*
      * @Author:HB
      * @Description: 修改用户状态
      * @Data:21:21 2018/6/17
      * @param userId
      returns:
     */
    @RequestMapping(value = "modify_user_status.do")
    @ResponseBody
    ServerResponse<String> modifyUserStatus(HttpSession session, HttpServletRequest request, HttpServletResponse response,Integer userId){
        // 解决跨域
        response.addHeader("Access-Control-Allow-Origin",request.getHeader("Origin"));
        // 跨域的session 保证同一性
        response.addHeader("Access-Control-Allow-Credentials","true");
        User adminUser = (User)session.getAttribute(Const.CURRENT_USER);
        if (adminUser == null){
            //return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，请进行管理员登录");
            return ServerResponse.createByErrorMessage("用户未登录，请进行管理员登录");
        }
        //判断权限
        if (iUserService.checkAdminRole(adminUser).isSuccess()){
            return iUserService.modifyUserStatus(userId);
        }else {
            return ServerResponse.createByErrorMessage("用户暂无管理员权限");
        }
    }

}

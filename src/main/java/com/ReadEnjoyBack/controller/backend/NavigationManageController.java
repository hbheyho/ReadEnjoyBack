package com.ReadEnjoyBack.controller.backend;

import com.ReadEnjoyBack.common.Const;
import com.ReadEnjoyBack.common.ResponseCode;
import com.ReadEnjoyBack.common.ServerResponse;
import com.ReadEnjoyBack.pojo.Navigation;
import com.ReadEnjoyBack.pojo.User;
import com.ReadEnjoyBack.service.INavigationService;
import com.ReadEnjoyBack.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Author:HB
 * @Description: 对导航模块的后台管理
 * @Createdata:Created in  10:21  2018/5/24.
 */
@Controller
@RequestMapping("/manage/navigation")
public class NavigationManageController {
    // 注入INavigation Service 和 IUserservice
    @Autowired
    private INavigationService iNavigationService;
    @Autowired
    private IUserService iUserService;

    /*
     * @Author:HB
     * @Description: 增加导航项
     * @Data:10:50 2018/5/24
     * @param parentId
    * @param navName
    * @param session
     returns:com.ReadEnjoyBack.common.ServerResponse<java.lang.String>
     */
    @RequestMapping(value = "add_navigation.do")
    @ResponseBody
    public ServerResponse<String> addNavigation(HttpSession session, String navName, String url,
                                                @RequestParam(value = "parentId" , defaultValue = "0") Integer parentId,
                                                HttpServletRequest request, HttpServletResponse response){
        // 解决跨域
        response.addHeader("Access-Control-Allow-Origin",request.getHeader("Origin"));
        // 跨域的session 保证同一性
        response.addHeader("Access-Control-Allow-Credentials","true");
         // 判断用户是否登录
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户暂未登录，请登录");
        }
        // 检验是否为管理员
        if (iUserService.checkAdminRole(user).isSuccess()){
            // 是管理员
            // 进行相应逻辑操作
            return  iNavigationService.addNavigation(navName,url,parentId);
        }else{
            return ServerResponse.createByErrorMessage("不是管理员，无权进行相应操作！");
        }
    }
    /*
     * @Author:HB
     * @Description:修改导航名字/url
     * @Data:14:17 2018/5/24
     * @param session
    * @param navId
    * @param navName
     returns:com.ReadEnjoyBack.common.ServerResponse<java.lang.String>
      */
    @RequestMapping(value = "set_navigationName.do")
    @ResponseBody
    public ServerResponse<String> setNavigationName(HttpSession session,Integer navId,String navName,String url,
                                                    HttpServletResponse response,HttpServletRequest request){
        // 解决跨域
        response.addHeader("Access-Control-Allow-Origin",request.getHeader("Origin"));
        // 跨域的session 保证同一性
        response.addHeader("Access-Control-Allow-Credentials","true");
        // 判断用户是否登录
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户暂未登录，请登录");
        }
        // 检验是否为管理员
        if (iUserService.checkAdminRole(user).isSuccess()){
            // 是管理员
            // 进行相应逻辑操作
            return  iNavigationService.updateNavUsername(navId,navName,url);
        }else{
            return ServerResponse.createByErrorMessage("不是管理员，无权进行相应操作！");
        }
    }
     /*
      * @Author:HB
      * @Description: 获取当前导航的子节点(平级)
      * @Data:14:34 2018/5/24
      * @param session
     * @param navId
      returns:com.ReadEnjoyBack.common.ServerResponse<java.lang.String>
      */
    @RequestMapping(value = "get_navigation.do")
    @ResponseBody
    public ServerResponse getNavigation(HttpSession session,@RequestParam(value = "navId",defaultValue = "0") Integer navId,
                                        HttpServletRequest request,HttpServletResponse response){
        // 解决跨域
        response.addHeader("Access-Control-Allow-Origin",request.getHeader("Origin"));
        // 跨域的session 保证同一性
        response.addHeader("Access-Control-Allow-Credentials","true");
        User user = (User)session.getAttribute(Const.CURRENT_USER);
         if (user == null){
             return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户暂未登录，请登录");
         }
        if (iUserService.checkAdminRole(user).isSuccess()){
            // 查询子节点的categoryId 并且不递归 保持平级
            return iNavigationService.getChildrenParallelNavigation(navId);
        }else {
            return ServerResponse.createByErrorMessage("不是管理员，无权进行相应操作！");
        }
    }
    /*
     * @Author:HB
     * @Description: 获取当前导航的子节点(深度查询)
     * @Data:15:13 2018/5/24
     * @param session
    * @param navId
     returns:com.ReadEnjoyBack.common.ServerResponse
     */
    @RequestMapping(value = "get_deep_navigation.do")
    @ResponseBody
    public ServerResponse<List<Navigation>> getDeepNavigation(HttpSession session, @RequestParam(value = "navId",defaultValue = "0") Integer navId,
                                                              HttpServletResponse response,HttpServletRequest request){
        // 解决跨域
        response.addHeader("Access-Control-Allow-Origin",request.getHeader("Origin"));
        // 跨域的session 保证同一性
        response.addHeader("Access-Control-Allow-Credentials","true");
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户暂未登录，请登录");
        }
        if (iUserService.checkAdminRole(user).isSuccess()){
            // 查询子节点的categoryId 递归查询
            return iNavigationService.getDeepChildrenParallelNavigation(navId);
        }else {
            return ServerResponse.createByErrorMessage("不是管理员，无权进行相应操作！");
        }
    }

}

package com.ReadEnjoyBack.controller.portal;

import com.ReadEnjoyBack.common.ResponseCode;
import com.ReadEnjoyBack.common.ServerResponse;
import com.ReadEnjoyBack.pojo.User;
import com.ReadEnjoyBack.service.IFileService;
import com.ReadEnjoyBack.service.IUserService;
import com.ReadEnjoyBack.common.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;
import javax.xml.ws.spi.http.HttpContext;

/**
 * @Author:HB
 * @Description:
 * @Createdata:Created in  15:17  2018/5/17.
 */
@Controller
@RequestMapping("/user/")
public class UserController {
     @Autowired
     private IUserService iUserService;
    @Autowired
    private IFileService iFileService;
      /*
      * @Author:HB
      * @Description: 用户登录
      * @Data:15:34 2018/5/17
      * @param username
     * @param password
     * @param session
      returns:java.lang.Object
     */
    @RequestMapping(value = "login.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> login(String username, String password, HttpSession session, HttpServletResponse response,
                                      HttpServletRequest request){
        // 解决跨域
        response.addHeader("Access-Control-Allow-Origin",request.getHeader("Origin"));
        // 跨域保证session 同一性
        response.addHeader("Access-Control-Allow-Credentials","true");
        ServerResponse<User> responseInfo = iUserService.login(username,password);
        if (responseInfo.isSuccess()){
           session.setAttribute(Const.CURRENT_USER,responseInfo.getData());
        }
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        System.out.println("用户信息是："+ user);
        return responseInfo;
    }
    /*
    * @Author:HB
    * @Description:  退出登录
    * @Data:19:33 2018/5/17
    * @param session
    returns:com.ReadEnjoyBack.common.ServerResponse<java.lang.String>
    */
    @RequestMapping(value = "logout.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> logout(HttpSession session,HttpServletResponse response,
                                         HttpServletRequest request){
        // 解决跨域
        response.addHeader("Access-Control-Allow-Origin",request.getHeader("Origin"));
        // 跨域保证session 同一性
        response.addHeader("Access-Control-Allow-Credentials","true");
       session.removeAttribute(Const.CURRENT_USER);
        return ServerResponse.createBySuccessMessage("用户退出成功！");
    }

    /*
     * @Author:HB
     * @Description: 用户注册模块
     * @Data:19:38 2018/5/17
     * @param userInfo
     returns:com.ReadEnjoyBack.common.ServerResponse<java.lang.String>
    */
    @RequestMapping(value = "register.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> register(User user,HttpServletRequest request,HttpServletResponse response){
        // 解决跨域
        response.addHeader("Access-Control-Allow-Origin",request.getHeader("Origin"));
        return iUserService.register(user);
    }
    /*
    * @Author:HB
    * @Description: 用户名和email的实时验证
    * @Data:8:39 2018/5/21
    * @param str
    * @param type
    returns:com.ReadEnjoyBack.common.ServerResponse<java.lang.String>
   */
    @RequestMapping(value = "check_valid.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> checkValid(String str,String type,HttpServletResponse response,HttpServletRequest request){
        // 解决跨域
        response.addHeader("Access-Control-Allow-Origin",request.getHeader("Origin"));
        return iUserService.checkValid(str,type);
    }
    /*
     * @Author:HB
     * @Description: 得到当前登录用户信息
     * @Data:8:55 2018/5/21
     * @param session
     returns:com.ReadEnjoyBack.common.ServerResponse<com.ReadEnjoyBack.pojo.User>
    */
    @RequestMapping(value = "get_user_info.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> getUserInfo(HttpSession session, HttpServletResponse response, HttpServletRequest request){
        // 解决跨域
        response.addHeader("Access-Control-Allow-Origin",request.getHeader("Origin"));
        // 跨域的session 保证同一性
        response.addHeader("Access-Control-Allow-Credentials","true");
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        System.out.println("用户信息是："+ user);
        if (user != null){
            return ServerResponse.createBySuccesse(user);
        }
        return ServerResponse.createBySuccessMessage("用户暂未登录");
    }
    /*
     * @Author:HB
     * @Description: 根据用户名得到用户的密保问题
     * @Data:8:52 2018/5/21
     * @param username
     returns:com.ReadEnjoyBack.common.ServerResponse<java.lang.String>
    */
    @RequestMapping(value = "forget_get_question.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> getQuestionByUsername(String username,HttpServletResponse response,HttpServletRequest request){
        // 解决跨域
        response.addHeader("Access-Control-Allow-Origin",request.getHeader("Origin"));
        return iUserService.getQuestionByUsername(username);
    }
    /*
     * @Author:HB
     * @Description: 根据密保问题验证密保答案
     * @Data:9:30 2018/5/21
     * @param username
    * @param question
    * @param answer
     returns:com.ReadEnjoyBack.common.ServerResponse<java.lang.String>
     */
    @RequestMapping(value = "forget_check_answer.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> checkAnswerByQuestion(String username,String question,String answer,HttpServletRequest request
    ,HttpServletResponse response){
        // 解决跨域
        response.addHeader("Access-Control-Allow-Origin",request.getHeader("Origin"));
        return iUserService.checkAnswerByQuestion(username,question,answer);
    }
    /*
     * @Author:HB
     * @Description: 根据用户返回的forgetToken修改密码(忘记密码)
     * @Data:10:00 2018/5/21
     * @param username
    * @param passwordNew
    * @param forgetToken
     returns:com.ReadEnjoyBack.common.ServerResponse<java.lang.String>
     */
    @RequestMapping(value = "forget_reset_password.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> resetPasswordByForgetToken(String username,String passwordNew,String forgetToken ,HttpServletRequest request
            ,HttpServletResponse response){
        // 解决跨域
        response.addHeader("Access-Control-Allow-Origin",request.getHeader("Origin"));
        return iUserService.resetPasswordByForgetToken(username,passwordNew,forgetToken);
    }
    /*
    * @Author:HB
    * @Description: 修改用户密码（用户登录之后）
    * @Data:14:42 2018/5/21
    * @param session
   * @param passwordOld
   * @param passwordNew
    returns:com.ReadEnjoyBack.common.ServerResponse<java.lang.String>
    */
    @RequestMapping(value = "reset_password.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> resetPassword(HttpSession session,String passwordOld,String passwordNew,HttpServletResponse response,
                                                HttpServletRequest request){
        // 解决跨域
        response.addHeader("Access-Control-Allow-Origin",request.getHeader("Origin"));
        // 跨域的session 保证同一性
        response.addHeader("Access-Control-Allow-Credentials","true");
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.createByErrorMessage("用户暂未登录");
        }
        return iUserService.resetPassword(passwordOld,passwordNew,user);
    }
    /*
     * @Author:HB
     * @Description: 登录之后更新用户信息
     * @Data:15:03 2018/5/21
     * @param session
     * @param user
     returns:com.ReadEnjoyBack.common.ServerResponse<com.ReadEnjoyBack.pojo.User>
    */
    @RequestMapping(value = "update_information.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> updateInformation(HttpSession session,User user,HttpServletRequest request,HttpServletResponse response){
        // 解决跨域
        response.addHeader("Access-Control-Allow-Origin",request.getHeader("Origin"));
        // 跨域的session 保证同一性
        response.addHeader("Access-Control-Allow-Credentials","true");
        User currentUser = (User)session.getAttribute(Const.CURRENT_USER);
        if (currentUser == null){
            return ServerResponse.createByErrorMessage("用户暂未登录");
        }
        System.out.println(user);
        user.setId(currentUser.getId());
        user.setUsername(currentUser.getUsername());
        ServerResponse<User> responseInfo = iUserService.updateInformation(user);
        if (responseInfo.isSuccess()){
            session.setAttribute(Const.CURRENT_USER,responseInfo.getData());
        }
        return responseInfo;
    }
    /*
     * @Author:HB
     * @Description: 获取登录用户信息
     * @Data:10:50 2018/5/21
     * @param session
     returns:com.ReadEnjoyBack.common.ServerResponse<com.ReadEnjoyBack.pojo.User>
    */
    @RequestMapping(value = "get_information.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> getInformation(HttpSession session,HttpServletResponse response,HttpServletRequest request){
        // 解决跨域
        response.addHeader("Access-Control-Allow-Origin",request.getHeader("Origin"));
        // 跨域的session 保证同一性
        response.addHeader("Access-Control-Allow-Credentials","true");
        User currentUser = (User)session.getAttribute(Const.CURRENT_USER);
        if (currentUser == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"未登录，需要强制登录");
        }
        return iUserService.getInformation(currentUser.getId());
    }

    /*
      * @Author:HB
      * @Description: 用户头像上传
      * @Data:9:13 2018/6/9
      * @param MultipartFile
      returns:
     */
    @RequestMapping(value = "upload_img.do")
    @ResponseBody
    public ServerResponse uploadImg(HttpSession session, @RequestParam(value = "upload_img",required = false) MultipartFile file,
                                     HttpServletRequest request,HttpServletResponse response){
        // 解决跨域
        response.addHeader("Access-Control-Allow-Origin",request.getHeader("Origin"));
        // 跨域的session 保证同一性
        response.addHeader("Access-Control-Allow-Credentials","true");
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，登录之后在进行操作噢！");
        }
        // 上传文件的路径
        String path =request.getSession().getServletContext().getRealPath("upload");
        return iFileService.uploadImg(file,path,user.getUsername());
        /*String url = PropertiesUtil.getProperty("ftp.server.http.prefix") + targetFileName;*/
        // url拼接返回
        /*Map fileMap = Maps.newHashMap();
        fileMap.put("uri",targetFileName);
        fileMap.put("url",url);*/
        /*return ServerResponse.createBySuccesse(fileMap);*/
    }



}

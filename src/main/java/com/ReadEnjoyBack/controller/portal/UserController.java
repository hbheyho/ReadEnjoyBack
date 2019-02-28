package com.ReadEnjoyBack.controller.portal;

import com.ReadEnjoyBack.common.ResponseCode;
import com.ReadEnjoyBack.common.ServerResponse;
import com.ReadEnjoyBack.pojo.Comments;
import com.ReadEnjoyBack.pojo.User;
import com.ReadEnjoyBack.service.IFileService;
import com.ReadEnjoyBack.service.IUserService;
import com.ReadEnjoyBack.common.Const;
import com.ReadEnjoyBack.util.CookieUtils;
import com.ReadEnjoyBack.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
/*import javax.servlet.http.HttpSessionContext;
import javax.xml.ws.spi.http.HttpContext;
import java.util.List;*/

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
    public ServerResponse<User> login(String email, String password,@RequestParam(required = false) String agree, HttpSession session, HttpServletResponse response,
                                      HttpServletRequest request){
        // 解决跨域
        response.addHeader("Access-Control-Allow-Origin",request.getHeader("Origin"));
        // 跨域保证session 同一性
        response.addHeader("Access-Control-Allow-Credentials","true");
        String MD5Password = MD5Util.MD5EncodeUtf8(password);
        ServerResponse<User> responseInfo = iUserService.login(email,MD5Password);
        if (responseInfo.isSuccess()){
            // 勾选了自动登录
            if (agree.equals("agree")){
                String remember_me = email + "-" + MD5Util.MD5EncodeUtf8(password);
                CookieUtils.addCookie(response,"remember_me",remember_me,null);
            }
           session.setAttribute(Const.CURRENT_USER,responseInfo.getData());
        }
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
        session.removeAttribute(Const.CURRENT_USER); // 结束当前会话
        CookieUtils.delCookie(request,response,"remember_me"); //删除记住我cookie
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
        if (user != null){
            return ServerResponse.createBySuccesse(user);
        }
        return ServerResponse.createByErrorMessage("用户暂未登录");
    }
    /*
     * @Author:HB
     * @Description: 根据email得到用户的密保问题
     * @Data:8:52 2018/5/21
     * @param username
     returns:com.ReadEnjoyBack.common.ServerResponse<java.lang.String>
    */
    @RequestMapping(value = "forget_get_question.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> getQuestionByEmail(String email,HttpServletResponse response,HttpServletRequest request){
        // 解决跨域
        response.addHeader("Access-Control-Allow-Origin",request.getHeader("Origin"));
        return iUserService.getQuestionByEmail(email);
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
    public ServerResponse<String> checkAnswerByQuestion(String email,String question,String answer,HttpServletRequest request
    ,HttpServletResponse response){
        // 解决跨域
        response.addHeader("Access-Control-Allow-Origin",request.getHeader("Origin"));
        return iUserService.checkAnswerByQuestion(email,question,answer);
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
    public ServerResponse<String> resetPasswordByForgetToken(String email,String passwordNew,String forgetToken ,HttpServletRequest request
            ,HttpServletResponse response){
        // 解决跨域
        response.addHeader("Access-Control-Allow-Origin",request.getHeader("Origin"));
        return iUserService.resetPasswordByForgetToken(email,passwordNew,forgetToken);
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
        user.setId(currentUser.getId());
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
            return ServerResponse.createByErrorMessage("请登录哦");
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
    /*
     * @Author:HB
     * @Description: 邮箱验证
     * @Data:17:23 2019/1/16
     * @param email
      returns: Token
    */
    @RequestMapping(value = "validateEmail.do")
    @ResponseBody
    public ServerResponse<String> sendEmailToValidate(@RequestParam String email,@RequestParam int code , HttpServletRequest request,HttpServletResponse response){
        // 解决跨域
        response.addHeader("Access-Control-Allow-Origin",request.getHeader("Origin"));
        return iUserService.sendEmailToValidate(email,code);
    }
    
    /*
     * @Author:HB
     * @Description: Token验证
     * @Data:17:51 2019/1/16
     * @param null
     returns:
    */
    @RequestMapping(value = "validateEmailByToken.do")
    @ResponseBody
    public ServerResponse<String> sendTokenToValidate(@RequestParam String email,@RequestParam String Token,HttpServletRequest  request,
                                                      HttpServletResponse response){
        // 解决跨域
        response.addHeader("Access-Control-Allow-Origin",request.getHeader("Origin"));
        return iUserService.sendTokenToValidate(email,Token);
    }

    /*
     * @Author:HB
     * @Description: 得到用户当前状态
     * @Data:17:51 2019/1/16
     * @param email
     returns:
    */
    @RequestMapping(value = "getUserStatus.do")
    @ResponseBody
    public ServerResponse<String> getUserStatus(@RequestParam String email,HttpServletRequest  request,
                                                      HttpServletResponse response){
        // 解决跨域
        response.addHeader("Access-Control-Allow-Origin",request.getHeader("Origin"));
        return iUserService.getUserStatus(email);
    }

    /*
     * @Author:HB
     * @Description: 得到用户的所有评论信息
     * @Data:17:51 2019/1/16
     * @param email
     returns:
    */
    @RequestMapping(value = "get_User_AllComments.do")
    @ResponseBody
    public ServerResponse<List<Comments>> getUserAllComments(HttpSession session, HttpServletRequest  request, HttpServletResponse response){
        // 解决跨域
        response.addHeader("Access-Control-Allow-Origin",request.getHeader("Origin"));
        // 跨域的session 保证同一性
        response.addHeader("Access-Control-Allow-Credentials","true");
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.createByErrorMessage("用户未登录，登录之后在进行操作噢！");
        }
        String email = user.getEmail();
        return iUserService.getUserAllComments(email);
    }
        /* * @Author:HB
         * @Description: 删除评论信息
         * @Data:17:51 2019/1/16
         * @param email
         returns:*/
    @RequestMapping(value = "delete_User_Comments.do")
    @ResponseBody
    public ServerResponse<String> deleteUserComments(@RequestParam(value = "commentId") int commentId, HttpSession session,
                                                     HttpServletRequest  request, HttpServletResponse response){
        // 解决跨域
        response.addHeader("Access-Control-Allow-Origin",request.getHeader("Origin"));
        // 跨域的session 保证同一性
        response.addHeader("Access-Control-Allow-Credentials","true");
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，登录之后在进行操作噢！");
        }
        String email = user.getEmail();
        return iUserService.deleteUserComments(commentId);
    }
    /*
     * @Author:HB
     * @Description: 用户反馈信息插入
     * @Data:21:28 2019/2/26
     * @param feedbackInfo feedbackName
     returns:
    */
    @RequestMapping(value = "user_feedback.do")
    @ResponseBody
    public ServerResponse<String> feedBackDo(HttpServletRequest request, HttpServletResponse response, HttpSession session,
                                             @RequestParam(value = "feedbackName") String feedbackName,
                                             @RequestParam(value = "feedbackInfo") String feedbackInfo){
        // 解决跨域
        response.addHeader("Access-Control-Allow-Origin",request.getHeader("Origin"));
        // 跨域的session 保证同一性
        response.addHeader("Access-Control-Allow-Credentials","true");
        String userName; // 若用户登录 则用户名 不然则游客
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            userName = "游客";
        }else {
            userName = user.getUsername();
        }
        return iUserService.feedbackDo(userName,feedbackInfo,feedbackName);
    }

}

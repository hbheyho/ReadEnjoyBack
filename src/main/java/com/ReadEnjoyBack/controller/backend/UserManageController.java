package com.ReadEnjoyBack.controller.backend;

import com.ReadEnjoyBack.common.Const;
import com.ReadEnjoyBack.common.ServerResponse;
import com.ReadEnjoyBack.pojo.User;
import com.ReadEnjoyBack.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

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
    public ServerResponse<User> login(String username, String password, HttpSession session){
        ServerResponse<User> response = iUserService.login(username,password);
        if (response.isSuccess()){
            User user = response.getData();
            if (user.getRole() == Const.Role.ROLE_ADMIN){
                // 权限为管理员
                session.setAttribute(Const.CURRENT_USER,user);
                return response;
            }else{
                return ServerResponse.createByErrorMessage("不是管理员，无法登录");
            }
        }
        return response;
    }
}

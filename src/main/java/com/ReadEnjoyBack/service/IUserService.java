package com.ReadEnjoyBack.service;

import com.ReadEnjoyBack.common.ServerResponse;
import com.ReadEnjoyBack.pojo.Comments;
import com.ReadEnjoyBack.pojo.User;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @Author:HB
 * @Description:
 * @Createdata:Created in  15:43  2018/5/17.
 */
public interface IUserService {
 /*------------------------------------- 前台 ---------------------------------------*/
   /*用户登录*/
    ServerResponse<User> login(String email, String password);
    /*用户注册*/
    ServerResponse<String> register(User user);
    /*用户名和email的实时检验*/
    ServerResponse<String> checkValid(String str,String type);
    /*得到用户的密保问题*/
    ServerResponse<String> getQuestionByEmail(String email);
    /*根据密保问题获取密保答案*/
    ServerResponse<String> checkAnswerByQuestion(String email,String question,String answer);
    /*根据用户返回的forgetToken修改密码（忘记密码渠道）*/
    ServerResponse<String> resetPasswordByForgetToken(String email,String passwordNew,String forgetToken);
    /*修改用户密码（用户登录之后）*/
    ServerResponse<String> resetPassword(String passwordOld,String passwordNew,User user);
    /*登录状态下修改用户信息*/
    ServerResponse<User> updateInformation(User user);
    /*获取登录用户信息*/
    ServerResponse<User> getInformation(Integer userId);
    /*邮箱验证*/
    ServerResponse<String> sendEmailToValidate(String email,int code);
    /*Token验证*/
    ServerResponse<String> sendTokenToValidate(String email,String Token);
    /*根据邮箱得到用户状态*/
    ServerResponse getUserStatus(String email);
   /*------------------------------------- 后台 ---------------------------------------*/
   /*检验是否有管理员权限*/
    ServerResponse<String> checkAdminRole(User user);
   /*得到用户列表*/
    ServerResponse<PageInfo> getUserList(int pageNum, int pageSize);
    /*增加用户*/
    ServerResponse<String> addUser(User user);
    /*删除用户*/
    ServerResponse<String> deleteUser(Integer userId);
    /*修改用户状态*/
    ServerResponse<String> modifyUserStatus(Integer userId);
}

package com.ReadEnjoyBack.dao;

import com.ReadEnjoyBack.pojo.User;
import com.ReadEnjoyBack.pojo.UserCollection;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /*-----前台------*/
    /*检验用户名是否存在*/
    int checkUsername(String username);

    /*检验email是否存在*/
    int checkEmail(@Param("email") String email);

    /*检验用户邮箱和密码 返回用户信息*/   //书写mybatis的时候的email对应于param里面的值
    User selectLogin(@Param("email") String email, @Param("password") String password);

    /*得到用户对应的密保问题*/
    String getQuestionByEmail(String email);

    /*检查用户密保答案*/
    int checkAnswer(@Param("email") String email, @Param("question") String question, @Param("answer") String answer);

    /*修改用户密码(忘记密码通道)*/
    int updatePasswordByEmail(@Param("email") String email, @Param("passwordNew") String passwordNew);

    /*对用户旧密码进行检查*/
    int  checkUserOlaPassword(@Param("passwordOld") String passwordOld,@Param("userId") Integer userId);

    /*根据用户ID进行邮箱检查*/
    int checkUsernameByUserId(@Param("userName") String username , @Param("userId") Integer userId );

    /*用户头像更新*/
    int updateUserHeadPicByUserName(@Param("uploadFileName") String uploadFileName, @Param("userName") String userName);

    /*-----后台------*/
    /*得到用户列表*/
    List<User> selectUserList();
    /*检查用户状态*/
    Integer selectUserStatus(@Param(value = "userId") Integer userId);
}
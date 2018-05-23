package com.ReadEnjoyBack.common;

/**
 * @Author:HB
 * @Description: 常量类
 * @Createdata:Created in  21:09  2018/5/7.
 */
public class Const {
    public  static final String CURRENT_USER = "current_user"; //当前用户

    public static final String EMAIL = "email";
    public static final String USERNAME = "username";

    /*定义一个用户权限类*/
    public interface Role{
        int ROLE_CUSTOMER = 0;  //普通用户
        int ROLE_ADMIN = 1; //管理员
    }
}

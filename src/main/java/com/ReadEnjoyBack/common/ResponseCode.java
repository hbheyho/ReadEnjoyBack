package com.ReadEnjoyBack.common;

/**
 * @Author:HB
 * @Description:  声明一个枚举类
 * @Createdata:Created in  19:38  2018/5/7.
 */
public enum  ResponseCode {
    SUCCESS(0,"SUCCESS"),
    ERROR(1,"ERROR"),
    NEED_LOGIN(10,"NEED_LOGIN"),  //需要登录
    ILLEGAL_ARGUMENT(2,"ILLEGAL_ARGUMENT");  //参数错误

    private final int code;
    private final String desc;

    ResponseCode(int code, String desc){
        this.code = code;
        this.desc = desc;
    }
    public int getCode() {
        return code;
    }
    public String getDesc() {
        return desc;
    }
}

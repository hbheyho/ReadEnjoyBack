package com.ReadEnjoyBack.common;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.io.Serializable;

/**
 * @Author:HB
 * @Description:  通用的响应服务类 响应中封装的数据对象为泛型
 * @Createdata:Created in  15:45  2018/5/17.
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)   //此注解为 当只返回status，msg和data为null时，json序列化忽略它
public class ServerResponse<T> implements Serializable {
    private int status;
    private String msg;
    private T data;  //返回数据为泛型 （返回的值可以指定为任何类型）

   /*定制为不同的构造器（私有构造方法 不对外开放） 为不同的响应类型匹配*/
    private ServerResponse(int status) {
        this.status = status;
    }
    private ServerResponse(int status,T data){
        this.status = status;
        this.data = data;
    }
    private ServerResponse(int status,String msg,T data){
        this.status = status;
        this.msg = msg;
        this.data = data;
    }
    private ServerResponse(int status,String msg){
        this.status = status;
        this.msg = msg;
    }

    /*确认响应是否正确  this.status == 0 即为成功的响应*/
    @JsonIgnore   //使之不会再json序列化的结果之中
    public boolean isSuccess(){
        return this.status == ResponseCode.SUCCESS.getCode();  //判断状态是否为0
    }

    /*getter方法*/
    public int getStatus() {
        return status;
    }
    public String getMsg() {
        return msg;
    }
    public T getData() {
        return data;
    }

    /*----------成功------------*/
    /*使用静态方法对外暴露 成功（status = 0）则创建一个只有状态码的对象*/
    public static <T> ServerResponse<T> createBySuccess(){
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode());
    }
    /*成功（status = 0）则创建一个有状态码和msg的对象*/
    public static <T> ServerResponse<T> createBySuccessMessage(String msg){
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),msg);
    }
    /*成功（status = 0）则创建一个有状态码和data的对象*/
    public static <T> ServerResponse<T> createBySuccesse (T data){
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),data);
    }
    /*成功（status = 0）则创建一个有状态码,data和msg的对象*/
    public static <T> ServerResponse<T> createBySuccesse (String msg,T data){
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),msg,data);
    }

    /*----------失败------------*/
    /*使用静态方法对外暴露 成功（status = 1）则创建一个只有状态码的对象*/
    public static <T> ServerResponse<T> createByError(){
        return new ServerResponse<T>(ResponseCode.ERROR.getCode(),ResponseCode.ERROR.getDesc());
    }
    /*成功（status = 1）则创建一个有状态码和Errormsg的对象*/
    public  static  <T> ServerResponse<T> createByErrorMessage(String errorMessage){
        return new ServerResponse<T>(ResponseCode.ERROR.getCode(),errorMessage);
    }
    public  static  <T> ServerResponse<T> createByErrorCodeMessage(int errorCode,String errorMessage){
        return new ServerResponse<T>(errorCode,errorMessage);
    }

}

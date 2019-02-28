package com.ReadEnjoyBack.util;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
/**
 * @Author:HB
 * @Description:
 * @Createdata:Created in  12:54  2019/1/22.
 */
public class CookieUtils {
    /*
     * @Author:HB
     * @Description: 添加一个新Cookie
     * @Data:12:54 2019/1/22
     * @param response cookie
     returns:
    */
    public static void addCookie(HttpServletResponse response, Cookie cookie) {
        if (cookie != null)
            response.addCookie(cookie);
    }
    /*
     * @Author:HB
     * @Description: 添加一个新Cookie
     * @Data:12:56 2019/1/22
     * @param response  cookieName-cookie名 cookieValue-cookie值
     *  domain-cookie所属的子域 httpOnly-是否将cookie设置成HttpOnly(不允许js操作)
     *  maxAge-设置cookie的最大生存期  path-设置cookie路径(可以访问此cookie的页面路径) secure-是否只允许HTTPS访问
     returns:
    */
    public static void addCookie(HttpServletResponse response, String cookieName, String cookieValue, String domain,
                                 boolean httpOnly, int maxAge, String path, boolean secure) {
        if (cookieName != null && !cookieName.equals("")) {
            if (cookieValue == null)
                cookieValue = "";

            Cookie newCookie = new Cookie(cookieName, cookieValue);
            if (domain != null)
                newCookie.setDomain(domain);

            newCookie.setHttpOnly(httpOnly);

            if (maxAge > 0)
                newCookie.setMaxAge(maxAge);

            if (path == null)
                newCookie.setPath("/");
            else
                newCookie.setPath(path);

            newCookie.setSecure(secure);
            addCookie(response, newCookie);
        }
    }
    /*
     * @Author:HB
     * @Description: 添加一个新Cookie
     * @Data:13:09 2019/1/22
     * @param cookieName response cookieValue domain
     returns:
    */
    public static void addCookie(HttpServletResponse response, String cookieName, String cookieValue, String domain) {
        addCookie(response, cookieName, cookieValue, domain, true, 60*60*24*7 , "/", false);
    }

    /*
     * @Author:HB
     * @Description: 根据Cookie名获取对应的Cookie
     * @Data:13:02 2019/1/22
     * @param request cookieName
     returns:
    */
    public static Cookie getCookie(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();

        if (cookies == null || cookieName == null || cookieName.equals(""))
            return null;

        for (Cookie c : cookies) {
            if (c.getName().equals(cookieName))
                return (Cookie) c;
        }
        return null;
    }

   /*
    * @Author:HB
    * @Description: 根据Cookie名获取对应的Cookie值
    * @Data:13:03 2019/1/22
    * @param: request cookieName
    returns:
   */
    public static String getCookieValue(HttpServletRequest request, String cookieName) {
        Cookie cookie = getCookie(request, cookieName);
        if (cookie == null)
            return null;
        else
            return cookie.getValue();
    }

    /*
     * @Author:HB
     * @Description: 删除指定Cookie
     * @Data:13:04 2019/1/22
     * @param null
     returns:
    */
    public static void delCookie(HttpServletResponse response, Cookie cookie) {
        if (cookie != null) {
            cookie.setPath("/");
            cookie.setMaxAge(0);
            cookie.setValue(null);

            response.addCookie(cookie);
        }
    }
    /*
     * @Author:HB
     * @Description: 根据cookie名删除指定的cookie
     * @Data:13:05 2019/1/22
     * @param request response cookieName
     returns:
    */
    public static void delCookie(HttpServletRequest request, HttpServletResponse response, String cookieName) {
        Cookie c = getCookie(request, cookieName);
        if (c != null && c.getName().equals(cookieName)) {
            delCookie(response, c);
        }
    }

    /*
     * @Author:HB
     * @Description: 根据cookie名修改指定的cookie
     * @Data:13:06 2019/1/22
     * @param request response cookieName cookieValue domain
     returns:
    */
    public static void editCookie(HttpServletRequest request, HttpServletResponse response, String cookieName,
                                  String cookieValue,String domain) {
        Cookie c = getCookie(request, cookieName);
        if (c != null && cookieName != null && !cookieName.equals("") && c.getName().equals(cookieName)) {
            addCookie(response, cookieName, cookieValue, domain);
        }
    }
}

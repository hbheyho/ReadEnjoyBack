package com.ReadEnjoyBack.interceptor;

import com.ReadEnjoyBack.common.Const;
import com.ReadEnjoyBack.common.ServerResponse;
import com.ReadEnjoyBack.pojo.User;
import com.ReadEnjoyBack.service.IUserService;
import com.ReadEnjoyBack.util.CookieUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author:HB
 * @Description:  定义一个登陆拦截器 用户检测是否进行自动登录
 * @Createdata:Created in  12:38  2019/1/22.
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private IUserService iUserService;
    // 进行登录之前的cookie检测
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        // 判断当前用户是否登录(是否有session)
        User user = (User)httpServletRequest.getSession().getAttribute(Const.CURRENT_USER);
        // 用户暂无登录（检验其中是有cookie)
        if (user == null){
            String userInfo = CookieUtils.getCookieValue(httpServletRequest,"remember_me");
           // 进行用户登录
            if (StringUtils.isNotBlank(userInfo)){
                String emailApsw[] = userInfo.split("-");
                ServerResponse<User> responseInfo = iUserService.login(emailApsw[0],emailApsw[1]);
                if (responseInfo.isSuccess()){
                    // 登录成功保存user session
                    httpServletRequest.getSession().setAttribute(Const.CURRENT_USER,responseInfo.getData());
                }
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}

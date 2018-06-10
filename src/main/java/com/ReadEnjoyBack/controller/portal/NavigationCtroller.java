package com.ReadEnjoyBack.controller.portal;

import com.ReadEnjoyBack.common.ServerResponse;
import com.ReadEnjoyBack.pojo.Navigation;
import com.ReadEnjoyBack.service.INavigationService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Author:HB
 * @Description: 导航信息获取
 * @Createdata:Created in  15:43  2018/5/30.
 */
@Controller
@RequestMapping("/navigation/")
public class NavigationCtroller {
    // 注入navigationMapper
    @Autowired
    private INavigationService iNavigationService;
    /*
     * @Author:HB
     * @Description: 在前台对导航信息进行加载
     * @Data:21:51 2018/5/31
     * @param null
     returns:
    */
    @RequestMapping(value = "get_deep_navigation.do")
    @ResponseBody
    public ServerResponse<List<Navigation>> getNavigation(@RequestParam(value = "navId",defaultValue = "0") Integer navId,
                                                          HttpServletRequest request, HttpServletResponse response){
        // 解决跨域
        response.addHeader("Access-Control-Allow-Origin",request.getHeader("Origin"));
        return iNavigationService.getDeepChildrenParallelNavigation(navId);
    }
}

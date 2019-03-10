package com.ReadEnjoyBack.controller.backend;

import com.ReadEnjoyBack.common.Const;
import com.ReadEnjoyBack.common.ServerResponse;
import com.ReadEnjoyBack.pojo.User;
import com.ReadEnjoyBack.service.IReportService;
import com.ReadEnjoyBack.service.IUserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Author:HB
 * @Description: 用户举报管理
 * @Createdata:Created in  10:33  2019/3/8.
 */
@Controller
@RequestMapping("/manage/report")
public class ReportManageController {
    @Autowired
    private IUserService iUserService;
    @Autowired
    private IReportService iReportService;
    /*
     * @Author:HB
     * @Description: 得到举报列表
     * @Data:10:36 2019/3/8
     * @param null
     returns:
    */
    @RequestMapping(value = "get_report_list.do")
    @ResponseBody
    ServerResponse<PageInfo> getReportList(HttpSession session, HttpServletRequest request,
                                           HttpServletResponse response,
                                           @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                                           @RequestParam(value = "pageSize", defaultValue = "10") int pageSize ){
        // 解决跨域
        response.addHeader("Access-Control-Allow-Origin",request.getHeader("Origin"));
        // 跨域的session 保证同一性
        response.addHeader("Access-Control-Allow-Credentials","true");
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            //return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，请进行管理员登录");
            return ServerResponse.createByErrorMessage("用户未登录，请进行管理员登录");
        }
        //判断权限
        if (iUserService.checkAdminRole(user).isSuccess()){
            return iReportService.getReportList(pageNum,pageSize);
        }else {
            return ServerResponse.createByErrorMessage("用户暂无管理员权限");
        }
    }

    /*
    * @Author:HB
    * @Description: 举报处理反馈
    * @Data:10:36 2019/3/8
    * @param null
    returns:
   */
    @RequestMapping(value = "deal_report.do")
    @ResponseBody
    ServerResponse dealReport(HttpSession session, HttpServletRequest request,
                                           HttpServletResponse response,
                                           @RequestParam(value = "status",defaultValue = "0") int status,
                                           @RequestParam(value = "reportId",defaultValue = "-1") int reportId){
        // 解决跨域
        response.addHeader("Access-Control-Allow-Origin",request.getHeader("Origin"));
        // 跨域的session 保证同一性
        response.addHeader("Access-Control-Allow-Credentials","true");
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            //return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，请进行管理员登录");
            return ServerResponse.createByErrorMessage("用户未登录，请进行管理员登录");
        }
        //判断权限
        if (iUserService.checkAdminRole(user).isSuccess()){
            return iReportService.dealReport(status,reportId);
        }else {
            return ServerResponse.createByErrorMessage("用户暂无管理员权限");
        }
    }
    /*
     * @Author:HB
     * @Description: 删除用户举报信息
     * @Data:10:36 2019/3/8
     * @param reportId
     returns:
    */
    @RequestMapping(value = "delete_report.do")
    @ResponseBody
    ServerResponse<String> deleteReport(HttpSession session, HttpServletRequest request,
                                         HttpServletResponse response,@RequestParam(value = "reportId",defaultValue = "-1") int reportId){
        // 解决跨域
        response.addHeader("Access-Control-Allow-Origin",request.getHeader("Origin"));
        // 跨域的session 保证同一性
        response.addHeader("Access-Control-Allow-Credentials","true");
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            //return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，请进行管理员登录");
            return ServerResponse.createByErrorMessage("用户未登录，请进行管理员登录");
        }
        //判断权限
        if (iUserService.checkAdminRole(user).isSuccess()){
            return iReportService.deleteReport(reportId);
        }else {
            return ServerResponse.createByErrorMessage("用户暂无管理员权限");
        }
    }
}

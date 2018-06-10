package com.ReadEnjoyBack.controller.backend;

import com.ReadEnjoyBack.common.Const;
import com.ReadEnjoyBack.common.ResponseCode;
import com.ReadEnjoyBack.common.ServerResponse;
import com.ReadEnjoyBack.pojo.User;
import com.ReadEnjoyBack.service.ICategoryService;
import com.ReadEnjoyBack.service.IUserService;
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
 * @Description:
 * @Createdata:Created in  9:32  2018/5/29.
 */
@Controller()
@RequestMapping("/manage/category")
public class CategoryManageController {
    // 注入INavigation Service 和 IUserservice
    @Autowired
    private ICategoryService iCategoryService;
    @Autowired
    private IUserService iUserService;
    /*
     * @Author:HB
     * @Description: 增加分类
     * @Data:10:50 2018/5/24
     * @param parentId
    * @param categoryName
    * @param session
     returns:com.ReadEnjoyBack.common.ServerResponse<java.lang.String>
     */
    @RequestMapping(value = "add_category.do")
    @ResponseBody
    public ServerResponse<String> addNavigation(HttpSession session, String categoryName,
                                                @RequestParam(value = "parentId" , defaultValue = "0") Integer parentId ,
                                                HttpServletRequest request, HttpServletResponse response){
        // 解决跨域
        response.addHeader("Access-Control-Allow-Origin",request.getHeader("Origin"));
        // 跨域的session 保证同一性
        response.addHeader("Access-Control-Allow-Credentials","true");
        // 判断用户是否登录
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户暂未登录，请登录");
        }
        // 检验是否为管理员
        if (iUserService.checkAdminRole(user).isSuccess()){
            // 是管理员
            // 进行相应逻辑操作
            return  iCategoryService.addCategory(categoryName,parentId);
        }else{
            return ServerResponse.createByErrorMessage("不是管理员，无权进行相应操作！");
        }
    }
     /*
     * @Author:HB
     * @Description:修改分类名字
     * @Data:14:17 2018/5/24
     * @param session
    * @param categoryId
    * @param categoryName
    returns:com.ReadEnjoyBack.common.ServerResponse<java.lang.String>
     */
    @RequestMapping(value = "set_categoryName.do")
    @ResponseBody
    public ServerResponse<String> setCategoryName(HttpSession session,Integer categoryId,String categoryName,
                                                  HttpServletResponse response,HttpServletRequest request){
        // 解决跨域
        response.addHeader("Access-Control-Allow-Origin",request.getHeader("Origin"));
        // 跨域的session 保证同一性
        response.addHeader("Access-Control-Allow-Credentials","true");
        // 判断用户是否登录
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户暂未登录，请登录");
        }
        // 检验是否为管理员
        if (iUserService.checkAdminRole(user).isSuccess()){
            // 是管理员
            // 进行相应逻辑操作
            return  iCategoryService.updateCategoryname(categoryId,categoryName);
        }else{
            return ServerResponse.createByErrorMessage("不是管理员，无权进行相应操作！");
        }
    }
    /*
      * @Author:HB
      * @Description: 获取当前分类的子节点(平级)
      * @Data:14:34 2018/5/29
      * @param session
     * @param categoryId
      returns:com.ReadEnjoyBack.common.ServerResponse<java.lang.String>
      */
    @RequestMapping(value = "get_category.do")
    @ResponseBody
    public ServerResponse getCategory(HttpSession session,@RequestParam(value = "categoryId",defaultValue = "0") Integer categoryId,
                                      HttpServletRequest request ,HttpServletResponse response){
        // 解决跨域
        response.addHeader("Access-Control-Allow-Origin",request.getHeader("Origin"));
        // 跨域的session 保证同一性
        response.addHeader("Access-Control-Allow-Credentials","true");
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户暂未登录，请登录");
        }
        if (iUserService.checkAdminRole(user).isSuccess()){
            // 查询子节点的categoryId 并且不递归 保持平级
            return iCategoryService.getChildrenParallelCategory(categoryId);
        }else {
            return ServerResponse.createByErrorMessage("不是管理员，无权进行相应操作！");
        }
    }
    /*
    * @Author:HB
    * @Description: 获取当前分类的子节点(深度查询)
    * @Data:15:13 2018/5/29
    * @param session
    * @param categoryId
    returns:com.ReadEnjoyBack.common.ServerResponse
    */
    @RequestMapping(value = "get_deep_category.do")
    @ResponseBody
    public ServerResponse getDeepCategory(HttpSession session,@RequestParam(value = "categoryId",defaultValue = "0") Integer categoryId,
                                          HttpServletResponse response ,HttpServletRequest request){
        // 解决跨域
        response.addHeader("Access-Control-Allow-Origin",request.getHeader("Origin"));
        // 跨域的session 保证同一性
        response.addHeader("Access-Control-Allow-Credentials","true");
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户暂未登录，请登录");
        }
        if (iUserService.checkAdminRole(user).isSuccess()){
            // 查询子节点的categoryId 递归查询
            return iCategoryService.getDeepChildrenParallelCategory(categoryId);
        }else {
            return ServerResponse.createByErrorMessage("不是管理员，无权进行相应操作！");
        }
    }

}

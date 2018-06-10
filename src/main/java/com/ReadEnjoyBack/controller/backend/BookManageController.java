package com.ReadEnjoyBack.controller.backend;

import com.ReadEnjoyBack.common.Const;
import com.ReadEnjoyBack.common.ResponseCode;
import com.ReadEnjoyBack.common.ServerResponse;
import com.ReadEnjoyBack.pojo.Book;
import com.ReadEnjoyBack.pojo.User;
import com.ReadEnjoyBack.service.IBookService;
import com.ReadEnjoyBack.service.IUserService;
import com.ReadEnjoyBack.vo.BookDetailVo;
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
 * @Description: 后台书籍管理controller
 * @Createdata:Created in  8:55  2018/5/29.
 */
@Controller
@RequestMapping("/manage/book")
public class BookManageController {
    // 用户/书籍service注入
    @Autowired
    private IUserService iUserService;
    @Autowired
    private IBookService iBookService;
    
    /*
     * @Author:HB
     * @Description:
     * @Data:10:47 2018/5/29
     * @param null
     returns:
    */
    @RequestMapping(value = "save.do")
    @ResponseBody
    public ServerResponse<String> savaOrUpdateBook(HttpSession session, Book book, HttpServletResponse response,
                                                   HttpServletRequest request){
        // 解决跨域
        response.addHeader("Access-Control-Allow-Origin",request.getHeader("Origin"));
        // 跨域的session 保证同一性
        response.addHeader("Access-Control-Allow-Credentials","true");
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，请进行管理员登录");
        }
        //判断权限
        if (iUserService.checkAdminRole(user).isSuccess()){
            return iBookService.saveOrUpdateBook(book);
        }else {
            return ServerResponse.createByErrorMessage("用户暂无管理员权限");
        }
    }
    /*
     * @Author:HB
     * @Description: 获取书籍详情信息
     * @Data:20:27 2018/5/29
     * @param bookId
     returns: BookDetailVo
    */
    @RequestMapping(value = "detail.do")
    @ResponseBody
    public ServerResponse<BookDetailVo> ManageGetBookDetail(HttpSession session , String bookISBN,HttpServletRequest request,
                                                            HttpServletResponse response){
        // 解决跨域
        response.addHeader("Access-Control-Allow-Origin",request.getHeader("Origin"));
        // 跨域的session 保证同一性
        response.addHeader("Access-Control-Allow-Credentials","true");
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，请进行管理员登录");
        }
        //判断权限
        if (iUserService.checkAdminRole(user).isSuccess()){
            return iBookService.getBookDetail(bookISBN);
        }else {
            return ServerResponse.createByErrorMessage("用户暂无管理员权限");
        }
    }

    /*
     * @Author:HB
     * @Description: 书籍列表开发
     * @Data:9:13 2018/5/30
     * @param pageNum， pageSize
     returns:
    */
    @RequestMapping(value = "list.do")
    @ResponseBody
    public ServerResponse<PageInfo> getList(HttpSession session, @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                                            @RequestParam(value = "pageSize",defaultValue = "10") int pageSize,
                                            HttpServletResponse response,HttpServletRequest request){
        // 解决跨域
        response.addHeader("Access-Control-Allow-Origin",request.getHeader("Origin"));
        // 跨域的session 保证同一性
        response.addHeader("Access-Control-Allow-Credentials","true");
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，请进行管理员登录");
        }
        //判断权限
        if (iUserService.checkAdminRole(user).isSuccess()){
            return iBookService.getBookList(pageNum,pageSize);
        }else {
            return ServerResponse.createByErrorMessage("用户暂无管理员权限");
        }
    }

    /*
    * @Author:HB
    * @Description: 书籍搜索模块
    * @Data:9:13 2018/5/30
    * @param pageNum， pageSize
    returns:
   */
    @RequestMapping(value = "search.do")
    @ResponseBody
    public ServerResponse<PageInfo> bookSearch(HttpSession session, @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                                            @RequestParam(value = "pageSize",defaultValue = "10") int pageSize,String bookName,String categoryName,
                                               String bookWriter,String publishName,HttpServletRequest request,
                                               HttpServletResponse response){
        // 解决跨域
        response.addHeader("Access-Control-Allow-Origin",request.getHeader("Origin"));
        // 跨域的session 保证同一性
        response.addHeader("Access-Control-Allow-Credentials","true");
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，请进行管理员登录");
        }
        //判断权限
        if (iUserService.checkAdminRole(user).isSuccess()){
            return iBookService.searchBook(pageNum,pageSize,bookName,bookWriter,publishName,categoryName);
        }else {
            return ServerResponse.createByErrorMessage("用户暂无管理员权限");
        }
    }


}

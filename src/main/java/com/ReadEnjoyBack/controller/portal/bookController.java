package com.ReadEnjoyBack.controller.portal;

import com.ReadEnjoyBack.common.Const;
import com.ReadEnjoyBack.common.ResponseCode;
import com.ReadEnjoyBack.common.ServerResponse;
import com.ReadEnjoyBack.pojo.User;
import com.ReadEnjoyBack.service.IBookService;
import com.ReadEnjoyBack.service.IFileService;
import com.ReadEnjoyBack.vo.BookDetailVo;
import com.ReadEnjoyBack.vo.BookListVo;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Author:HB
 * @Description：书籍信息获取
 * @Createdata:Created in  19:22  2018/5/30.
 */
@Controller
@RequestMapping("/book/")
public class BookController {
    @Autowired
    private IBookService iBookService;
    @Autowired
    private IFileService iFileService;
     /*
      * @Author:HB
      * @Description: 获取书籍信息 根据下载的书籍次数
      * @Data:20:36 2018/6/1
      * @param null
      returns:
     */
    @RequestMapping(value = "get_book_downNumber.do")
    @ResponseBody
    public ServerResponse<List<BookListVo>> getBookByDownNumber(HttpServletRequest request, HttpServletResponse response){
        // 解决跨域
        response.addHeader("Access-Control-Allow-Origin",request.getHeader("Origin"));
        return iBookService.getBookByDownNumber();
    }
    /*
     * @Author:HB
     * @Description: 前台书籍信息获取（分页获取 获取全部）
     * @Data:19:26 2018/5/30
     * @param pageNum pageSize
     returns:
    */
    @RequestMapping(value = "list.do")
    @ResponseBody
    public ServerResponse<PageInfo> getBookList(@RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                                                @RequestParam(value = "pageSize",defaultValue = "10") int pageSize,
                                                HttpServletResponse response,HttpServletRequest request){
        // 解决跨域
        response.addHeader("Access-Control-Allow-Origin",request.getHeader("Origin"));
        return iBookService.getBookList(pageNum,pageSize);
    }

    /*
    * @Author:HB
    * @Description: 数据搜索 （根据书名/ 书籍作者/ 书籍出版社）
    * @Data:21:34 2018/6/1
    * @param bookName
   * @param bookWriter
   * @param publishName
   * @param categoryName
   returns:com.ReadEnjoyBack.common.ServerResponse<java.util.List<com.ReadEnjoyBack.vo.BookListVo>>
  */
    @RequestMapping(value = "search.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<List<BookListVo>> bookSerachFront(@RequestParam(value = "conditionName") String conditionName,
                                                            HttpServletRequest request,HttpServletResponse response){
        // 解决跨域
        response.addHeader("Access-Control-Allow-Origin",request.getHeader("Origin"));;
       return iBookService.searchBookByBookNameAndBookWriterAndPublish(conditionName);
    }
    /*
     * @Author:HB
     * @Description: 得到书籍详情信息
     * @Data: 2018/6/3
     * @param bookId
     returns: BookDetailIVo
    */
    @RequestMapping(value = "detail.do")
    @ResponseBody
    public ServerResponse<BookDetailVo> getBookDetailInfoFront(String bookISBN,HttpServletRequest request,HttpServletResponse response){
        // 解决跨域
        response.addHeader("Access-Control-Allow-Origin",request.getHeader("Origin"));
        return  iBookService.getBookDetail(bookISBN);
    }

    /*
     * @Author:HB
     * @Description: 用户书籍上传
     * @Data:9:13 2018/5/30
     * @param MultipartFile
     returns:
    */
    @RequestMapping(value = "upload.do")
    @ResponseBody
    public ServerResponse uploadFile(HttpSession session, @RequestParam(value = "upload_file",required = false) MultipartFile file,
                                 HttpServletRequest request,HttpServletResponse response,@RequestParam(value = "bookISBN") String bookISBN){
        // 解决跨域
        response.addHeader("Access-Control-Allow-Origin",request.getHeader("Origin"));
        // 跨域的session 保证同一性
        response.addHeader("Access-Control-Allow-Credentials","true");
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，登录之后在进行操作噢！");
        }
        // 上传文件的路径
         String path =request.getSession().getServletContext().getRealPath("upload");
         return iFileService.upload(file,path,user.getUsername(),bookISBN);
        /*String url = PropertiesUtil.getProperty("ftp.server.http.prefix") + targetFileName;*/
        // url拼接返回
        /*Map fileMap = Maps.newHashMap();
        fileMap.put("uri",targetFileName);
        fileMap.put("url",url);*/
        /*return ServerResponse.createBySuccesse(fileMap);*/
    }
    
    /*
     * @Author:HB
     * @Description: // 分类信息搜索通过categoryId
     * @Data:14:36 2019/1/24
     * @param categoryId pageNum pageSize
     returns:
    */
    @RequestMapping(value = "get_bookList_categoryId.do")
    @ResponseBody
    ServerResponse<PageInfo> getBookListByCategoryId(@RequestParam(value = "categoryId" , defaultValue = "0") int categoryId,HttpServletRequest request,HttpServletResponse response,
                                                     @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                                                     @RequestParam(value = "pageSize",defaultValue = "10") int pageSize){
        System.out.println("111111111" + "|" + categoryId + "|" + pageNum + pageSize);
        // 解决跨域
        response.addHeader("Access-Control-Allow-Origin",request.getHeader("Origin"));
        return iBookService.getBookListByCategoryId(categoryId,pageNum,pageSize);
    }


}

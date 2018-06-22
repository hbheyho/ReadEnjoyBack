package com.ReadEnjoyBack.controller.portal;

import com.ReadEnjoyBack.common.Const;
import com.ReadEnjoyBack.common.ResponseCode;
import com.ReadEnjoyBack.common.ServerResponse;
import com.ReadEnjoyBack.pojo.User;
import com.ReadEnjoyBack.service.IBookVersionService;
import com.ReadEnjoyBack.service.IFileService;
import com.ReadEnjoyBack.vo.BookVersionVO;
import com.ReadEnjoyBack.vo.UserOperationVo;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

/**
 * @Author:HB
 * @Description: 书籍版本controller
 * @Createdata:Created in  22:23  2018/6/8.
 */
@Controller
@RequestMapping("/bookVersion/")
public class BookVersionController {
    @Autowired
    private IBookVersionService iBookVersionService;
    @Autowired
    private IFileService iFileService;
    /*
    * @Author:HB
    * @Description: 存储用户的收藏信息
    * @Data:22:20 2018/6/8
    * @param bookVersionId uploadUserName
    returns:
    */
    @RequestMapping(value = "store_user_collection.do")
    @ResponseBody
    public ServerResponse storeUserCollectionInfo(HttpSession session,HttpServletRequest request, HttpServletResponse response,Integer bookVersionId){
        // 解决跨域
        response.addHeader("Access-Control-Allow-Origin",request.getHeader("Origin"));
        // 跨域的session 保证同一性
        response.addHeader("Access-Control-Allow-Credentials","true");
        User currentUser = (User)session.getAttribute(Const.CURRENT_USER);
        if (currentUser == null){
            return ServerResponse.createByErrorMessage("没登陆怎么收藏呢？");
        }
        // 当前收藏用户的用户名
        String userName = currentUser.getUsername();
        if (bookVersionId == null || userName == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        return iBookVersionService.inserCollectionInfo(bookVersionId,userName);
    }
    /*
    * @Author:HB
    * @Description: 书籍下载
    * @Data:17:16 2018/6/5
    * @param bookName
    returns:
   */
    @RequestMapping(value = "download.do")
    @ResponseBody
    public ResponseEntity<byte[]> downloadFile(HttpSession session, HttpServletRequest request, HttpServletResponse response,
                                               @RequestParam(value = "bookName") String bookName) throws IOException {
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        // 当前下载用户的用户名
        String userName = user.getUsername();
        // 解决跨域
        response.addHeader("Access-Control-Allow-Origin",request.getHeader("Origin"));
        // 得到文件原始名
        String originName = iBookVersionService.getOrignName(bookName);
        // 得到下载版本的版本号
        int versionId = iBookVersionService.getVersionId(bookName);
        if (originName == null){
            originName = bookName;
        }
        System.out.println("下载的文件名值"+originName);
        HttpHeaders headers = new HttpHeaders();
        // 设置通过浏览器下载
        response.addHeader("Content-disposition", "attachment; filename=" + URLEncoder.encode(originName,"UTF-8"));
        // 定义以流的形式下载返回的文件数据
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<byte[]>(iFileService.download(bookName,userName,versionId),headers, HttpStatus.OK);
    }
    /*
     * @Author:HB
     * @Description: 根据书籍的ISBN获得书籍的版本信息
     * @Data:15:08 2018/6/6
     * @param bookISBN
     returns:
    */
    @RequestMapping(value = "get_bookVersion_list.do")
    @ResponseBody
    public ServerResponse<List<BookVersionVO>> getBookVersionList(HttpServletRequest request, HttpServletResponse response, String bookISBN ){
        // 解决跨域
        response.addHeader("Access-Control-Allow-Origin",request.getHeader("Origin"));
        if (bookISBN == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        return iBookVersionService.getBookVersionList(bookISBN);
    }
    /*
   * @Author:HB
   * @Description: 得到当前登录用户的收藏书籍信息
   * @Data:8:25 2018/6/11
   * @param
   returns:
  */
    @RequestMapping(value = "get_user_collection.do")
    @ResponseBody
    ServerResponse<PageInfo> getUserCollectInfo(HttpServletRequest request, HttpServletResponse response, HttpSession session,
                                                @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                                                @RequestParam(value = "pageSize",defaultValue = "10") int pageSize){
        // 解决跨域
        response.addHeader("Access-Control-Allow-Origin",request.getHeader("Origin"));
        // 跨域的session 保证同一性
        response.addHeader("Access-Control-Allow-Credentials","true");
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，登录之后在进行操作噢！");
        }
        // 得到当前用户名
        String userName = user.getUsername();
        return iBookVersionService.getUserCollection(userName,pageNum,pageSize);
    }
    /*
  * @Author:HB
  * @Description: 得到当前登录用户的上传书籍信息
  * @Data:8:25 2018/6/11
  * @param
  returns:
 */
    @RequestMapping(value = "get_user_upload.do")
    @ResponseBody
    ServerResponse<PageInfo> getUserUploadInfo(HttpServletRequest request, HttpServletResponse response, HttpSession session,
                                                            @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                                                            @RequestParam(value = "pageSize",defaultValue = "10") int pageSize){
        // 解决跨域
        response.addHeader("Access-Control-Allow-Origin",request.getHeader("Origin"));
        // 跨域的session 保证同一性
        response.addHeader("Access-Control-Allow-Credentials","true");
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，登录之后在进行操作噢！");
        }
        // 得到当前用户名
        String userName = user.getUsername();
        return iBookVersionService.getUserUploadInfo(userName,pageNum,pageSize);
    }
    /*
    * @Author:HB
    * @Description: 得到当前登录用户的上传书籍信息
    * @Data:8:25 2018/6/11
    * @param
    returns:
    */
    @RequestMapping(value = "get_user_download.do")
    @ResponseBody
    ServerResponse<PageInfo> getUserDownloadInfo(HttpServletRequest request, HttpServletResponse response, HttpSession session,
                                                              @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                                                              @RequestParam(value = "pageSize",defaultValue = "10") int pageSize){
        // 解决跨域
        response.addHeader("Access-Control-Allow-Origin",request.getHeader("Origin"));
        // 跨域的session 保证同一性
        response.addHeader("Access-Control-Allow-Credentials","true");
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，登录之后在进行操作噢！");
        }
        // 得到当前用户名
        String userName = user.getUsername();
        return iBookVersionService.getUserDownloadInfo(userName,pageNum,pageSize);
    }

}

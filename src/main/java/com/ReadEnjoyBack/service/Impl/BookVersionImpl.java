package com.ReadEnjoyBack.service.Impl;

import com.ReadEnjoyBack.common.ResponseCode;
import com.ReadEnjoyBack.common.ServerResponse;
import com.ReadEnjoyBack.dao.*;
import com.ReadEnjoyBack.pojo.*;
import com.ReadEnjoyBack.service.IBookVersionService;
import com.ReadEnjoyBack.util.DateTimeUtil;
import com.ReadEnjoyBack.util.PropertiesUtil;
import com.ReadEnjoyBack.vo.BookVersionVO;
import com.ReadEnjoyBack.vo.UserOperationVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:HB
 * @Description: bookVersionService实现
 * @Createdata:Created in  12:05  2018/6/6.
 */
@Service("iBookVersionService")
public class BookVersionImpl  implements IBookVersionService{
    private Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);
    @Autowired
    private BookVersionMapper bookVersionMapper;
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private UserCollectionMapper userCollectionMapper;
    @Autowired
    private CommentsMapper commentsMapper;
    @Autowired
    private BookReportMapper bookReportMapper;
    @Autowired
    private UserMapper userMapper;
    /*
   * @Author:HB
   * @Description: 通过uploadName 得到 originName
   * @Data: 2018/6/6
   * @param bookName
   returns:
   */
    @Override
    public String getOrignName(String bookName) {
        return bookVersionMapper.getOrignName(bookName);
    }
    /*
     * @Author:HB
     * @Description:  通过uploadName 得到 bookversionId
     * @Data: 2018/6/6
     * @param bookName
     returns:
     */
    @Override
    public int getVersionId(String bookName) {
        return bookVersionMapper.getBookVersionId(bookName);
    }

    /*
     * @Author:HB
     * @Description: 根据书籍的ISBN获得书籍的版本信息
     * @Data:15:13 2018/6/6
     * @param bookISBN
     returns:
    */
    @Override
    public ServerResponse<List<BookVersionVO>> getBookVersionList(String bookISBN) {
        if (bookISBN == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        List<BookVersion> bookVersionList = bookVersionMapper.selectByBookISBN(bookISBN);
        List<BookVersionVO> bookVersionVOList = new ArrayList<BookVersionVO>();
        for (BookVersion bookVersion : bookVersionList){
            BookVersionVO bookVersionVO = assembleBookVersionVo(bookVersion);
            bookVersionVOList.add(bookVersionVO);
        }
        return ServerResponse.createBySuccesse(bookVersionVOList);
    }
      /*
       * @Author:HB
       * @Description: 存储用户的收藏信息
       * @Data:22:20 2018/6/8
       * @param bookVersionId uploadUserName
       returns:
       */
    @Override
    public ServerResponse inserCollectionInfo(Integer bookVersionId, String username) {
        // 判断当前版本是否收藏
        int result = userCollectionMapper.isCollection(username,bookVersionId);
        if (result == 0){
            UserCollection userCollection = new UserCollection();
            userCollection.setBookVersionId(bookVersionId);
            userCollection.setUserName(username);
            int resultCount = userCollectionMapper.insert(userCollection);
            if (resultCount == 0){
                return ServerResponse.createByErrorMessage("收藏失败");
            }
            // 更新书籍版本的收藏数量
            resultCount = bookVersionMapper.updateCollectNumber(bookVersionId);
            if (resultCount == 0){
                logger.error("更新书籍版本的数量失败");
            }
            return ServerResponse.createBySuccessMessage("收藏成功");
        }else {
            return ServerResponse.createBySuccessMessage("你已收藏该版本!");
        }
    }
    /*
     * @Author:HB
     * @Description: 得到当前登录用户的收藏书籍信息 --- 1 不分类
     * @Data:12:20 2019/1/26
     * @param userName
     returns:
    */
    @Override
    public ServerResponse getUserCollectionNotPage(String userName) {
        List<BookVersion> userCollectionList = bookVersionMapper.selectUserCollection(userName);
        return ServerResponse.createBySuccesse(userCollectionList);
    }

    /*
     * @Author:HB
     * @Description: 得到当前登录用户的收藏书籍信息 --- 2 分类
     * @Data:8:25 2018/6/11
     * @param userName
     returns:
     */
    @Override
    public ServerResponse<PageInfo> getUserCollection(String userName,int pageNum,int pageSize) {
        // 开始分页
        PageHelper.startPage(pageNum,pageSize);
        // sql逻辑
        List<BookVersion> userCollectionList = bookVersionMapper.selectUserCollection(userName);
        List<UserOperationVo> userOperationVoList = new ArrayList<UserOperationVo>();
        for (BookVersion bookVersionItem: userCollectionList){
            String bookISBN = bookVersionItem.getBookIsbn();
            Book book = bookMapper.getBookDetail(bookISBN);
            String bookName = book.getBookName();
            UserOperationVo userOperationVo = assembleUserCollectionVo(bookVersionItem,bookName);
            userOperationVoList.add(userOperationVo);
        }
        PageInfo pageInfo = new PageInfo(userCollectionList);
        pageInfo.setList(userOperationVoList);
        return ServerResponse.createBySuccesse(pageInfo);
    }

    /*
     * @Author:HB
     * @Description: 得到当前登录用户的上传书籍信息
     * @Data:8:25 2018/6/11
     * @param userName
     returns:
     */
    @Override
    public ServerResponse<PageInfo> getUserUploadInfo(String username,int pageNum,int pageSize) {
        // 开始分页
        PageHelper.startPage(pageNum,pageSize);
        List<BookVersion> userUploadList = bookVersionMapper.selectUserUpload(username);
        List<UserOperationVo> userOperationVoList = new ArrayList<UserOperationVo>();
        for (BookVersion bookVersionItem: userUploadList){
            String bookISBN = bookVersionItem.getBookIsbn();
            Book book = bookMapper.getBookDetail(bookISBN);
            String bookName = book.getBookName();
            UserOperationVo userOperationVo = assembleUserUploadVo(bookVersionItem,bookName);
            userOperationVoList.add(userOperationVo);
        }
        PageInfo pageInfo = new PageInfo(userUploadList);
        pageInfo.setList(userOperationVoList);
        return ServerResponse.createBySuccesse(pageInfo);
    }
    /*
     * @Author:HB
     * @Description:得到当前登录用户的下载书籍信息
     * @Data:16:07 2018/6/12
     * @param null
     returns:
    */
    @Override
    public ServerResponse<PageInfo> getUserDownloadInfo(String username,int pageNum,int pageSize) {
        // 开始分页
        PageHelper.startPage(pageNum,pageSize);
        List<BookVersion> userDownloadList = bookVersionMapper.selectUserDown(username);
        List<UserOperationVo> userOperationVoList = new ArrayList<UserOperationVo>();
        for (BookVersion bookVersionItem: userDownloadList){
            String bookISBN = bookVersionItem.getBookIsbn();
            Book book = bookMapper.getBookDetail(bookISBN);
            String bookName = book.getBookName();
            UserOperationVo userOperationVo = assembleUserDownloadVo(bookVersionItem,bookName);
            userOperationVoList.add(userOperationVo);
        }
        PageInfo pageInfo = new PageInfo(userDownloadList);
        pageInfo.setList(userOperationVoList);
        return ServerResponse.createBySuccesse(pageInfo);
    }


    /*---------------------------------------VO业务类-------------------------------------------*/
    // 书籍版本业务类初始化
    private BookVersionVO assembleBookVersionVo(BookVersion bookVersion){
        // 根据versionID查询相应的评论信息
        int versionID = bookVersion.getId();
        List<Comments> commentsList = commentsMapper.getCommentInfo(versionID);
        // 评论时间格式化
        for (Comments comments: commentsList){
            comments.setCommentTimeS(DateTimeUtil.dateToStr(comments.getCommentTime()));
            comments.setImageHost(PropertiesUtil.getProperty("userImage.server","userImage.readenjoy.com"));
            comments.setUsername(comments.getUserList().get(0).getUsername());
            comments.setHeadpic(comments.getUserList().get(0).getHeadpic());
        }
        BookVersionVO bookVresionVo = new BookVersionVO();
        // 当前版本书籍的评论信息
        bookVresionVo.setVersionComments(commentsList);
        bookVresionVo.setCommentNumber(commentsList.size());

        bookVresionVo.setId(bookVersion.getId());
        bookVresionVo.setBookSize(bookVersion.getBookSize());
        bookVresionVo.setBookOriginname(bookVersion.getBookOriginname());
        bookVresionVo.setBookUploadname(bookVersion.getBookUploadname());
        bookVresionVo.setCollectNumber(bookVersion.getCollectNumber());
        bookVresionVo.setDownNumber(bookVersion.getDownNumber());
        bookVresionVo.setUpdateTime(DateTimeUtil.dateToStr(bookVersion.getUpdateTime()));
        bookVresionVo.setUploadTime(DateTimeUtil.dateToStr(bookVersion.getUpdateTime()));
        bookVresionVo.setUploadUser(bookVersion.getUploadUser());

        // 根据用户名得到相应用户头像
        String uploadUserHeadPic = userMapper.getUserHeadPicByName(bookVersion.getUploadUser());
        bookVresionVo.setUploadUserHeadPic(uploadUserHeadPic);

        /*-----版本生成的swf文件的名字以及所存的服务器地址（mobi epub格式不生成）*/
        String extentName = bookVersion.getBookOriginname().
                substring(bookVersion.getBookOriginname().lastIndexOf(".") + 1);
        if (!StringUtils.equals(extentName,"mobi") && !StringUtils.equals(extentName,"epub")){
            bookVresionVo.setSwfHost(PropertiesUtil.getProperty("swf.server"));
            String swfName = bookVersion.getBookUploadname()
                    .substring(0,bookVersion.getBookUploadname().lastIndexOf(".")) + ".swf";
            bookVresionVo.setSwfName(swfName);
        }
        return bookVresionVo;
    }
    // 用户收藏业务类初始化
      private UserOperationVo assembleUserCollectionVo(BookVersion bookVersion, String bookName){
          UserOperationVo userOperationVo = new UserOperationVo();
          userOperationVo.setId(bookVersion.getUserCollectionList().get(0).getId());
          userOperationVo.setBookName(bookName);
          userOperationVo.setBookIsbn(bookVersion.getBookIsbn());
          userOperationVo.setBookSize(bookVersion.getBookSize());
          userOperationVo.setBookOriginname(bookVersion.getBookOriginname());
          userOperationVo.setOperationTime(DateTimeUtil.dateToStr(bookVersion.getUserCollectionList().get(0).getCollectTime()));
         return userOperationVo;
      }
    // 用户上传业务类初始化
    private UserOperationVo assembleUserUploadVo(BookVersion bookVersion, String bookName){
        UserOperationVo userOperationVo = new UserOperationVo();
        userOperationVo.setId(bookVersion.getUserUploadList().get(0).getId());
        userOperationVo.setBookName(bookName);
        userOperationVo.setBookIsbn(bookVersion.getBookIsbn());
        userOperationVo.setBookSize(bookVersion.getBookSize());
        userOperationVo.setBookOriginname(bookVersion.getBookOriginname());
        userOperationVo.setOperationTime(DateTimeUtil.dateToStr(bookVersion.getUploadTime()));
        return userOperationVo;
    }
    // 用户上传业务类初始化
    private UserOperationVo assembleUserDownloadVo(BookVersion bookVersion, String bookName){
        UserOperationVo userOperationVo = new UserOperationVo();
        userOperationVo.setId(bookVersion.getUserDownLoadList().get(0).getId());
        userOperationVo.setBookName(bookName);
        userOperationVo.setBookIsbn(bookVersion.getBookIsbn());
        userOperationVo.setBookSize(bookVersion.getBookSize());
        userOperationVo.setBookOriginname(bookVersion.getBookOriginname());
        userOperationVo.setOperationTime(DateTimeUtil.dateToStr(bookVersion.getUserDownLoadList().get(0).getDownTime()));
        return userOperationVo;
    }
}



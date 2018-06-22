package com.ReadEnjoyBack.service.Impl;

import com.ReadEnjoyBack.common.ResponseCode;
import com.ReadEnjoyBack.common.ServerResponse;
import com.ReadEnjoyBack.dao.BookMapper;
import com.ReadEnjoyBack.dao.BookVersionMapper;
import com.ReadEnjoyBack.dao.UserCollectionMapper;
import com.ReadEnjoyBack.pojo.Book;
import com.ReadEnjoyBack.pojo.BookVersion;
import com.ReadEnjoyBack.pojo.UserCollection;
import com.ReadEnjoyBack.service.IBookVersionService;
import com.ReadEnjoyBack.util.DateTimeUtil;
import com.ReadEnjoyBack.vo.BookVersionVO;
import com.ReadEnjoyBack.vo.UserOperationVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
        System.out.println("开始获得书籍版本信息:" );
        List<BookVersion> bookVersionList = bookVersionMapper.selectByBookISBN(bookISBN);
        System.out.println("当前书籍版本信息:" + bookVersionList);
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
    }
       /*
        * @Author:HB
        * @Description: 得到当前登录用户的收藏书籍信息
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
        BookVersionVO bookVresionVo = new BookVersionVO();
        bookVresionVo.setId(bookVersion.getId());
        bookVresionVo.setBookSize(bookVersion.getBookSize());
        bookVresionVo.setBookOriginname(bookVersion.getBookOriginname());
        bookVresionVo.setBookUploadname(bookVersion.getBookUploadname());
        bookVresionVo.setCollectNumber(bookVersion.getCollectNumber());
        bookVresionVo.setDownNumber(bookVersion.getDownNumber());
        bookVresionVo.setUpdateTime(DateTimeUtil.dateToStr(bookVersion.getUpdateTime()));
        bookVresionVo.setUploadTime(DateTimeUtil.dateToStr(bookVersion.getUpdateTime()));
        bookVresionVo.setUploadUser(bookVersion.getUploadUser());
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

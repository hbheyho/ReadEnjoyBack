package com.ReadEnjoyBack.service.Impl;

import com.ReadEnjoyBack.common.ResponseCode;
import com.ReadEnjoyBack.common.ServerResponse;
import com.ReadEnjoyBack.dao.BookVersionMapper;
import com.ReadEnjoyBack.dao.UserCollectionMapper;
import com.ReadEnjoyBack.dao.UserDownLoadMapper;
import com.ReadEnjoyBack.pojo.BookVersion;
import com.ReadEnjoyBack.pojo.UserCollection;
import com.ReadEnjoyBack.pojo.UserDownLoad;
import com.ReadEnjoyBack.service.IBookVersionService;
import com.ReadEnjoyBack.util.DateTimeUtil;
import com.ReadEnjoyBack.vo.BookVersionVO;
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
     * @Description:  通过tploadName 得到 bookversionId
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

    /*---------------------------------------VO业务类-------------------------------------------*/
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
}

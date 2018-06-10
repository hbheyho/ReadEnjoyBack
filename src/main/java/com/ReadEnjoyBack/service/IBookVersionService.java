package com.ReadEnjoyBack.service;

import com.ReadEnjoyBack.common.ServerResponse;
import com.ReadEnjoyBack.vo.BookVersionVO;

import java.util.List;

/**
 * @Author:HB
 * @Description:  书籍版本的service
 * @Createdata:Created in  12:05  2018/6/6.
 */
public interface IBookVersionService {
    /*通过uploadName 得到 originName*/
    String getOrignName(String bookName);
    /*通过uploadName 得到 bookversionId*/
    int getVersionId(String bookName);
    /*根据书籍的ISBN获得书籍的版本信息*/
    ServerResponse<List<BookVersionVO>> getBookVersionList(String bookISBN);
    /*存储用户的收藏信息*/
    ServerResponse inserCollectionInfo(Integer bookVersionId,String username);
}

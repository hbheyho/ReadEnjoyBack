package com.ReadEnjoyBack.dao;

import com.ReadEnjoyBack.pojo.BookVersion;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookVersionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BookVersion record);

    int insertSelective(BookVersion record);

    BookVersion selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BookVersion record);

    int updateByPrimaryKey(BookVersion record);
    /*-------------------前台--------------------*/
    /*通过uploadName 得到 originName*/
    String getOrignName(@Param("uploadName") String booName);
    /*通过uploadName 得到 bookversionId*/
    int getBookVersionId(@Param("uploadName") String booName);
    /*根据书籍的ISBN获得书籍的版本信息*/
    List<BookVersion> selectByBookISBN(@Param("bookISBN") String bookISBN);
    /*对书籍下载次数进行更新*/
    int updateDownNumber(@Param("versionId") Integer versionId);
    /*对书籍收藏次数进行更新*/
    int updateCollectNumber(@Param("versionId") Integer versionId);
}
package com.ReadEnjoyBack.dao;

import com.ReadEnjoyBack.pojo.BookReport;

public interface  BookReportMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BookReport record);

    int insertSelective(BookReport record);

    BookReport selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BookReport record);

    int updateByPrimaryKey(BookReport record);
}
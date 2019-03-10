package com.ReadEnjoyBack.dao;

import com.ReadEnjoyBack.pojo.BookReport;

import java.util.List;

public interface  BookReportMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BookReport record);

    int insertSelective(BookReport record);

    BookReport selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BookReport record);

    int updateByPrimaryKey(BookReport record);

    /*得到举报列表*/
    List<BookReport> selectReportList();
}
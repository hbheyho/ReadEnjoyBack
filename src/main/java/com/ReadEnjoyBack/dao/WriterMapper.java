package com.ReadEnjoyBack.dao;

import com.ReadEnjoyBack.pojo.Writer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WriterMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Writer record);

    int insertSelective(Writer record);

    Writer selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Writer record);

    int updateByPrimaryKey(Writer record);

    /*得到作者信息根据年份*/
    List<Writer> SelectWriterByYear(@Param(value = "year") int year);
}
package com.ReadEnjoyBack.dao;

import com.ReadEnjoyBack.pojo.Navigation;

public interface NavigationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Navigation record);

    int insertSelective(Navigation record);

    Navigation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Navigation record);

    int updateByPrimaryKey(Navigation record);
}
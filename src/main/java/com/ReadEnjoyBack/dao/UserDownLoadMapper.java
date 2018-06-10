package com.ReadEnjoyBack.dao;

import com.ReadEnjoyBack.pojo.UserDownLoad;

public interface UserDownLoadMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserDownLoad record);

    int insertSelective(UserDownLoad record);

    UserDownLoad selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserDownLoad record);

    int updateByPrimaryKey(UserDownLoad record);
}
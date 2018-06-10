package com.ReadEnjoyBack.dao;

import com.ReadEnjoyBack.pojo.UserCollection;

public interface UserCollectionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserCollection record);

    int insertSelective(UserCollection record);

    UserCollection selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserCollection record);

    int updateByPrimaryKey(UserCollection record);
}
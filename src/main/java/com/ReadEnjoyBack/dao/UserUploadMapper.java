package com.ReadEnjoyBack.dao;

import com.ReadEnjoyBack.pojo.UserUpload;

public interface UserUploadMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserUpload record);

    int insertSelective(UserUpload record);

    UserUpload selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserUpload record);

    int updateByPrimaryKey(UserUpload record);
}
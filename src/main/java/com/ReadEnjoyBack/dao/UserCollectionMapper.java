package com.ReadEnjoyBack.dao;

import com.ReadEnjoyBack.pojo.UserCollection;
import org.apache.ibatis.annotations.Param;

public interface UserCollectionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserCollection record);

    int insertSelective(UserCollection record);

    UserCollection selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserCollection record);

    int updateByPrimaryKey(UserCollection record);

    // 判断当前书籍版本是否被用户收藏
    int isCollection(@Param(value = "userName") String userName, @Param(value = "bookVersionId") int bookVersionId);
}
package com.ReadEnjoyBack.dao;

import com.ReadEnjoyBack.pojo.Comments;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentsMapper {
    int deleteByPrimaryKey(Integer cid);

    int insert(Comments record);

    int insertSelective(Comments record);

    Comments selectByPrimaryKey(Integer cid);

    int updateByPrimaryKeySelective(Comments record);

    int updateByPrimaryKey(Comments record);

    /*根据versionID得到当前版本的评论信息*/
    List<Comments> getCommentInfo(@Param("versionId") int versionId);
   /*得到当前用户的评论信息*/
    List<Comments> getUserAllComments(@Param("email") String email);
    /*得到评论列表*/
    List<Comments> selectCommentsList();

}
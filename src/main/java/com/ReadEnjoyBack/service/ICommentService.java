package com.ReadEnjoyBack.service;

import com.ReadEnjoyBack.common.ServerResponse;
import com.ReadEnjoyBack.pojo.Comments;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @Author:HB
 * @Description: 用户评论管理
 * @Createdata:Created in  20:34  2019/3/9.
 */
public interface ICommentService {
    /*插入用户评论信息*/
    ServerResponse insertComments(String userEmail,int bookVersion, String bookIsbn, String commentInfo);
    /*得到当前用户所有评论*/
    ServerResponse<List<Comments>> getUserAllComments(String email);
    /*得到评论列表*/
    ServerResponse<PageInfo> getCommentList(int pageNum, int pageSize);
    /*删除用户评论*/
    ServerResponse<String> deleteComment(int commentId);
}

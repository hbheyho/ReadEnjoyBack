package com.ReadEnjoyBack.service.Impl;

import com.ReadEnjoyBack.common.ServerResponse;
import com.ReadEnjoyBack.dao.BookMapper;
import com.ReadEnjoyBack.dao.BookVersionMapper;
import com.ReadEnjoyBack.dao.CommentsMapper;
import com.ReadEnjoyBack.dao.UserMapper;
import com.ReadEnjoyBack.pojo.Book;
import com.ReadEnjoyBack.pojo.BookVersion;
import com.ReadEnjoyBack.pojo.Comments;
import com.ReadEnjoyBack.pojo.User;
import com.ReadEnjoyBack.service.ICommentService;
import com.ReadEnjoyBack.util.DateTimeUtil;
import com.ReadEnjoyBack.vo.CommentVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:HB
 * @Description:
 * @Createdata:Created in  20:37  2019/3/9.
 */
@Service("iCommentService")
public class CommentServiceImpl implements ICommentService {
    @Autowired
    private CommentsMapper commentsMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private BookVersionMapper bookVersionMapper;
    /*对类CategoryServiceImpl进行日志打印*/
    private Logger logger = LoggerFactory.getLogger(CommentServiceImpl.class);
    /*
    * @Author:HB
    * @Description: 插入用户评论信息
    * @Data:15:43 2018/12/27
    * @param null
    returns:
   */
    @Override
    public ServerResponse insertComments(String userEmail, int bookVersion, String bookIsbn, String commentInfo) {
        try {
            Comments comments = new Comments();
            comments.setUserEmail(userEmail);
            comments.setBookIsbn(bookIsbn);
            comments.setBookVersion(bookVersion);
            comments.setCommentInfo(commentInfo);
            commentsMapper.insertSelective(comments);
        }catch (Exception e){
            return ServerResponse.createByErrorMessage("评论失败!");
        }
        return ServerResponse.createBySuccessMessage("评论成功!");
    }
    /*
     * @Author:HB
     * @Description: 得到当前用户所有评论
     * @Data:10:10 2019/1/28
     * @param email
     returns:
    */
    @Override
    public ServerResponse<List<Comments>> getUserAllComments(String email) {
        List<Comments> commentsList = commentsMapper.getUserAllComments(email);
        if (commentsList.size() == 0){
            return ServerResponse.createByErrorMessage("当前用户暂无评论");
        }
        return ServerResponse.createBySuccesse(commentsList);
    }
    /*
     * @Author:HB
     * @Description: 得到评论列表
     * @Data:20:38 2019/3/9
     * @param pageNum pageSize
     returns:
    */
    @Override
    public ServerResponse<PageInfo> getCommentList(int pageNum, int pageSize) {
        // 开始分页
        PageHelper.startPage(pageNum,pageSize);
        // 填充自己的sal逻辑
        List<Comments> CommentsList = commentsMapper.selectCommentsList();
        List<CommentVo> commentVoList = new ArrayList<CommentVo>();
        for(Comments  CommentsItem: CommentsList ){
            CommentVo commentVo = assembleCommentsCollectionVo(CommentsItem);
            commentVoList.add(commentVo);
        }
        // 分页收尾
        PageInfo pageInfo = new PageInfo(CommentsList);
        pageInfo.setList(commentVoList);
        return ServerResponse.createBySuccesse(pageInfo);
    }
    /*
     * @Author:HB
     * @Description: 删除用户评论
     * @Data:16:58 2019/3/10
     * @param commentId
     returns:
    */
    @Override
    public ServerResponse<String> deleteComment(int commentId) {
            if (commentId == -1){ // 评论id为空
                return ServerResponse.createByErrorMessage("评论删除失败-参数错误");
            }
            int result = commentsMapper.deleteByPrimaryKey(commentId);
            if (result > 0){
                return ServerResponse.createBySuccessMessage("评论删除成功!");
            }else{
                return ServerResponse.createByErrorMessage("评论删除失败-参数错误");
            }
    }

    /*------------------------vo业务类处理---------------------*/
    // 用户收藏业务类初始化
    private CommentVo assembleCommentsCollectionVo(Comments comments){
        CommentVo commentVo = new CommentVo();
        commentVo.setId(comments.getCid());
        commentVo.setCommentsInfo(comments.getCommentInfo());
        commentVo.setCtime(DateTimeUtil.dateToStr(comments.getCommentTime()));
        // 根据email得到用户名字
        String email = comments.getUserEmail();
        User user = userMapper.getUserByEmail(email);
        commentVo.setCommentName(user.getUsername());
        //根据ISBN得到书名
        String bookIsbn = comments.getBookIsbn();
        Book book = bookMapper.getBookDetail(bookIsbn);
        commentVo.setBookName(book.getBookName());
        // 根据书籍版本号得到书籍版本名字
        int bookVersionId = comments.getBookVersion();
        BookVersion bookVersion = bookVersionMapper.selectByPrimaryKey(bookVersionId);
        commentVo.setBookVersionName(bookVersion.getBookOriginname());
        return commentVo;
    }
}

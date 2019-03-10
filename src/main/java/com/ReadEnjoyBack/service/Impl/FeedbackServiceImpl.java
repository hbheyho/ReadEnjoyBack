package com.ReadEnjoyBack.service.Impl;

import com.ReadEnjoyBack.common.ServerResponse;
import com.ReadEnjoyBack.dao.*;
import com.ReadEnjoyBack.pojo.*;
import com.ReadEnjoyBack.service.ICommentService;
import com.ReadEnjoyBack.service.IFeedbackService;
import com.ReadEnjoyBack.util.DateTimeUtil;
import com.ReadEnjoyBack.vo.CommentVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
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
@Service("iFeedbackService")
public class FeedbackServiceImpl implements IFeedbackService {
    @Autowired
    private FeedbackMapper feedbackMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private BookVersionMapper bookVersionMapper;
    /*对类CategoryServiceImpl进行日志打印*/
    private Logger logger = LoggerFactory.getLogger(FeedbackServiceImpl.class);
    /*
    * @Author:HB
    * @Description: 用户反馈信息插入
    * @Data:21:38 2019/2/26
    * @param  feedbackInfo feedbackName
    returns: String
   */
    @Override
    public ServerResponse<String> feedbackDo(String userName, String feedbackInfo, String feedbackName) {
        if (StringUtils.isBlank(feedbackInfo) && StringUtils.isBlank(feedbackName)){
            return ServerResponse.createByErrorMessage("请输入相应反馈类型或内容");
        }
        Feedback feedback = new Feedback();
        feedback.setFbInfo(feedbackInfo);
        feedback.setFbName(feedbackName);  // 反馈类型
        feedback.setFbUsrname(userName);
        feedback.setFbStatus(0);  // 该反馈还未处理
        int result = feedbackMapper.insertSelective(feedback);
        if (result > 0){
            return ServerResponse.createBySuccessMessage("反馈成功!");
        }
        return ServerResponse.createByErrorMessage("反馈失败!");
    }
    /*
     * @Author:HB
     * @Description: 得到反馈列表
     * @Data:20:38 2019/3/9
     * @param pageNum pageSize
     returns:
    */
    @Override
    public ServerResponse<PageInfo> getFeedBackList(int pageNum, int pageSize) {
        // 开始分页
        PageHelper.startPage(pageNum,pageSize);
        // 填充自己的sal逻辑
        List<Feedback> feedbackList = feedbackMapper.selectFeedbacksList();
        for(Feedback  FeedbackItem: feedbackList ){
            FeedbackItem.setCTime(DateTimeUtil.dateToStr(FeedbackItem.getFbTime()));
        }
        // 分页收尾
        PageInfo pageInfo = new PageInfo(feedbackList);
        return ServerResponse.createBySuccesse(pageInfo);
    }
    /*
     * @Author:HB
     * @Description: 删除用户反馈
     * @Data:16:58 2019/3/10
     * @param commentId
     returns:
    */
    @Override
    public ServerResponse<String> deleteFeedback(int feedbackId) {
            if (feedbackId == -1){ // 评论id为空
                return ServerResponse.createByErrorMessage("反馈删除失败-参数错误");
            }
            int result = feedbackMapper.deleteByPrimaryKey(feedbackId);
            if (result > 0){
                return ServerResponse.createBySuccessMessage("反馈删除成功!");
            }else{
                return ServerResponse.createByErrorMessage("反馈删除失败-参数错误");
            }
    }
    /*
     * @Author:HB
     * @Description: 用户反馈处理
     * @Data:19:28 2019/3/10
     * @param status feedbackId
     returns:
    */
    @Override
    public ServerResponse dealFeedback(int status, int feedbackId) {
        if (feedbackId == -1){
            return ServerResponse.createByErrorMessage("反馈处理失败-参数错误");
        }
        if (status == 0) {  //举报未处理,进行处理
            Feedback feedback = new Feedback();
            feedback.setFbStatus(1);
            feedback.setId(feedbackId);
            int result = feedbackMapper.updateByPrimaryKeySelective(feedback);
            if (result > 0){
                return ServerResponse.createBySuccesse("反馈处理成功!");
            }else {
                return ServerResponse.createByErrorMessage("反馈处理失败-参数错误");
            }
        }
        return ServerResponse.createByErrorMessage("该信息已经处理!");
    }
}

package com.ReadEnjoyBack.service;

import com.ReadEnjoyBack.common.ServerResponse;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @Author:HB
 * @Description: 用户反馈
 * @Createdata:Created in  20:34  2019/3/9.
 */
public interface IFeedbackService {
    /*进行用户反馈操作*/
    ServerResponse<String> feedbackDo(String userName,String feedbackInfo,String feedbackName);
    /*得到反馈列表*/
    ServerResponse<PageInfo> getFeedBackList(int pageNum, int pageSize);
    /*删除用户反馈*/
    ServerResponse<String> deleteFeedback(int feedbackId);
    /*用户反馈处理*/
    ServerResponse dealFeedback(int status,int feedbackId);
}

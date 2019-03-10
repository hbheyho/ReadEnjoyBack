package com.ReadEnjoyBack.dao;

import com.ReadEnjoyBack.pojo.Feedback;

import java.util.List;

public interface FeedbackMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Feedback record);

    int insertSelective(Feedback record);

    Feedback selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Feedback record);

    int updateByPrimaryKey(Feedback record);

    /*得到反馈类型*/
    List<Feedback> selectFeedbacksList();
}
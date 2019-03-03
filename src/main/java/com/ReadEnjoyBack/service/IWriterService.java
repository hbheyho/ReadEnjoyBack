package com.ReadEnjoyBack.service;

import com.ReadEnjoyBack.common.ServerResponse;
import com.ReadEnjoyBack.pojo.Writer;

import java.util.List;

/**
 * @Author:HB
 * @Description: 关于作者信息简介操作接口
 * @Createdata:Created in  16:08  2019/2/22.
 */
public interface IWriterService {
    /*通过年份得到用户信息*/
    ServerResponse<List<Writer>> getWriterByYears(int year);
    /*获取年份信息*/
    ServerResponse<List<String>> getYears();
}

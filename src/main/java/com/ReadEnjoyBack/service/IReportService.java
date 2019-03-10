package com.ReadEnjoyBack.service;

import com.ReadEnjoyBack.common.ServerResponse;
import com.github.pagehelper.PageInfo;

/**
 * @Author:HB
 * @Description: 举报信息管理
 * @Createdata:Created in  10:39  2019/3/8.
 */
public interface IReportService {
    /*得到举报列表*/
    ServerResponse<PageInfo> getReportList(int pageNum, int pageSize);
    /*举报处理*/
    ServerResponse dealReport(int satus,int reportId);
    /*删除举报信息*/
    ServerResponse<String> deleteReport(int reportId);
    /*书籍版本举报*/
    ServerResponse<String> reportBookVersion(String userName,int bookVersionId,String reason);
}

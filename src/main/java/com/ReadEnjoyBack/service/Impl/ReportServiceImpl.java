package com.ReadEnjoyBack.service.Impl;

import com.ReadEnjoyBack.common.ServerResponse;
import com.ReadEnjoyBack.dao.BookMapper;
import com.ReadEnjoyBack.dao.BookReportMapper;
import com.ReadEnjoyBack.dao.BookVersionMapper;
import com.ReadEnjoyBack.pojo.Book;
import com.ReadEnjoyBack.pojo.BookReport;
import com.ReadEnjoyBack.pojo.BookVersion;
import com.ReadEnjoyBack.service.IReportService;
import com.ReadEnjoyBack.util.DateTimeUtil;
import com.ReadEnjoyBack.vo.ReportVo;
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
 * @Description: 用户举报实现
 * @Createdata:Created in  10:40  2019/3/8.
 */
@Service("iReportService")
public class ReportServiceImpl implements IReportService {
    @Autowired
    private BookReportMapper bookReportMapper;
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private BookVersionMapper bookVersionMapper;
    /*对类CategoryServiceImpl进行日志打印*/
    private Logger logger = LoggerFactory.getLogger(ReportServiceImpl.class);
   
    /*
     * @Author:HB
     * @Description: 得到用户举报信息
     * @Data:10:44 2019/3/8
     * @param pageNum pageSize
     returns:
    */
    @Override
    public ServerResponse<PageInfo> getReportList(int pageNum, int pageSize) {
        // 开始分页
        PageHelper.startPage(pageNum,pageSize);
        // 填充自己的sal逻辑
        List<BookReport> BookReportsList = bookReportMapper.selectReportList();
        List<ReportVo> BookReportVoList = new ArrayList<ReportVo>();
        for(BookReport  BookReportItem: BookReportsList ){
            ReportVo reportVo = assembleReportCollectionVo(BookReportItem);
            BookReportVoList.add(reportVo);
        }
        // 分页收尾
        PageInfo pageInfo = new PageInfo(BookReportsList);
        pageInfo.setList(BookReportVoList);
        return ServerResponse.createBySuccesse(pageInfo);
    }
    /*
     * @Author:HB
     * @Description:
     * @Data:19:54 2019/3/9
     * @param 举报处理
     returns:
    */
    @Override
    public ServerResponse dealReport(int status,int reportId) {
        if (reportId == -1){
            return ServerResponse.createByErrorMessage("举报处理失败-参数错误");
        }
        if (status == 0) {  //举报未处理,进行处理
           BookReport bookreport = new BookReport();
           bookreport.setReportStatus(1);
           bookreport.setId(reportId);
           int result = bookReportMapper.updateByPrimaryKeySelective(bookreport);
           if (result > 0){
               return ServerResponse.createBySuccesse("举报处理成功!");
           }else {
               return ServerResponse.createByErrorMessage("举报处理失败!");
           }
        }
        return ServerResponse.createByErrorMessage("该信息已经处理!");
    }
    /*
     * @Author:HB
     * @Description: 删除用户举报信息
     * @Data:17:12 2019/3/10
     * @param reportId
     returns:
    */
    @Override
    public ServerResponse<String> deleteReport(int reportId) {
        if (reportId == -1){ // 评论id为空
            return ServerResponse.createByErrorMessage("举报删除失败-参数错误");
        }
        int result = bookReportMapper.deleteByPrimaryKey(reportId);
        if (result > 0){
            return ServerResponse.createBySuccessMessage("举报删除成功!");
        }else{
            return ServerResponse.createByErrorMessage("举报删除失败-参数错误");
        }
    }
    /*
   * @Author:HB
   * @Description: 书籍版本举报
   * @Data:16:19 2019/1/27
   * @param userName,bookVersionId,reason
   returns:
  */
    @Override
    public ServerResponse<String> reportBookVersion(String userName, int bookVersionId, String reason) {
        // 得到书籍上传者
        String uploadName = bookVersionMapper.selectUploadUserNameById(bookVersionId);
        if (uploadName == null){
            return ServerResponse.createByErrorMessage("举报失败!请重新再试!");
        }
        BookReport bookReport = new BookReport();
        bookReport.setBookVersionId(bookVersionId);
        bookReport.setReportName(userName);
        bookReport.setReportReason(reason);
        bookReport.setUploadName(uploadName);
        bookReport.setReportStatus(0);
        // 进行举报信息插入
        try {
            bookReportMapper.insertSelective(bookReport);
        }catch (Exception e){
            return ServerResponse.createByErrorMessage("举报失败!请重新再试!");
        }
        return ServerResponse.createBySuccessMessage("举报成功!");
    }

    /*------------------------vo业务类处理---------------------*/
    // 用户收藏业务类初始化
    private ReportVo assembleReportCollectionVo(BookReport bookReport){
        ReportVo reportVo = new ReportVo();
        reportVo.setId(bookReport.getId());
        reportVo.setReportReason(bookReport.getReportReason());
        reportVo.setCTime(DateTimeUtil.dateToStr(bookReport.getReportTime()));
        reportVo.setReportStatus(bookReport.getReportStatus());
        reportVo.setUploadName(bookReport.getUploadName());
        reportVo.setReportName(bookReport.getReportName());
        // 根据书籍版本号得到书籍版本名字
        int bookVersionId = bookReport.getBookVersionId();
        BookVersion bookVersion = bookVersionMapper.selectByPrimaryKey(bookVersionId);
        reportVo.setReportBookVersionName(bookVersion.getBookOriginname());
        //根据ISBN得到书名
        String bookIsbn = bookVersion.getBookIsbn();
        Book book = bookMapper.getBookDetail(bookIsbn);
        reportVo.setReportBookName(book.getBookName());
        return reportVo;
    }
}

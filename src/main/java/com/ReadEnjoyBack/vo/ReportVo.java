package com.ReadEnjoyBack.vo;

/**
 * @Author:HB
 * @Description:举报业务类
 * @Createdata:Created in  21:26  2019/3/9.
 */
public class ReportVo {
    private Integer id;
    private String CTime; // 用户举报时间 字符型
    private String reportBookVersionName; // 所举报版本名
    private String reportBookName;  // 所举报版本所属书名
    private String reportReason;  // 举报原因
    private String reportName;
    private String uploadName;
    private Integer reportStatus;

    @Override
    public String toString() {
        return "ReportVo{" +
                "id=" + id +
                ", CTime='" + CTime + '\'' +
                ", reportBookVersionName='" + reportBookVersionName + '\'' +
                ", reportBookName='" + reportBookName + '\'' +
                ", reportReason='" + reportReason + '\'' +
                ", reportName='" + reportName + '\'' +
                ", uploadName='" + uploadName + '\'' +
                ", reportStatus=" + reportStatus +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCTime() {
        return CTime;
    }

    public void setCTime(String CTime) {
        this.CTime = CTime;
    }

    public String getReportBookVersionName() {
        return reportBookVersionName;
    }

    public void setReportBookVersionName(String reportBookVersionName) {
        this.reportBookVersionName = reportBookVersionName;
    }

    public String getReportBookName() {
        return reportBookName;
    }

    public void setReportBookName(String reportBookName) {
        this.reportBookName = reportBookName;
    }

    public String getReportReason() {
        return reportReason;
    }

    public void setReportReason(String reportReason) {
        this.reportReason = reportReason;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public String getUploadName() {
        return uploadName;
    }

    public void setUploadName(String uploadName) {
        this.uploadName = uploadName;
    }

    public Integer getReportStatus() {
        return reportStatus;
    }

    public void setReportStatus(Integer reportStatus) {
        this.reportStatus = reportStatus;
    }
}

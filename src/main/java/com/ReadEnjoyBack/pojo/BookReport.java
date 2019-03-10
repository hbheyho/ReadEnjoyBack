package com.ReadEnjoyBack.pojo;

import java.util.Date;

public class BookReport {
    private Integer id;

    private Integer bookVersionId;

    private String reportReason;

    private String reportName;

    private String uploadName;

    private Integer reportStatus;

    private Date reportTime;


    public BookReport(Integer id, Integer bookVersionId, String reportReason, String reportName, String uploadName, Integer reportStatus, Date reportTime) {
        this.id = id;
        this.bookVersionId = bookVersionId;
        this.reportReason = reportReason;
        this.reportName = reportName;
        this.uploadName = uploadName;
        this.reportStatus = reportStatus;
        this.reportTime = reportTime;
    }

    public BookReport() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBookVersionId() {
        return bookVersionId;
    }

    public void setBookVersionId(Integer bookVersionId) {
        this.bookVersionId = bookVersionId;
    }

    public String getReportReason() {
        return reportReason;
    }

    public void setReportReason(String reportReason) {
        this.reportReason = reportReason == null ? null : reportReason.trim();
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName == null ? null : reportName.trim();
    }

    public String getUploadName() {
        return uploadName;
    }

    public void setUploadName(String uploadName) {
        this.uploadName = uploadName == null ? null : uploadName.trim();
    }

    public Integer getReportStatus() {
        return reportStatus;
    }

    public void setReportStatus(Integer reportStatus) {
        this.reportStatus = reportStatus;
    }

    public Date getReportTime() {
        return reportTime;
    }

    public void setReportTime(Date reportTime) {
        this.reportTime = reportTime;
    }

}
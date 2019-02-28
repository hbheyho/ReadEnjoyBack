package com.ReadEnjoyBack.pojo;

import java.util.Date;

public class Writer {
    private Integer id;

    private String writerName;

    private String writerBirth;

    private String writerInfo;

    private String writerImage;

    private String enterTime;

    private String writerFrom;

    private Date createTime;

    private Date updateTime;

    public Writer(Integer id, String writerName, String writerBirth, String writerInfo, String writerImage, String enterTime, String writerFrom, Date createTime, Date updateTime) {
        this.id = id;
        this.writerName = writerName;
        this.writerBirth = writerBirth;
        this.writerInfo = writerInfo;
        this.writerImage = writerImage;
        this.enterTime = enterTime;
        this.writerFrom = writerFrom;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Writer() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWriterName() {
        return writerName;
    }

    public void setWriterName(String writerName) {
        this.writerName = writerName == null ? null : writerName.trim();
    }

    public String getWriterBirth() {
        return writerBirth;
    }

    public void setWriterBirth(String writerBirth) {
        this.writerBirth = writerBirth == null ? null : writerBirth.trim();
    }

    public String getWriterInfo() {
        return writerInfo;
    }

    public void setWriterInfo(String writerInfo) {
        this.writerInfo = writerInfo == null ? null : writerInfo.trim();
    }

    public String getWriterImage() {
        return writerImage;
    }

    public void setWriterImage(String writerImage) {
        this.writerImage = writerImage == null ? null : writerImage.trim();
    }

    public String getEnterTime() {
        return enterTime;
    }

    public void setEnterTime(String enterTime) {
        this.enterTime = enterTime;
    }

    public String getWriterFrom() {
        return writerFrom;
    }

    public void setWriterFrom(String writerFrom) {
        this.writerFrom = writerFrom == null ? null : writerFrom.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
package com.ReadEnjoyBack.pojo;

import java.util.Date;

public class BookVersion {
    private Integer id;

    private String uploadUser;

    private String bookIsbn;

    private String bookSize;

    private String bookOriginname;

    private String bookUploadname;

    private Integer downNumber;

    private Integer collectNumber;

    private Date uploadTime;

    private Date updateTime;

    public BookVersion(Integer id, String uploadUser, String bookIsbn, String bookSize, String bookOriginname, String bookUploadname, Integer downNumber, Integer collectNumber, Date uploadTime, Date updateTime) {
        this.id = id;
        this.uploadUser = uploadUser;
        this.bookIsbn = bookIsbn;
        this.bookSize = bookSize;
        this.bookOriginname = bookOriginname;
        this.bookUploadname = bookUploadname;
        this.downNumber = downNumber;
        this.collectNumber = collectNumber;
        this.uploadTime = uploadTime;
        this.updateTime = updateTime;
    }

    public BookVersion() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUploadUser() {
        return uploadUser;
    }

    public void setUploadUser(String uploadUser) {
        this.uploadUser = uploadUser == null ? null : uploadUser.trim();
    }

    public String getBookIsbn() {
        return bookIsbn;
    }

    public void setBookIsbn(String bookIsbn) {
        this.bookIsbn = bookIsbn == null ? null : bookIsbn.trim();
    }

    public String getBookSize() {
        return bookSize;
    }

    public void setBookSize(String bookSize) {
        this.bookSize = bookSize == null ? null : bookSize.trim();
    }

    public String getBookOriginname() {
        return bookOriginname;
    }

    public void setBookOriginname(String bookOriginname) {
        this.bookOriginname = bookOriginname == null ? null : bookOriginname.trim();
    }

    public String getBookUploadname() {
        return bookUploadname;
    }

    public void setBookUploadname(String bookUploadname) {
        this.bookUploadname = bookUploadname == null ? null : bookUploadname.trim();
    }

    public Integer getDownNumber() {
        return downNumber;
    }

    public void setDownNumber(Integer downNumber) {
        this.downNumber = downNumber;
    }

    public Integer getCollectNumber() {
        return collectNumber;
    }

    public void setCollectNumber(Integer collectNumber) {
        this.collectNumber = collectNumber;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
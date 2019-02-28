

package com.ReadEnjoyBack.pojo;

import java.util.Date;
import java.util.List;

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

    private List<UserCollection> userCollectionList;  // 版本的用户收藏信息

    private List<UserUpload> userUploadList;  // 版本的用户上传信息

    private List<UserDownLoad> userDownLoadList; // 版本的用户下载信息

    public BookVersion() {
        super();
    }

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

    @Override
    public String toString() {
        return "BookVersion{" +
                "id=" + id +
                ", uploadUser='" + uploadUser + '\'' +
                ", bookIsbn='" + bookIsbn + '\'' +
                ", bookSize='" + bookSize + '\'' +
                ", bookOriginname='" + bookOriginname + '\'' +
                ", bookUploadname='" + bookUploadname + '\'' +
                ", downNumber=" + downNumber +
                ", collectNumber=" + collectNumber +
                ", uploadTime=" + uploadTime +
                ", updateTime=" + updateTime +
                ", userCollectionList=" + userCollectionList +
                ", userUploadList=" + userUploadList +
                ", userDownLoadList=" + userDownLoadList +
                '}';
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
        this.uploadUser = uploadUser;
    }

    public String getBookIsbn() {
        return bookIsbn;
    }

    public void setBookIsbn(String bookIsbn) {
        this.bookIsbn = bookIsbn;
    }

    public String getBookSize() {
        return bookSize;
    }

    public void setBookSize(String bookSize) {
        this.bookSize = bookSize;
    }

    public String getBookOriginname() {
        return bookOriginname;
    }

    public void setBookOriginname(String bookOriginname) {
        this.bookOriginname = bookOriginname;
    }

    public String getBookUploadname() {
        return bookUploadname;
    }

    public void setBookUploadname(String bookUploadname) {
        this.bookUploadname = bookUploadname;
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

    public List<UserCollection> getUserCollectionList() {
        return userCollectionList;
    }

    public void setUserCollectionList(List<UserCollection> userCollectionList) {
        this.userCollectionList = userCollectionList;
    }

    public List<UserUpload> getUserUploadList() {
        return userUploadList;
    }

    public void setUserUploadList(List<UserUpload> userUploadList) {
        this.userUploadList = userUploadList;
    }

    public List<UserDownLoad> getUserDownLoadList() {
        return userDownLoadList;
    }

    public void setUserDownLoadList(List<UserDownLoad> userDownLoadList) {
        this.userDownLoadList = userDownLoadList;
    }
}
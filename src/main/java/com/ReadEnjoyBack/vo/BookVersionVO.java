package com.ReadEnjoyBack.vo;

import com.ReadEnjoyBack.pojo.Comments;

import java.util.List;

/**
 * @Author:HB
 * @Description: 书籍版本业务类
 * @Createdata:Created in  15:25  2018/6/6.
 */
public class BookVersionVO {
    private Integer id;

    private String uploadUser;

    private String uploadUserHeadPic;

    private String bookSize;

    private String bookOriginname;

    private String bookUploadname;

    private Integer downNumber;

    private Integer collectNumber;

    private String uploadTime;

    private String updateTime;


    private List<Comments> versionComments; // 版本的评论信息

    int commentNumber;  // 评论条数

    /*-----版本生成的swf文件的名字以及所存的服务器地址*/
    private String swfHost;
    private String swfName;

    public BookVersionVO() {
    }

    public BookVersionVO(Integer id, String uploadUser,String uploadUserHeadPic, String bookSize, String bookOriginname, String bookUploadname, Integer downNumber, Integer collectNumber, String uploadTime, String updateTime) {
        this.id = id;
        this.uploadUser = uploadUser;
        this.uploadUserHeadPic = uploadUserHeadPic;
        this.bookSize = bookSize;
        this.bookOriginname = bookOriginname;
        this.bookUploadname = bookUploadname;
        this.downNumber = downNumber;
        this.collectNumber = collectNumber;
        this.uploadTime = uploadTime;
        this.updateTime = updateTime;
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

    public String getUploadUserHeadPic() {
        return uploadUserHeadPic;
    }

    public void setUploadUserHeadPic(String uploadUserHeadPic) {
        this.uploadUserHeadPic = uploadUserHeadPic;
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

    public String getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(String uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public List<Comments> getVersionComments() {
        return versionComments;
    }

    public void setVersionComments(List<Comments> versionComments) {
        this.versionComments = versionComments;
    }

    public int getCommentNumber() {
        return commentNumber;
    }

    public void setCommentNumber(int commentNumber) {
        this.commentNumber = commentNumber;
    }

    /*-----版本生成的swf文件的名字以及所存的服务器地址*/
    public String getSwfHost() {
        return swfHost;
    }

    public void setSwfHost(String swfHost) {
        this.swfHost = swfHost;
    }

    public String getSwfName() {
        return swfName;
    }

    public void setSwfName(String swfName) {
        this.swfName = swfName;
    }
}

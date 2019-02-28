package com.ReadEnjoyBack.pojo;

import java.util.Date;
import java.util.List;

public class Comments {
    private Integer cid;

    private String userEmail;

    private Integer bookVersion;

    private String bookIsbn;

    private String commentInfo;

    private Date commentTime;

    private String commentMark;


    /*====添加的辅助字段=====*/
    private List<User> userList;

    private String commentTimeS; // 评论时间(字符型)

    private String username; // 评论人

    private String headpic; // 评论者头像

    private String imageHost; // 头像地址

    public Comments(Integer cid, String userEmail, Integer bookVersion, String bookIsbn, String commentInfo, Date commentTime, String commentMark) {
        this.cid = cid;
        this.userEmail = userEmail;
        this.bookVersion = bookVersion;
        this.bookIsbn = bookIsbn;
        this.commentInfo = commentInfo;
        this.commentTime = commentTime;
        this.commentMark = commentMark;
    }

    public Comments() {
        super();
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail == null ? null : userEmail.trim();
    }

    public Integer getBookVersion() {
        return bookVersion;
    }

    public void setBookVersion(Integer bookVersion) {
        this.bookVersion = bookVersion;
    }

    public String getBookIsbn() {
        return bookIsbn;
    }

    public void setBookIsbn(String bookIsbn) {
        this.bookIsbn = bookIsbn == null ? null : bookIsbn.trim();
    }

    public String getCommentInfo() {
        return commentInfo;
    }

    public void setCommentInfo(String commentInfo) {
        this.commentInfo = commentInfo == null ? null : commentInfo.trim();
    }

    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }

    public String getCommentMark() {
        return commentMark;
    }

    public void setCommentMark(String commentMark) {
        this.commentMark = commentMark == null ? null : commentMark.trim();
    }


    /*====添加的辅助字段=====*/
    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public String getCommentTimeS() {
        return commentTimeS;
    }

    public void setCommentTimeS(String commentTimeS) {
        this.commentTimeS = commentTimeS;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHeadpic() {
        return headpic;
    }

    public void setHeadpic(String headpic) {
        this.headpic = headpic;
    }

    public String getImageHost() {
        return imageHost;
    }

    public void setImageHost(String imageHost) {
        this.imageHost = imageHost;
    }

    @Override
    public String toString() {
        return "Comments{" +
                "cid=" + cid +
                ", userEmail='" + userEmail + '\'' +
                ", bookVersion=" + bookVersion +
                ", bookIsbn='" + bookIsbn + '\'' +
                ", commentInfo='" + commentInfo + '\'' +
                ", commentTime=" + commentTime +
                ", commentMark='" + commentMark + '\'' +
                ", userList=" + userList +
                '}';
    }
}
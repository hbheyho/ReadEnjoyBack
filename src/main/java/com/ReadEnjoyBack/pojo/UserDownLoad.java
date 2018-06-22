package com.ReadEnjoyBack.pojo;

import java.util.Date;

public class UserDownLoad {
    private Integer id;

    private String userName;

    private Integer bookVersionId;

    private Date downTime;

    public UserDownLoad(Integer id, String userName, Integer bookVersionId, Date downTime) {
        this.id = id;
        this.userName = userName;
        this.bookVersionId = bookVersionId;
        this.downTime = downTime;
    }

    @Override
    public String toString() {
        return "UserDownLoad{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", bookVersionId=" + bookVersionId +
                ", downTime=" + downTime +
                '}';
    }

    public UserDownLoad() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public Integer getBookVersionId() {
        return bookVersionId;
    }

    public void setBookVersionId(Integer bookVersionId) {
        this.bookVersionId = bookVersionId;
    }

    public Date getDownTime() {
        return downTime;
    }

    public void setDownTime(Date downTime) {
        this.downTime = downTime;
    }
}
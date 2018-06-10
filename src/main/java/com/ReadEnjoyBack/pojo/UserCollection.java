package com.ReadEnjoyBack.pojo;

import java.util.Date;

public class UserCollection {
    private Integer id;

    private String userName;

    private Integer bookVersionId;

    private Date collectTime;

    public UserCollection(Integer id, String userName, Integer bookVersionId, Date collectTime) {
        this.id = id;
        this.userName = userName;
        this.bookVersionId = bookVersionId;
        this.collectTime = collectTime;
    }

    public UserCollection() {
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

    public Date getCollectTime() {
        return collectTime;
    }

    public void setCollectTime(Date collectTime) {
        this.collectTime = collectTime;
    }
}
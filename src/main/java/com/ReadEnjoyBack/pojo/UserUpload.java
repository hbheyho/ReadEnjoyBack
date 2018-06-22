package com.ReadEnjoyBack.pojo;

public class UserUpload {
    private Integer id;

    private String userName;

    private Integer bookVersionId;

    public UserUpload(Integer id, String userName, Integer bookVersionId) {
        this.id = id;
        this.userName = userName;
        this.bookVersionId = bookVersionId;
    }

    @Override
    public String toString() {
        return "UserUpload{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", bookVersionId='" + bookVersionId + '\'' +
                '}';
    }

    public UserUpload() {
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
}
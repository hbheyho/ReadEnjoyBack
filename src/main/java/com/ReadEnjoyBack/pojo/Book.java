package com.ReadEnjoyBack.pojo;

import java.util.Date;

public class Book {
    private Integer bookId;

    private String bookIsbn;

    private Integer categoryId;

    private String bookName;

    private String bookWriter;

    private String bookTranster;

    private String bookPublish;

    private String bookInfo;

    private String bookDirectory;

    private String bookWriterInformation;

    private String bookImage;

    private Double bookScore;

    private Integer bookStatus;

    private Integer bookDownNumber;

    private Date createTime;

    private Date updateTime;

    public Book(Integer bookId, String bookIsbn, Integer categoryId, String bookName, String bookWriter, String bookTranster, String bookPublish, String bookInfo, String bookDirectory, String bookWriterInformation, String bookImage, Double bookScore, Integer bookStatus,Integer bookDownNumber, Date createTime, Date updateTime) {
        this.bookId = bookId;
        this.bookIsbn = bookIsbn;
        this.categoryId = categoryId;
        this.bookName = bookName;
        this.bookWriter = bookWriter;
        this.bookTranster = bookTranster;
        this.bookPublish = bookPublish;
        this.bookInfo = bookInfo;
        this.bookDirectory = bookDirectory;
        this.bookWriterInformation = bookWriterInformation;
        this.bookImage = bookImage;
        this.bookScore = bookScore;
        this.bookStatus = bookStatus;
        this.bookDownNumber = bookDownNumber;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Book() {
        super();
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getBookIsbn() {
        return bookIsbn;
    }

    public void setBookIsbn(String bookIsbn) {
        this.bookIsbn = bookIsbn == null ? null : bookIsbn.trim();
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName == null ? null : bookName.trim();
    }

    public String getBookWriter() {
        return bookWriter;
    }

    public void setBookWriter(String bookWriter) {
        this.bookWriter = bookWriter == null ? null : bookWriter.trim();
    }

    public String getBookTranster() {
        return bookTranster;
    }

    public void setBookTranster(String bookTranster) {
        this.bookTranster = bookTranster == null ? null : bookTranster.trim();
    }

    public String getBookPublish() {
        return bookPublish;
    }

    public void setBookPublish(String bookPublish) {
        this.bookPublish = bookPublish == null ? null : bookPublish.trim();
    }

    public String getBookInfo() {
        return bookInfo;
    }

    public void setBookInfo(String bookInfo) {
        this.bookInfo = bookInfo == null ? null : bookInfo.trim();
    }

    public String getBookDirectory() {
        return bookDirectory;
    }

    public void setBookDirectory(String bookDirectory) {
        this.bookDirectory = bookDirectory == null ? null : bookDirectory.trim();
    }

    public String getBookWriterInformation() {
        return bookWriterInformation;
    }

    public void setBookWriterInformation(String bookWriterInformation) {
        this.bookWriterInformation = bookWriterInformation == null ? null : bookWriterInformation.trim();
    }

    public String getBookImage() {
        return bookImage;
    }

    public void setBookImage(String bookImage) {
        this.bookImage = bookImage == null ? null : bookImage.trim();
    }

    public Double getBookScore() {
        return bookScore;
    }

    public void setBookScore(Double bookScore) {
        this.bookScore = bookScore;
    }

    public Integer getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(Integer bookStatus) {
        this.bookStatus = bookStatus;
    }

    public Integer getBookDownNumber() {
        return bookDownNumber;
    }

    public void setBookDownNumber(Integer bookDownNumber) {
        this.bookDownNumber = bookDownNumber;
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
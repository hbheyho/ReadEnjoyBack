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

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", bookIsbn='" + bookIsbn + '\'' +
                ", categoryId=" + categoryId +
                ", bookName='" + bookName + '\'' +
                ", bookWriter='" + bookWriter + '\'' +
                ", bookTranster='" + bookTranster + '\'' +
                ", bookPublish='" + bookPublish + '\'' +
                ", bookInfo='" + bookInfo + '\'' +
                ", bookDirectory='" + bookDirectory + '\'' +
                ", bookWriterInformation='" + bookWriterInformation + '\'' +
                ", bookImage='" + bookImage + '\'' +
                ", bookScore=" + bookScore +
                ", bookStatus=" + bookStatus +
                ", bookDownNumber=" + bookDownNumber +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
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
        this.bookIsbn = bookIsbn;
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
        this.bookName = bookName;
    }

    public String getBookWriter() {
        return bookWriter;
    }

    public void setBookWriter(String bookWriter) {
        this.bookWriter = bookWriter;
    }

    public String getBookTranster() {
        return bookTranster;
    }

    public void setBookTranster(String bookTranster) {
        this.bookTranster = bookTranster;
    }

    public String getBookPublish() {
        return bookPublish;
    }

    public void setBookPublish(String bookPublish) {
        this.bookPublish = bookPublish;
    }

    public String getBookInfo() {
        return bookInfo;
    }

    public void setBookInfo(String bookInfo) {
        this.bookInfo = bookInfo;
    }

    public String getBookDirectory() {
        return bookDirectory;
    }

    public void setBookDirectory(String bookDirectory) {
        this.bookDirectory = bookDirectory;
    }

    public String getBookWriterInformation() {
        return bookWriterInformation;
    }

    public void setBookWriterInformation(String bookWriterInformation) {
        this.bookWriterInformation = bookWriterInformation;
    }

    public String getBookImage() {
        return bookImage;
    }

    public void setBookImage(String bookImage) {
        this.bookImage = bookImage;
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
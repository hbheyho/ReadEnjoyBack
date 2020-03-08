package com.ReadEnjoyBack.vo;

import java.util.Date;

/**
 * @Author:HB
 * @Description: 书籍详情业务类
 * @Createdata:Created in  20:42  2018/5/29.
 */
public class BookDetailVo {
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
    private String createTime;
    private String updateTime;

    private String imageHost; // 图片服务器url前缀
    private Integer parentCategoryId;  // 父分类Id
    private String  categoryName; // 属于的category名字

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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getImageHost() {
        return imageHost;
    }

    public void setImageHost(String imageHost) {
        this.imageHost = imageHost;
    }

    public Integer getParentCategoryId() {
        return parentCategoryId;
    }

    public void setParentCategoryId(Integer parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}

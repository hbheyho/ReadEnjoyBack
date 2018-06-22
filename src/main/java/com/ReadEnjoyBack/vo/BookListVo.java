package com.ReadEnjoyBack.vo;

/**
 * @Author:HB
 * @Description: 书籍列表业务类
 * @Createdata:Created in  9:27  2018/5/30.
 */
public class BookListVo {
    private Integer bookId;
    private String bookIsbn;
    private Integer categoryId;
    private String bookName;
    private String bookWriter;
    private String bookCategoryName; // 书籍所属分类名
    private String bookInfo;
    private String bookImage;
    private Integer bookStatus;

    private String bookTranster;
    private String bookPublish;
    private Double bookScore;
    private String createTime;
    private String updateTime;
    private Integer bookDownNumber;
    private Integer bookVersionNumber;

    private String imageHost; // 图片服务器url前缀

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

    public Double getBookScore() {
        return bookScore;
    }

    public void setBookScore(Double bookScore) {
        this.bookScore = bookScore;
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

    public Integer getBookDownNumber() {
        return bookDownNumber;
    }

    public void setBookDownNumber(Integer bookDownNumber) {
        this.bookDownNumber = bookDownNumber;
    }

    public Integer getBookVersionNumber() {
        return bookVersionNumber;
    }

    public void setBookVersionNumber(Integer bookVersionNumber) {
        this.bookVersionNumber = bookVersionNumber;
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

    public String getBookCategoryName() {
        return bookCategoryName;
    }

    public void setBookCategoryName(String bookCategoryName) {
        this.bookCategoryName = bookCategoryName;
    }

    public String getBookInfo() {
        return bookInfo;
    }

    public void setBookInfo(String bookInfo) {
        this.bookInfo = bookInfo;
    }

    public String getBookImage() {
        return bookImage;
    }

    public void setBookImage(String bookImage) {
        this.bookImage = bookImage;
    }

    public Integer getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(Integer bookStatus) {
        this.bookStatus = bookStatus;
    }

    public String getImageHost() {
        return imageHost;
    }

    public void setImageHost(String imageHost) {
        this.imageHost = imageHost;
    }
}

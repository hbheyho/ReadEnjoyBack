package com.ReadEnjoyBack.vo;

/**
 * @Author:HB
 * @Description: 用户操作信息业务类 (下载 收藏 上传)
 * @Createdata:Created in  14:17  2018/6/12.
 */
public class UserOperationVo {
    private Integer id;

    private String bookName;

    private String bookIsbn;

    private String bookSize;

    private String bookOriginname;

    private String operationTime;

    public UserOperationVo() {
    }

    public UserOperationVo(Integer id , String bookName, String bookIsbn, String bookSize, String bookOriginname, String operationTime) {
        this.id = id;
        this.bookName = bookName;
        this.bookIsbn = bookIsbn;
        this.bookSize = bookSize;
        this.bookOriginname = bookOriginname;
        this.operationTime = operationTime;
    }

    @Override
    public String toString() {
        return "UserCollectionVo{" +
                "id=" + id +
                ", bookName='" + bookName + '\'' +
                ", bookIsbn='" + bookIsbn + '\'' +
                ", bookSize='" + bookSize + '\'' +
                ", bookOriginname='" + bookOriginname + '\'' +
                ", operationTime='" + operationTime + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
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

    public String getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(String operationTime) {
        this.operationTime = operationTime;
    }
}

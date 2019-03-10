package com.ReadEnjoyBack.vo;

/**
 * @Author:HB
 * @Description: 评论业务类
 * @Createdata:Created in  20:47  2019/3/9.
 */
public class CommentVo {
    private int id; // 所属id
    private String commentName; // 评论人
    private String commentsInfo; // 评论信息
    private String bookVersionName;     //评论版本名字
    private String bookName;  // 评论版本所属于书籍
    private String Ctime;  // 举报时间
    private int mark;   // 该评论的状态

    @Override
    public String toString() {
        return "CommentVo{" +
                "id=" + id +
                ", commentName='" + commentName + '\'' +
                ", commentsInfo='" + commentsInfo + '\'' +
                ", bookVersionName='" + bookVersionName + '\'' +
                ", bookName='" + bookName + '\'' +
                ", Ctime='" + Ctime + '\'' +
                ", mark=" + mark +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCommentName() {
        return commentName;
    }

    public void setCommentName(String commentName) {
        this.commentName = commentName;
    }

    public String getCommentsInfo() {
        return commentsInfo;
    }

    public void setCommentsInfo(String commentsInfo) {
        this.commentsInfo = commentsInfo;
    }

    public String getBookVersionName() {
        return bookVersionName;
    }

    public void setBookVersionName(String bookVersionName) {
        this.bookVersionName = bookVersionName;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getCtime() {
        return Ctime;
    }

    public void setCtime(String ctime) {
        Ctime = ctime;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }
}

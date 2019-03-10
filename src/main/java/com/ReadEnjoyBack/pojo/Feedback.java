package com.ReadEnjoyBack.pojo;

import java.util.Date;

public class Feedback {
    private Integer id;

    private String fbName;

    private String fbInfo;

    private Integer fbStatus;

    private String fbUsrname;

    private Date fbTime;

    private String CTime;  // 反馈时间字符型

    public Feedback(Integer id, String fbName, String fbInfo, Integer fbStatus, String fbUsrname, Date fbTime) {
        this.id = id;
        this.fbName = fbName;
        this.fbInfo = fbInfo;
        this.fbStatus = fbStatus;
        this.fbUsrname = fbUsrname;
        this.fbTime = fbTime;
    }

    public Feedback() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFbName() {
        return fbName;
    }

    public void setFbName(String fbName) {
        this.fbName = fbName == null ? null : fbName.trim();
    }

    public String getFbInfo() {
        return fbInfo;
    }

    public void setFbInfo(String fbInfo) {
        this.fbInfo = fbInfo == null ? null : fbInfo.trim();
    }

    public Integer getFbStatus() {
        return fbStatus;
    }

    public void setFbStatus(Integer fbStatus) {
        this.fbStatus = fbStatus;
    }

    public String getFbUsrname() {
        return fbUsrname;
    }

    public void setFbUsrname(String fbUsrname) {
        this.fbUsrname = fbUsrname == null ? null : fbUsrname.trim();
    }

    public Date getFbTime() {
        return fbTime;
    }

    public void setFbTime(Date fbTime) {
        this.fbTime = fbTime;
    }

    public String getCTime() {
        return CTime;
    }

    public void setCTime(String CTime) {
        this.CTime = CTime;
    }
}
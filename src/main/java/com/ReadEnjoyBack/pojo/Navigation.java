package com.ReadEnjoyBack.pojo;

import java.util.Date;

public class Navigation {
    private Integer id;

    private Integer parentId;

    private String name;

    private String url;

    private Boolean status;

    private Integer sortOrder;

    private Date creatTime;

    private Date updatetime;

    public Navigation(Integer id, Integer parentId, String name,String url, Boolean status, Integer sortOrder, Date creatTime, Date updatetime) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
        this.url = url;
        this.status = status;
        this.sortOrder = sortOrder;
        this.creatTime = creatTime;
        this.updatetime = updatetime;
    }

    public Navigation() {
        super();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    /*重写hashcode*/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Navigation navigation = (Navigation) o;

        return !(id != null ? !id.equals(navigation.id) : navigation.id != null);

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
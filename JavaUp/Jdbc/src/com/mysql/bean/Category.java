package com.mysql.bean;

public class Category {
    private String cid;
    private String cname;

    public Category() {

    }

    public String getCid() {
        return cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    @Override
    public String toString() {
        return "Category{" +
                "cid='" + cid + '\'' +
                ", cname='" + cname + '\'' +
                '}';
    }
}

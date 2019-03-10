package com.store.domain;

import java.io.Serializable;

//    `cid` varchar(32) NOT NULL,
//`cname` varchar(20) DEFAULT NULL,
public class Category implements Serializable{

    private String cid;
    private String cname;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }
}

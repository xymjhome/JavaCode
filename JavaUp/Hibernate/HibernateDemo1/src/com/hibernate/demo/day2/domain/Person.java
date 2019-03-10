package com.hibernate.demo.day2.domain;

public class Person {
    private String pid;
    private String pname;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    @Override
    public String toString() {
        return "Person{" +
                "pid='" + pid + '\'' +
                ", pname='" + pname + '\'' +
                '}';
    }
}

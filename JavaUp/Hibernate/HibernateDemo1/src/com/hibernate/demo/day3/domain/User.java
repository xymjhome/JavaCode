package com.hibernate.demo.day3.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * 系统用户
 * @author Administrator
 */
public class User {
    private Long uid;
    private String username;
    private String password;

    private Set<Role> roles = new HashSet<>();

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}

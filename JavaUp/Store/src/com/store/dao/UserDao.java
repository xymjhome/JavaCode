package com.store.dao;

import com.store.domain.User;

import java.sql.SQLException;

public interface UserDao {
    void add(User user) throws SQLException;

    User getUserByCode(String code) throws SQLException;

    void update(User user) throws SQLException;

    User getUserByNameAndPwd(String username, String pwd) throws SQLException;
}

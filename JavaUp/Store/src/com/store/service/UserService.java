package com.store.service;

import com.store.domain.User;

import java.sql.SQLException;

public interface UserService {
    void register(User user) throws  Exception;

    User active(String code) throws SQLException;

    User login(String username, String pwd) throws SQLException;
}

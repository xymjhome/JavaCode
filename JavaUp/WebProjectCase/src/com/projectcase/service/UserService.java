package com.projectcase.service;

import com.projectcase.dao.UserDao;
import com.projectcase.domain.User;

import java.sql.SQLException;

/**
 * 创建处理servlet传递过来的业务请求，处理判断密码业务
 */
public class UserService {

    public User login(String username, String inputPassword) throws SQLException {
        User user = new UserDao().getUserByNameAndPassword(username,inputPassword);
        return user;
    }

    public int register(User user) throws SQLException {
        int row = new UserDao().addUser(user);
        return row;
    }
}

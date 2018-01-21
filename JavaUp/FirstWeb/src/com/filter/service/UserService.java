package com.filter.service;

import com.filter.dao.UserDao;
import com.filter.domain.User;

import java.sql.SQLException;

/**
 * Created by Administrator on 2018/1/20 0020.
 */
public class UserService {
    public User findUser(String username, String password) throws SQLException {
        return new UserDao().findUser(username,password);
    }
}

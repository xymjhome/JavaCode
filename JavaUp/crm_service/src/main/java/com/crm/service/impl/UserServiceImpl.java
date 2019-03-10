package com.crm.service.impl;

import com.crm.dao.UserDao;
import com.crm.domain.User;
import com.crm.service.UserService;

/**
 * 用户的业务层
 * @author Administrator
 */
public class UserServiceImpl implements UserService{

    private UserDao userDao;
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User checkCode(String userCode) {
        return userDao.checkCode(userCode);
    }

    @Override
    public void save(User user) {
        userDao.save(user);
    }

    @Override
    public User login(User user) {
        return userDao.login(user);
    }
}

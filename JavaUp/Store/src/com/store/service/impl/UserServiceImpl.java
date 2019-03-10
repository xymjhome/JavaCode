package com.store.service.impl;

import com.store.dao.UserDao;
import com.store.dao.impl.UserDaoImpl;
import com.store.domain.User;
import com.store.service.UserService;
import com.store.utils.MailUtils;

import java.sql.SQLException;

public class UserServiceImpl implements UserService{
    @Override
    public void register(User user) throws Exception {
        UserDao userDao = new UserDaoImpl();
        userDao.add(user);

        //发送邮件进行验证
        String emailMsg = "欢迎注册为新用户，<a href = 'http://localhost:8080/Store/user?method=active&code="+user.getCode()+"'> 点击激活</a>";
        MailUtils.sendMail(user.getEmail(),emailMsg);
    }

    @Override
    public User active(String code) throws SQLException {
        UserDao userDao = new UserDaoImpl();
        User user = userDao.getUserByCode(code);
        if (user != null) {
            user.setState(1);
            userDao.update(user);
        }
        return user;
    }

    @Override
    public User login(String username, String pwd) throws SQLException {
        UserDao userDao = new UserDaoImpl();
        User user = userDao.getUserByNameAndPwd(username,pwd);
        return user;
    }
}

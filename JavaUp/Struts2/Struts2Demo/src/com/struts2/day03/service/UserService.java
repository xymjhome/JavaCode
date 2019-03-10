package com.struts2.day03.service;

import com.struts2.day03.dao.UserDao;
import com.struts2.day03.domain.User;
import com.struts2.day03.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

public class UserService {

    /**
     * 处理登录的功能
     *
     * @param user
     * @return
     */
    public User login(User user) {
        Session session = HibernateUtils.getCurrentSession();
        Transaction tr = session.beginTransaction();
        User existUser = null;
        try {
            existUser = new UserDao().findUserByPW(user);
            tr.commit();
        } catch (Exception e) {
            tr.rollback();
            e.printStackTrace();
        }

        return existUser;
    }


    @Test
    public void run(){
        User user = new User();
        user.setUsername("admin");
        user.setPassword("123456");
        User existUser = this.login(user);
        if(existUser != null){
            System.out.println("登录成功了..." + existUser.getName());
        } else {
            System.out.println("登陆失败");
        }
    }
}

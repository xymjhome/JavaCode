package com.struts2.day02.dao;

import com.struts2.day02.domain.User;
import com.struts2.day02.utils.HibernateUtils;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

public class UserDao {
    public User findUserByPW(User user) {
        Session session = HibernateUtils.getCurrentSession();
        Query query = session.createQuery("from User where username = ? and password = ?");
        query.setParameter(0,user.getUsername());
        query.setParameter(1,user.getPassword());

        List<User> list = query.list();

        if (list.size() > 0)
            return list.get(0);

        return null;
    }
}

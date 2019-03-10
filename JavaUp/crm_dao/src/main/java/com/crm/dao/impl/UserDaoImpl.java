package com.crm.dao.impl;

import com.crm.dao.UserDao;
import com.crm.domain.User;
import org.apache.commons.collections.CollectionUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.List;

/**
 * 持久层：都可以继承HibernateDaoSupport类
 *
 * @author Administrator
 */


public class UserDaoImpl extends HibernateDaoSupport implements UserDao {
    @Override
    public User checkCode(String userCode) {
        List<User> objects = (List<User>) this.getHibernateTemplate().find("from User where userCode = ?", userCode);
        if (CollectionUtils.isNotEmpty(objects)) {
            return objects.get(0);
        }
//        if (objects != null && objects.size() > 0) {
//            return objects.get(0);
//        }
        return null;
    }

    @Override
    public void save(User user) {
        this.getHibernateTemplate().save(user);
    }

    @Override
    public User login(User user) {
        // QBC的查询，按条件进行查询
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(User.class);
        // 拼接查询的条件
        detachedCriteria.add(Restrictions.eq("userCode",user.getUserCode()));//切记，此处propertyName值得bean类User的属性名，不是数据库表的属性名
        detachedCriteria.add(Restrictions.eq("userPassword",user.getUserPassword()));
        detachedCriteria.add(Restrictions.eq("userState","1"));

        // 查询
        List<User> byCriteria = (List<User>)this.getHibernateTemplate().findByCriteria(detachedCriteria);
//        List<User> byCriteria1 = (List<User>) this.getHibernateTemplate().find("from User ");
//        System.out.println(byCriteria1);
        if (CollectionUtils.isNotEmpty(byCriteria)) {
            return byCriteria.get(0);
        }
//        if (byCriteria != null && byCriteria.size() > 0) {
//            return byCriteria.get(0);
//        }
        return null;

    }

    @Test
    public void test(){
        User user = new User();
        user.setUserCode("tom");
        user.setUserPassword("123");
        user.setUserState("1");

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext-dao.xml");
        UserDao dao = (UserDao) applicationContext.getBean("userDao");

        User login = dao.login(user);
        System.out.println(login);
    }
}

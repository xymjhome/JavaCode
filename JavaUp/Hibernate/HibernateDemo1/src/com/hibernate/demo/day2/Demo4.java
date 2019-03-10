package com.hibernate.demo.day2;

import com.hibernate.demo.day2.domain.User;
import com.hibernate.utils.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.RecursiveAction;

/**
 * 测试的查询
 * @author Administrator
 */
public class Demo4 {


    /**
     * 按条件的查询，写法很麻烦
     */
    @Test
    public void run6() {
        Session session = HibernateUtil.openSession();
        Transaction tr = session.beginTransaction();
        // 先获取到Criteria接口
        Criteria criteria = session.createCriteria(User.class);
        // 添加查询的条件  select * from t_user where age > 18
        // Criterion 是Hibernate提供的条件查询的对象，想传入条件的使用的工具类Restrictions
        criteria.add(Restrictions.gt("age",48));
        criteria.add(Restrictions.like("name","%老%"));

        List<User> list = criteria.list();
        System.out.println(list);
        tr.commit();
        session.close();

        // Restrictions提供的静态的方法，拼接查询的条件
    }


    /**
     * Criteria接口：条件查询，非常适合
     */
    @Test
    public void run5() {
        Session session = HibernateUtil.openSession();
        Transaction tr = session.beginTransaction();
        // 先获取到Criteria接口
        Criteria criteria = session.createCriteria(User.class);
        // 没有添加条件，查询所有的数据
        List<User> list = criteria.list();
        System.out.println(list);
        tr.commit();
        session.close();

    }




    @Test
    public void run4(){
        Session session = HibernateUtil.openSession();
        Transaction tr = session.beginTransaction();

        // 查询的方式 HQL from User where 属性 条件
        // SQL：select * from t_user where 字段 条件
        Query query = session.createQuery("from User  where age > :aaa");
        //设置值，？的索引从0开始
        query.setInteger("aaa",88);

        List<User> list = query.list();
        for (User user : list)
        {
            System.out.println(user);
        }
        tr.commit();
        session.close();

    }


    /**
     * 添加查询的条件
     */
    @Test
    public void run3() {
        Session session = HibernateUtil.openSession();
        Transaction tr = session.beginTransaction();

        // 查询的方式 HQL from User where 属性 条件
        // SQL：select * from t_user where 字段 条件
        Query query = session.createQuery("from User u where u.name like ?");
        //设置值，？的索引从0开始
        query.setString(0,"%老%");

        List<User> list = query.list();
        for (User user : list)
        {
            System.out.println(user);
        }
        tr.commit();
        session.close();
    }

    /**
     * 添加查询的条件
     */
    @Test
    public void run2(){
        Session session = HibernateUtil.openSession();
        Transaction tr = session.beginTransaction();

        // 查询的方式 HQL from User where 属性 条件
        // SQL：select * from t_user where 字段 条件
        Query query = session.createQuery("from User u where u.age > ?");
        //设置值，？的索引从0开始
        query.setInteger(0,88);

        List<User> list = query.list();
        for (User user : list)
        {
            System.out.println(user);
        }
        tr.commit();
        session.close();

    }


    /**
     * 测试Query的查询接口
     */
    @Test
    public void run1() {
        Session session = HibernateUtil.openSession();
        Transaction tr = session.beginTransaction();
        // 查询的方式
        Query query = session.createQuery("from User ");
        List<User> list = query.list();
        for (User user : list)
        {
            System.out.println(user);
        }

        tr.commit();
        session.close();

    }
}

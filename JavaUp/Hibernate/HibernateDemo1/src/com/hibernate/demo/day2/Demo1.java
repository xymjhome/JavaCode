package com.hibernate.demo.day2;

import com.hibernate.demo.day2.domain.Person;
import com.hibernate.demo.day2.domain.User;
import com.hibernate.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import java.io.Serializable;

public class Demo1 {

    /**
     * 演示持久类的对象的三个状态
     */
    @Test
    public void run3(){
        Session session = HibernateUtil.openSession();
        Transaction transaction = session.beginTransaction();

        // 持久User的对象
        // 瞬时态：没有OID的值，没有被session管理，此时user对象是瞬时态对象
        User user = new User();
        user.setName("小泽老师");
        user.setAge(48);

        // 使用session保存用户
        // user对象中已经存在id的值，默认的情况下，把user对象也保存到session的缓存中
        Serializable id = session.save(user);
        System.out.println("主键的值："+id);
        // user是持久态对象

        transaction.commit();
        // session销毁，缓存没有了
        session.close();
        // 打印
        // user对象存在id值，session销毁了，缓存不存在，session管理user对象？不管理
        // user是托管态的对象
        System.out.println(user.getId());
        System.out.println(user.getName());
    }


    /**
     * 测试uuid主键的生成策略
     */
    @Test
    public void run2(){
        Session session = HibernateUtil.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        //保存用户
        Person p = new Person();
        p.setPid("aaa");
        p.setPname("小明");
        session.save(p);

        transaction.commit();
    }
    @Test
    public void run1(){
        Session session = HibernateUtil.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        //保存用户
        User user = new User();
        user.setName("奶茶妹");
        user.setAge(18);

        session.save(user);

        transaction.commit();
    }
}

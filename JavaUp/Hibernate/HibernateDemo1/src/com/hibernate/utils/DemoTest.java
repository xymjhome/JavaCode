package com.hibernate.utils;

import com.hibernate.domain.Customer;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;

import java.util.List;


public class DemoTest {

    @Test
    public void findAll() {

//        Session session = HibernateUtil.openSession();
//        Query query = session.createQuery("from Customer ");
//        List list = query.list();
//        session.close();
        String custName = "小";
        Session currentSession = HibernateUtil.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        Criteria criteria = currentSession.createCriteria(Customer.class);
        if (StringUtils.isNotEmpty(custName)) {
            String likeValue = new StringBuilder().append("%").append(custName).append("%").toString();
            System.out.println(likeValue);
            criteria.add(Restrictions.like("custName",likeValue));
        }
        List list = criteria.list();
        System.out.println(list);
        transaction.commit();
    }

    @Test
    public void testSel(){
        Session session = HibernateUtil.openSession();
        String custId = "94";
        Customer customer = session.get(Customer.class, custId);
        System.out.println(customer);

    }

    @Test
    public void testSave(){
        System.out.println("testSave");
        // 先加载配置文件
        Configuration config = new Configuration();
        // 默认加载src目录下的配置文件
        config.configure();
        // 创建SessionFactory对象
        SessionFactory factory = config.buildSessionFactory();
        // 创建session对象
        Session session = factory.openSession();
        // 开启事务
        Transaction transaction = session.beginTransaction();
        // 编写保存代码
        Customer customer = new Customer();
        // c.setCustId(cust_id);   已经自动递增
        customer.setCustName("测试1");
        customer.setCustUserId(100L);
        // 保存客户
        session.save(customer);
        // 提交事务
        transaction.commit();
        // 释放资源
        session.close();
        factory.close();
    }
}

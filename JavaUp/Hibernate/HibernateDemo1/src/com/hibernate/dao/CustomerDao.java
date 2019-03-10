package com.hibernate.dao;

import com.hibernate.domain.Customer;
import com.hibernate.utils.HibernateUtil;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class CustomerDao {
    public void add(Customer c) {
        Session session = HibernateUtil.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            transaction.begin();
            session.save(c);
            transaction.commit();
        } catch (Exception e) {
            System.out.println("add customer error");
            transaction.rollback();
        } finally {
            session.close();
        }


    }

    public List<Customer> findAll() {
        Session session = HibernateUtil.openSession();
        Query query = session.createQuery("from Customer ");
        List list = query.list();
        session.close();
        return list;
    }

    public Customer getByCustId(String custId) {
        Session session = HibernateUtil.openSession();
        Customer customer = session.get(Customer.class, Long.parseLong(custId));
        session.close();
        return customer;
    }

    public void edit(Customer customer) {
        Session session = HibernateUtil.openSession();
        Transaction transaction = session.beginTransaction();//更新保存要开启事务
        try {
            transaction.begin();
            session.update(customer);
            transaction.commit();
        } catch (Exception e) {
            System.out.println("add customer error");
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    public void delete(Customer customer) {
        Session session = HibernateUtil.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            transaction.begin();
            session.delete(customer);
            transaction.commit();
        } catch (Exception e) {
            System.out.println("delete customer error");
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    public List<Customer> findAll(String custName) {
//        Session session = HibernateUtil.openSession();
//        Query query = session.createQuery("from Customer ");
//        List list = query.list();
//        session.close();

        Session currentSession = HibernateUtil.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        Criteria criteria = currentSession.createCriteria(Customer.class);
        if (StringUtils.isNotEmpty(custName)) {
            String likeValue = new StringBuilder().append("%").append(custName).append("%").toString();
            System.out.println(likeValue);
            criteria.add(Restrictions.like("custName",likeValue));
        }
        List list = criteria.list();
        transaction.commit();
        return list;
    }
}

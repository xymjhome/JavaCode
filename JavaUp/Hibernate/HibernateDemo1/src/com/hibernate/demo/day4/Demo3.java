package com.hibernate.demo.day4;

import com.hibernate.demo.day4.domain.Customer;
import com.hibernate.demo.day4.domain.Linkman;
import com.hibernate.utils.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveAction;

/**
 * QBC的查询
 * @author Administrator
 */
public class Demo3 {


    /**
     * 演示离线条件对象
     */
    @Test
    public void run10() {
        Session session = HibernateUtil.getCurrentSession();
        Transaction tr = session.beginTransaction();
        // 创建离线条件查询的对象
        DetachedCriteria criteria = DetachedCriteria.forClass(Linkman.class);
        criteria.add(Restrictions.eq("lkmGender","女"));

        // 查询了
        List<Linkman> lists = criteria.getExecutableCriteria(session).list();
        for (Linkman linkman : lists) {
            System.out.println(linkman.getLkmId() + " ======= " + linkman.getLkmName() + " :" + linkman.getLkmGender());
        }
        tr.commit();

    }


    /**
     * 强调问题： select count(*) from 表，又想查select * from 表单，存在问题
     */
    @Test
    public void run9() {
        Session session = HibernateUtil.getCurrentSession();
        Transaction tr = session.beginTransaction();
        // 创建QBC查询接口
        Criteria criteria = session.createCriteria(Linkman.class);
        // 设置聚合函数的方式  select count(lkm_id) from 表;  5
        criteria.setProjection(Projections.count("lkmId"));

        List<Number> list = criteria.list();
        System.out.println(list.get(0).intValue());

        criteria.setProjection(null);


        List<Linkman> lists = criteria.list();
        for (Linkman linkman : lists) {
            System.out.println(linkman.getLkmId() + " ======= " + linkman.getLkmName() + " :" + linkman.getLkmGender());
        }
        tr.commit();

    }




    /**
     * 聚合函数的查询
     */
    @Test
    public void run8() {
        Session session = HibernateUtil.getCurrentSession();
        Transaction tr = session.beginTransaction();
        // 创建QBC查询接口
        Criteria criteria = session.createCriteria(Linkman.class);
        // 设置聚合函数的方式
        criteria.setProjection(Projections.count("lkmId"));
        List<Number> list = criteria.list();

        System.out.println(list.get(0).intValue());
        tr.commit();
    }



    /**
     * 判断值是否为空
     */
    @Test
    public void run7() {
        Session session = HibernateUtil.getCurrentSession();
        Transaction tr = session.beginTransaction();
        // 创建QBC查询接口
        Criteria criteria = session.createCriteria(Linkman.class);

        // 找所有的lkm_email是空的值
        criteria.add(Restrictions.isNull("lkmEmail"));

        List<Linkman> list = criteria.list();
        for (Linkman linkman : list) {
            System.out.println(linkman.getLkmId() + " ======= " + linkman.getLkmName() + " :" + linkman.getLkmGender());
        }
        tr.commit();
    }


    /**
     * 演示QBC的or方法
     */
    @Test
    public void run6() {
        Session session = HibernateUtil.getCurrentSession();
        Transaction tr = session.beginTransaction();
        // 创建QBC查询接口
        Criteria criteria = session.createCriteria(Linkman.class);

        // SQL：select * from cst_linkman where lkm_gender = '女' or lkm_id > 3L;
        criteria.add(Restrictions.or(Restrictions.eq("lkmGender","女"),Restrictions.gt("lkmId",3L)));

        List<Linkman> list = criteria.list();
        for (Linkman linkman : list) {
            System.out.println(linkman.getLkmId() + " ======= " + linkman.getLkmName() + " :" + linkman.getLkmGender());
        }
        tr.commit();


    }



    /**
     * in查询
     */
    @Test
    public void run5() {
        Session session = HibernateUtil.getCurrentSession();
        Transaction tr = session.beginTransaction();
        // 创建QBC查询接口
        Criteria criteria = session.createCriteria(Linkman.class);

        // SQL：select * from cst_linkman where lkm_id in (1,2,7);
        List<Long> params = new ArrayList<>();
        params.add(1L);
        params.add(3L);
        params.add(4L);

        criteria.add(Restrictions.in("lkmId",params));

        List<Linkman> list = criteria.list();
        for (Linkman linkman : list) {
            System.out.println(linkman.getLkmId() + " ======= " + linkman.getLkmName() + " :" + linkman.getLkmGender());
        }
        tr.commit();

    }



    /**
     * QBC的条件查询
     */
    @Test
    public void run4() {
        Session session = HibernateUtil.getCurrentSession();
        Transaction tr = session.beginTransaction();
        // 创建QBC查询接口
        Criteria criteria = session.createCriteria(Linkman.class);

        // 使用方法添加条件  and
        criteria.add(Restrictions.eq("lkmGender","女"));
        criteria.add(Restrictions.gt("lkmId",3L));
        criteria.addOrder(Order.desc("lkmId"));

        List<Linkman> list = criteria.list();
        for (Linkman linkman : list) {
            System.out.println(linkman.getLkmId() + " ======= " + linkman.getLkmName() + " :" + linkman.getLkmGender());
        }
        tr.commit();
    }







    /**
     * QBC分页的方法和HQL分页的方式一样的
     */
    @Test
    public void run3() {
        Session session = HibernateUtil.getCurrentSession();
        Transaction tr = session.beginTransaction();
        // 创建QBC查询接口
        Criteria criteria = session.createCriteria(Linkman.class);
        int size = criteria.list().size();

        int totalPage = size / 3;
        for (int i = 1; i <= totalPage; i++) {
            criteria.setFirstResult((i-1) * 3);
            criteria.setMaxResults(3);
            List<Linkman> list = criteria.list();

            for (Linkman l : list) {
                System.out.println("第 " + i + "页:" + l.getLkmId() + " ======= " + l.getLkmName());
            }
        }
    }



    /**
     * QBC的基本入门查询
     * 排序查询，调用的方法
     */
    @Test
    public void run2() {
        Session session = HibernateUtil.getCurrentSession();
        Transaction tr = session.beginTransaction();
        // 创建QBC查询接口
        Criteria criteria = session.createCriteria(Linkman.class);
        // 调用排序的方法，addOrder()
        criteria.addOrder(Order.desc("lkmId"));
        List<Linkman> list = criteria.list();

        for (Linkman  l : list) {
            System.out.println(l.getLkmId() + " ======= " + l.getLkmName());
        }
        tr.commit();
    }






    /**
     * QBC的基本入门查询
     */
    @Test
    public void run1() {
        Session session = HibernateUtil.getCurrentSession();
        Transaction tr = session.beginTransaction();
        // 创建QBC查询接口
        Criteria criteria =
                session.createCriteria(Customer.class);
        List<Customer> list = criteria.list();

        for (Customer c : list) {
            System.out.println(c.getCustName());
        }
        tr.commit();

    }
}

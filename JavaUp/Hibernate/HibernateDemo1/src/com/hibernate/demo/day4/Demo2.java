package com.hibernate.demo.day4;

import com.hibernate.demo.day4.domain.Customer;
import com.hibernate.demo.day4.domain.Linkman;
import com.hibernate.utils.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * 演示HQL的基本查询
 * @author Administrator
 */
public class Demo2 {


    /**
     * 聚合函数：求数量
     */
    @Test
    public void run11() {
        Session session = HibernateUtil.getCurrentSession();
        Transaction tr = session.beginTransaction();
        // 查询的所有的联系人的数量
        List<Number> list = session.createQuery("select sum(lm.lkmId) from Linkman lm").list();

        System.out.println(list.get(0).intValue());
        tr.commit();
    }


    /**
     * 聚合函数
     */
    @Test
    public void run10() {
        Session session = HibernateUtil.getCurrentSession();
        Transaction tr = session.beginTransaction();
        // 查询的所有的联系人的数量
        List<Number> list = session.createQuery("select count(lm) from Linkman lm").list();
        System.out.println(list.get(0).intValue());
        tr.commit();
    }



    /**
     * 聚合函数：count() sum() avg() max() min()
     */
    @Test
    public void run9() {
        Session session = HibernateUtil.getCurrentSession();
        Transaction tr = session.beginTransaction();
        // 查询的所有的联系人的数量
        List<Number> list = session.createQuery("select count(*) from Linkman ").list();
        System.out.println(list.get(0).intValue());
        tr.commit();
    }





    /**
     * 投影查询：只查询几个字段，不是所有的字段
     * 第一步：需要在JavaBean类提供对应的构造方法
     * 第二步：HQL语句的发生变化
     */
    @Test
    public void run8() {
        Session session = HibernateUtil.getCurrentSession();
        Transaction tr = session.beginTransaction();
        // 查询联系人
        List<Linkman> list = session.createQuery("select new Linkman(lkmId,lkmName,lkmGender) from Linkman ").list();

        for (Linkman  l : list) {
            System.out.println(l.getLkmId() + " ======= " + l.getLkmName() + " :" + l.getLkmGender());
        }
        tr.commit();

    }



    /**
     * 投影查询：只查询几个字段，不是所有的字段
     */
    @Test
    public void run7() {
        Session session = HibernateUtil.getCurrentSession();
        Transaction tr = session.beginTransaction();
        // 查询联系人
        Query query = session.createQuery("select l.lkmId,l.lkmName from Linkman l");
        List<Object[]> list = query.list();

        for (Object[] l : list) {
            System.out.println(Arrays.toString(l));
        }
        tr.commit();

    }


    /**
     * 按条件进行查询
     */
    @Test
    public void run6() {
        Session session = HibernateUtil.getCurrentSession();
        Transaction tr = session.beginTransaction();
        // 查询联系人
        Query query = session.createQuery("from Linkman l where l.lkmId > ? and l.lkmGender = ?");
        query.setParameter(0,3L);
        query.setParameter(1,"女");

        List<Linkman> list = query.list();

        for (Linkman  l : list) {
            System.out.println(l.getLkmId() + " ======= " + l.getLkmName() + " :" + l.getLkmGender());
        }
        tr.commit();

    }



    /**
     * HQL分页查询的两个方法
     * 	* setFirstResult(a)		-- 从哪条记录开始，如果查询是从第一条开启，值是0
     * setMaxResults(b)		-- 每页查询的记录条数
     */
    @Test
    public void run5() {
        Session session = HibernateUtil.getCurrentSession();
        Transaction tr = session.beginTransaction();
        // 查询联系人
        Query query = session.createQuery("from Linkman ");

        int size = query.list().size();
        // 分页查询，调用方法，查询第一页的数据 1-3条
		/*query.setFirstResult(0);
		query.setMaxResults(3);*/

        // 查询第二页的数据 query.setFirstResult(3);	(当前页-1)*pageSize=3
        int totalPage = size / 3;
        for (int i = 1; i <= totalPage; i++) {
            query.setFirstResult((i-1) * 3);
            query.setMaxResults(3);
            List<Linkman> list = query.list();

            for (Linkman l : list) {
                System.out.println("第 " + i + "页:" + l.getLkmId() + " ======= " + l.getLkmName());
            }
        }

        tr.commit();
    }



    /**
     * 排序查询
     * SQL：order by 字段 asc/desc;
     * HQL：关键字是一样的，都是有order by 属性
     */
    @Test
    public void run4() {
        Session session = HibernateUtil.getCurrentSession();
        Transaction tr = session.beginTransaction();
        // 查询联系人
        List<Linkman> list = session.createQuery("from Linkman l order by l.lkmId desc ").list();

        for (Linkman  l : list) {
            System.out.println(l.getLkmId() + " ======= " + l.getLkmName());
        }
        tr.commit();
    }




    /**
     * 是有别名的方式
     * select * from cst_cutomer c
     * select * from Customer 	语句错误的
     */
    @Test
    public void run3() {
        Session session = HibernateUtil.getCurrentSession();
        Transaction tr = session.beginTransaction();
        // 创建HQL的查询的接口
        List<Customer> list = session.createQuery("select c from Customer c").list();//c相当于对象Customer的别名

        for (Customer c : list) {
            System.out.println(c.getCustName());
        }
        tr.commit();
    }



    /**
     * 支持方法链的编程风格
     */
    @Test
    public void run2() {
        Session session = HibernateUtil.getCurrentSession();
        Transaction tr = session.beginTransaction();
        // 创建HQL的查询的接口
        List<Customer> list = session.createQuery("from Customer ").list();

        for (Customer c : list) {
            System.out.println(c.getCustName());
        }
        tr.commit();
    }


    /**
     * 基本的演示
     */
    @Test
    public void run1() {
        Session currentSession =
                HibernateUtil.getCurrentSession();
        Transaction tr = currentSession.beginTransaction();
//        Query select_customer = currentSession.createQuery("select from Customer "); //没有select
        // 创建HQL的查询的接口
        Query select_customer = currentSession.createQuery("from Customer ");
        List<Customer> list = select_customer.list();
        for (Customer c : list) {
            System.out.println(c.getCustName());
        }
        tr.commit();
    }
}

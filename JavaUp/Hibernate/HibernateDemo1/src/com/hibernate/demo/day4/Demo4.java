package com.hibernate.demo.day4;


import com.hibernate.demo.day4.domain.Linkman;
import com.hibernate.utils.HibernateUtil;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * SQL的查询的方式
 * @author Administrator
 */
public class Demo4 {



    /**
     * 把数据封装到对象中
     */
    @Test
    public void run2() {
        Session session = HibernateUtil.getCurrentSession();
        Transaction tr = session.beginTransaction();
        // 创建的是SQL的查询的接口
        SQLQuery query = session.createSQLQuery("SELECT * FROM cst_linkman");
        query.addEntity(Linkman.class);
        List<Linkman> list = query.list();

        for (Linkman linkman : list) {
            System.out.println(linkman.getLkmId() + " ======= " + linkman.getLkmName() + " :" + linkman.getLkmGender());
        }
        tr.commit();


    }

    /**
     * 测试SQL语句的查询
     */
    @Test
    public void run1() {
        Session session = HibernateUtil.getCurrentSession();
        Transaction tr = session.beginTransaction();
        // 创建的是SQL的查询的接口
        SQLQuery sqlQuery = session.createSQLQuery("SELECT * from cst_linkman");


        // 查询数据
        List<Object[]> list = sqlQuery.list();
        for (Object[] objects : list) {
            System.out.println(Arrays.toString(objects));
        }

        tr.commit();

    }
}

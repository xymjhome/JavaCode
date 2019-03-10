package com.hibernate.demo.day4;


import com.hibernate.demo.day4.domain.Customer;
import com.hibernate.demo.day4.domain.Linkman;
import com.hibernate.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

/**
 * 演示对象导航的方式
 * @author Administrator
 */
public class Demo1 {

    /**
     * 对象导航的方式
     */
    @Test
    public void run1() {
        Session currentSession =
                HibernateUtil.getCurrentSession();
        Transaction tr = currentSession.beginTransaction();
        Customer customer = currentSession.get(Customer.class, 1L);
        System.out.println(customer);
        System.out.println("*******************");
        System.out.println(customer.getLinkmans().size());
        tr.commit();
    }


    /**
     * 查询联系人，属于某一个客户
     */
    @Test
    public void run2() {
        // 先查询1号客户
        Session currentSession =
                HibernateUtil.getCurrentSession();
        Transaction tr = currentSession.beginTransaction();
        Linkman linkman = currentSession.get(Linkman.class, 1L);
        System.out.println(linkman.getCustomer().getCustName());
        tr.commit();
    }

}

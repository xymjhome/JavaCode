package com.ssh.dao;

import com.ssh.domain.Customer;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

public class CustomerDaoImpl extends HibernateDaoSupport implements CustomerDao{
    @Override
    public void save(Customer customer) {
        System.out.println("持久层：保存客户...");
        // 把数据，保存到数据库中
        System.out.println(customer);
        this.getHibernateTemplate().save(customer);
    }
}

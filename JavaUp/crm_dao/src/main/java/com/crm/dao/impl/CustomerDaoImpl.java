package com.crm.dao.impl;

import java.util.List;

import com.crm.dao.CustomerDao;
import com.crm.domain.Customer;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;


public class CustomerDaoImpl extends HibernateDaoSupport implements CustomerDao {

	public List<Customer> findAll() {
		return (List<Customer>)this.getHibernateTemplate().find("from Customer");
	}

}

package com.hibernate.dao;

import com.hibernate.demo.day3.domain.Linkman;
import com.hibernate.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

public class LinkmanDao {
    public List<Linkman> findAll(DetachedCriteria criteria) {

        Session currentSession = HibernateUtil.getCurrentSession();
        return criteria.getExecutableCriteria(currentSession).list();
    }
}

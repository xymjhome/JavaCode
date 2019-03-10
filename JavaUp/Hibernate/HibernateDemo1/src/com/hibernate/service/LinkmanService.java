package com.hibernate.service;

import com.hibernate.dao.LinkmanDao;
import com.hibernate.demo.day3.domain.Linkman;
import com.hibernate.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

public class LinkmanService {


    public List<Linkman> findAll(DetachedCriteria criteria) {
        LinkmanDao linkmanDao = new LinkmanDao();
        Session session = HibernateUtil.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        List<Linkman> linkmanList = linkmanDao.findAll(criteria);

        transaction.commit();
        return linkmanList;
    }
}

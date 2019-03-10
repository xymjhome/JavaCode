package com.struts2.day03.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {

    private static final Configuration CONFIG;
    private static final SessionFactory FACTORY;

    static {
        CONFIG = new Configuration().configure();
        FACTORY = CONFIG.buildSessionFactory();
    }

    public static Session openSession(){
        return FACTORY.openSession();
    }

    //    类似通过ThreadLocal类实现的获取当前线程的Session
    public static Session getCurrentSession(){
        return FACTORY.getCurrentSession();
    }

    public static void main(String[] args) {
        openSession();
    }
}

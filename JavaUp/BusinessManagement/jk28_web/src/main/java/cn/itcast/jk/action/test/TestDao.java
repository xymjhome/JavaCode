package cn.itcast.jk.action.test;

import cn.itcast.jk.dao.impl.BaseDaoImpl;
import cn.itcast.jk.domain.Dept;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("classpath:applicationContext-dao.xml")
public class TestDao {

    @Test
    public void test1() {
        System.out.println("run");
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");

        BaseDaoImpl baseDao = (BaseDaoImpl) context.getBean("baseDao");
        System.out.println(baseDao);
        Session session = baseDao.getSession();
//        Transaction transaction = session.beginTransaction();
//        transaction.begin();
        Query query = session.createQuery("from Dept");
        List<Dept> list = query.list();
        int count = list.size();
        session.close();
//        transaction.commit();
        System.out.println(count);
        System.out.println(list);
    }
}

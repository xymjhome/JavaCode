package com.hibernate.demo.day3;

import com.hibernate.demo.day3.domain.Customer;
import com.hibernate.demo.day3.domain.Linkman;
import com.hibernate.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

/**
 * 测试一对多
 * @author Administrator
 */
public class Demo1 {


    /**
     * cascade和inverse的区别    一般在一端设置放弃对外键的维护，多端设置对级联保存和更新
     *
     *  在<set>标签上配置一个inverse=”true”.true:放弃.false:不放弃.默认值是false
     *
     *   <set name="linkmans" inverse="true">
     <key column="lkm_cust_id"></key>
     <one-to-many class="com.hibernate.demo.day3.domain.Linkman"/>
     </set>

     添加客户端放弃对外键的维护
     */
    @Test
    public void run12(){
        Session session = HibernateUtil.getCurrentSession();
        Transaction tr = session.beginTransaction();

        // 级联保存
        Customer c1 = new Customer();
        c1.setCustName("美美");

        // 创建2个联系人
        Linkman l1 = new Linkman();
        l1.setLkmName("熊大");
        Linkman l2 = new Linkman();
        l2.setLkmName("熊二");

        l1.setCustomer(c1);
        l2.setCustomer(c1);

        session.save(l1);
        session.save(l2);

        // 不用修改
        tr.commit();
    }



    /**
     * 放弃外键的维护
     * 需求：让熊大联系人属于小风客户
     *  <set name="linkmans" >
         <key column="lkm_cust_id"></key>
         <one-to-many class="com.hibernate.demo.day3.domain.Linkman"/>
        </set>
     * 不设置客户对外键为维护，会造成多次查询数据库，多好几条sql语句，会降低性能
     */
    @Test
    public void run11(){
        Session session = HibernateUtil.getCurrentSession();
        Transaction tr = session.beginTransaction();

        // 先获取到小风的客户
        Customer c2 = session.get(Customer.class, 7L);
        Linkman l1 = session.get(Linkman.class, 3L);

        // 做双向的关联
        c2.getLinkmans().add(l1);

        l1.setCustomer(c2);
        // 不用修改
        tr.commit();
    }



    /**
     * 解除关系：从集合中删除联系人
     */
    @Test
    public void run10(){
        Session session = HibernateUtil.getCurrentSession();
        Transaction tr = session.beginTransaction();
        // 先获取到客户
        Customer c1 = session.get(Customer.class, 6L);
        Linkman l1 = session.get(Linkman.class, 8L);
        // 解除
        c1.getLinkmans().remove(l1);
        tr.commit();
    }


    /**
     * 测试级联删除
     *  <many-to-one name="customer" class="com.hibernate.demo.day3.domain.Customer" column="lkm_cust_id" cascade="save-update,delete-orphan"/>
     *
     * 孤儿删除（孤子删除），只有在一对多的环境下才有孤儿删除
     * 在一对多的关系中,可以将一的一方认为是父方.将多的一方认为是子方.孤儿删除:在解除了父子关系的时候.将子方记录就直接删除。
     * <many-to-one cascade="delete-orphan" />
     *
     * 但是不知道为什么此demo测试会出错
     */
    @Test
    public void run9(){
        Session session = HibernateUtil.getCurrentSession();
        Transaction tr = session.beginTransaction();
        Linkman man = session.get(Linkman.class, 8L);
        session.delete(man);
        tr.commit();
    }




    /**
     * 删除联系人，级联删除客户
     *  <many-to-one name="customer" class="com.hibernate.demo.day3.domain.Customer" column="lkm_cust_id" cascade="save-update,delete"/>
     *  删除掉联系人，删除掉客户，同时把此客户对应的联系人外键置为null
     */
    @Test
    public void run8() {
        Session session = HibernateUtil.getCurrentSession();
        Transaction tr = session.beginTransaction();

        Linkman man = session.get(Linkman.class, 6L);
        session.delete(man);
        tr.commit();
    }

    /**
     * 测试级联删除，删除客户，级联删除客户下的联系人,但是联系人不是真正的删除了
     *      是把联系人对应的外键置为null
     * <set name="linkmans" cascade="delete">
         <\!-- 需要出现子标签 -->
         <\!-- 外键的字段 -->
         <key column="lkm_cust_id"></key>
         <one-to-many class="com.hibernate.demo.day3.domain.Linkman"/>
        </set>
     */
    @Test
    public void run7(){
        Session session = HibernateUtil.getCurrentSession();
        Transaction tr = session.beginTransaction();
        // 先查询1号客户
        Customer c1 = session.get(Customer.class, 3L);
        session.delete(c1);
        tr.commit();
    }


    /**
     * 测试：删除客户，客户下有2个联系人
     *       没有配置删除级联，会只删除客户即一端，不能删除客户对应的 联系人
     *
     */
    @Test
    public void run6() {
        Session session = HibernateUtil.getCurrentSession();
        Transaction tr = session.beginTransaction();
        // 先查询1号客户
        Customer c1 = session.get(Customer.class, 2L);
        session.delete(c1);

        tr.commit();
    }



    /**
     * 测试级联保存
     * 这种插入数据就要求两端都要进行保存级联的配置,一般不采用此配置
     */
    @Test
    public void run5() {
        Session session = HibernateUtil.getCurrentSession();
        Transaction tr = session.beginTransaction();
        // 保存客户和联系人的数据
        Customer c1 = new Customer();
        c1.setCustName("美美");

        // 创建2个联系人
        Linkman l1 = new Linkman();
        l1.setLkmName("熊大");
        Linkman l2 = new Linkman();
        l2.setLkmName("熊二");

        l1.setCustomer(c1);
        c1.getLinkmans().add(l2);
        session.save(l1);

        tr.commit();

    }

    /**
     * 级联保存：保存联系人，级联客户
     <many-to-one name="customer" class="com.hibernate.demo.day3.domain.Customer" column="lkm_cust_id" cascade="save-update"/>
     */
    @Test
    public void run4(){
        Session session = HibernateUtil.getCurrentSession();
        Transaction tr = session.beginTransaction();
        // 保存客户和联系人的数据
        Customer c1 = new Customer();
        c1.setCustName("美美");

        // 创建2个联系人
        Linkman l1 = new Linkman();
        l1.setLkmName("熊大");
        Linkman l2 = new Linkman();
        l2.setLkmName("熊二");

        // 使用联系人关联客户
        l1.setCustomer(c1);
        l2.setCustomer(c1);

        // 保存
        session.save(l1);
        // 没有保存2个联系人
        session.save(l2);

        tr.commit();
    }




    /**
     * 级联保存：保存客户，级联联系人
     *            设置客户端，即一端进行级联
     *  <set name="linkmans" cascade="save-update">
             <\!-- 需要出现子标签 -->
             <\!-- 外键的字段 -->
             <key column="lkm_cust_id"></key>
             <one-to-many class="com.hibernate.demo.day3.domain.Linkman"/>
        </set>
     */
    @Test
    public void run3() {
        Session session = HibernateUtil.getCurrentSession();
        Transaction tr = session.beginTransaction();
        // 保存客户和联系人的数据
        Customer c1 = new Customer();
        c1.setCustName("美美");

        // 创建2个联系人
        Linkman l1 = new Linkman();
        l1.setLkmName("熊大");
        Linkman l2 = new Linkman();
        l2.setLkmName("熊二");

        // 单向关联
        c1.getLinkmans().add(l1);
        c1.getLinkmans().add(l2);

        session.save(c1);

        tr.commit();

    }

    /**
     * 单向的关联，如果不配置级联保存，程序出现异常
     */
    @Test
    public void run2() {
        Session session = HibernateUtil.getCurrentSession();
        Transaction tr = session.beginTransaction();
        // 保存客户和联系人的数据
        Customer c1 = new Customer();
        c1.setCustName("美美");

        // 创建2个联系人
        Linkman l1 = new Linkman();
        l1.setLkmName("熊大");
        Linkman l2 = new Linkman();
        l2.setLkmName("熊二");

        // 单向关联
        c1.getLinkmans().add(l1);
        c1.getLinkmans().add(l2);

        session.save(c1);

        tr.commit();

    }

    /**
     * 最麻烦的双向关联的方式，保存数据
     */
    @Test
    public void run1(){
        Session session = HibernateUtil.getCurrentSession();
        Transaction tr = session.beginTransaction();
        // 保存客户和联系人的数据
        Customer c1 = new Customer();
        c1.setCustName("美美");

        // 创建2个联系人
        Linkman l1 = new Linkman();
        l1.setLkmName("熊大");
        Linkman l2 = new Linkman();
        l2.setLkmName("熊二");

        // 演示双向关联
        c1.getLinkmans().add(l1);
        c1.getLinkmans().add(l2);

        l1.setCustomer(c1);
        l2.setCustomer(c1);

        // 保存数据
        session.save(c1);
        session.save(l1);
        session.save(l2);

        tr.commit();
    }
}

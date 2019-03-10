package com.crm.test;

import org.junit.Test;
import org.junit.runner.RunWith;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("classpath:applicationContext.xml")
public class TestDemo {

    @Test
    public void test() {
        SingleDemo instance = SingleDemo.getInstance();
        System.out.println(instance);
        SingleDemo instance2 = SingleDemo.getInstance();
        System.out.println(instance2);

    }
    @Test
    public void test2() {
        SingleEnumDemo instance = SingleEnumDemo.INSTANCE;
        instance.setName("1111");
        System.out.println(instance.getName());

        SingleEnumDemo instance2 = SingleEnumDemo.INSTANCE;
        instance2.setName("222");
        System.out.println(instance2.getName());
        System.out.println(instance.getName());
    }
}

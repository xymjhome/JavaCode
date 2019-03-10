package com.spring.day01.demo1;

import org.apache.log4j.Logger;
import org.junit.Test;

/**
 * 演示日志用法
 * @author Administrator
 */
public class Demo1 {

    private static final Logger LOGGER = Logger.getLogger(Demo1.class);

    @Test
    public void testLog(){
        System.out.println("sout run");
        LOGGER.info("log4j run");
    }

}

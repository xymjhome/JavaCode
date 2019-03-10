package com.spring.day02.aop;


import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 使用CGLIB方式生成代理的对象
 *
 * @return
 */
public class MyCglibUtils {
    /**
     * 使用CGLIB方式生成代理的对象
     *
     * @return
     */
    public static BookDaoImpl getProxy() {
        // 创建CGLIB核心的类
        Enhancer enhancer = new Enhancer();
        // 设置父类
        enhancer.setSuperclass(BookDaoImpl.class);
        // 设置回调函数
        enhancer.setCallback(new MethodInterceptor() {
            // 代理对象的方法执行，回调函数就会执行
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                if ("save".equals(method.getName())) {
                    System.out.println("记录日志。。。。");
                }
                // 正常执行
                return methodProxy.invokeSuper(o,objects);
            }
        });

        BookDaoImpl bookDao = (BookDaoImpl) enhancer.create();
        return bookDao;
    }
}

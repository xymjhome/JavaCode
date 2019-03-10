package com.spring.day03.aop.demo1;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * 注解方式的切面类
 * @author Administrator
 * @Aspect 这个注解声明此类为切面类
 */
@Aspect
public class MyAspectAnnotion {
    /**
     * 自动定义切入点	@Pointcut
     * execution(* *..*.*DaoImpl.save*())
     */
    @Pointcut(value = "execution(* *..*.*DaoImpl.save*())")
    public void cutFn(){}

    /**
     * 通知类型：@Before前置通知（切入点的表达式）
     */
//    @Before(value = "execution(public void com.spring.day03.aop.demo1.CustomerDaoImpl.save())")
    @Before(value = "MyAspectAnnotion.cutFn()")
    public void log(){
        System.out.println("记录日志。。。。");
    }

    /**
     * 最终通知：方法执行成功或者右异常，都会执行
     */
    @After(value="MyAspectAnnotion.cutFn()")
    public void after(){
        System.out.println("最终通知...");
    }

    /**
     * 环绕通知
     */
    @Around(value = "MyAspectAnnotion.cutFn()")
    public void around(ProceedingJoinPoint joinPoint){
        System.out.println("环绕通知1...");
        try {
            // 让目标对象的方法执行
            joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("环绕通知2...");
    }


}

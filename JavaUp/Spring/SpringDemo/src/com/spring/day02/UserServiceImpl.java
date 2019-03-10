package com.spring.day02;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 组件注解，标记类
 * <bean id="userService" class="com.itheima.demo1.UserServiceImpl"> == @Component(value="userService")
 *
 * @author Administrator
 */
@Component(value = "userService")
//@Scope(value = "protopyte")
public class UserServiceImpl implements UserService {
    // 给name属性注入美美的字符串，setName方法还可以省略不写
    @Value(value = "哈哈")
    private String name;
    /*public void setName(String name) {
        this.name = name;
	}*/

    // @Autowired 按类型自动装配
//    @Autowired
//    @Qualifier(value = "userDao")
    // 是Java的注解，Spring框架支持该注解
    @Resource(name = "userDao")
    private UserDao userDao;


    @Override
    public void sayHell() {
        System.out.println("hello Spring!!" + name);
        userDao.save();
    }

}

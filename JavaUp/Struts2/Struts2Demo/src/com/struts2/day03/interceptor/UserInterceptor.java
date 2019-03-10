package com.struts2.day03.interceptor;


import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.struts2.day03.domain.User;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;

/**
 * 自定义拦截器，判断当前系统是否已经登录，如果登录，继续执行。如果没有登录，跳转到登录页面
 * @author Administrator
 */
public class UserInterceptor extends MethodFilterInterceptor{

    /**
     * 进行拦截的方法
     */
    @Override
    protected String doIntercept(ActionInvocation invocation) throws Exception {

        System.out.println("enter UserInterceptor");
        // 获取session对象
        User existUser = (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
        System.out.println(existUser);
        if (existUser == null) {
            // 说明，没有登录，后面就不会执行了
            System.out.println("没有登录用。。。。。");
            return "login";
        }
        return invocation.invoke();
    }
}

package com.struts2.day03.demo;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.ValueStack;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


/**
 * 演示值栈对象的目录结构
 * @author Administrator
 */
public class ValueStack1Action  extends ActionSupport{

    @Override
    public String execute() throws Exception {
        // 使用获取值栈对象
//        HttpServletRequest request = ServletActionContext.getRequest();
//        Object vs = request.getAttribute("struts.valueStack");
//        System.out.println(vs);

        // 获取到值栈对象，先获取到ActionContext对象
        ValueStack valueStack = ActionContext.getContext().getValueStack();
        System.out.println(valueStack);
        // 栈顶压的小风 字符串
        // vs.push("小凤");
        valueStack.push("小明");
        // 栈顶：
        // vs.set("msg", "美美");
        valueStack.set("msg","消息");
        // vs.set("info", "小苍");
         valueStack.set("info", "小苍");

        return SUCCESS;
    }

    /**
     * 演示从值栈中获取值
     * @return
     * @throws Exception
     */
    public String save() throws Exception {
        // 获取值栈
        ValueStack vs = ActionContext.getContext().getValueStack();
        // 压栈
        // vs.push("美美");
        // vs.set("msg", "小凤");

        // 创建User对象
         User user = new User("小苍","123");
        // 压栈
//         vs.push(user);
//         vs.set("user", user);

        List<User> ulist = new ArrayList<User>();
        ulist.add(new User("熊大","123"));
        ulist.add(new User("熊二","456"));
        ulist.add(new User("熊三","789"));

        // 把ulist集合压栈
//         vs.push(ulist);

        // set方法进行压栈
        vs.set("ulist", ulist);

		/*
		// 从context栈中获取值，底层已经封装到request session对象，操作就是map集合
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("msg", "美美");
		request.getSession().setAttribute("msg", "小风");*/

        HttpServletRequest request = ServletActionContext.getRequest();
        request.setAttribute("msg", "美美");
        request.getSession().setAttribute("msg", "小风");
        return SUCCESS;
    }
}


package com.struts2.day03.action;

import com.opensymphony.xwork2.ActionSupport;
import com.struts2.day03.domain.User;
import com.struts2.day03.service.UserService;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class UserAction extends ActionSupport{

    public String login(){
        HttpServletRequest request = ServletActionContext.getRequest();
        Map<String, String[]> parameterMap = request.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user,parameterMap);
            User existUser = new UserService().login(user);
            if (existUser == null) {
                System.out.println("用户名密码错误");
                return LOGIN;
            } else {
                // 存入到session中
                System.out.println("登陆成功.Action");
                request.getSession().setAttribute("existUser",existUser);
                return SUCCESS;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return NONE;
    }
}

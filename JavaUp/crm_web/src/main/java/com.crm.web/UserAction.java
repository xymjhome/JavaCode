package com.crm.web;

import com.crm.domain.User;
import com.crm.service.UserService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UserAction extends ActionSupport implements ModelDriven<User> {

    User user = new User();
    @Override
    public User getModel() {
        return user;
    }

    private UserService userService;
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    /**
     * 注册功能
     * @return
     */
    public String regist() {
        userService.save(user);
        return LOGIN;
    }

    /**
     * 通过登录名，判断，登录名是否已经存在
     * @return
     */
    public String checkCode(){
        User user = userService.checkCode(this.user.getUserCode());
        //获取response对象
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=UTF-8");
        try {
            PrintWriter writer = response.getWriter();
            if (user == null) {
                writer.println("yes");
            } else {
                writer.println("no");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return NONE;
    }

    /**
     * 登录功能
     * @return
     */
    public String login() {
        System.out.println("user:" + user);
        User existUser =  userService.login(user);
        System.out.println("existUser:" + existUser);
        if (existUser == null) {
            return LOGIN;
        } else {
            ServletActionContext.getRequest().getSession().setAttribute("existUser",existUser);
            return "loginOK";
        }

    }


    /**
     * 退出功能
     * @return
     */
    public String exit(){
        ServletActionContext.getRequest().getSession().removeAttribute("existUser");
        return LOGIN;
    }





}

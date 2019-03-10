package com.crm.domain;

/**
 * 用户的模块
 * @author Administrator
 */
public class User {

    /**
     * `user_id` BIGINT(32) NOT NULL AUTO_INCREMENT COMMENT '用户id',
     `user_code` VARCHAR(32) NOT NULL COMMENT '用户账号',
     `user_name` VARCHAR(64) NOT NULL COMMENT '用户名称',
     `user_password` VARCHAR(32) NOT NULL COMMENT '用户密码',
     `user_state` CHAR(1) NOT NULL COMMENT '1:正常,0:暂停',
     */

    // 主键
    private Long userId;
    // 登录名称
    private String userCode;
    // 用户姓名
    private String userName;
    // 密码（保存的时候，需要加密处理）
    private String userPassword;
    // 用户的状态 1正常 0暂停
    private String userState;


    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userCode='" + userCode + '\'' +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userState='" + userState + '\'' +
                '}';
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserState() {
        return userState;
    }

    public void setUserState(String userState) {
        this.userState = userState;
    }
}





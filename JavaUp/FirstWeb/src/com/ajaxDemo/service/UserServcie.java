package com.ajaxDemo.service;

import java.sql.SQLException;

import com.ajaxDemo.dao.UserDao;
import com.ajaxDemo.domain.User;

public class UserServcie {

	/**
	 * 检测用户名是否被占用
	 * @param username
	 * @return
	 * @throws SQLException 
	 */
	public User checkUsername4Ajax(String username) throws SQLException {
		return new UserDao().getUserByUsername4Ajax(username);
	}

}

package com.ajaxDemo.dao;

import java.sql.SQLException;

import com.ajaxDemo.domain.User;
import com.ajaxDemo.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;


public class UserDao {

	/**
	 * 通过用户名获取一个用户
	 * @param username
	 * @return
	 * @throws SQLException 
	 */
	public User getUserByUsername4Ajax(String username) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select * from user where username = ? limit 1";
		return qr.query(sql, new BeanHandler<>(User.class), username);
	}

}

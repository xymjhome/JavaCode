package com.ajaxDemo.service;

import java.sql.SQLException;
import java.util.List;

import com.ajaxDemo.dao.KeyWordDao;

public class KeyWordService {

	/**
	 * 通过关键词查询
	 * @param kw
	 * @return
	 * @throws SQLException 
	 */
	public List<Object> findKw4Ajax(String kw) throws SQLException {
		return new KeyWordDao().findKw4Ajax(kw);
	}

}

package com.ajaxDemo.service;

import java.sql.SQLException;
import java.util.List;

import com.ajaxDemo.dao.ProvinceDao;
import com.ajaxDemo.domain.Province;

public class ProvinceService {

	public List<Province> findAll() throws SQLException {
		
		return new ProvinceDao().findAll();
	}

}

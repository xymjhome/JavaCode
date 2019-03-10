package com.spring.day02.aop;

public class CustomerDaoImpl implements CustomerDao {

	public void save() {
		// 模拟异常
		// int a = 10/0;	
		System.out.println("保存客户...");
	}

	@Override
	public void saveUserName(String name) {
    System.out.println("save UserName  " + name);
	}

	public void update() {
		System.out.println("修改客户...");
	}

}

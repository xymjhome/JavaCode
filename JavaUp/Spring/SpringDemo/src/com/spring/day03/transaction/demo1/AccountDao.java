package com.spring.day03.transaction.demo1;

public interface AccountDao {
	
	// 扣钱
	public void outMoney(String out, double money);
	
	// 加钱
	public void inMoney(String in, double money);
	
}

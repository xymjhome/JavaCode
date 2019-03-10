package com.crm.domain;

import java.io.Serializable;

/**
 * po的规范  （Persistent Object 持久化对象）
 * 1.公有类
 * 2.私有属性
 * 3.公有的getter与setter
 * 4.不能使用final修饰
 * 5.提供默认无参构造
 * 6.如果是基本类型，就写它对应的包装类
 * 7.一般都要实现java.io.Serializable
 * @author Administrator
 *
 */
public class Customer implements Serializable {

	private Long custId;
	private String custName;
	private Long custUserId;
	private Long custCreateId;
	private String custIndustry;
	private String custLevel;
	private String custLinkman;
	private String custPhone;
	private String custMobile;
	public Long getCustId() {
		return custId;
	}
	public void setCustId(Long custId) {
		this.custId = custId;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public Long getCustUserId() {
		return custUserId;
	}
	public void setCustUserId(Long custUserId) {
		this.custUserId = custUserId;
	}
	public Long getCustCreateId() {
		return custCreateId;
	}
	public void setCustCreateId(Long custCreateId) {
		this.custCreateId = custCreateId;
	}
	public String getCustIndustry() {
		return custIndustry;
	}
	public void setCustIndustry(String custIndustry) {
		this.custIndustry = custIndustry;
	}
	public String getCustLevel() {
		return custLevel;
	}
	public void setCustLevel(String custLevel) {
		this.custLevel = custLevel;
	}
	public String getCustLinkman() {
		return custLinkman;
	}
	public void setCustLinkman(String custLinkman) {
		this.custLinkman = custLinkman;
	}
	public String getCustPhone() {
		return custPhone;
	}
	public void setCustPhone(String custPhone) {
		this.custPhone = custPhone;
	}
	public String getCustMobile() {
		return custMobile;
	}
	public void setCustMobile(String custMobile) {
		this.custMobile = custMobile;
	}

	
	


}

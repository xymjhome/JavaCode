package com.struts2.day03.demo;

import org.junit.Test;

import ognl.Ognl;
import ognl.OgnlContext;
import ognl.OgnlException;

/**
 * 演示OGNL表达式
 * @author Administrator
 */
public class OgnlDemo {
	
	/**
	 * 测试
	 * @throws OgnlException 
	 */
	@Test
	public void run1() throws OgnlException{
		// 上下文对象
		OgnlContext context = new OgnlContext();
		// 获取到跟对象
		Object root = context.getRoot();
		// 存储数据
		context.put("name", "美美");
		// 获取值，表达式写法
		Object value = Ognl.getValue("#name", context, root);
		System.out.println(value);
	}
	
	/**
	 * OGNL表达式调用方法的
	 * @throws OgnlException
	 */
	@Test
	public void run2() throws OgnlException{
		// 上下文对象
		OgnlContext context = new OgnlContext();
		// 获取到跟对象
		Object root = context.getRoot();
		// 获取值，表达式写法
		Object value = Ognl.getValue("'haha'.length()", context, root);
		System.out.println(value);
	}
	
	// ctrl + shift + t
	
}














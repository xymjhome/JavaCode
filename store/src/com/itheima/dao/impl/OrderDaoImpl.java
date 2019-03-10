package com.itheima.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.itheima.dao.OrderDao;
import com.itheima.domain.Order;
import com.itheima.domain.OrderItem;
import com.itheima.domain.Product;
import com.itheima.utils.DataSourceUtils;

public class OrderDaoImpl implements OrderDao{

	/**
	 * 添加一条订单
	 */
	@Override
	public void add(Order order) throws Exception {
		QueryRunner qr = new QueryRunner();
		
		/*
		 * `oid` varchar(32) NOT NULL,
		  `ordertime` datetime DEFAULT NULL,
		  `total` double DEFAULT NULL,
		  
		  `state` int(11) DEFAULT NULL,
		  `address` varchar(30) DEFAULT NULL,
		  `name` varchar(20) DEFAULT NULL,
		  
		  `telephone` varchar(20) DEFAULT NULL,
		  `uid` varchar(32) DEFAULT NULL,
		 */
		String sql="insert into orders values(?,?,?,?,?,?,?,?)";
		qr.update(DataSourceUtils.getConnection(),sql, order.getOid(),order.getOrdertime(),order.getTotal(),order.getState(),
				order.getAddress(),order.getName(),order.getTelephone(),order.getUser().getUid());
	}

	/**
	 * 添加一条订单项
	 */
	@Override
	public void addItem(OrderItem oi) throws Exception {
		QueryRunner qr = new QueryRunner();
		 /**
		 * `itemid` varchar(32) NOT NULL,
		  `count` int(11) DEFAULT NULL,
		  `subtotal` double DEFAULT NULL,
		  `pid` varchar(32) DEFAULT NULL,
		  `oid` varchar(32) DEFAULT NULL,
		 */
		String sql="insert into orderitem values(?,?,?,?,?)";
		qr.update(DataSourceUtils.getConnection(),sql, oi.getItemid(),oi.getCount(),oi.getSubtotal(),oi.getProduct().getPid(),oi.getOrder().getOid());
	}

	/**
	 * 查询我的订单 分页
	 */
	@Override
	public List<Order> findAllByPage(int currPage, int pageSize, String uid) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select * from orders where uid = ? order by ordertime desc limit ? , ?";
		List<Order> list = qr.query(sql, new BeanListHandler<>(Order.class), uid,(currPage-1)*pageSize,pageSize);
		
		//遍历订单集合 封装每个订单的订单项列表
		sql="select * from orderitem oi,product p where oi.pid=p.pid and oi.oid = ?";
		for (Order order : list) {
			//当前订单包含的所有内容
			List<Map<String, Object>> mList = qr.query(sql, new MapListHandler(), order.getOid());
			//map的key:字段名  value:字段值
			for (Map<String, Object> map : mList) {
				//封装product
				Product p=new Product();
				BeanUtils.populate(p, map);
				
				//封装orderItem
				OrderItem oi = new OrderItem();
				BeanUtils.populate(oi, map);
				
				oi.setProduct(p);
				
				//将orderItem对象添加到对应的order对象的list集合中
				order.getItems().add(oi);
			}
		}
		return list;
	}

	/**
	 * 获取我的订单的总条数
	 */
	@Override
	public int getTotalCount(String uid) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select count(*) from orders where uid = ?";
		return ((Long)qr.query(sql, new ScalarHandler(), uid)).intValue();
	}

	/**
	 * 通过oid 查询订单详情
	 */
	@Override
	public Order getById(String oid) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select * from orders where oid = ?";
		Order order = qr.query(sql, new BeanHandler<>(Order.class), oid);
		
		//封装orderitems
		sql="select * from orderitem oi,product p where oi.pid = p.pid and oi.oid = ?";
		List<Map<String, Object>> query = qr.query(sql, new MapListHandler(), oid);
		for (Map<String, Object> map : query) {
			//封装product
			Product product = new Product();
			BeanUtils.populate(product, map);
			
			//封装orderitem
			OrderItem oi = new OrderItem();
			BeanUtils.populate(oi, map);
			oi.setProduct(product);
			
			//将orderitem假如中order的items中
			order.getItems().add(oi);
		}
		return order;
	}

	/**
	 * 修改订单
	 */
	@Override
	public void update(Order order) throws Exception {
		/*
		  `state` int(11) DEFAULT NULL,
		  `address` varchar(30) DEFAULT NULL,
		  `name` varchar(20) DEFAULT NULL,
		  
		  `telephone` varchar(20) DEFAULT NULL,
		  `uid` varchar(32) DEFAULT NULL,
		 */
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql="update orders set state=?,address=?,name=?,telephone=? where oid=?";
		qr.update(sql, order.getState(),order.getAddress(),order.getName(),order.getTelephone(),order.getOid());
	}

	@Override
	public List<Order> findAllByState(String state) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select * from orders where 1=1 ";
		if(state!=null && state.trim().length()>0){
			sql += "and state = ? order by ordertime desc";
			return qr.query(sql,new BeanListHandler<>(Order.class),state);
		}
		sql+=" order by ordertime desc";
		return qr.query(sql, new BeanListHandler<>(Order.class));
	}

}

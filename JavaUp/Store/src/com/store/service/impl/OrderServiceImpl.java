package com.store.service.impl;

import com.store.dao.OrderDao;
import com.store.domain.Order;
import com.store.domain.OrderItem;
import com.store.domain.PageBean;
import com.store.domain.User;
import com.store.service.OrderService;
import com.store.utils.BeanFactory;
import com.store.utils.DataSourceUtils;

import java.sql.SQLException;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    @Override
    public void add(Order order) throws SQLException {
        //订单表中插入数据需要操作两张表，要么同时插入成功，要么同时失败，所以要用同一个连接开启事务
        try {
//        1 、开启事务
            DataSourceUtils.startTransaction();
//        2、先往订单表中插入一条数据
            OrderDao orderDao = (OrderDao)BeanFactory.getBean("OrderDao");
            orderDao.add(order);
//        3、往订单项表中插入n条数据
            for (OrderItem item : order.getItems()) {
                orderDao.addItem(item);
            }

            //int i = 1/0;
//        4、提交事务
            DataSourceUtils.commitAndClose();
        } catch (SQLException e) {
            e.printStackTrace();
            DataSourceUtils.rollbackAndClose();
            throw e;
        }

    }

    @Override
    public PageBean<Order> findAllByPage(int currPage, int pageSize, User user) throws Exception {
        OrderDao orderDao = (OrderDao)BeanFactory.getBean("OrderDao");
        List<Order> list = orderDao.findAllByPage(currPage,pageSize,user.getUid());

        int totalCount = orderDao.getTotalCount(user.getUid());
        //public PageBean(List<E> list, Integer currPage, Integer pageSize, Integer totalCount) {
        return new PageBean<>(list,currPage,pageSize,totalCount);
    }

    @Override
    public Order getById(String oid) throws Exception {
        OrderDao orderDao = (OrderDao)BeanFactory.getBean("OrderDao");
        return orderDao.getById(oid);
    }

    @Override
    public void update(Order order) throws Exception {
        OrderDao od=(OrderDao) BeanFactory.getBean("OrderDao");
        od.update(order);
    }

    @Override
    public List<Order> findAllByState(String state) throws Exception {
        OrderDao od=(OrderDao) BeanFactory.getBean("OrderDao");
        return od.findAllByState(state);
    }
}

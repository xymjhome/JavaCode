package com.store.dao;

import com.store.domain.Order;
import com.store.domain.OrderItem;

import java.sql.SQLException;
import java.util.List;

public interface OrderDao {
    void add(Order order) throws SQLException;

    void addItem(OrderItem item) throws SQLException;

    List<Order> findAllByPage(int currPage, int pageSize, String uid) throws Exception;

    int getTotalCount(String uid) throws SQLException;

    Order getById(String oid) throws Exception;

    void update(Order order) throws Exception;

    List<Order> findAllByState(String state) throws Exception;
}

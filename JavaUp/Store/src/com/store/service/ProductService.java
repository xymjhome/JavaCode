package com.store.service;

import com.store.domain.PageBean;
import com.store.domain.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductService {
    List<Product> findNew() throws SQLException;

    List<Product> findHot() throws SQLException;

    Product getProductById(String pid) throws SQLException;

    PageBean<Product> findByPage(Integer currPage, Integer pageSize, String cid) throws SQLException;

    List<Product> findAll() throws SQLException;

    void add(Product p) throws SQLException;
}

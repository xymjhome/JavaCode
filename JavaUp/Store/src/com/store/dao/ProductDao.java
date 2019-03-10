package com.store.dao;

import com.store.domain.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductDao {
    List<Product> findNew() throws SQLException;

    List<Product> findHot() throws SQLException;

    Product getById(String pid) throws SQLException;

    List<Product> findByPageProduct(Integer currPage, Integer pageSize, String cid) throws SQLException;

    Integer getTotalCount(String cid) throws SQLException;

    List<Product> findAll() throws SQLException;

    void add(Product p) throws SQLException;
}

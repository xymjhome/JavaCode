package com.project.service;

import com.project.dao.ProductDao;
import com.project.domain.Product;

import java.sql.SQLException;
import java.util.List;

public class ProductService {
    public List<Product> findAll() throws SQLException {
        return new ProductDao().findAll();
    }
}

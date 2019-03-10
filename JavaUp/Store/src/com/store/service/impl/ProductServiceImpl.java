package com.store.service.impl;

import com.store.dao.ProductDao;
import com.store.dao.impl.ProductDaoImpl;
import com.store.domain.PageBean;
import com.store.domain.Product;
import com.store.service.ProductService;

import java.sql.SQLException;
import java.util.List;

public class ProductServiceImpl implements ProductService {
    @Override
    public List<Product> findNew() throws SQLException {
        ProductDao productDao = new ProductDaoImpl();

        return productDao.findNew();
    }

    @Override
    public List<Product> findHot() throws SQLException {
        ProductDao productDao = new ProductDaoImpl();

        return productDao.findHot();
    }

    @Override
    public Product getProductById(String pid) throws SQLException {
        ProductDao productDao = new ProductDaoImpl();
        return productDao.getById(pid);
    }

    @Override
    public PageBean<Product> findByPage(Integer currPage, Integer pageSize, String cid) throws SQLException {
        ProductDao productDao = new ProductDaoImpl();
        //获取每页商品列表
        List<Product> productList = productDao.findByPageProduct(currPage,pageSize,cid);

        //获取总商品数
        Integer totalCount = productDao.getTotalCount(cid);
        return new PageBean<Product>(productList,currPage,pageSize,totalCount);//List<E> list, Integer currPage, Integer pageSize, Integer totalCount
    }

    @Override
    public List<Product> findAll() throws SQLException {
        ProductDao productDao = new ProductDaoImpl();
        return productDao.findAll();
    }

    @Override
    public void add(Product p) throws SQLException {
        ProductDao productDao = new ProductDaoImpl();
        productDao.add(p);
    }
}

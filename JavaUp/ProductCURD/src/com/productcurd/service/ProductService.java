package com.productcurd.service;

import com.productcurd.dao.ProductDao;
import com.productcurd.domain.PageBean;
import com.productcurd.domain.Product;

import java.sql.SQLException;
import java.util.List;

public class ProductService {
    public List<Product> findAll() throws SQLException{
        return new ProductDao().findAll();
    }

    public void addProduct(Product product) throws SQLException {
        new ProductDao().addProduct(product);
    }

    public Product getProductById(String pid) throws SQLException {
        return new ProductDao().getProductById(pid);
    }

    public void updateProduct(Product p) throws SQLException {
        new ProductDao().updateProduct(p);
    }

    public void deleteProductById(String pid) throws SQLException {
        new ProductDao().deleteProductById(pid);
    }

    public void deleteCheckedProduct(String[] pids) throws SQLException {
        for (String pid:pids) {
            deleteProductById(pid);
        }
    }

    public List<Product> searchProductByCondition(String name, String kw) throws SQLException {
        return new ProductDao().searchProductByCondition(name,kw);
    }

    public PageBean<Product> getPageBean(int currPage, int pageSize) throws SQLException {
        ProductDao productDao = new ProductDao();
        //获取当前页展示的商品
        List<Product> list = productDao.getPageProductList(currPage,pageSize);
        int totalCount = productDao.getTotalCount();
        return new PageBean<>(list,currPage,pageSize,totalCount);
    }
}

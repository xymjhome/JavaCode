package com.store.dao.impl;

import com.store.dao.ProductDao;
import com.store.domain.PageBean;
import com.store.domain.Product;
import com.store.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class ProductDaoImpl implements ProductDao {
    @Override
    public List<Product> findNew() throws SQLException {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from product order by pdate limit 9";
        return qr.query(sql,new BeanListHandler<Product>(Product.class));
    }

    @Override
    public List<Product> findHot() throws SQLException {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from product where is_hot = 1 order by pdate limit 9";
        return qr.query(sql,new BeanListHandler<Product>(Product.class));
    }

    @Override
    public Product getById(String pid) throws SQLException {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from product where pid = ?";
        return qr.query(sql,new BeanHandler<Product>(Product.class),pid);
    }

    @Override
    public List<Product> findByPageProduct(Integer currPage, Integer pageSize, String cid) throws SQLException {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from product where cid = ? limit ?,?";
        return qr.query(sql,new BeanListHandler<Product>(Product.class),cid,(currPage - 1)*pageSize,pageSize);
    }

    @Override
    public Integer getTotalCount(String cid) throws SQLException {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select count(*) from product where cid = ?";
        return ((Long)qr.query(sql,new ScalarHandler(),cid)).intValue();
    }

    @Override
    public List<Product> findAll() throws SQLException {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from product";
        return qr.query(sql,new BeanListHandler<Product>(Product.class));
    }

    /**
     * 添加商品
     */
    @Override
    public void add(Product p) throws SQLException {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        /**
         * `pid` VARCHAR(32) NOT NULL,
         `pname` VARCHAR(50) DEFAULT NULL,
         `market_price` DOUBLE DEFAULT NULL,

         `shop_price` DOUBLE DEFAULT NULL,
         `pimage` VARCHAR(200) DEFAULT NULL,
         `pdate` DATE DEFAULT NULL,

         `is_hot` INT(11) DEFAULT NULL,
         `pdesc` VARCHAR(255) DEFAULT NULL,
         `pflag` INT(11) DEFAULT NULL,
         `cid` VARCHAR(32) DEFAULT NULL,
         */
        String sql="insert into product values(?,?,?,?,?,?,?,?,?,?);";
        qr.update(sql, p.getPid(),p.getPname(),p.getMarket_price(),
                p.getShop_price(),p.getPimage(),p.getPdate(),
                p.getIs_hot(),p.getPdesc(),p.getPflag(),p.getCategory().getCid());
    }
}

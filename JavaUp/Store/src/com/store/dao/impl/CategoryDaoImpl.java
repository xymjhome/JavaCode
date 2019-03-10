package com.store.dao.impl;

import com.store.dao.CategoryDao;
import com.store.domain.Category;
import com.store.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class CategoryDaoImpl implements CategoryDao {
    @Override
    public List<Category> findAllCategory() throws SQLException {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from category";
        return qr.query(sql,new BeanListHandler<Category>(Category.class));
    }

    @Override
    public void add(Category category) throws SQLException {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "insert into category values(?,?)";
        qr.update(sql,category.getCid(),category.getCname());
    }

    @Override
    public Category getById(String cid) throws SQLException {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from category  where cid = ?";
        return qr.query(sql,new BeanHandler<>(Category.class),cid);
    }

    @Override
    public void update(Category category) throws SQLException {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "update category set cid = ?,cname = ? where cid = ?";
        qr.update(sql,category.getCid(),category.getCname(),category.getCid());
    }

    @Override
    public void delete(String cid) throws SQLException {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "delete from category  where cid = ?";
        qr.update(sql,cid);
    }
}

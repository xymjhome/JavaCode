package com.project.dao;

import com.project.domain.Product;
import com.project.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class ProductDao {
    public List<Product> findAll() throws SQLException {//数据库day12
        QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from product";
        List<Product> query = queryRunner.query(sql, new BeanListHandler<>(Product.class));
        return query;
    }
}

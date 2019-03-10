package com.mysql.dbutils;


import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mysql.bean.Category;
import com.mysql.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.*;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * ResultSetHandler:封装结果集 接口

 ArrayHandler, ArrayListHandler, BeanHandler, BeanListHandler, ColumnListHandler, KeyedHandler, MapHandler, MapListHandler, ScalarHandler

 (了解)ArrayHandler, 将查询结果的第一条记录封装成数组,返回
 (了解)ArrayListHandler, 将查询结果的每一条记录封装成数组,将每一个数组放入list中返回
 ★★BeanHandler, 将查询结果的第一条记录封装成指定的bean对象,返回
 ★★BeanListHandler, 将查询结果的每一条记录封装成指定的bean对象,将每一个bean对象放入list中 返回.
 (了解)ColumnListHandler, 将查询结果的指定一列放入list中返回
 (了解)MapHandler, 将查询结果的第一条记录封装成map,字段名作为key,值为value 返回
 ★MapListHandler, 将查询结果的每一条记录封装map集合,将每一个map集合放入list中返回
 ★ScalarHandler,针对于聚合函数 例如:count(*) 返回的是一个Long值

 */
public class ResultHandleDemo {

//    ArrayHandler, 将查询结果的第一条记录封装成数组,返回
    @Test
    public void arrayHandler() throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from category";

        Object[] query = queryRunner.query(sql, new ArrayHandler());
        System.out.println(query);
    }

//    ArrayListHandler, 将查询结果的每一条记录封装成数组,将每一个数组放入list中返回
    @Test
    public void arrayListHandler() throws SQLException {
        QueryRunner queryRunner = new QueryRunner(new ComboPooledDataSource());
        String sql = "select * from category";
        List<Object[]> query = queryRunner.query(sql, new ArrayListHandler());
        for (Object obj: query
             ) {
            System.out.println(obj);
        }
    }

//    ★★BeanHandler, 将查询结果的第一条记录封装成指定的bean对象,返回
    @Test
    public void beanHandler()  throws SQLException {
        QueryRunner queryRunner = new QueryRunner(new ComboPooledDataSource());
        String sql = "select * from category";
        Category query = queryRunner.query(sql, new BeanHandler<>(Category.class));
        System.out.println(query);
    }

//    ★★BeanListHandler, 将查询结果的每一条记录封装成指定的bean对象,将每一个bean对象放入list中 返回.
    @Test
    public void beanListHandler() throws SQLException {
        QueryRunner queryRunner = new QueryRunner(new ComboPooledDataSource());
        String sql = "select * from category";
        List<Category> query = queryRunner.query(sql, new BeanListHandler<Category>(Category.class));
        for (Category ca:query
             ) {
            System.out.println(ca);
        }
    }

//    (了解)ColumnListHandler, 将查询结果的指定一列放入list中返回
    @Test
    public void columListHandler() throws SQLException {
        QueryRunner queryRunner = new QueryRunner(new ComboPooledDataSource());
        String sql = "select * from category";
        List<Object> query = queryRunner.query(sql, new ColumnListHandler(1));
        for (Object object:query
             ) {
            System.out.println(object);

        }
    }

//    (了解)MapHandler, 将查询结果的第一条记录封装成map,字段名作为key,值为value 返回
    @Test
    public void mapHandler() throws SQLException {
        QueryRunner queryRunner = new QueryRunner(new ComboPooledDataSource());
        String sql = "select * from category";
        Map<String, Object> query = queryRunner.query(sql, new MapHandler());
        System.out.println(query);

    }

//    ★MapListHandler, 将查询结果的每一条记录封装map集合,将每一个map集合放入list中返回
    @Test
    public void mapListHandler() throws SQLException {
        QueryRunner queryRunner = new QueryRunner(new ComboPooledDataSource());
        String sql = "select * from category";
        List<Map<String, Object>> query = queryRunner.query(sql, new MapListHandler());
        for (Map<String,Object> mp:query
             ) {
            System.out.println(mp);
        }
    }

//    ★ScalarHandler,针对于聚合函数 例如:count(*) 返回的是一个Long值
    @Test
    public void scalarHandler() throws SQLException {
        QueryRunner queryRunner = new QueryRunner(new ComboPooledDataSource());
        String sql = "select count(*) from category";
        Object query = queryRunner.query(sql, new ScalarHandler());
        System.out.println(query);
        System.out.println(query.getClass().getName());
    }
}

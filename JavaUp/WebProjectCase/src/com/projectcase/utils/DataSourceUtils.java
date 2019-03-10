package com.projectcase.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;

/**
 * 通过c3p0创建数据库连接DataSource
 */
public class DataSourceUtils {
    private static DataSource ds= new ComboPooledDataSource();

    public static DataSource getDataSource() {
        return ds;
    }
}

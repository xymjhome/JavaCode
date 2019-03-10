package com.project.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;

public class DataSourceUtils {
    private static DataSource ds = new ComboPooledDataSource();

    public static DataSource getDataSource() {
        return ds;
    }
}

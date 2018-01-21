package com.annotation;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by Administrator on 2018/1/21 0021.
 */
public class JdbcUtils {
    @JdbcInfo(url ="jdbc:mysql://localhost:3306/day15",password = "root")
    public static Connection getConnection() throws Exception {
        Class clazz = JdbcUtils.class;

        Method getConnection = clazz.getMethod("getConnection");



        if (getConnection.isAnnotationPresent(JdbcInfo.class)){
            JdbcInfo annotation = getConnection.getAnnotation(JdbcInfo.class);

            String driverClass = annotation.driverClass();
            String url = annotation.url();
            String user = annotation.user();
            String password = annotation.password();

            Class.forName(driverClass);

            Connection connection = DriverManager.getConnection(url, user, password);

            return connection;

        }

        return null;
    }


    public static void main(String[] args) throws Exception {
        System.out.println(getConnection());
    }
}

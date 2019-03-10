package com.mysql.utils;

import java.sql.*;
import java.util.ResourceBundle;

public class JdbcUtils {
    public static final String CLASSDRIVER;
    public static final String URL;
    public static final String USER;
    public static final String PASSWORD;


    static {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("jdbc");
        CLASSDRIVER = resourceBundle.getString("classDriver");
        URL = resourceBundle.getString("url");
        USER = resourceBundle.getString("user");
        PASSWORD = resourceBundle.getString("password");
    }

    static {
        try {
            Class.forName(CLASSDRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return  DriverManager.getConnection(URL,USER,PASSWORD);
    }

    public static void closeResourse(Connection con, PreparedStatement ps, ResultSet rs){
        closeRes(rs);
        closePreparedStatement(ps);
        closeConn(con);
    }
    private static void closeConn(Connection con){
        if (con != null)
        {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            con = null;
        }
    }

    private static void closePreparedStatement(PreparedStatement ps){
        if (ps != null)
        {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ps = null;
        }
    }

    private static void closeRes(ResultSet re){
        if (re != null)
        {
            try {
                re.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            re = null;
        }
    }
}

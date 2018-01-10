package com.mysql.utils;

import java.sql.*;

public class JdbcUtils1 {
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/day07","root","root");
        return con;
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

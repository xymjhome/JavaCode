package com.account.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataSourceUtils {
    private static DataSource ds = new ComboPooledDataSource();

    //此工具类的一个核心点就在于用本地线程绑定唯一的一个数据库连接
    private static ThreadLocal<Connection> tl = new ThreadLocal<>();//本地线程绑定数据库连接

    public static DataSource getDataSource() {
        return ds;
    }

    public static Connection getConnection() throws SQLException {
        //从本地线程中获取连接
        Connection connection = tl.get();
        if (connection == null){
            connection = ds.getConnection();
            tl.set(connection);
        }
        return connection;
    }

    //关闭连接资源
    public static void closeResource(Connection con, PreparedStatement ps, ResultSet rs) {
        closeResource(ps,rs);
        closeConnection(con);
//        closePreparedStatement(ps);
//        closeResultSet(rs);
    }

    //开启事物
    public static void startTransaction() throws SQLException{
        Connection connection = getConnection();
        connection.setAutoCommit(false);

    }

    //提交并关闭连接
    public static void commitAndCloseConnection() throws SQLException {
        Connection connection = getConnection();
        connection.commit();
        closeConnection(connection);
    }

    /**
     * 事务回滚
     */
    public static void rollbackAndCloseConnection() {
        Connection connection = null;
        try {
            connection = getConnection();
            connection.rollback();
            closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void closeResource( PreparedStatement ps, ResultSet rs) {
        closeResultSet(rs);
        closePreparedStatement(ps);
    }
    private static void closeResultSet(ResultSet rs)  {

        try {
            if (rs != null){
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

       rs = null;
    }

    private static void closePreparedStatement(PreparedStatement ps) {
        try {
            if (ps != null){
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ps = null;
    }

    private static void closeConnection(Connection con) {
        try {
            if (con != null){
                tl.remove();
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        con = null;
    }

}

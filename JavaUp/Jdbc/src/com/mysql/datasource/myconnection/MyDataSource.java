package com.mysql.datasource.myconnection;

import com.mysql.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;

public class MyDataSource {
    static LinkedList<Connection> pool = new LinkedList<>();
    static {
        createCon();
    }

    public Connection getCon(){
        if (pool.isEmpty()){
            createCon();
        }
        System.out.println("get con from pool");
        ConnectionWrap connectionWrap = new ConnectionWrap(pool.removeFirst(),pool);
        return connectionWrap;
    }

    public void addCon(Connection con){
        System.out.println("add back pool");
        pool.addLast(con);
    }

    public static void createCon(){
        for (int i = 0; i < 3; i++) {
            try {
                Connection con = JdbcUtils.getConnection();
                pool.addLast(con);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

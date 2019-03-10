package com.mysql.datasource.myconnection;

import java.sql.Connection;
import java.sql.SQLException;

public class TestMyDataSource {
    public static void main(String[] args) {
        MyDataSource myDataSource = new MyDataSource();
        Connection con = myDataSource.getCon();
        System.out.println(con);
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

package com.mysql.datasource.mydatasource;

import java.sql.Connection;

public class TestMyDataSource {
    public static void main(String[] args) {
        MyDataSource myDataSource = new MyDataSource();
        Connection con = myDataSource.getCon();
        System.out.println(con);
        myDataSource.addCon(con);
    }
}

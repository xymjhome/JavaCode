package com.account.dao;

import com.account.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDao4DBUtils {
    public void accountOut(String fromuser, String money) throws SQLException {
            QueryRunner queryRunner = new QueryRunner();//构造函数不传入参数，需要自己获取连接，和释放连接

            String sql = "update account set money = money - ? where name = ?";

            int i = queryRunner.update(DataSourceUtils.getConnection(),sql,money,fromuser);
            System.out.println("转出："+i);


    }

    public void accountIn(String touser, String money) throws SQLException {


            QueryRunner queryRunner = new QueryRunner();//构造函数不传入参数，需要自己获取连接，和释放连接

            String sql = "update account set money = money + ? where name = ?";

            int i = queryRunner.update(DataSourceUtils.getConnection(),sql,money,touser);
            System.out.println("转入："+i);

    }
}

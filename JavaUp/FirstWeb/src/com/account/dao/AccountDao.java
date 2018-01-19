package com.account.dao;

import com.account.utils.DataSourceUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDao {//数据库day13
    public void accountOut(String fromuser, String money) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = DataSourceUtils.getConnection();
            String sql = "update account set money = money - ? where name = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1,money);
            ps.setString(2,fromuser);

            int i = ps.executeUpdate();
            System.out.println("转出："+i);

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DataSourceUtils.closeResource(ps,rs);
        }
    }

    public void accountIn(String touser, String money) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = DataSourceUtils.getConnection();
            String sql = "update account set money = money + ? where name = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1,money);
            ps.setString(2,touser);

            int i = ps.executeUpdate();
            System.out.println("转入："+i);

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DataSourceUtils.closeResource(ps,rs);
        }
    }
}

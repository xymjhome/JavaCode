package com.mysql.datasource.c3p0;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mysql.utils.JdbcUtils;
import org.junit.Test;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class C3p0Demo {
    //硬编码获取数据库连接池
    @Test
    public void test1() throws PropertyVetoException {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setDriverClass("com.mysql.jdbc.Driver");
        comboPooledDataSource.setJdbcUrl("jdbc:mysql://localhost:3306/day07");
        comboPooledDataSource.setUser("root");
        comboPooledDataSource.setPassword("root");

        Connection con = null;
        PreparedStatement ps= null;
        ResultSet rs = null;

        try {
            con = comboPooledDataSource.getConnection();
            String sql = "insert into category values (?,?)";
            ps= con.prepareStatement(sql);
            ps.setString(1,"c006");
            ps.setString(2,"肚皮");
            int i = ps.executeUpdate();
            System.out.println(i);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.closeResourse(con,ps,rs);
        }
    }
    //通过配置文件获取数据库连接池
    //注意配置文件中必须按照固定格式写,可以使propries文件，也可以是xml文件，必须放到工程目录下
    @Test
    public void test2() throws Exception {
       // ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();//new之后默认加载配置文件
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource("itcast2");//找不到name为itcast的数据库配置项，使用默认配置的数据库
                                                                                                        //警告: named-config with name 'itcast2' does not exist. Using default-config.
        Connection con = null;
        PreparedStatement ps= null;
        ResultSet rs = null;

        con = comboPooledDataSource.getConnection();
        String sql = "insert into category values(?,?)";
        ps= con.prepareStatement(sql);
        ps.setString(1,"c011");
        ps.setString(2,"肚皮");
        int i = ps.executeUpdate();
        System.out.println(i);
        JdbcUtils.closeResourse(con,ps,rs);
    }

}

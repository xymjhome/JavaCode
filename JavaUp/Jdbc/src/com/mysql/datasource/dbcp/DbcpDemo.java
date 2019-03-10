package com.mysql.datasource.dbcp;

import com.mysql.utils.JdbcUtils;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class DbcpDemo {
    //硬编码获取数据库连接池
    @Test
    public void test1(){
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        basicDataSource.setUrl("jdbc:mysql://localhost:3306/day07");
        basicDataSource.setUsername("root");
        basicDataSource.setPassword("root");

        Connection con = null;
        PreparedStatement ps= null;
        ResultSet rs = null;

        try {
            con = basicDataSource.getConnection();
            String sql = "select * from category";
            ps= con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next())
            {
                System.out.println(rs.getString(1) +"...." + rs.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.closeResourse(con,ps,rs);
        }
    }
    //通过配置文件获取数据库连接池
    //注意配置文件中必须按照固定格式写
    @Test
    public void test2() throws Exception {
        Properties prop = new Properties();
        prop.load(new FileInputStream("./src/dbcp.properties"));
        DataSource dataSource = new BasicDataSourceFactory().createDataSource(prop);
        Connection connection = dataSource.getConnection();
        String sql = "update  category set cname = ? where cid = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,"你好啊");
        preparedStatement.setString(2,"c004");

        int i = preparedStatement.executeUpdate();
        System.out.println(i);

        JdbcUtils.closeResourse(connection,preparedStatement,null);

    }

}

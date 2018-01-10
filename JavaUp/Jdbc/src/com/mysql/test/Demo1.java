package com.mysql.test;

import com.mysql.utils.JdbcUtils;
import com.mysql.utils.JdbcUtils1;
import org.junit.Test;

import java.sql.*;

public class Demo1 {

    /**
     * @param
     * @
     */
    @Test
    public void test(){
        System.out.println("hello");
    }

    //mysql-connector-java-5.1.39-bin.jar  数据库连接驱动
    @Test
    public  void test2() throws Exception {
        System.out.println("Test2");
        //注册驱动
        Class.forName("com.mysql.jdbc.Driver");
        //获取连接
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/day07","root","root");
        //编写sql
        String sql = "select * from category";
        //创建语句执行者
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        //执行sql
        ResultSet rs = preparedStatement.executeQuery();
        //处理结果
        while (rs.next()){
            System.out.println(rs.getString("cid")+ "::" +rs.getString("cname"));
        }

        //释放资源
        rs.close();
        preparedStatement.close();
        conn.close();
    }


    /**
     * 插入操作，利用抽象出去的JdbcUtil类
     */
    @Test
    public void insertTest(){
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = JdbcUtils1.getConnection();
            String sql = "insert into category values (?,?);";
            ps = con.prepareStatement(sql);
            ps.setString(1,"c005");
            ps.setString(2,"户外");
            int row = ps.executeUpdate();
            if (row == 1)
            {
                System.out.println("insert sucess");
            }else{
                System.out.println("insert fail");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtils1.closeResourse(con,ps,rs);
        }

    }

    @Test
    public void selectTest(){
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = JdbcUtils.getConnection();
            String sql = "select * from category";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                System.out.println(rs.getString("cid")+"==="+rs.getString("cname"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.closeResourse(con,ps,rs);
        }
    }

    @Test
    public void updateTest(){
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = JdbcUtils.getConnection();
            String sql = "update category set cname = ? where cid = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1,"哈哈");
            ps.setString(2,"c005");

            int row = ps.executeUpdate();
            if (row == 1)
                System.out.println("sucess");
            else{
                System.out.println("fail");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.closeResourse(con,ps,rs);
        }
    }

    @Test
    public void deleteTest() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = JdbcUtils.getConnection();
            String sql = "delete from category where cid = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1,"c005");

            int row = ps.executeUpdate();
            if (row == 1){
                System.out.println("sucess");
            }else{
                System.out.println("fail");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.closeResourse(con,ps,rs);
        }
    }
}

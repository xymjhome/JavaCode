package com.spring.day03.jdbc;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * 测试JDBC的模板类，使用IOC的方式
 * @author Administrator
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class Demo2 {

    @Resource(name = "jdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    /**
     * update(String sql,Object...params)	可以完成增删改操作
     */
    @Test
    public void run1() {
        jdbcTemplate.update("INSERT INTO t_account VALUES (NULL ,?,?)","test2",399999);
    }

    @Test
    public void run2(){
        jdbcTemplate.update("UPDATE t_account set name = ? ,money = ? WHERE id = ?","test1",90000,2);
    }

    /**
     * 删除测试
     */
    @Test
    public void run3(){
        jdbcTemplate.update("DELETE FROM t_account WHERE id = ?",2);
    }

    /**
     * 测试查询：通过主键查询一条记录
     */
    @Test
    public void run4(){
//        Account account = jdbcTemplate.queryForObject("select * from t_account where id = ?", new RowMapper<Account>() {
//            @Override
//            public Account mapRow(ResultSet resultSet, int i) throws SQLException {
//                Account account = new Account();
//                account.setId(resultSet.getInt("id"));
//                account.setName(resultSet.getString("name"));
//                account.setMoney(resultSet.getDouble("money"));
//                return account;
//            }
//        }, 1);

        Account account = jdbcTemplate.queryForObject("select * from t_account where id = ?", new BeanMapper(), 1);
        System.out.println(account);
    }


    /**
     * 查询所有的数据
     */
    @Test
    public void run5(){
        List<Account> query = jdbcTemplate.query("select * from t_account", new BeanMapper());
        System.out.println(query);
    }


}

/**
 * 自己手动的来封装数据（一行一行封装数据）
 * @author Administrator
 */
class BeanMapper implements RowMapper<Account>{
    @Override
    public Account mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Account account = new Account();
        account.setId(resultSet.getInt("id"));
        account.setName(resultSet.getString("name"));
        account.setMoney(resultSet.getDouble("money"));
        return account;
    }
}
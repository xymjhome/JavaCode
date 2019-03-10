package com.spring.day03.transaction.demo1;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class AccountDaoImpl extends JdbcDaoSupport implements AccountDao {

    //继承JdbcDaoSupport类，可以省去对JdbcTemplate的注入,在配置文件中直接注入dataSource就行
//    private JdbcTemplate jdbcTemplate;
//
//    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }

    /**
     * 扣钱
     */
    public void outMoney(String out, double money) {
        this.getJdbcTemplate().update("update t_account set money = money - ? where name = ?", money,out);
    }

    /**
     * 加钱
     */
    public void inMoney(String in, double money) {
        this.getJdbcTemplate().update("update t_account set money = money + ? where name = ?", money,in);
    }

//    private JdbcTemplate getJdbcTemplate() {
//        return jdbcTemplate;
//    }
}

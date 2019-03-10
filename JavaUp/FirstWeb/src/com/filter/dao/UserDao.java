package com.filter.dao;

import com.ajaxDemo.utils.DataSourceUtils;
import com.filter.domain.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Created by Administrator on 2018/1/20 0020.
 */
public class UserDao {//day09  user数据表
    public User findUser(String username, String password) throws SQLException {

        DataSource dataSource = DataSourceUtils.getDataSource();
        QueryRunner queryRunner =  new QueryRunner(dataSource);

        String sql = "select * from user where username = ? and password = ?;";

        return queryRunner.query(sql,new BeanHandler<>(User.class),username,password);

    }
}

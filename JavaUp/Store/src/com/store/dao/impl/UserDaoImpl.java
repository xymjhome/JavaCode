package com.store.dao.impl;

import com.store.dao.UserDao;
import com.store.domain.User;
import com.store.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

public class UserDaoImpl implements UserDao {
    @Override
    public void add(User user) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "insert into user values (?,?,?,?,?,?,?,?,?,?)";
        queryRunner.update(sql,user.getUid(),user.getUsername(),user.getPassword(),user.getName(),
                user.getEmail(),user.getTelephone(),user.getBirthday(),user.getSex(),user.getState(),user.getCode());
    }

    @Override
    public User getUserByCode(String code) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from user where code = ? limit 1";
        User query = queryRunner.query(sql, new BeanHandler<>(User.class), code);
        return query;
    }

    @Override
    public void update(User user) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql="update user set username = ?,password = ? ,name=?,email=?,birthday = ?,state = ?,code=? where uid =? ";
        queryRunner.update(sql, user.getUsername(),user.getPassword(),user.getName(),user.getEmail(),user.getBirthday(),
                user.getState(),null,user.getUid());
    }

    @Override
    public User getUserByNameAndPwd(String username, String pwd) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql="select * from user where username = ? and password = ? limit 1";
        User query = queryRunner.query(sql, new BeanHandler<>(User.class), username,pwd);
        return query;
    }
}

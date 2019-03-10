package com.projectcase.dao;

import com.projectcase.domain.User;
import com.projectcase.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

//操作数据库User表的类
public class UserDao {

    public User getUserByNameAndPassword(String username, String inputPassword) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
        System.out.println("select pre username = "+username+" and inputPassword" + inputPassword);
        String sql = "select * from user where username = ? and password = ?";
        User query = queryRunner.query(sql, new BeanHandler<User>(User.class), username, inputPassword);

        return query;
    }

    public int addUser(User user) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "insert into user(username,password,email,name,sex,birthday,hobby) values(?,?,?,?,?,?,?);";
        String tmp = "insert into user(username,password,email,name,sex,birthday,hobby) values("+user.getUsername()+
                ","+user.getPassword()+","+user.getEmail()+","+user.getName()+","+user.getSex()+","+user.getBirthday()+","+user.getHobby()+");";
        System.out.println(tmp);
        int row = queryRunner.update(sql,user.getUsername(),user.getPassword(),user.getEmail(),user.getName(),
                user.getSex(),user.getBirthday(),user.getHobby());
        return row;
    }
}

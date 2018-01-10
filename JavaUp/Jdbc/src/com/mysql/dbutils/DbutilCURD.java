package com.mysql.dbutils;

import com.mysql.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.junit.Test;

import java.sql.SQLException;

public class DbutilCURD {
    @Test
    public void insert() throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "insert into category values (?,?);";
        int update = queryRunner.update(sql, "c012", "数据看");
        System.out.println(update);
    }

    @Test
    public void update() throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "update category set cname = ? where cid = ?;";
        int update = queryRunner.update(sql, "德玛西亚",  "c012");
        System.out.println(update);
    }
}

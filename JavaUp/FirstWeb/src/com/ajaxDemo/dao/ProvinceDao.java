package com.ajaxDemo.dao;

import com.ajaxDemo.domain.Province;
import com.ajaxDemo.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Administrator on 2018/1/20 0020.
 */
public class ProvinceDao {
    public List<Province> findAll() throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "Select * from province;";

        return queryRunner.query(sql,new BeanListHandler<>(Province.class));
    }

}

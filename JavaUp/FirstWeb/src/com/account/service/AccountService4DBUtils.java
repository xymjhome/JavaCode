package com.account.service;

import com.account.dao.AccountDao;
import com.account.dao.AccountDao4DBUtils;
import com.account.utils.DataSourceUtils;

import java.sql.SQLException;

public class AccountService4DBUtils {
    public void account(String fromuser, String touser, String money) throws SQLException {
        AccountDao4DBUtils accountDao = new AccountDao4DBUtils();

        try {
            DataSourceUtils.startTransaction();
            //转出
            accountDao.accountOut(fromuser,money);

            int i = 1/0;//异常测试

            //转入
            accountDao.accountIn(touser,money);

            DataSourceUtils.commitAndCloseConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            DataSourceUtils.rollbackAndCloseConnection();
            throw e;
        }

    }
}

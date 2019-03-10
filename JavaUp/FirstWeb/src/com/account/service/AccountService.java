package com.account.service;

import com.account.dao.AccountDao;
import com.account.utils.DataSourceUtils;

import java.sql.SQLException;

public class AccountService {
    public void account(String fromuser, String touser, String money) throws SQLException {
        AccountDao accountDao = new AccountDao();

        try {
            DataSourceUtils.startTransaction();
            //转出
            accountDao.accountOut(fromuser,money);

            //int i = 1/0;//异常测试

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

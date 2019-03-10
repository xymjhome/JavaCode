package com.crm.service;

import com.crm.domain.User;

public interface UserService {

    User checkCode(String userCode);

    void save(User user);

    User login(User user);

}

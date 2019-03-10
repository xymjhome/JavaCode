package com.store.service;

import com.store.domain.Category;

import java.sql.SQLException;
import java.util.List;

public interface CategoryService {

    List<Category> findAll() throws SQLException;

    void add(Category category) throws Exception;

    Category getById(String cid)throws Exception;

    void update(Category category) throws Exception;

    void delete(String cid) throws Exception;
}

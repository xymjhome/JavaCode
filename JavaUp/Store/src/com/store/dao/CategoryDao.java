package com.store.dao;

import com.store.domain.Category;

import java.sql.SQLException;
import java.util.List;

public interface CategoryDao {
    List<Category> findAllCategory() throws SQLException;

    void add(Category category)throws SQLException;

    Category getById(String cid) throws SQLException;

    void update(Category category) throws SQLException;

    void delete(String cid) throws SQLException;
}

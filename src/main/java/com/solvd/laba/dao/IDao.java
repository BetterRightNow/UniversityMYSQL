package com.solvd.laba.dao;

import com.solvd.laba.model.Address;

import java.sql.SQLException;

public interface IDao<T> {
    void create(T t) throws SQLException, InterruptedException;
    void delete(Integer id) throws SQLException;
    void update(T t) throws SQLException;
    T getById(Integer id) throws SQLException;
}

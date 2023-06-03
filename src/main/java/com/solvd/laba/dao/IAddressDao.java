package com.solvd.laba.dao;

import com.solvd.laba.model.Address;

import java.sql.SQLException;

public interface IAddressDao {
    void save(Address address) throws SQLException, InterruptedException;
}

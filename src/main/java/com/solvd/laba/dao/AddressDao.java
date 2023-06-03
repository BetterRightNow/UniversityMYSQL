package com.solvd.laba.dao;

import com.solvd.laba.connectionPool.ConnectionPool;
import com.solvd.laba.model.Address;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddressDao implements IAddressDao{
    Logger addressLogger = LogManager.getLogger();
    private ConnectionPool connectionPool;

    public AddressDao() throws SQLException {

    }

    public AddressDao(ConnectionPool connectionPool) throws SQLException {
        this.connectionPool = connectionPool;
    }

    @Override
    public void save(Address address) throws SQLException, InterruptedException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO address (country, city, street, building) VALUES (?, ?, ?, ?)");
            statement.setString(1, address.getCountry());
            statement.setString(2, address.getCity());
            statement.setString(3, address.getStreet());
            statement.setInt(4, address.getBuilding());
            statement.executeUpdate();
        } catch (SQLException | InterruptedException e) {
            addressLogger.error(e);
        } finally {
            if (connection != null) {
                connectionPool.releaseConnection(connection);
            }
        }
    }
}

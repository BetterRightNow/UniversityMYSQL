package com.solvd.laba.dao;

import com.solvd.laba.connectionPool.ConnectionPool;
import com.solvd.laba.model.Address;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddressDao implements IDao<Address> {
    Logger addressLogger = LogManager.getLogger();
    private ConnectionPool connectionPool;

    public AddressDao() throws SQLException {

    }

    public AddressDao(ConnectionPool connectionPool) throws SQLException {
        this.connectionPool = connectionPool;
    }

    @Override
    public void create(Address address) throws SQLException, InterruptedException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO address (id, country, city, street, building) VALUES (?, ?, ?, ?, ?)");
            statement.setInt(1, address.getId());
            statement.setString(2, address.getCountry());
            statement.setString(3, address.getCity());
            statement.setString(4, address.getStreet());
            statement.setInt(5, address.getBuilding());
            statement.executeUpdate();
        } catch (SQLException | InterruptedException e) {
            addressLogger.error(e);
        } finally {
            if (connection != null) {
                connectionPool.releaseConnection(connection);
            }
        }
    }

    @Override
    public void delete(Integer addressId) throws SQLException {
        Connection connection = null;
        try{
            connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement("DELETE FROM address WHERE id = ?");
            statement.setInt(1, addressId);
            statement.executeUpdate();
        } catch (SQLException | InterruptedException e) {
            addressLogger.error(e);
        } finally {
            if (connection != null) {
                connectionPool.releaseConnection(connection);
            }
        }
    }

    @Override
    public void update(Address address) throws SQLException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement("UPDATE address SET country = ?, city = ?, street = ?, building = ? WHERE id = ?");
            statement.setString(1, address.getCountry());
            statement.setString(2, address.getCity());
            statement.setString(3, address.getStreet());
            statement.setInt(4, address.getBuilding());
            statement.setInt(5, address.getId());
            statement.executeUpdate();
        } catch (SQLException | InterruptedException e) {
            addressLogger.error(e);
        } finally {
            if (connection != null) {
                connectionPool.releaseConnection(connection);
            }
        }
    }

    @Override
    public Address getById(Integer addressId) throws SQLException {
        Address address = null;
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM address WHERE id = ?");
            statement.setInt(1, addressId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                address = new Address(
                        resultSet.getInt("id"),
                        resultSet.getString("country"),
                        resultSet.getString("city"),
                        resultSet.getString("street"),
                        resultSet.getInt("building")
                );
            }
        } catch (SQLException | InterruptedException e) {
            addressLogger.error(e);
        } finally {
            if (connection != null) {
                connectionPool.releaseConnection(connection);
            }
        }
        return address;
    }
}

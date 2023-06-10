package com.solvd.laba.dao;

import com.solvd.laba.connectionPool.ConnectionPool;
import com.solvd.laba.model.DateOfBirth;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class DateOfBirthDao implements IDao<DateOfBirth>{
    Logger dateLogger = LogManager.getLogger();
    private ConnectionPool connectionPool;

    public DateOfBirthDao() {}

    public DateOfBirthDao(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public void create(DateOfBirth dateOfBirth) throws SQLException, InterruptedException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO dateOfBirth (id, dateOfBirth) VALUES (?, ?)");
            statement.setInt(1, dateOfBirth.getId());
            statement.setString(2, dateOfBirth.getDateOfBirth().toString());
            statement.executeUpdate();
        } catch (SQLException | InterruptedException e) {
            dateLogger.error(e);
        } finally {
            if (connection != null) {
                connectionPool.releaseConnection(connection);
            }
        }
    }

    @Override
    public void delete(Integer dateId) throws SQLException {
        Connection connection = null;
        try{
            connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement("DELETE FROM DateOfBirth WHERE id = ?");
            statement.setInt(1, dateId);
            statement.executeUpdate();
        } catch (SQLException | InterruptedException e) {
            dateLogger.error(e);
        } finally {
            if (connection != null) {
                connectionPool.releaseConnection(connection);
            }
        }
    }

    @Override
    public void update(DateOfBirth dateOfBirth) throws SQLException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE dateOfBirth SET dateOfBirth = ? WHERE id = ?");
            statement.setString(1, dateOfBirth.getDateOfBirth().toString());
            statement.setInt(2, dateOfBirth.getId());
            statement.executeUpdate();
        } catch (SQLException | InterruptedException e) {
            dateLogger.error(e);
        } finally {
            if (connection != null) {
                connectionPool.releaseConnection(connection);
            }
        }
    }

    @Override
    public DateOfBirth getById(Integer dateId) throws SQLException {
        DateOfBirth dateOfBirth = null;
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM dateOfBirth WHERE id = ?");
            statement.setInt(1, dateId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                dateOfBirth = new DateOfBirth(
                        resultSet.getInt("id"),
                        resultSet.getDate("dateOfBirth")
                );
            }
        } catch (SQLException | InterruptedException e) {
            dateLogger.error(e);
        } finally {
            if (connection != null) {
                connectionPool.releaseConnection(connection);
            }
        }
        return dateOfBirth;
    }
}

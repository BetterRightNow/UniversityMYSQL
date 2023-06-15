package com.solvd.laba.dao;

import com.solvd.laba.connectionPool.ConnectionPool;
import com.solvd.laba.model.Faculties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class facultyDao implements IDao<Faculties> {
    Logger facultyLogger = LogManager.getLogger();
    private ConnectionPool connectionPool;

    public facultyDao() {}

    public facultyDao(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public void create(Faculties faculties) throws SQLException, InterruptedException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO faculties (id, facultyName, emailId) VALUES (?, ?, ?)");
            statement.setInt(1, faculties.getId());
            statement.setString(2, faculties.getFacultyName());
            statement.setInt(3, faculties.getEmailId());
            statement.executeUpdate();
        } catch (SQLException | InterruptedException e) {
            facultyLogger.error(e);
        } finally {
            if (connection != null) {
                connectionPool.releaseConnection(connection);
            }
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement("DELETE FROM faculties WHERE id = ?");
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException | InterruptedException e) {
            facultyLogger.error(e);
        } finally {
            if (connection != null) {
                connectionPool.releaseConnection(connection);
            }
        }
    }

    @Override
    public void update(Faculties faculties) throws SQLException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement("UPDATE faculties SET facultyName = ?, emailId = ? WHERE id = ?");
            statement.setString(1, faculties.getFacultyName());
            statement.setInt(2, faculties.getEmailId());
            statement.setInt(3, faculties.getId());
            statement.executeUpdate();
        } catch (SQLException | InterruptedException e) {
            facultyLogger.error(e);
        } finally {
            if (connection != null) {
                connectionPool.releaseConnection(connection);
            }
        }
    }

    @Override
    public Faculties getById(Integer id) throws SQLException {
        Faculties faculties = null;
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM faculties WHERE id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                faculties = new Faculties(
                        resultSet.getInt("id"),
                        resultSet.getString("facultyName"),
                        resultSet.getInt("emailId")
                );
            }
        } catch (SQLException | InterruptedException e) {
            facultyLogger.error(e);
        } finally {
            if (connection != null) {
                connectionPool.releaseConnection(connection);
            }
        }
        return faculties;
    }
}

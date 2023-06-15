package com.solvd.laba.dao;

import com.solvd.laba.connectionPool.ConnectionPool;
import com.solvd.laba.model.Address;
import com.solvd.laba.model.StudentSchedules;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentSchedulesDao implements IDao<StudentSchedules>{
    Logger schedulesLogger = LogManager.getLogger();
    private ConnectionPool connectionPool;

    public StudentSchedulesDao() {}

    public StudentSchedulesDao(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }


    @Override
    public void create(StudentSchedules studentSchedules) throws SQLException, InterruptedException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO studentSchedules (id, dayOfWeek, room) VALUES (?, ?, ?)");
            statement.setInt(1, studentSchedules.getId());
            statement.setString(2, studentSchedules.getDayOfWeek());
            statement.setInt(3, studentSchedules.getRoom());
            statement.executeUpdate();
        } catch (SQLException | InterruptedException e) {
            schedulesLogger.error(e);
        } finally {
            if (connection != null) {
                connectionPool.releaseConnection(connection);
            }
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {
        Connection connection = null;
        try{
            connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement("DELETE FROM studentSchedules WHERE id = ?");
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException | InterruptedException e) {
            schedulesLogger.error(e);
        } finally {
            if (connection != null) {
                connectionPool.releaseConnection(connection);
            }
        }
    }

    @Override
    public void update(StudentSchedules studentSchedules) throws SQLException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE studentSchedules SET dayOfWeek= ?, room = ? WHERE id = ?");
            statement.setString(1, studentSchedules.getDayOfWeek());
            statement.setInt(2, studentSchedules.getRoom());
            statement.setInt(3, studentSchedules.getId());
            statement.executeUpdate();
        } catch (SQLException | InterruptedException e) {
            schedulesLogger.error(e);
        } finally {
            if (connection != null) {
                connectionPool.releaseConnection(connection);
            }
        }
    }

    @Override
    public StudentSchedules getById(Integer id) throws SQLException {
        StudentSchedules studentSchedules = null;
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM studentSchedules WHERE id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                studentSchedules = new StudentSchedules(
                        resultSet.getInt("id"),
                        resultSet.getString("dayOfWeek"),
                        resultSet.getInt("room")
                );
            }
        } catch (SQLException | InterruptedException e) {
            schedulesLogger.error(e);
        } finally {
            if (connection != null) {
                connectionPool.releaseConnection(connection);
            }
        }
        return studentSchedules;
    }
}

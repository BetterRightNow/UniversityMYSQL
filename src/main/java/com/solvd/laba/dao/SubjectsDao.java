package com.solvd.laba.dao;

import com.solvd.laba.connectionPool.ConnectionPool;
import com.solvd.laba.model.Address;
import com.solvd.laba.model.Subjects;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SubjectsDao implements IDao<Subjects> {
    Logger subjectsLogger = LogManager.getLogger();
    private ConnectionPool connectionPool;

    public SubjectsDao() {}

    public SubjectsDao(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public void create(Subjects subjects) throws SQLException, InterruptedException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO subjects (id, subjectName, coursesId, studentSchedulesId) VALUES (?, ?, ?, ?)");
            statement.setInt(1, subjects.getId());
            statement.setString(2, subjects.getSubjectName());
            statement.setInt(3, subjects.getCoursesId());
            statement.setInt(4, subjects.getStudentSchedulesId());
            statement.executeUpdate();
        } catch (SQLException | InterruptedException e) {
            subjectsLogger.error(e);
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
            PreparedStatement statement = connection.prepareStatement("DELETE FROM subjects WHERE id = ?");
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException | InterruptedException e) {
            subjectsLogger.error(e);
        } finally {
            if (connection != null) {
                connectionPool.releaseConnection(connection);
            }
        }
    }

    @Override
    public void update(Subjects subjects) throws SQLException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE subjects SET subjectName = ?, coursesId = ?, studentSchedulesId = ? WHERE id = ?");
            statement.setString(1, subjects.getSubjectName());
            statement.setInt(2, subjects.getCoursesId());
            statement.setInt(3, subjects.getStudentSchedulesId());
            statement.setInt(4, subjects.getId());
            statement.executeUpdate();
        } catch (SQLException | InterruptedException e) {
            subjectsLogger.error(e);
        } finally {
            if (connection != null) {
                connectionPool.releaseConnection(connection);
            }
        }
    }

    @Override
    public Subjects getById(Integer id) throws SQLException {
        Subjects subjects = null;
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM subjects WHERE id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                subjects = new Subjects(
                        resultSet.getInt("id"),
                        resultSet.getString("subjectName"),
                        resultSet.getInt("coursesId"),
                        resultSet.getInt("studentSchedulesId")
                );
            }
        } catch (SQLException | InterruptedException e) {
            subjectsLogger.error(e);
        } finally {
            if (connection != null) {
                connectionPool.releaseConnection(connection);
            }
        }
        return subjects;
    }
}

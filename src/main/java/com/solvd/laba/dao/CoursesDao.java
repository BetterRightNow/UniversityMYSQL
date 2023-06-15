package com.solvd.laba.dao;

import com.solvd.laba.connectionPool.ConnectionPool;
import com.solvd.laba.model.Address;
import com.solvd.laba.model.Courses;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CoursesDao implements IDao<Courses> {
    Logger coursesLogger = LogManager.getLogger();
    private ConnectionPool connectionPool;

    public CoursesDao() {}

    public CoursesDao(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public void create(Courses courses) throws SQLException, InterruptedException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO courses (id, courseName, facultiesId) VALUES (?, ?, ?)");
            statement.setInt(1, courses.getId());
            statement.setString(2, courses.getCourseName());
            statement.setInt(3, courses.getFacultiesId());
            statement.executeUpdate();
        } catch (SQLException | InterruptedException e) {
            coursesLogger.error(e);
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
            PreparedStatement statement = connection.prepareStatement("DELETE FROM courses WHERE id = ?");
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException | InterruptedException e) {
            coursesLogger.error(e);
        } finally {
            if (connection != null) {
                connectionPool.releaseConnection(connection);
            }
        }
    }

    @Override
    public void update(Courses courses) throws SQLException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE courses SET courseName = ?, facultiesId = ? WHERE id = ?");
            statement.setString(1, courses.getCourseName());
            statement.setInt(2, courses.getFacultiesId());
            statement.setInt(3, courses.getId());
            statement.executeUpdate();
        } catch (SQLException | InterruptedException e) {
            coursesLogger.error(e);
        } finally {
            if (connection != null) {
                connectionPool.releaseConnection(connection);
            }
        }
    }

    @Override
    public Courses getById(Integer id) throws SQLException {
        Courses courses = null;
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM courses WHERE id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                courses = new Courses(
                        resultSet.getInt("id"),
                        resultSet.getString("courseName"),
                        resultSet.getInt("facultiesId")
                );
            }
        } catch (SQLException | InterruptedException e) {
            coursesLogger.error(e);
        } finally {
            if (connection != null) {
                connectionPool.releaseConnection(connection);
            }
        }
        return courses;
    }
}

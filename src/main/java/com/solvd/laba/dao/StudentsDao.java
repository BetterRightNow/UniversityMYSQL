package com.solvd.laba.dao;

import com.solvd.laba.connectionPool.ConnectionPool;
import com.solvd.laba.model.Address;
import com.solvd.laba.model.Students;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentsDao implements IDao<Students> {
    Logger studentsLogger = LogManager.getLogger();
    private ConnectionPool connectionPool;

    public StudentsDao() {}

    public StudentsDao(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public void create(Students students) throws SQLException, InterruptedException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO students (id, firstName, lastName, dateOfBirthId, addressId, " +
                            "facultiesId, emailId, studentSchedulesId) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            statement.setInt(1, students.getId());
            statement.setString(2, students.getFirstName());
            statement.setString(3, students.getLastName());
            statement.setInt(4, students.getDateOfBirthId());
            statement.setInt(5, students.getAddressId());
            statement.setInt(6, students.getFacultiesId());
            statement.setInt(7, students.getEmailId());
            statement.setInt(8, students.getStudentSchedulesId());
            statement.executeUpdate();
        } catch (SQLException | InterruptedException e) {
            studentsLogger.error(e);
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
            PreparedStatement statement = connection.prepareStatement("DELETE FROM students WHERE id = ?");
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException | InterruptedException e) {
            studentsLogger.error(e);
        } finally {
            if (connection != null) {
                connectionPool.releaseConnection(connection);
            }
        }
    }

    @Override
    public void update(Students students) throws SQLException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE students SET firstName = ?, lastName = ?, dateOfBirthId = ?, " +
                            "addressId= ?, facultiesId = ?, emailId = ?, studentSchedulesId = ? WHERE id = ?");
            statement.setString(1, students.getFirstName());
            statement.setString(2, students.getLastName());
            statement.setInt(3, students.getDateOfBirthId());
            statement.setInt(4, students.getAddressId());
            statement.setInt(5, students.getFacultiesId());
            statement.setInt(6, students.getEmailId());
            statement.setInt(7, students.getStudentSchedulesId());
            statement.setInt(8, students.getId());
            statement.executeUpdate();
        } catch (SQLException | InterruptedException e) {
            studentsLogger.error(e);
        } finally {
            if (connection != null) {
                connectionPool.releaseConnection(connection);
            }
        }
    }

    @Override
    public Students getById(Integer id) throws SQLException {
        Students students = null;
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM students WHERE id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                students = new Students(
                        resultSet.getInt("id"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getInt("dateOfBirthId"),
                        resultSet.getInt("addressId"),
                        resultSet.getInt("facultiesId"),
                        resultSet.getInt("emailId"),
                        resultSet.getInt("studentSchedulesId")
                );
            }
        } catch (SQLException | InterruptedException e) {
            studentsLogger.error(e);
        } finally {
            if (connection != null) {
                connectionPool.releaseConnection(connection);
            }
        }
        return students;
    }
}

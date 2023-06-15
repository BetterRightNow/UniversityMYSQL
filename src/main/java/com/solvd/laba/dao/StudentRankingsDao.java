package com.solvd.laba.dao;

import com.solvd.laba.connectionPool.ConnectionPool;
import com.solvd.laba.model.DateOfBirth;
import com.solvd.laba.model.StudentRankings;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentRankingsDao implements IDao<StudentRankings> {
    Logger rankingsLogger = LogManager.getLogger();
    private ConnectionPool connectionPool;

    public StudentRankingsDao() {}

    public StudentRankingsDao(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public void create(StudentRankings studentRankings) throws SQLException, InterruptedException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO studentRankings (id, ranking, rankingDate, studentsId) VALUES (?, ?, ?, ?)");
            statement.setInt(1, studentRankings.getId());
            statement.setInt(2, studentRankings.getRanking());
            statement.setString(3, studentRankings.getRankingDate().toString());
            statement.setInt(4, studentRankings.getStudentsId());
            statement.executeUpdate();
        } catch (SQLException | InterruptedException e) {
            rankingsLogger.error(e);
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
            PreparedStatement statement = connection.prepareStatement("DELETE FROM studentRankings WHERE id = ?");
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException | InterruptedException e) {
            rankingsLogger.error(e);
        } finally {
            if (connection != null) {
                connectionPool.releaseConnection(connection);
            }
        }
    }

    @Override
    public void update(StudentRankings studentRankings) throws SQLException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE studentRankings SET ranking = ?, rankingDate = ?, studentsId = ? WHERE id = ?");
            statement.setInt(1, studentRankings.getRanking());
            statement.setString(2, studentRankings.getRankingDate().toString());
            statement.setInt(3, studentRankings.getStudentsId());
            statement.setInt(4, studentRankings.getId());
            statement.executeUpdate();
        } catch (SQLException | InterruptedException e) {
            rankingsLogger.error(e);
        } finally {
            if (connection != null) {
                connectionPool.releaseConnection(connection);
            }
        }
    }

    @Override
    public StudentRankings getById(Integer id) throws SQLException {
        StudentRankings studentRankings = null;
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM studentRankings WHERE id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                studentRankings = new StudentRankings(
                        resultSet.getInt("id"),
                        resultSet.getInt("ranking"),
                        resultSet.getDate("rankingDate"),
                        resultSet.getInt("studentsId")
                );
            }
        } catch (SQLException | InterruptedException e) {
            rankingsLogger.error(e);
        } finally {
            if (connection != null) {
                connectionPool.releaseConnection(connection);
            }
        }
        return studentRankings;
    }
}

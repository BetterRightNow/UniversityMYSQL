package com.solvd.laba.connectionPool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;

public class ConnectionPool {
    private BlockingQueue<Connection> connections; // Queue to hold the available connections
    private Semaphore semaphore; // Semaphore to control access to connections
    private int poolSize; // Maximum number of connections in the pool
    private static final String URL = "jdbc:mysql://localhost:3306/UniversityJava";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "P6u#jDWk6b6Msz#7";

    private static class ConnectionPoolHolder {
        static final ConnectionPool INSTANCE = new ConnectionPool();
    }

    private ConnectionPool() {
        // Private constructor for lazy initialization
    }

    private ConnectionPool(int poolSize) throws SQLException {
        this.poolSize = poolSize;
        this.connections = new ArrayBlockingQueue<>(poolSize); // Initialize the blocking queue with the specified pool size
        semaphore = new Semaphore(poolSize); // Initialize the semaphore with the specified pool size
        for (int i = 0; i < poolSize; i++) {
            connections.add(DriverManager.getConnection(URL, USERNAME, PASSWORD)); // Add connections to the queue
        }
    }

    public static ConnectionPool getInstance() {
        return ConnectionPoolHolder.INSTANCE; // Return the instance of the ConnectionPool
    }

    public Connection getConnection() throws InterruptedException, SQLException {
        semaphore.acquire(); // Acquire a permit from the semaphore
        Connection connection = connections.poll(); // Get a connection from the queue
        if (connection == null) {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD); // Create a new connection if the queue is empty
        }
        return connection;
    }

    public void releaseConnection(Connection connection) throws SQLException {
        connection.close(); // Add the released connection back to the queue
        semaphore.release(); // Release a permit in the semaphore
    }

    public static ConnectionPool createInstance(int poolSize) throws SQLException {
        return new ConnectionPool(poolSize); // Create a new instance of ConnectionPool with the specified pool size
    }
}
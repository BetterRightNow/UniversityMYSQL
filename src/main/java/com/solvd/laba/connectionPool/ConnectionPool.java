package com.solvd.laba.connectionPool;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;

import static org.apache.logging.log4j.core.net.UrlConnectionFactory.createConnection;

public class ConnectionPool {
    private BlockingQueue<Connection> connections; // Queue to hold the available connections
    private Semaphore semaphore; // Semaphore to control access to connections
    private int poolSize; // Maximum number of connections in the pool
    private static final String PROPERTY_FILE_PATH = "src/main/resources/connection.properties";

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
            connections.add(createConnection()); // Add connections to the queue
        }
    }

    private Connection createConnection() throws SQLException {
        try (FileInputStream fis = new FileInputStream(PROPERTY_FILE_PATH)) {
            Properties properties = new Properties();
            properties.load(fis);

            String url = properties.getProperty("db.url");
            String username = properties.getProperty("db.username");
            String password = properties.getProperty("db.password");

            return DriverManager.getConnection(url, username, password);
        } catch (IOException e) {
            throw new SQLException("Failed to load connection properties: " + e.getMessage());
        }
    }

    public static ConnectionPool getInstance() {
        return ConnectionPoolHolder.INSTANCE; // Return the instance of the ConnectionPool
    }

    public Connection getConnection() throws InterruptedException, SQLException {
        semaphore.acquire(); // Acquire a permit from the semaphore
        Connection connection = connections.poll(); // Get a connection from the queue
        if (connection == null) {
            connection = createConnection(); // Create a new connection if the queue is empty
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
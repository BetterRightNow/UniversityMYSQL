package com.solvd.laba.service;

import com.solvd.laba.connectionPool.ConnectionPool;
import com.solvd.laba.dao.AddressDao;
import com.solvd.laba.model.Address;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Service {
    public static void main(String[] args) throws SQLException, InterruptedException {
        Logger logger = LogManager.getLogger();
        ConnectionPool connectionPool = ConnectionPool.createInstance(5);
        AddressDao addressDao = new AddressDao(connectionPool);
        int numTasks = 7;
        ExecutorService executorService = Executors.newFixedThreadPool(numTasks);
        for (int i = 0; i < numTasks; i++) {
            final int taskId = i;
            executorService.execute(() -> {
                try {
                    Address address = new Address("Poland", "Warsaw", "Andersa", taskId);
                    addressDao.save(address);
                    logger.info("Task " + taskId + " - Address saved successfully.");
                } catch (SQLException | InterruptedException e) {
                    logger.error("Error occurred while saving address in task " + taskId + ": " + e.getMessage());
                }
            });
        }
        executorService.shutdown();
    }
}



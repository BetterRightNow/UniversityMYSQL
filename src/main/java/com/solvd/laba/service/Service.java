package com.solvd.laba.service;

import com.solvd.laba.connectionPool.ConnectionPool;
import com.solvd.laba.dao.AddressDao;
import com.solvd.laba.dao.DateOfBirthDao;
import com.solvd.laba.dao.EmailDao;
import com.solvd.laba.model.Address;
import com.solvd.laba.model.DateOfBirth;
import com.solvd.laba.model.Email;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Service {
    public static void main(String[] args) throws SQLException {
        Logger logger = LogManager.getLogger();
        ConnectionPool connectionPool = ConnectionPool.createInstance(5);
        AddressDao addressDao = new AddressDao(connectionPool);
        DateOfBirthDao dateOfBirthDao = new DateOfBirthDao(connectionPool);
        EmailDao emailDao = new EmailDao(connectionPool);
        int numTasks = 7;
        ExecutorService executorService = Executors.newFixedThreadPool(numTasks);
        executorService.execute(() -> {
            try {
                Address address = new Address(1, "Poland", "Warsaw", "Andersa", 1);
                addressDao.create(address);
                logger.info("Task " + 1 + " - Address created successfully.");

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date utilDateOfBirth = dateFormat.parse("1990-08-22");
                Date sqlDateOfBirth = new Date(utilDateOfBirth.getTime());
                DateOfBirth dateOfBirth = new DateOfBirth(1, sqlDateOfBirth);
                dateOfBirthDao.create(dateOfBirth);
                logger.info("Task " + 2 + " - Date of birth created successfully.");

                Email email1 = new Email(1, "alex@gmail.com");
                emailDao.create(email1);
                logger.info("Task " + 3 + " - Email created successfully.");

                Email email2 = new Email(2, "informatics@university.com");
                emailDao.create(email2);
                logger.info("Task " + 4 + " - Email created successfully.");

                addressDao.delete(1);
                logger.info("Task " + 5 + " - Address deleted successfully.");
                dateOfBirthDao.delete(1);
                logger.info("Task " + 6 + " - Date of birth deleted successfully.");
                emailDao.delete(1);
                logger.info("Task " + 7 + " - Email deleted successfully.");
                emailDao.delete(2);
                logger.info("Task " + 8 + " - Email deleted successfully.");

                addressDao.create(address);
                logger.info("Task " + 9 + " - Address created successfully.");

                dateOfBirthDao.create(dateOfBirth);
                logger.info("Task " + 10 + " - Date of birth created successfully.");

                emailDao.create(email1);
                logger.info("Task " + 11 + " - Email created successfully.");

                emailDao.create(email2);
                logger.info("Task " + 12 + " - Email created successfully.");

                Address updatedAddress = new Address(
                        1, "Poland", "Warsaw", "Pilsudskiego", 1);
                addressDao.update(updatedAddress);
                logger.info("Task " + 13 + " - Address updated successfully.");

                java.util.Date utilUpdatedDateOfBirth = dateFormat.parse("1990-08-31");
                Date sqlUpdatedDateOfBirth = new Date(utilUpdatedDateOfBirth.getTime());
                DateOfBirth updatedDateOfBirth = new DateOfBirth(1, sqlUpdatedDateOfBirth);
                dateOfBirthDao.update(updatedDateOfBirth);
                logger.info("Task " + 14 + " - Date of birth updated successfully.");

                Email email3 = new Email(1, "alex90@gmail.com");
                emailDao.update(email3);
                logger.info("Task " + 15 + " - Email updated successfully.");

                Email email4 = new Email(2, "informF@university.com");
                emailDao.update(email4);
                logger.info("Task " + 116 + " - Email updated successfully.");


            } catch (SQLException | InterruptedException | ParseException e) {
                logger.error("Error occurred while working with database " + e.getMessage());
            }
        });
        executorService.shutdown();
    }
}



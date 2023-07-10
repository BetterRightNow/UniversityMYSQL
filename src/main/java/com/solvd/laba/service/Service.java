package com.solvd.laba.service;

import com.solvd.laba.connectionPool.ConnectionPool;
import com.solvd.laba.dao.*;
import com.solvd.laba.model.*;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import mappers.IEmailMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;


public class Service {
    public static void main(String[] args) throws SQLException {
        Logger logger = LogManager.getLogger();
        ConnectionPool connectionPool = ConnectionPool.createInstance(5);
        AddressDao addressDao = new AddressDao(connectionPool);
        DateOfBirthDao dateOfBirthDao = new DateOfBirthDao(connectionPool);
        EmailDao emailDao = new EmailDao(connectionPool);
        FacultyDao facultyDao = new FacultyDao(connectionPool);
        StudentSchedulesDao studentSchedulesDao = new StudentSchedulesDao(connectionPool);
        StudentsDao studentsDao = new StudentsDao(connectionPool);
        StudentRankingsDao studentRankingsDao = new StudentRankingsDao(connectionPool);
        CoursesDao coursesDao = new CoursesDao(connectionPool);
        SubjectsDao subjectsDao = new SubjectsDao(connectionPool);
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
                logger.info("Task " + 16 + " - Email updated successfully.");

                Faculties faculty1 = new Faculties(1, "Informatics", 2);
                facultyDao.create(faculty1);
                logger.info("Task " + 17 + " - Faculty created successfully.");

                StudentSchedules studentSchedule = new StudentSchedules(1, "Monday", 303);
                studentSchedulesDao.create(studentSchedule);
                logger.info("Task " + 18 + " - Student schedule created successfully.");

                Students student1 = new Students(1, "Alex", "Nazarevich",
                        1, 1, 1, 1, 1);
                studentsDao.create(student1);
                logger.info("Task " + 19 + " - Student created successfully.");

                java.util.Date utilRankingDate = dateFormat.parse("2023-04-07");
                Date sqlRankingDate = new Date(utilRankingDate.getTime());
                StudentRankings studentRanking1 = new StudentRankings(1, 1, sqlRankingDate, 1);
                studentRankingsDao.create(studentRanking1);
                logger.info("Task " + 20 + " - Student ranking created successfully.");

                Courses course1 = new Courses(1, "Test automation", 1);
                coursesDao.create(course1);
                logger.info("Task " + 21 + " - Course created successfully.");

                Subjects subject1 = new Subjects(1, "Java", 1, 1);
                subjectsDao.create(subject1);
                logger.info("Task " + 22 + " - Subject created successfully.");

                logger.info("\n");
                logger.info("running returnStudentsInfo joining all tables\n");
                studentsDao.returnStudentsInfo(1);

                logger.info("\n");
                logger.info("running SAX parser\n");
                try {
                    SAXParserFactory factory = SAXParserFactory.newInstance();
                    factory.setValidating(true);
                    factory.setNamespaceAware(true);
                    SAXParser parser = factory.newSAXParser();

                    XMLHandler handler = new XMLHandler();
                    parser.parse("src/main/resources/university.xml", handler);

                    String xmlPath = "src/main/resources/university.xml";
                    String xsdPath = "src/main/resources/schema.xsd";
                    XMLValidator.validate(xmlPath, xsdPath);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                logger.info("\n");
                logger.info("Working with Jaxb\n");
                try{
                    File xmlFile = new File("src/main/resources/university.xml");

                    JAXBContext jaxbContext = JAXBContext.newInstance(Root.class);
                    Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
                    Root root = (Root) unmarshaller.unmarshal(xmlFile);

                    // Access the parsed objects and perform desired operations

                    List<Address> addressList = root.getAddressList();
                    List<Email> emailList = root.getEmailList();
                    List<Faculties> facultiesList = root.getFacultiesList();
                    List<StudentSchedules> studentSchedulesList = root.getStudentSchedulesList();

                    logger.info("Address:");
                    for (Address addres : addressList) {
                        logger.info("ID: " + addres.getId());
                        logger.info("Country: " + addres.getCountry());
                        logger.info("City: " + addres.getCity());
                        logger.info("Street: " + addres.getStreet());
                        logger.info("Building: " + addres.getBuilding());
                        logger.info("---------------");
                    }

                    logger.info("Email:");
                    for (Email email : emailList) {
                        logger.info("Id: " + email.getId());
                        logger.info("Email" + email.getEmail());
                        logger.info("---------------");
                    }

                    logger.info("Faculties:");
                    for (Faculties faculties: facultiesList) {
                        logger.info("Id% " + faculties.getId());
                        logger.info("FacultyName: " + faculties.getFacultyName());
                        logger.info("EmailId: " + faculties.getEmailId());
                        logger.info("---------------");
                    }

                    logger.info("Student Schedule: ");
                    for (StudentSchedules studentSchedules: studentSchedulesList) {
                        logger.info("Id: " + studentSchedules.getId());
                        logger.info("Day of week: " + studentSchedules.getDayOfWeek());
                        logger.info("Room: " + studentSchedules.getRoom());
                        logger.info("---------------");
                    }
                } catch (JAXBException e) {
                    e.printStackTrace();
                }

                logger.info("\n");
                logger.info("Working with Json\n");
                JsonFileCreator.createJsonFile();
                JsonParser.readJsonFile();

                logger.info("\n");
                logger.info("Working with Mybatis\n");
                try (Reader reader = Resources.getResourceAsReader("mybatis-config.xml")) {
                    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
                    SqlSession sqlSession = sqlSessionFactory.openSession();
                    IEmailMapper iEmailMapper = sqlSession.getMapper(IEmailMapper.class);
                    logger.info(iEmailMapper.getById(1));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                logger.info("\n");
                logger.info("Working with solid\n");
                SolidFactory solidFactory = new SolidFactory();
                StudentSchedules solidSchedule = solidFactory.createSolid(StudentSchedules.class);
                solidSchedule.setId(2);
                solidSchedule.setRoom(303);
                solidSchedule.setDayOfWeek("Friday");
                logger.info(solidSchedule);

                Professors solidProfessor = new Professors.Builder()
                        .id(1)
                        .firstName("John")
                        .lastName("Doe")
                        .dateOfBirthId(123)
                        .emailId(456)
                        .facultiesId(789)
                        .build();
                logger.info(solidProfessor);

            } catch (SQLException | InterruptedException | ParseException e) {
                logger.error("Error occurred while working with database " + e.getMessage());
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            }
        });
        executorService.shutdown();
    }
}



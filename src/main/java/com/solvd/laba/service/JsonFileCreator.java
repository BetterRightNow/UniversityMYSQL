package com.solvd.laba.service;


import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.solvd.laba.model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;

public class JsonFileCreator {
    static Logger jsonLogger = LogManager.getLogger();
    public static void createJsonFile() {
        ObjectMapper objectMapper = new ObjectMapper();
        Address addressJson = new Address(1, "Poland", "Krakov", "Kovalskego", 123);
        Courses coursesJson = new Courses(1, "AQA", 1);
        Email emailJson = new Email(1, "email@example.com");
        Faculties facultiesJson = new Faculties(1, "Informatics", 1);
        Students studentsJson = new Students(1, "Marek", "Krakovski",
                1, 1, 1, 1, 1);
        StudentSchedules studentSchedulesJson = new StudentSchedules(1, "Monday", 101);
        Subjects subjectsJson = new Subjects(1, "Java", 1, 1);

        JsonWrapper[] jsonWrappers = new JsonWrapper[]{
                new JsonWrapper(Address.class.getName(), addressJson),
                new JsonWrapper(Courses.class.getName(), coursesJson),
                new JsonWrapper(Email.class.getName(), emailJson),
                new JsonWrapper(Faculties.class.getName(), facultiesJson),
                new JsonWrapper(Students.class.getName(), studentsJson),
                new JsonWrapper(StudentSchedules.class.getName(), studentSchedulesJson),
                new JsonWrapper(Subjects.class.getName(), subjectsJson)
        };

        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(
                    "src/main/resources/classes.json"), jsonWrappers);
            jsonLogger.info("JSON file created successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

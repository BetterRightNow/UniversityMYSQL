package com.solvd.laba.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;

import java.io.File;
import java.io.IOException;
import org.apache.logging.log4j.Logger;

public class JsonParser {

    static Logger jsonParserLogger = LogManager.getLogger();

    public static void readJsonFile() {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            JsonWrapper[] jsonWrappers = objectMapper.readValue(new File("src/main/resources/classes.json"),
                    JsonWrapper[].class);

            for (JsonWrapper jsonWrapper : jsonWrappers) {
                String className = jsonWrapper.getClassName();
                Object object = jsonWrapper.getObject();

                jsonParserLogger.info("Class: " + className);
                jsonParserLogger.info("Data: " + object + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

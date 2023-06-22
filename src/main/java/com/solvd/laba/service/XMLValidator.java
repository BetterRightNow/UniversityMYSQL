package com.solvd.laba.service;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import java.io.File;
import java.io.IOException;


public class XMLValidator {


    public static void validate(String xmlPath, String xsdPath) {
        try {
            Logger validatorLogger = LogManager.getLogger();
            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

            File schemaFile = new File(xsdPath);
            Schema schema = schemaFactory.newSchema(schemaFile);

            Validator validator = schema.newValidator();

            Source xmlSource = new StreamSource(new File(xmlPath));
            validator.validate(xmlSource);
            validatorLogger.info("XML document is valid.");
        } catch (SAXException e) {
            System.err.println("XML document is not valid: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("IO error: " + e.getMessage());
        }
    }
}

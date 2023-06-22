package com.solvd.laba.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;


public class XMLHandler extends DefaultHandler {
    Logger saxlogger = LogManager.getLogger();

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        saxlogger.info(qName);
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String content = new String(ch, start, length).trim();
        if (!content.isEmpty()) {
            saxlogger.info("Content: " + content);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

    }

    @Override
    public void error(SAXParseException e) throws SAXException {

    }

    @Override
    public void fatalError(SAXParseException e) throws SAXException {
        saxlogger.fatal("Fatal Error: " + e.getMessage());
    }

    @Override
    public void warning(SAXParseException e) throws SAXException {
        saxlogger.warn("Warning: " + e.getMessage());
    }


}

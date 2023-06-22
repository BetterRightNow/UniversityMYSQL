package com.solvd.laba.model;

import jakarta.xml.bind.annotation.XmlElement;

public class Faculties {
    private int id;
    private String facultyName;
    private int emailId;

    public Faculties() {
    }

    public Faculties(int id, String facultyName, int emailId) {
        this.id = id;
        this.facultyName = facultyName;
        this.emailId = emailId;
    }
    @XmlElement
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @XmlElement
    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }
    @XmlElement
    public int getEmailId() {
        return emailId;
    }

    public void setEmailId(int emailId) {
        this.emailId = emailId;
    }

    @Override
    public String toString() {
        return "Faculties{" +
                "id=" + id +
                ", facultyName='" + facultyName + '\'' +
                ", emailId=" + emailId +
                '}';
    }
}

package com.solvd.laba.model;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public int getEmailId() {
        return emailId;
    }

    public void setEmailId(int emailId) {
        this.emailId = emailId;
    }
}

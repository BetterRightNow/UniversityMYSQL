package com.solvd.laba.model;

public class Courses {
    private int id;
    private String courseName;
    private int facultiesId;

    public Courses() {}

    public Courses(int id, String courseName, int facultiesId) {
        this.id = id;
        this.courseName = courseName;
        this.facultiesId = facultiesId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getFacultiesId() {
        return facultiesId;
    }

    public void setFacultiesId(int facultiesId) {
        this.facultiesId = facultiesId;
    }
}

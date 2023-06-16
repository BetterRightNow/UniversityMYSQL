package com.solvd.laba.model;

public class Subjects {
    private int id;
    private String subjectName;
    private int coursesId;
    private int studentSchedulesId;

    public Subjects() {}

    public Subjects(int id, String subjectName, int coursesId, int studentSchedulesId) {
        this.id = id;
        this.subjectName = subjectName;
        this.coursesId = coursesId;
        this.studentSchedulesId = studentSchedulesId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getCoursesId() {
        return coursesId;
    }

    public void setCoursesId(int coursesId) {
        this.coursesId = coursesId;
    }

    public int getStudentSchedulesId() {
        return studentSchedulesId;
    }

    public void setStudentSchedulesId(int studentSchedulesId) {
        this.studentSchedulesId = studentSchedulesId;
    }

    @Override
    public String toString() {
        return "Subjects{" +
                "id=" + id +
                ", subjectName='" + subjectName + '\'' +
                ", coursesId=" + coursesId +
                ", studentSchedulesId=" + studentSchedulesId +
                '}';
    }
}

package com.solvd.laba.model;

public class Students {
    private int id;
    private String firstName;
    private String lastName;
    private int dateOfBirthId;
    private int addressId;
    private int facultiesId;
    private int emailId;
    private int studentSchedulesId;

    public Students() {}

    public Students(int id, String firstName, String lastName, int dateOfBirthId,
                    int addressId, int facultiesId, int emailId, int studentSchedulesId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirthId = dateOfBirthId;
        this.addressId = addressId;
        this.facultiesId = facultiesId;
        this.emailId = emailId;
        this.studentSchedulesId = studentSchedulesId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getDateOfBirthId() {
        return dateOfBirthId;
    }

    public void setDateOfBirthId(int dateOfBirthId) {
        this.dateOfBirthId = dateOfBirthId;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public int getFacultiesId() {
        return facultiesId;
    }

    public void setFacultiesId(int facultiesId) {
        this.facultiesId = facultiesId;
    }

    public int getEmailId() {
        return emailId;
    }

    public void setEmailId(int emailId) {
        this.emailId = emailId;
    }

    public int getStudentSchedulesId() {
        return studentSchedulesId;
    }

    public void setStudentSchedulesId(int studentSchedulesId) {
        this.studentSchedulesId = studentSchedulesId;
    }

    @Override
    public String toString() {
        return "Students{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirthId=" + dateOfBirthId +
                ", addressId=" + addressId +
                ", facultiesId=" + facultiesId +
                ", emailId=" + emailId +
                ", studentSchedulesId=" + studentSchedulesId +
                '}';
    }
}

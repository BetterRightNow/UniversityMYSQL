package com.solvd.laba.model;

import java.sql.Date;

public class DateOfBirth {
    int id;
    Date dateOfBirth;

    public DateOfBirth() {
    }

    public DateOfBirth(int id, Date dateOfBirth) {
        this.id = id;
        this.dateOfBirth = dateOfBirth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "DateOfBirth{" +
                "id=" + id +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }
}

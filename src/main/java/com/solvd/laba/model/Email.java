package com.solvd.laba.model;

import jakarta.xml.bind.annotation.XmlElement;

public class Email {
    private int id;
    private String email;

    public Email() {}

    public Email(int id, String email) {
        this.id = id;
        this.email = email;
    }
    @XmlElement
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @XmlElement
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Email{" +
                "id=" + id +
                ", email='" + email + '\'' +
                '}';
    }
}

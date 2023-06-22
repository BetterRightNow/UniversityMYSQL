package com.solvd.laba.model;

import jakarta.xml.bind.annotation.XmlElement;

public class StudentSchedules {
    private int id;
    private String dayOfWeek;
    private int room;

    public StudentSchedules() {}

    public StudentSchedules(int id, String dayOfWeek, int room) {
        this.id = id;
        this.dayOfWeek = dayOfWeek;
        this.room = room;
    }
    @XmlElement
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @XmlElement
    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }
    @XmlElement
    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    @Override
    public String toString() {
        return "StudentSchedules{" +
                "id=" + id +
                ", dayOfWeek='" + dayOfWeek + '\'' +
                ", room=" + room +
                '}';
    }
}

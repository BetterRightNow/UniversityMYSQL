package com.solvd.laba.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;
@XmlRootElement(name = "root")
@XmlAccessorType(XmlAccessType.FIELD)
public class Root {

    @XmlElement(name = "Address")
    private List<Address> addressList;

    @XmlElement(name = "Email")
    private List<Email> emailList;

    @XmlElement(name = "Faculties")
    private List<Faculties> facultiesList;

    @XmlElement(name = "StudentSchedules")
    private List<StudentSchedules> studentSchedulesList;

    public List<Address> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
    }

    public List<Email> getEmailList() {
        return emailList;
    }

    public void setEmailList(List<Email> emailList) {
        this.emailList = emailList;
    }

    public List<Faculties> getFacultiesList() {
        return facultiesList;
    }

    public void setFacultiesList(List<Faculties> facultiesList) {
        this.facultiesList = facultiesList;
    }

    public List<StudentSchedules> getStudentSchedulesList() {
        return studentSchedulesList;
    }

    public void setStudentSchedulesList(List<StudentSchedules> studentSchedulesList) {
        this.studentSchedulesList = studentSchedulesList;
    }
}

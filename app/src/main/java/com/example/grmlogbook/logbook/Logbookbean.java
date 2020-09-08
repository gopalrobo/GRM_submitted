package com.example.grmlogbook.logbook;

import java.io.Serializable;

public class Logbookbean implements Serializable {
    String id;
    String project;
    String focalLocation;
    String focalPerson;
    String grievanceNumber;
    String date;
    String name;
    String address;
    String telephone;
    String type;
    String nature;
    String location;
    String summary;
    String dpSign;

    public Logbookbean(String id, String project, String focalLocation, String focalPerson, String grievanceNumber, String date, String name, String address, String telephone, String type, String nature, String location, String summary, String dpSign) {
        this.id = id;
        this.project = project;
        this.focalLocation = focalLocation;
        this.focalPerson = focalPerson;
        this.grievanceNumber = grievanceNumber;
        this.date = date;
        this.name = name;
        this.address = address;
        this.telephone = telephone;
        this.type = type;
        this.nature = nature;
        this.location = location;
        this.summary = summary;
        this.dpSign = dpSign;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getFocalLocation() {
        return focalLocation;
    }

    public void setFocalLocation(String focalLocation) {
        this.focalLocation = focalLocation;
    }

    public String getFocalPerson() {
        return focalPerson;
    }

    public void setFocalPerson(String focalPerson) {
        this.focalPerson = focalPerson;
    }

    public String getGrievanceNumber() {
        return grievanceNumber;
    }

    public void setGrievanceNumber(String grievanceNumber) {
        this.grievanceNumber = grievanceNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDpSign() {
        return dpSign;
    }

    public void setDpSign(String dpSign) {
        this.dpSign = dpSign;
    }
}
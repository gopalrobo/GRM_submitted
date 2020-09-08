package com.example.grmlogbook;

import java.io.Serializable;

public class Projectmasterbean implements Serializable {
    String id;
    String projectNumber;
    String name;
    String country;
    String Province;
    String district;
    String commune;
    String village;
    String implemntingAgency;
    String excecutiveAgency;
    String consultant;
    String contracterName;
    String contracterDesignation;
    String contracterCompany;
    String vilresPerson;

    public Projectmasterbean() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Projectmasterbean(String projectNumber, String name,String country, String province, String district, String commune, String village, String implemntingAgency, String excecutiveAgency, String consultant, String contracterName, String contracterDesignation, String contracterCompany, String vilresPerson) {
        this.projectNumber = projectNumber;
        this.country = country;
        Province = province;
        this.name=name;
        this.district = district;
        this.commune = commune;
        this.village = village;
        this.implemntingAgency = implemntingAgency;
        this.excecutiveAgency = excecutiveAgency;
        this.consultant = consultant;
        this.contracterName = contracterName;
        this.contracterDesignation = contracterDesignation;
        this.contracterCompany = contracterCompany;
        this.vilresPerson = vilresPerson;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectNumber() {
        return projectNumber;
    }

    public void setProjectNumber(String projectNumber) {
        this.projectNumber = projectNumber;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return Province;
    }

    public void setProvince(String province) {
        Province = province;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCommune() {
        return commune;
    }

    public void setCommune(String commune) {
        this.commune = commune;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getImplemntingAgency() {
        return implemntingAgency;
    }

    public void setImplemntingAgency(String implemntingAgency) {
        this.implemntingAgency = implemntingAgency;
    }

    public String getExcecutiveAgency() {
        return excecutiveAgency;
    }

    public void setExcecutiveAgency(String excecutiveAgency) {
        this.excecutiveAgency = excecutiveAgency;
    }

    public String getConsultant() {
        return consultant;
    }

    public void setConsultant(String consultant) {
        this.consultant = consultant;
    }

    public String getContracterName() {
        return contracterName;
    }

    public void setContracterName(String contracterName) {
        this.contracterName = contracterName;
    }

    public String getContracterDesignation() {
        return contracterDesignation;
    }

    public void setContracterDesignation(String contracterDesignation) {
        this.contracterDesignation = contracterDesignation;
    }

    public String getContracterCompany() {
        return contracterCompany;
    }

    public void setContracterCompany(String contracterCompany) {
        this.contracterCompany = contracterCompany;
    }

    public String getVilresPerson() {
        return vilresPerson;
    }

    public void setVilresPerson(String vilresPerson) {
        this.vilresPerson = vilresPerson;
    }
}

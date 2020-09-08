package com.example.grmlogbook;

import java.io.Serializable;

public class RegMainbean implements Serializable {
    String id;
    String salutation;
    String name;
    String mobile;
    String level;
    String position;
    String organization;
    String doornumber;
    String Province;
    String district;
    String commune;
    String village;
    String email;
    String password;
    String confirmpassword;
    String surname;
    String parentname;
    String relation;
    String Phonedial;

    public RegMainbean() {
    }

    public RegMainbean(String salutation, String name, String mobile, String level, String position, String organization, String doornumber, String province, String district, String commune, String village, String email, String password, String confirmpassword, String surname, String parentname, String relation) {
        this.salutation = salutation;
        this.name = name;
        this.mobile = mobile;
        this.level = level;
        this.position = position;
        this.organization = organization;
        this.doornumber = doornumber;
        Province = province;
        this.district = district;
        this.commune = commune;
        this.village = village;
        this.email = email;
        this.password = password;
        this.confirmpassword = confirmpassword;
        this.surname = surname;
        this.parentname = parentname;
        this.relation = relation;
    }

    public RegMainbean(String salutation, String name, String mobile, String position, String doornumber, String province, String district, String commune, String village, String email, String password, String confirmpassword, String surname, String parentname, String relation) {
       this.salutation = salutation;
        this.name = name;
        this.mobile = mobile;
        this.position = position;
        this.doornumber = doornumber;
        Province = province;
        this.district = district;
        this.commune = commune;
        this.village = village;
        this.email = email;
        this.password = password;
        this.confirmpassword = confirmpassword;
        this.surname = surname;
        this.parentname = parentname;
        this.relation = relation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSalutation() {
        return salutation;
    }

    public void setSalutation(String salutation) {
        this.salutation = salutation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDoornumber() {
        return doornumber;
    }

    public void setDoornumber(String doornumber) {
        this.doornumber = doornumber;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmpassword() {
        return confirmpassword;
    }

    public void setConfirmpassword(String confirmpassword) {
        this.confirmpassword = confirmpassword;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getParentname() {
        return parentname;
    }

    public void setParentname(String parentname) {
        this.parentname = parentname;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getPhonedial() {
        return Phonedial;
    }

    public void setPhonedial(String phonedial) {
        Phonedial = phonedial;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }
}
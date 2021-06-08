package com.my.finalProject.db.entity.entityImpl;

import com.my.finalProject.db.entity.Entity;

public class Account extends Entity {
    private static final long serialVersionUID = -68890362561494958L;
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String middleName;
    private String eMail;
    private String city;
    private String region;
    private String localeName;
    private Long certificate_id;
    private int role_id;
    private Long studyForm_id;
    private String faculties_name;
    public Long getCertificate_id() {
        return certificate_id;
    }

    public void setCertificate_id(Long certificate_id) {
        this.certificate_id = certificate_id;
    }



    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setMail(String eMail) {
        this.eMail = eMail;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setLocaleName(String localeName) {
        this.localeName = localeName;
    }



    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public void setStudyForm_id(Long studyForm_id) {
        this.studyForm_id = studyForm_id;
    }

    public void setFaculties_name(String faculties_name) {
        this.faculties_name = faculties_name;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getEMail() {
        return eMail;
    }

    public String getCity() {
        return city;
    }

    public String getRegion() {
        return region;
    }

    public String getLocaleName() {
        return localeName;
    }



    public int getRole_id() {
        return role_id;
    }

    public Long getStudyForm_id() {
        return studyForm_id;
    }

    @Override
    public String toString() {
        return "Account [login=" + login
                + ", firstName=" + firstName + ", lastName=" + lastName
                + ", middleName=" + middleName + ", city=" + city
                + ", region=" + region + ", email=" + eMail
                + ", localeName=" + localeName + ", roleId=" + role_id
                + ", certificate_id=" + certificate_id + ", studyForm_id=" + studyForm_id
                + ", faculties_id=" + faculties_name
                + "]";
    }

    public String getFaculties_name() {
        return faculties_name;
    }


}

package com.my.finalProject.builder.impl;

import com.my.finalProject.builder.Builder;
import com.my.finalProject.builder.exception.BuildException;
import com.my.finalProject.db.entity.entityImpl.Account;

import javax.servlet.http.HttpServletRequest;

import static com.my.finalProject.Fields.*;
import static com.my.finalProject.utils.PasswordHash.hash;


public class AccountBuilder extends Builder<Account> {

    @Override
    public Account build(HttpServletRequest request, Account account) throws BuildException {

        String login = request.getParameter("login");

        if (!login.isEmpty()) {
            account.setLogin(login);
        } else {
            account.setLogin(NOT_VALID_FIELD);
        }

        String password = request.getParameter("password");
        if (account.getPassword() == null) {
            if (!password.isEmpty())
                password = hash(password);
        }

        if (!password.isEmpty()) {

            account.setPassword(password);
        } else {
            account.setPassword(NOT_VALID_FIELD);
        }

        String firstName = request.getParameter("firstName");

        if (!firstName.isEmpty()) {
            account.setFirstName(firstName);
        } else {
            account.setFirstName(NOT_VALID_FIELD);
        }
        if (isNumeric(firstName)) {
            account.setFirstName(NOT_VALID_ISNUMBER);
        }

        String lastName = request.getParameter("lastName");
        if (!lastName.isEmpty()) {
            account.setLastName(lastName);
        } else {
            account.setLastName(NOT_VALID_FIELD);
        }
        if (isNumeric(lastName)) {
            account.setLastName(NOT_VALID_ISNUMBER);
        }

        String middleName = request.getParameter("middleName");
        if (!middleName.isEmpty()) {
            account.setMiddleName(middleName);
        } else {
            account.setMiddleName(NOT_VALID_FIELD);
        }
        if (isNumeric(middleName)) {
            account.setMiddleName(NOT_VALID_ISNUMBER);
        }

        String eMail = request.getParameter("email");
        if (!eMail.isEmpty()) {
            account.setMail(eMail);
        } else {
            account.setMail(NOT_VALID_FIELD);
        }

        String city = request.getParameter("city");
        if (!city.isEmpty()) {
            account.setCity(city);
        } else {
            account.setCity(NOT_VALID_FIELD);
        }
        if (isNumeric(city)) {
            account.setCity(NOT_VALID_ISNUMBER);
        }

        String region = request.getParameter("region");
        if (!region.isEmpty()) {
            account.setRegion(region);
        } else {
            account.setRegion(NOT_VALID_FIELD);
        }
        if (isNumeric(region)) {
            account.setRegion(NOT_VALID_ISNUMBER);
        }

        String role_id = request.getParameter("role_id");
        if (!role_id.isEmpty()) {
            account.setRole_id(Integer.parseInt(role_id));
        } else {
            account.setRole_id(DEFAULT_ROLE_USER_ID);
        }

        String studyForm_id = request.getParameter("studyForm");

        if (!studyForm_id.isEmpty()) {
            account.setStudyForm_id(Long.parseLong(studyForm_id));
        } else {
            account.setStudyForm_id(DEFAULT_STUDY_FORM_ID);
        }

        String faculties_name = request.getParameter("faculties_name");

        if (!faculties_name.isEmpty()) {
            account.setFaculties_name(faculties_name);
        } else {
            account.setFaculties_name(DEFAULT_FACULTIES_NAME);
        }

        return account;
    }
}

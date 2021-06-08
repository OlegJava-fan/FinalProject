package com.my.finalProject.validator.impl;

import com.my.finalProject.db.AccountDAO;
import com.my.finalProject.db.entity.entityImpl.Account;
import com.my.finalProject.db.exception.DBException;
import com.my.finalProject.validator.Validator;

import java.util.HashMap;
import java.util.Map;

import static com.my.finalProject.Fields.*;

public class AccountValidator extends Validator<Account> {
    @Override
    public Map<String, String> validate(Account account) throws DBException {

        String errorMassage;
        Map<String, String> notValidAccountFields = new HashMap<>();

        Account existingAccount = AccountDAO.getInstance().findAccountByLogin(account.getLogin());

        if (existingAccount != null) {
            notValidAccountFields.put(ACCOUNT_LOGIN, "account with this login already exist");
        }

       if (NOT_VALID_FIELD.equals(account.getLogin())) {
            errorMassage = "Field: login is empty";
            notValidAccountFields.put(ACCOUNT_LOGIN, errorMassage);
        }


        if (NOT_VALID_FIELD.equals(account.getPassword())) {
            errorMassage = "Field: password is empty";
            notValidAccountFields.put(ACCOUNT_PASSWORD, errorMassage);
        }

        if (NOT_VALID_FIELD.equals(account.getFirstName())) {
            errorMassage = "Field: first name cant be empty";
            notValidAccountFields.put(ACCOUNT__FIRST_NAME, errorMassage);
        }
        if (NOT_VALID_ISNUMBER.equals(account.getFirstName())) {
            errorMassage = "Field: first name cant be digit";
            notValidAccountFields.put(ACCOUNT__FIRST_NAME, errorMassage);
        }

        if (NOT_VALID_FIELD.equals(account.getLastName())) {
            errorMassage = "Field: last name cant be empty ";
            notValidAccountFields.put(ACCOUNT__LAST_NAME, errorMassage);
        }
        if (NOT_VALID_ISNUMBER.equals(account.getLastName())) {
            errorMassage = "Field: last name cant be digit";
            notValidAccountFields.put(ACCOUNT__LAST_NAME, errorMassage);
        }
        if (NOT_VALID_FIELD.equals(account.getMiddleName())) {
            errorMassage = "Field: middle name cant be empty ";
            notValidAccountFields.put(ACCOUNT__MIDDLE_NAME, errorMassage);
        }
        if (NOT_VALID_ISNUMBER.equals(account.getMiddleName())) {
            errorMassage = "Field: middle name cant be digit";
            notValidAccountFields.put(ACCOUNT__MIDDLE_NAME, errorMassage);
        }
        if (NOT_VALID_FIELD.equals(account.getEMail())) {
            errorMassage = "Field: email cant be empty";
            notValidAccountFields.put(ACCOUNT_EMAIL, errorMassage);
        }
        if (NOT_VALID_ISNUMBER.equals(account.getEMail())) {
            errorMassage = "Field: email name cant be digit";
            notValidAccountFields.put(ACCOUNT_EMAIL, errorMassage);
        }
        if (NOT_VALID_FIELD.equals(account.getCity())) {
            errorMassage = "Field: city cant be empty";
            notValidAccountFields.put(ACCOUNT__CITY, errorMassage);
        }
        if (NOT_VALID_ISNUMBER.equals(account.getCity())) {
            errorMassage = "Field: city  cant be digit";
            notValidAccountFields.put(ACCOUNT__CITY, errorMassage);
        }

        if (NOT_VALID_FIELD.equals(account.getRegion())) {
            errorMassage = "Field: region cant be empty";
            notValidAccountFields.put(ACCOUNT__REGION, errorMassage);
        }
        if (NOT_VALID_ISNUMBER.equals(account.getRegion())) {
            errorMassage = "Field: region cant be digit";
            notValidAccountFields.put(ACCOUNT__REGION, errorMassage);
        }
        return notValidAccountFields;
    }
}

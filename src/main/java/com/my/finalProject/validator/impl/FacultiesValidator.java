package com.my.finalProject.validator.impl;

import com.my.finalProject.db.entity.entityImpl.Faculties;
import com.my.finalProject.validator.Validator;

import java.util.HashMap;
import java.util.Map;

import static com.my.finalProject.Fields.*;

public class FacultiesValidator extends Validator<Faculties> {

    @Override
    public Map<String, String> validate(Faculties faculties) {

        Map<String, String> notValidFields = new HashMap<>();

        validateName(faculties, notValidFields);

        validatePassingScorePayForm(faculties, notValidFields);

        validatePassingScoreFreeForm(faculties, notValidFields);

        validatePayFormPlaces(faculties, notValidFields);

        validateAllPlace(faculties, notValidFields);

        validateFreeFormPlaces(faculties, notValidFields);

        validateSumPayPlaceAndFreePlace(faculties, notValidFields);

        validateLogicAllPlace(faculties, notValidFields);

        validateLogicFreeForm(faculties, notValidFields);

        return notValidFields;
    }

    private void validateLogicFreeForm(Faculties faculties, Map<String, String> notValidFields) {
        String errorMassage;
        if (faculties.getFreeFormPlaces() >= 0L
                && faculties.getPayFormPlaces() >= 0L) {
            if (faculties.getFreeFormPlaces() > faculties.getAllPlace()) {
                errorMassage = "Field: free form places more then all place field ";
                notValidFields.put(FACULTIES_FREE_FORM_PLACES, errorMassage);
            }
        }
    }

    private void validateLogicAllPlace(Faculties faculties, Map<String, String> notValidFields) {
        String errorMassage;
        if (faculties.getAllPlace() >= 0L
                && faculties.getPayFormPlaces() >= 0L) {
            if (faculties.getPayFormPlaces() > faculties.getAllPlace()) {
                errorMassage = "Field: pay form places cant bee more then all place field ";
                notValidFields.put(FACULTIES_PAY_FORM_PLACES, errorMassage);
            }
        }
    }

    private void validateSumPayPlaceAndFreePlace(Faculties faculties, Map<String, String> notValidFields) {
        String errorMassage;
        if (faculties.getFreeFormPlaces() >= 0L
                && faculties.getAllPlace() >= 0L
                && faculties.getPayFormPlaces() >= 0L) {
            if (faculties.getAllPlace() < faculties.getFreeFormPlaces() + faculties.getPayFormPlaces()) {
                errorMassage = "Field: sum free place and pay place more then all place";
                notValidFields.put(FACULTIES_ALL_PLACE, errorMassage);
            }

        }
    }

    private void validateFreeFormPlaces(Faculties faculties, Map<String, String> notValidFields) {
        String errorMassage;
        if (faculties.getFreeFormPlaces().equals(NOT_VALID_IS_EMPTY)) {
            errorMassage = "Field: free form place places cannot be empty";
            notValidFields.put(FACULTIES_FREE_FORM_PLACES, errorMassage);
        }
        if (faculties.getFreeFormPlaces().equals(NOT_VALID_NEGATIVE_NUMBER)) {
            errorMassage = "Field: free form place cannot be contains negative numbers";
            notValidFields.put(FACULTIES_FREE_FORM_PLACES, errorMassage);
        }
        if (faculties.getFreeFormPlaces().equals(NOT_VALID_IS_LETTER)) {
            errorMassage = "Field: free form place places cannot be contains latter";
            notValidFields.put(FACULTIES_FREE_FORM_PLACES, errorMassage);
        }
    }

    private void validateAllPlace(Faculties faculties, Map<String, String> notValidFields) {
        String errorMassage;
        if (faculties.getAllPlace().equals(NOT_VALID_IS_EMPTY)) {
            errorMassage = "Field: all places cannot be empty";
            notValidFields.put(FACULTIES_ALL_PLACE, errorMassage);
        }
        if (faculties.getAllPlace().equals(NOT_VALID_NEGATIVE_NUMBER)) {
            errorMassage = "Field: all places cannot be contains negative numbers";
            notValidFields.put(FACULTIES_ALL_PLACE, errorMassage);
        }
        if (faculties.getAllPlace().equals(NOT_VALID_IS_LETTER)) {
            errorMassage = "Field: all places cannot be contains latter";
            notValidFields.put(FACULTIES_ALL_PLACE, errorMassage);
        }
    }

    private void validatePayFormPlaces(Faculties faculties, Map<String, String> notValidFields) {
        String errorMassage;
        if (faculties.getPayFormPlaces().equals(NOT_VALID_IS_EMPTY)) {
            errorMassage = "Field: pay form places cannot be empty";
            notValidFields.put(FACULTIES_PAY_FORM_PLACES, errorMassage);
        }
        if (faculties.getPayFormPlaces().equals(NOT_VALID_NEGATIVE_NUMBER)) {
            errorMassage = "Field: pay form places cannot be contains negative numbers";
            notValidFields.put(FACULTIES_PAY_FORM_PLACES, errorMassage);
        }
        if (faculties.getPayFormPlaces().equals(NOT_VALID_IS_LETTER)) {
            errorMassage = "Field: pay form places cannot be contains latter";
            notValidFields.put(FACULTIES_PAY_FORM_PLACES, errorMassage);
        }
    }

    private void validatePassingScoreFreeForm(Faculties faculties, Map<String, String> notValidFields) {
        String errorMassage;
        if (faculties.getPassingScoreFreeForm().equals(NOT_VALID_IS_EMPTY)) {
            errorMassage = "Field: Passing score to free form cannot be empty";
            notValidFields.put(FACULTIES_PASSING_SCORE_FREE_FORM, errorMassage);
        }
        if (faculties.getPassingScoreFreeForm().equals(NOT_VALID_NEGATIVE_NUMBER)) {
            errorMassage = "Field: Passing score to free form cannot be contains negative numbers";
            notValidFields.put(FACULTIES_PASSING_SCORE_FREE_FORM, errorMassage);
        }
        if (faculties.getPassingScoreFreeForm().equals(NOT_VALID_IS_LETTER)) {
            errorMassage = "Field: Passing score to free form cannot be contains latter";
            notValidFields.put(FACULTIES_PASSING_SCORE_FREE_FORM, errorMassage);
        }
    }

    private void validatePassingScorePayForm(Faculties faculties, Map<String, String> notValidFields) {
        String errorMassage;
        if (faculties.getPassingScorePayForm().equals(NOT_VALID_IS_EMPTY)) {
            errorMassage = "Field: Passing score pay form cannot be empty";
            notValidFields.put(FACULTIES_PASSING_SCORE_PAY_FORM, errorMassage);
        }
        if (faculties.getPassingScorePayForm().equals(NOT_VALID_NEGATIVE_NUMBER)) {
            errorMassage = "Field: Passing score pay form cannot be contains negative numbers";
            notValidFields.put(FACULTIES_PASSING_SCORE_PAY_FORM, errorMassage);
        }
        if (faculties.getPassingScorePayForm().equals(NOT_VALID_IS_LETTER)) {
            errorMassage = "Field:Passing score pay form cannot be contains latter";
            notValidFields.put(FACULTIES_PASSING_SCORE_PAY_FORM, errorMassage);
        }
    }

    private void validateName(Faculties faculties, Map<String, String> notValidFields) {
        String errorMassage;
        if (NOT_VALID_EMPTY_STRING.equals(faculties.getName())) {
            errorMassage = "Fields: name is empty";
            notValidFields.put(FACULTIES_NAME, errorMassage);
        }
        if (NOT_VALID_ISNUMBER.equals(faculties.getName())) {
            errorMassage = "Fields: name cannot be a number";
            notValidFields.put(FACULTIES_NAME, errorMassage);
        }
    }
}

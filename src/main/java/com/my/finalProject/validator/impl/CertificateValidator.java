package com.my.finalProject.validator.impl;

import com.my.finalProject.db.entity.entityImpl.Certificate;
import com.my.finalProject.validator.Validator;

import java.util.HashMap;
import java.util.Map;

import static com.my.finalProject.Fields.*;

public class CertificateValidator extends Validator<Certificate> {
    @Override
    public Map<String, String> validate(Certificate certificate) {

        Map<String, String> notValidCertificateFields = new HashMap<>();

        validMath(certificate, notValidCertificateFields);

        validChemistry(certificate, notValidCertificateFields);

        validPhysics(certificate, notValidCertificateFields);

        validEnglish(certificate, notValidCertificateFields);

        validUkrainian(certificate, notValidCertificateFields);

        validInformatics(certificate, notValidCertificateFields);

        validGeography(certificate, notValidCertificateFields);

        validBiology(certificate, notValidCertificateFields);

        validHistory(certificate, notValidCertificateFields);

        validGym(certificate, notValidCertificateFields);

        return notValidCertificateFields;
    }

    private void validGym(Certificate certificate, Map<String, String> notValidCertificateFields) {
        String errorMassage;
        if (certificate.getGym().equals(NOT_VALID_IS_EMPTY)) {
            errorMassage = "Field: gym cannot be empty";
            notValidCertificateFields.put(CERTIFICATE_GYM, errorMassage);
        }
        if (certificate.getGym().equals(NOT_VALID_NEGATIVE_NUMBER)) {
            errorMassage = "Field: gym cannot be contains negative numbers";
            notValidCertificateFields.put(CERTIFICATE_GYM, errorMassage);
        }
        if (certificate.getGym().equals(NOT_VALID_IS_LETTER)) {
            errorMassage = "Field: gym cannot be contains latter";
            notValidCertificateFields.put(CERTIFICATE_GYM, errorMassage);
        }
    }

    private void validHistory(Certificate certificate, Map<String, String> notValidCertificateFields) {
        String errorMassage;
        if (certificate.getHistory().equals(NOT_VALID_IS_EMPTY)) {
            errorMassage = "Field: history cannot be empty";
            notValidCertificateFields.put(CERTIFICATE_HISTORY, errorMassage);
        }
        if (certificate.getHistory().equals(NOT_VALID_NEGATIVE_NUMBER)) {
            errorMassage = "Field: history cannot be contains negative numbers";
            notValidCertificateFields.put(CERTIFICATE_HISTORY, errorMassage);
        }
        if (certificate.getHistory().equals(NOT_VALID_IS_LETTER)) {
            errorMassage = "Field: history cannot be contains latter";
            notValidCertificateFields.put(CERTIFICATE_HISTORY, errorMassage);
        }
    }

    private void validBiology(Certificate certificate, Map<String, String> notValidCertificateFields) {
        String errorMassage;
        if (certificate.getBiology().equals(NOT_VALID_IS_EMPTY)) {
            errorMassage = "Field: biology cannot be empty";
            notValidCertificateFields.put(CERTIFICATE_BIOLOGY, errorMassage);
        }
        if (certificate.getBiology().equals(NOT_VALID_NEGATIVE_NUMBER)) {
            errorMassage = "Field: biology cannot be contains negative numbers";
            notValidCertificateFields.put(CERTIFICATE_BIOLOGY, errorMassage);
        }
        if (certificate.getBiology().equals(NOT_VALID_IS_LETTER)) {
            errorMassage = "Field: biology cannot be contains latter";
            notValidCertificateFields.put(CERTIFICATE_BIOLOGY, errorMassage);
        }
    }

    private void validGeography(Certificate certificate, Map<String, String> notValidCertificateFields) {
        String errorMassage;
        if (certificate.getGeography().equals(NOT_VALID_IS_EMPTY)) {
            errorMassage = "Field: geography cannot be empty";
            notValidCertificateFields.put(CERTIFICATE_GEOGRAPHY, errorMassage);
        }
        if (certificate.getGeography().equals(NOT_VALID_NEGATIVE_NUMBER)) {
            errorMassage = "Field: geography cannot be contains negative numbers";
            notValidCertificateFields.put(CERTIFICATE_GEOGRAPHY, errorMassage);
        }
        if (certificate.getGeography().equals(NOT_VALID_IS_LETTER)) {
            errorMassage = "Field: geography cannot be contains latter";
            notValidCertificateFields.put(CERTIFICATE_GEOGRAPHY, errorMassage);
        }
    }

    private void validInformatics(Certificate certificate, Map<String, String> notValidCertificateFields) {
        String errorMassage;
        if (certificate.getInformatics().equals(NOT_VALID_IS_EMPTY)) {
            errorMassage = "Field: informatics cannot be empty";
            notValidCertificateFields.put(CERTIFICATE_INFORMATICS, errorMassage);
        }
        if (certificate.getInformatics().equals(NOT_VALID_NEGATIVE_NUMBER)) {
            errorMassage = "Field: informatics cannot be contains negative numbers";
            notValidCertificateFields.put(CERTIFICATE_INFORMATICS, errorMassage);
        }
        if (certificate.getInformatics().equals(NOT_VALID_IS_LETTER)) {
            errorMassage = "Field: informatics cannot be contains latter";
            notValidCertificateFields.put(CERTIFICATE_INFORMATICS, errorMassage);
        }
    }

    private void validUkrainian(Certificate certificate, Map<String, String> notValidCertificateFields) {
        String errorMassage;
        if (certificate.getUkrainian().equals(NOT_VALID_IS_EMPTY)) {
            errorMassage = "Field: ukrainian cannot be empty";
            notValidCertificateFields.put(CERTIFICATE_UKRAINIAN, errorMassage);
        }
        if (certificate.getUkrainian().equals(NOT_VALID_NEGATIVE_NUMBER)) {
            errorMassage = "Field: ukrainian cannot be contains negative numbers";
            notValidCertificateFields.put(CERTIFICATE_UKRAINIAN, errorMassage);
        }
        if (certificate.getUkrainian().equals(NOT_VALID_IS_LETTER)) {
            errorMassage = "Field: ukrainian cannot be contains latter";
            notValidCertificateFields.put(CERTIFICATE_UKRAINIAN, errorMassage);
        }
    }

    private void validEnglish(Certificate certificate, Map<String, String> notValidCertificateFields) {
        String errorMassage;
        if (certificate.getEnglish().equals(NOT_VALID_IS_EMPTY)) {
            errorMassage = "Field: english cannot be empty";
            notValidCertificateFields.put(CERTIFICATE_ENGLISH, errorMassage);
        }

        if (certificate.getEnglish().equals(NOT_VALID_NEGATIVE_NUMBER)) {
            errorMassage = "Field: english cannot be contains negative numbers";
            notValidCertificateFields.put(CERTIFICATE_ENGLISH, errorMassage);
        }

        if (certificate.getEnglish().equals(NOT_VALID_IS_LETTER)) {
            errorMassage = "Field: english cannot be contains latter";
            notValidCertificateFields.put(CERTIFICATE_ENGLISH, errorMassage);
        }
    }

    private void validPhysics(Certificate certificate, Map<String, String> notValidCertificateFields) {
        String errorMassage;
        if (certificate.getPhysics().equals(NOT_VALID_IS_EMPTY)) {
            errorMassage = "Field: physics cannot be empty";
            notValidCertificateFields.put(CERTIFICATE_PHYSICS, errorMassage);
        }
        if (certificate.getPhysics().equals(NOT_VALID_NEGATIVE_NUMBER)) {
            errorMassage = "Field: physics cannot be contains negative numbers";
            notValidCertificateFields.put(CERTIFICATE_PHYSICS, errorMassage);
        }
        if (certificate.getPhysics().equals(NOT_VALID_IS_LETTER)) {
            errorMassage = "Field: physics cannot be contains latter";
            notValidCertificateFields.put(CERTIFICATE_PHYSICS, errorMassage);
        }
    }

    private void validChemistry(Certificate certificate, Map<String, String> notValidCertificateFields) {
        String errorMassage;
        if (certificate.getChemistry().equals(NOT_VALID_IS_EMPTY)) {
            errorMassage = "Field: chemistry cannot be empty";
            notValidCertificateFields.put(CERTIFICATE_CHEMISTRY, errorMassage);
        }
        if (certificate.getChemistry().equals(NOT_VALID_NEGATIVE_NUMBER)) {
            errorMassage = "Field: chemistry cannot be contains negative numbers";
            notValidCertificateFields.put(CERTIFICATE_CHEMISTRY, errorMassage);
        }
        if (certificate.getChemistry().equals(NOT_VALID_IS_LETTER)) {
            errorMassage = "Field: chemistry cannot be contains latter";
            notValidCertificateFields.put(CERTIFICATE_CHEMISTRY, errorMassage);
        }
    }

    private void validMath(Certificate certificate, Map<String, String> notValidCertificateFields) {
        String errorMassage;
        if (certificate.getMathematics().equals(NOT_VALID_IS_EMPTY)) {
            errorMassage = "Field: mathematics cannot be empty";
            notValidCertificateFields.put(CERTIFICATE_MATH, errorMassage);
        }
        if (certificate.getMathematics().equals(NOT_VALID_NEGATIVE_NUMBER)) {
            errorMassage = "Field: mathematics cannot be contains negative numbers";
            notValidCertificateFields.put(CERTIFICATE_MATH, errorMassage);
        }
        if (certificate.getMathematics().equals(NOT_VALID_IS_LETTER)) {
            errorMassage = "Field: mathematics cannot be contains latter";
            notValidCertificateFields.put(CERTIFICATE_MATH, errorMassage);
        }
    }
}

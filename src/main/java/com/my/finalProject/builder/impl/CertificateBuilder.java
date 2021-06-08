package com.my.finalProject.builder.impl;

import com.my.finalProject.builder.Builder;
import com.my.finalProject.db.entity.entityImpl.Certificate;

import javax.servlet.http.HttpServletRequest;

import static com.my.finalProject.Fields.*;

public class CertificateBuilder extends Builder<Certificate> {

    @Override
    public Certificate build(HttpServletRequest request, Certificate certificate) {
        String mathematics = request.getParameter("mathematics");
        buildMath(certificate, mathematics);

        String chemistry = request.getParameter("chemistry");
        buildChemistry(certificate, chemistry);

        String physics = request.getParameter("physics");
        buildPhysics(certificate, physics);

        String english = request.getParameter("english");
        buildEnglish(certificate, english);

        String ukrainian = request.getParameter("ukrainian");
        buildUkrainian(certificate, ukrainian);

        String informatics = request.getParameter("informatics");
        buildInformatics(certificate, informatics);

        String geography = request.getParameter("geography");
        buildGeography(certificate, geography);

        String biology = request.getParameter("biology");
        buildBiology(certificate, biology);

        String history = request.getParameter("history");
        buildHistory(certificate, history);

        String gym = request.getParameter("gym");
        buildGym(certificate, gym);

        return certificate;
    }

    private void buildGym(Certificate certificate, String gym) {
        if (isNumeric(gym)) {
            if (Long.parseLong(gym) > 0) {
                certificate.setGym(Long.parseLong(gym));
            } else {
                certificate.setGym(NOT_VALID_NEGATIVE_NUMBER);
            }
        }
        if (!isNumeric(gym)) {
            certificate.setGym(NOT_VALID_IS_LETTER);
        }
        if (gym.isEmpty()) {
            certificate.setGym(NOT_VALID_IS_EMPTY);
        }
    }

    private void buildHistory(Certificate certificate, String history) {
        if (isNumeric(history)) {
            if (Long.parseLong(history) > 0) {
                certificate.setHistory(Long.parseLong(history));
            } else {
                certificate.setHistory(NOT_VALID_NEGATIVE_NUMBER);
            }
        }
        if (!isNumeric(history)) {
            certificate.setHistory(NOT_VALID_IS_LETTER);
        }
        if (history.isEmpty()) {
            certificate.setHistory(NOT_VALID_IS_EMPTY);
        }
    }

    private void buildBiology(Certificate certificate, String biology) {
        if (isNumeric(biology)) {
            if (Long.parseLong(biology) > 0) {
                certificate.setBiology(Long.parseLong(biology));
            } else {
                certificate.setBiology(NOT_VALID_NEGATIVE_NUMBER);
            }
        }
        if (!isNumeric(biology)) {
            certificate.setBiology(NOT_VALID_IS_LETTER);
        }
        if (biology.isEmpty()) {
            certificate.setBiology(NOT_VALID_IS_EMPTY);
        }
    }

    private void buildGeography(Certificate certificate, String geography) {
        if (isNumeric(geography)) {
            if (Long.parseLong(geography) > 0) {
                certificate.setGeography(Long.parseLong(geography));
            } else {
                certificate.setGeography(NOT_VALID_NEGATIVE_NUMBER);
            }
        }
        if (!isNumeric(geography)) {
            certificate.setGeography(NOT_VALID_IS_LETTER);
        }
        if (geography.isEmpty()) {
            certificate.setGeography(NOT_VALID_IS_EMPTY);
        }
    }

    private void buildInformatics(Certificate certificate, String informatics) {
        if (isNumeric(informatics)) {
            if (Long.parseLong(informatics) > 0) {
                certificate.setInformatics(Long.parseLong(informatics));
            } else {
                certificate.setInformatics(NOT_VALID_NEGATIVE_NUMBER);
            }
        }
        if (!isNumeric(informatics)) {
            certificate.setInformatics(NOT_VALID_IS_LETTER);
        }
        if (informatics.isEmpty()) {
            certificate.setInformatics(NOT_VALID_IS_EMPTY);
        }
    }

    private void buildUkrainian(Certificate certificate, String ukrainian) {
        if (isNumeric(ukrainian)) {
            if (Long.parseLong(ukrainian) > 0) {
                certificate.setUkrainian(Long.parseLong(ukrainian));
            } else {
                certificate.setUkrainian(NOT_VALID_NEGATIVE_NUMBER);
            }
        }
        if (!isNumeric(ukrainian)) {
            certificate.setUkrainian(NOT_VALID_IS_LETTER);
        }
        if (ukrainian.isEmpty()) {
            certificate.setUkrainian(NOT_VALID_IS_EMPTY);
        }
    }

    private void buildEnglish(Certificate certificate, String english) {
        if (isNumeric(english)) {
            if (Long.parseLong(english) > 0) {
                certificate.setEnglish(Long.parseLong(english));
            } else {
                certificate.setEnglish(NOT_VALID_NEGATIVE_NUMBER);
            }
        }
        if (!isNumeric(english)) {
            certificate.setEnglish(NOT_VALID_IS_LETTER);
        }
        if (english.isEmpty()) {
            certificate.setEnglish(NOT_VALID_IS_EMPTY);
        }
    }

    private void buildPhysics(Certificate certificate, String physics) {
        if (isNumeric(physics)) {
            if (Long.parseLong(physics) > 0) {
                certificate.setPhysics(Long.parseLong(physics));
            } else {
                certificate.setPhysics(NOT_VALID_NEGATIVE_NUMBER);
            }
        }
        if (!isNumeric(physics)) {
            certificate.setPhysics(NOT_VALID_IS_LETTER);
        }
        if (physics.isEmpty()) {
            certificate.setPhysics(NOT_VALID_IS_EMPTY);
        }
    }

    private void buildChemistry(Certificate certificate, String chemistry) {
        if (isNumeric(chemistry)) {
            if (Long.parseLong(chemistry) > 0) {
                certificate.setChemistry(Long.parseLong(chemistry));
            } else {
                certificate.setChemistry(NOT_VALID_NEGATIVE_NUMBER);
            }
        }
        if (!isNumeric(chemistry)) {
            certificate.setChemistry(NOT_VALID_IS_LETTER);
        }
        if (chemistry.isEmpty()) {
            certificate.setChemistry(NOT_VALID_IS_EMPTY);
        }
    }

    private void buildMath(Certificate certificate, String mathematics) {
        if (isNumeric(mathematics)) {
            if (Long.parseLong(mathematics) > 0) {
                certificate.setMathematics(Long.parseLong(mathematics));
            } else {
                certificate.setMathematics(NOT_VALID_NEGATIVE_NUMBER);
            }
        }
        if (!isNumeric(mathematics)) {
            certificate.setMathematics(NOT_VALID_IS_LETTER);
        }
        if (mathematics.isEmpty()) {
            certificate.setMathematics(NOT_VALID_IS_EMPTY);
        }
    }
}

package com.my.finalProject.builder.impl;

import com.my.finalProject.builder.Builder;
import com.my.finalProject.db.entity.entityImpl.Faculties;

import javax.servlet.http.HttpServletRequest;

import static com.my.finalProject.Fields.*;


public class FacultiesBuilder extends Builder<Faculties> {


    @Override
    public Faculties build(HttpServletRequest request, Faculties faculties) {

        String name = request.getParameter("name");
        buildName(faculties, name);

        String passingScoreFreeForm = request.getParameter("passingScoreFreeForm");
        buildPassingScoreFreeForm(faculties, passingScoreFreeForm);

        String passingScorePayForm = request.getParameter("passingScorePayForm");
        buildPassingScorePayForm(faculties, passingScorePayForm);

        String allPlaces = request.getParameter("allPlaces");
        buildAllPlaces(faculties, allPlaces);

        String freeFormPlaces = request.getParameter("freeFormPlaces");
        buildFreeFormPlaces(faculties, freeFormPlaces);

        String payFormPlaces = request.getParameter("payFormPlaces");
        buildPayFormPlaces(faculties, payFormPlaces);
        return faculties;
    }

    private void buildPayFormPlaces(Faculties faculties, String payFormPlaces) {
        buildOrUpdatePayFormPlaces(faculties, payFormPlaces);
        if (payFormPlaces.isEmpty()) {
            faculties.setPayFormPlaces(NOT_VALID_IS_EMPTY);
        }
    }

    private void buildFreeFormPlaces(Faculties faculties, String freeFormPlaces) {
        buildOrUpdateFreeFormPlaces(faculties, freeFormPlaces);
        if (freeFormPlaces.isEmpty()) {
            faculties.setFreeFormPlaces(NOT_VALID_IS_EMPTY);
        }
    }

    private void buildAllPlaces(Faculties faculties, String allPlaces) {
        buildOrUpdateAllPlace(faculties, allPlaces);
        if (allPlaces.isEmpty()) {
            faculties.setAllPlace(NOT_VALID_IS_EMPTY);
        }
    }

    private void buildPassingScorePayForm(Faculties faculties, String passingScorePayForm) {
        buildOrUpdatePassingScorePayForm(faculties, passingScorePayForm);
        if (passingScorePayForm.isEmpty()) {
            faculties.setPassingScorePayForm(NOT_VALID_IS_EMPTY);
        }
    }

    private void buildPassingScoreFreeForm(Faculties faculties, String passingScoreFreeForm) {
        buildOrUpdatePassingScoreFreeForm(faculties, passingScoreFreeForm);
        if (passingScoreFreeForm.isEmpty()) {
            faculties.setPassingScoreFreeForm(NOT_VALID_IS_EMPTY);
        }
    }

    private void buildName(Faculties faculties, String name) {
        if (!name.isEmpty()) {
            faculties.setName(name);
        } else {
            faculties.setName(NOT_VALID_EMPTY_STRING);
        }
        if (isNumeric(name)) {
            faculties.setName(NOT_VALID_ISNUMBER);
        }
    }

    public Faculties buildingUpdate(HttpServletRequest request, Faculties faculties) {

        String name = request.getParameter("name");
        updateName(faculties, name);

        String passingScoreFreeForm = request.getParameter("passingScoreFreeForm");
        if (!passingScoreFreeForm.isEmpty())
            buildOrUpdatePassingScoreFreeForm(faculties, passingScoreFreeForm);

        String passingScorePayForm = request.getParameter("passingScorePayForm");
        if (!passingScorePayForm.isEmpty())
            buildOrUpdatePassingScorePayForm(faculties, passingScorePayForm);


        String allPlaces = request.getParameter("allPlaces");
        if (!allPlaces.isEmpty())
            buildOrUpdateAllPlace(faculties, allPlaces);


        String freeFormPlaces = request.getParameter("freeFormPlaces");
        if (!freeFormPlaces.isEmpty())
            buildOrUpdateFreeFormPlaces(faculties, freeFormPlaces);


        String payFormPlaces = request.getParameter("payFormPlaces");
        if (!payFormPlaces.isEmpty())
            buildOrUpdatePayFormPlaces(faculties, payFormPlaces);
        return faculties;
    }

    private void buildOrUpdateFreeFormPlaces(Faculties faculties, String freeFormPlaces) {
        if (isNumeric(freeFormPlaces)) {
            if (Long.parseLong(freeFormPlaces) > 0) {
                faculties.setFreeFormPlaces(Long.parseLong(freeFormPlaces));
            } else {
                faculties.setFreeFormPlaces(NOT_VALID_NEGATIVE_NUMBER);
            }
        }
        if (!isNumeric(freeFormPlaces)) {
            faculties.setFreeFormPlaces(NOT_VALID_IS_LETTER);
        }
    }

    private void buildOrUpdateAllPlace(Faculties faculties, String allPlaces) {
        if (isNumeric(allPlaces)) {
            if (Long.parseLong(allPlaces) > 0) {
                faculties.setAllPlace(Long.parseLong(allPlaces));
            } else {
                faculties.setAllPlace(NOT_VALID_NEGATIVE_NUMBER);
            }
        }
        if (!isNumeric(allPlaces)) {
            faculties.setAllPlace(NOT_VALID_IS_LETTER);
        }
    }

    private void buildOrUpdatePayFormPlaces(Faculties faculties, String payFormPlaces) {
        if (isNumeric(payFormPlaces)) {
            if (Long.parseLong(payFormPlaces) > 0) {
                faculties.setPayFormPlaces(Long.parseLong(payFormPlaces));
            } else {
                faculties.setPayFormPlaces(NOT_VALID_NEGATIVE_NUMBER);
            }
        }
        if (!isNumeric(payFormPlaces)) {
            faculties.setPayFormPlaces(NOT_VALID_IS_LETTER);
        }
    }

    private void buildOrUpdatePassingScorePayForm(Faculties faculties, String passingScorePayForm) {
        if (isNumeric(passingScorePayForm)) {
            if (Long.parseLong(passingScorePayForm) > 0) {
                faculties.setPassingScorePayForm(Long.parseLong(passingScorePayForm));
            } else {
                faculties.setPassingScorePayForm(NOT_VALID_NEGATIVE_NUMBER);
            }
        }
        if (!isNumeric(passingScorePayForm)) {
            faculties.setPassingScorePayForm(NOT_VALID_IS_LETTER);
        }
    }

    private void buildOrUpdatePassingScoreFreeForm(Faculties faculties, String passingScoreFreeForm) {
        if (isNumeric(passingScoreFreeForm)) {
            if (Long.parseLong(passingScoreFreeForm) > 0) {
                faculties.setPassingScoreFreeForm(Long.parseLong(passingScoreFreeForm));
            } else {
                faculties.setPassingScoreFreeForm(NOT_VALID_NEGATIVE_NUMBER);
            }
        }
        if (!isNumeric(passingScoreFreeForm)) {
            faculties.setPassingScoreFreeForm(NOT_VALID_IS_LETTER);
        }
    }

    private void updateName(Faculties faculties, String name) {
        if (!name.isEmpty()) {
            faculties.setName(name);
        }
        if (isNumeric(name)) {
            faculties.setName(NOT_VALID_ISNUMBER);
        }
    }
}
package com.my.finalProject.db.entity.entityImpl;

import com.my.finalProject.db.entity.Entity;

public class Faculties extends Entity {

    private static final long serialVersionUID = -5658255758974319134L;
    private String name;
    private Long passingScoreFreeForm;
    private Long passingScorePayForm;
    private Long allPlace;
    private Long freeFormPlaces;
    private Long payFormPlaces;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPassingScoreFreeForm() {
        return passingScoreFreeForm;
    }

    public void setPassingScoreFreeForm(Long passingScoreFreeForm) {
        this.passingScoreFreeForm = passingScoreFreeForm;
    }

    public Long getPassingScorePayForm() {
        return passingScorePayForm;
    }

    public void setPassingScorePayForm(Long passingScorePayForm) {
        this.passingScorePayForm = passingScorePayForm;
    }

    public Long getAllPlace() {
        return allPlace;
    }

    public void setAllPlace(Long allPlace) {
        this.allPlace = allPlace;
    }

    public Long getFreeFormPlaces() {
        return freeFormPlaces;
    }

    public void setFreeFormPlaces(Long freeFormPlaces) {
        this.freeFormPlaces = freeFormPlaces;
    }

    public Long getPayFormPlaces() {
        return payFormPlaces;
    }

    public void setPayFormPlaces(Long payFormPlaces) {
        this.payFormPlaces = payFormPlaces;
    }

    @Override
    public String toString() {
        return "Faculties [name= " + name
                + "passingScoreFreeForm" + passingScoreFreeForm
                + "passingScorePayForm= " + passingScorePayForm
                + "allPlace= " + allPlace
                + "freeFormPlaces= " + freeFormPlaces
                + "payFormPlaces= " + payFormPlaces + "]";
    }
}

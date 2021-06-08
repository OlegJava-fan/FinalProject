package com.my.finalProject.db.entity.entityImpl;

import com.my.finalProject.db.entity.Entity;

public class Certificate extends Entity {
    private static final long serialVersionUID = -6894236256733494958L;

    private Long mathematics;
    private Long chemistry;
    private Long physics;
    private Long english;
    private Long ukrainian;
    private Long informatics;
    private Long geography;
    private Long biology;
    private Long history;
    private Long gym;
    private Long averageScore;

    public void setAverageScore(Long averageScore) {
        this.averageScore = averageScore;
    }

    public Long getMathematics() {
        return mathematics;
    }

    public void setMathematics(Long mathematics) {
        this.mathematics = mathematics;
    }

    public Long getChemistry() {
        return chemistry;
    }

    public void setChemistry(Long chemistry) {
        this.chemistry = chemistry;
    }

    public Long getPhysics() {
        return physics;
    }

    public void setPhysics(Long physics) {
        this.physics = physics;
    }

    public Long getEnglish() {
        return english;
    }

    public void setEnglish(Long english) {
        this.english = english;
    }

    public Long getUkrainian() {
        return ukrainian;
    }

    public void setUkrainian(Long ukrainian) {
        this.ukrainian = ukrainian;
    }

    public Long getInformatics() {
        return informatics;
    }

    public void setInformatics(Long informatics) {
        this.informatics = informatics;
    }

    public Long getGeography() {
        return geography;
    }

    public void setGeography(Long geography) {
        this.geography = geography;
    }

    public Long getBiology() {
        return biology;
    }

    public void setBiology(Long biology) {
        this.biology = biology;
    }

    public Long getHistory() {
        return history;
    }

    public void setHistory(Long history) {
        this.history = history;
    }

    public Long getGym() {
        return gym;
    }

    public void setGym(Long gym) {
        this.gym = gym;
    }

    public Long getAverageScore() {
        if (averageScore == null||averageScore==0) {
            averageScore = mathematics + chemistry + physics + english + ukrainian
                    + informatics
                    + geography
                    + biology
                    + history
                    + gym;
        }
        return averageScore;
    }

    @Override
    public String toString() {
        return "Certificate [id = " + getId() + "mathematics= " + mathematics +
                "chemistry= " + chemistry + "physics= " + physics + "english= " + english
                + "ukrainian= " + ukrainian + "informatics=" + informatics + "geography= " + geography
                + "biology= " + biology + "history= " + history
                + "gym= " + gym + "averageScore= " + averageScore + "]";
    }
}

package com.my.finalProject.db.entity.entityImpl;

import com.my.finalProject.db.entity.Entity;

public class Act extends Entity {
    private static final long serialVersionUID = -5989236820235495800L;
    private Long account_id;
    private Long faculties_id;
    private String status;
    private String faculties_name;

    public String getActFacultiesName() {
        return faculties_name;
    }

    public void setActFacultiesName(String faculties_name) {
        this.faculties_name = faculties_name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getAccount_id() {
        return account_id;
    }

    public void setAccount_id(Long account_id) {
        this.account_id = account_id;
    }

    public Long getFaculties_id() {
        return faculties_id;
    }

    public void setFaculties_id(Long faculties_id) {
        this.faculties_id = faculties_id;
    }

    @Override
    public String toString() {
        return "Act [account_id= " + account_id
                + ", faculties_id= " + faculties_id + "]";
    }
}

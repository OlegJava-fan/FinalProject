package com.my.finalProject.db.entity.entityImpl;

import com.my.finalProject.db.entity.Entity;

public class Order extends Entity {
    private static final long serialVersionUID = -565498876459337483L;
    private Long account_id ;
    private Long status_id;
    private Long faculties_id;


    public Long getAccount_id() {
        return account_id;
    }

    public void setAccount_id(Long account_id) {
        this.account_id = account_id;
    }

    public Long getStatus_id() {
        return status_id;
    }

    public void setStatus_id(Long status_id) {
        this.status_id = status_id;
    }

    public Long getFaculties_id() {
        return faculties_id;
    }

    public void setFaculties_id(Long faculties_id) {
        this.faculties_id = faculties_id;
    }

    @Override
    public String toString() {
        return "Account [account_id= "+account_id
                +", status_id= "+ status_id
                +", faculties_id= " + faculties_id + "]";
    }
}

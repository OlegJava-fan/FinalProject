package com.my.finalProject.db.entity;

import com.my.finalProject.db.entity.entityImpl.Account;

import java.io.Serializable;

public enum Role implements Serializable {
   ADMIN, USER, BANNED;
    private static final long serialVersionUID = 965423425744534535L;
    public static Role getRole(Account account) {
        int roleId = account.getRole_id();
        return Role.values()[roleId-1];
    }

    public String getName() {
        return name().toLowerCase();
    }


}

package com.my.finalProject.db.entity;

import com.my.finalProject.db.entity.entityImpl.Order;

public enum StatusOrder {

    REJECTED,APPROVE_PAY_FORM,APPROVE_FREE_FORM;
    public static StatusOrder getStatus(Order order) {
        Long statusId = order.getStatus_id();
        return StatusOrder.values()[Math.toIntExact(statusId)-1];
    }

    public String getName() {
        return name().toLowerCase();
    }
}

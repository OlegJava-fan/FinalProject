package com.my.finalProject.db.entity;

import com.my.finalProject.db.entity.entityImpl.Account;

public enum StudyForm {
   DEFAULT_FORM,PAY_FORM,FREE_FORM;
    public static StudyForm getStudyForm(Account account) {
        Long studyForm_id = account.getStudyForm_id();
        return StudyForm.values()[Math.toIntExact(studyForm_id)-1];
    }

    public String getName() {
        return name().toLowerCase();
    }
}

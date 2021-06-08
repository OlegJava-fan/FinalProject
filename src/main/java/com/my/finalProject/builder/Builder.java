package com.my.finalProject.builder;

import com.my.finalProject.builder.exception.BuildException;

import javax.servlet.http.HttpServletRequest;

public abstract class Builder <T> {

    public abstract T build (HttpServletRequest request,T entity) throws BuildException;

    public boolean isNumeric(String str) {
        if (!str.isEmpty()) {
            for (char c : str.toCharArray()) {
                if (Character.isDigit(c)) return true;
            }
        }
        return false;
    }
}

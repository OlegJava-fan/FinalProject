package com.my.finalProject.validator;

import com.my.finalProject.db.exception.DBException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public abstract class Validator<T> {
    public abstract Map<String, String> validate(T entity) throws DBException;
}

package com.my.finalProject.web.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

public abstract class Command implements Serializable {
    private static final long serialVersionUID = 265256262652561651L;

    public abstract String execute(HttpServletRequest req, HttpServletResponse resp);

}

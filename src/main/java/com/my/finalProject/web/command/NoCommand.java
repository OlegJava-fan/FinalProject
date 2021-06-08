package com.my.finalProject.web.command;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.my.finalProject.Path.ERROR_PAGE;

public class NoCommand extends Command {
    private static final long serialVersionUID = -5422359766166866457L;

    private static final Logger LOGGER = Logger.getLogger(NoCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        LOGGER.debug("start command");
        String errorMassage = "dont find command";
        req.getSession().setAttribute("errorMassage",errorMassage);
        LOGGER.debug("finish command");
        return ERROR_PAGE;
    }
}

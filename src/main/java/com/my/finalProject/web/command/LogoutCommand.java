package com.my.finalProject.web.command;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.my.finalProject.Path.LOGIN_PAGE;

public class LogoutCommand extends Command {
    private static final long serialVersionUID = -924631597642356144L;
    private static final Logger LOGGER = Logger.getLogger(LogoutCommand.class.getName());

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.debug("Logout command start");
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return LOGIN_PAGE;
    }
}

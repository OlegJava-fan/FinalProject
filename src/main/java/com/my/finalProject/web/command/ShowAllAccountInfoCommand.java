package com.my.finalProject.web.command;

import com.my.finalProject.manager.AccountManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowAllAccountInfoCommand extends Command {
    private static final long serialVersionUID = -3240459007865090904L;
    private static final Logger LOGGER = Logger.getLogger(ShowAllAccountInfoCommand.class.getName());

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse resp) {
        LOGGER.debug("start command");
        return new AccountManager().findAllAccountInfo(request);

    }
}

package com.my.finalProject.web.command.update;

import com.my.finalProject.manager.AccountManager;
import com.my.finalProject.web.command.Command;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.my.finalProject.Path.*;

public class UpdateAccountCommand extends Command {
    private static final long serialVersionUID = -430298537134234254L;
    private static final Logger LOGGER = Logger.getLogger(UpdateAccountCommand.class.getName());

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse resp) {
        LOGGER.debug("start command");

        String forward;
        AccountManager accountManager = new AccountManager();

        String account_id = request.getParameter("account_id");
        LOGGER.trace("account id -->" + account_id);
        forward = accountManager.findAccountAndUpdateRole(request);
        if (ERROR_PAGE.equals(forward)) {
            return ERROR_PAGE;
        }
        request.getSession().removeAttribute("accountList");
        forward = accountManager.findAllAccountInfo(request);

        return forward;
    }
}

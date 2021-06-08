package com.my.finalProject.web.command.find;

import com.my.finalProject.db.AccountDAO;
import com.my.finalProject.db.ActDAO;
import com.my.finalProject.db.entity.entityImpl.Account;
import com.my.finalProject.db.entity.entityImpl.Act;
import com.my.finalProject.db.exception.DBException;
import com.my.finalProject.web.command.Command;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static com.my.finalProject.Fields.DEFAULT_FACULTIES_NAME;
import static com.my.finalProject.Path.ERROR_PAGE;
import static com.my.finalProject.Path.MY_ACCOUNT_ACT;

public class FindAccountActCommand extends Command {
    private static final long serialVersionUID = -2934579823476852340L;
    private static final Logger LOGGER = Logger.getLogger(FindAccountActCommand.class.getName());

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse resp) {
        LOGGER.debug("start command ");
        String account_id = request.getParameter("id");
        List<Act> accountActList;
        Account account;
        try {
            account = AccountDAO.getInstance().findAccountByID(Long.parseLong(account_id));
        } catch (DBException | NumberFormatException e) {
            LOGGER.error("dont find account", e);
            request.getSession(). setAttribute("errorMassage", e.getMessage());
            return ERROR_PAGE;
        }

        if (!account.getFaculties_name().equals(DEFAULT_FACULTIES_NAME)) {
            request.setAttribute("alreadyAcceptedFaculty", "You already accepted faculty " + account.getFaculties_name());
            return MY_ACCOUNT_ACT;
        }


        try {
            accountActList = ActDAO.getInstance().findAllActByAccountID(Long.parseLong(account_id));
            request.getSession().setAttribute("accountActList", accountActList);
            LOGGER.trace(accountActList.size());
        } catch (DBException e) {
            LOGGER.error("cant find all account acts ", e);
            request.getSession(). setAttribute("errorMassage", e.getMessage());
            return ERROR_PAGE;
        }
        LOGGER.debug("command finish");
        return MY_ACCOUNT_ACT;
    }
}

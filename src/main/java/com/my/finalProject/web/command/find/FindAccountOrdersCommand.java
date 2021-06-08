package com.my.finalProject.web.command.find;

import com.my.finalProject.db.*;
import com.my.finalProject.db.entity.entityImpl.*;
import com.my.finalProject.db.exception.DBException;
import com.my.finalProject.web.command.Command;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.List;

import static com.my.finalProject.Path.*;

public class FindAccountOrdersCommand extends Command {
    private static final long serialVersionUID = -562534554434523454L;
    private static final Logger LOGGER = Logger.getLogger(FindAccountCertificateCommand.class.getName());

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse resp) {
        LOGGER.debug("start command");
        String account_id = request.getParameter("account_id");
        LOGGER.trace("account_id -->" + account_id);
        List<Order> orderList;
        List<Faculties> facultiesListGroupByOrder = new ArrayList<>();
        Certificate certificate;
        List<Act> acts = new ArrayList<>();
        Account account = null;
        try {
            account = AccountDAO.getInstance().findAccountByID(Long.parseLong(account_id));
        }catch (DBException e){
            LOGGER.error("can not find current account", e);
            request.getSession().setAttribute("errorMassage", e.getMessage());
            return ERROR_PAGE;
        }

        try {
            request.getSession().setAttribute("currentAccountInfo", account);
            acts = ActDAO.getInstance().findAllActByAccountID(Long.parseLong(account_id));
        } catch (DBException | NumberFormatException | NullPointerException e) {
            LOGGER.error("can not find all account acts", e);
            request.getSession().setAttribute("errorMassage", e.getMessage());
        }
        if (acts.size() > 0) {
            LOGGER.error("act for this user already exists");
            request.getSession().setAttribute("actExists", "act for this user already exists");
            request.getSession().setAttribute("accountIdActExists", account_id);
            return SHOW_ALL_ACCOUNT;
        }

        try {
            certificate = CertificateDAO.getInstance().findCertificateByID(Long.parseLong(account_id));
            request.getSession().setAttribute("certificateAccount", certificate);
            orderList = OrderDAO.getInstance().findAllOrderByAccountID(Long.parseLong(account_id));
            request.getSession().setAttribute("orderList", orderList);
            for (Order orderItem : orderList) {
                facultiesListGroupByOrder.add(FacultiesDAO.getInstance().findFacultiesByID(orderItem.getFaculties_id()));
            }
            request.getSession().setAttribute("facultiesListGroupByOrder", facultiesListGroupByOrder);
        } catch (DBException e) {

            request.getSession().setAttribute("errorMassage", e.getMessage());
            return ERROR_PAGE;
        }

            LOGGER.debug("command finish");
            return CREATE_ACT + "?account_id=" + account.getId();
    }
}

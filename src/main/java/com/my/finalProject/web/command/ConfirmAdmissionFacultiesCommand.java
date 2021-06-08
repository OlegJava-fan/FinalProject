package com.my.finalProject.web.command;

import com.my.finalProject.db.*;
import com.my.finalProject.db.exception.DBException;
import com.my.finalProject.db.entity.entityImpl.Account;
import com.my.finalProject.db.entity.entityImpl.Faculties;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;

import static com.my.finalProject.Fields.*;
import static com.my.finalProject.Path.ERROR_PAGE;
import static com.my.finalProject.Path.MY_ACCOUNT_ACT;

public class ConfirmAdmissionFacultiesCommand extends Command {
    private static final long serialVersionUID = -2889622347685234009L;
    private static final Logger LOGGER = Logger.getLogger(ConfirmAdmissionFacultiesCommand.class.getName());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        LOGGER.debug("start command ");
        String account_id = req.getParameter("account_id");
        String faculties_id = req.getParameter("faculties_id");
        String status = req.getParameter("status");

        Faculties faculties;
        Account account;

        try {
            faculties = FacultiesDAO.getInstance().findFacultiesByID(Long.parseLong(faculties_id));
            account = AccountDAO.getInstance().findAccountByID(Long.parseLong(account_id));

        } catch (DBException e) {
            req.getSession().setAttribute("errorMassage", e.getMessage());
            return ERROR_PAGE;
        }

        if (!status.isEmpty()) {
            account.setFaculties_name(faculties.getName());
            if (PAY_FORM_STUDY.equals(status)) {
                account.setStudyForm_id(PAY_FORM);
                faculties.setPayFormPlaces(faculties.getPayFormPlaces() - 1);
            }
            if (FREE_FORM_STUDY.equals(status)) {
                account.setStudyForm_id(FREE_FORM);
                faculties.setFreeFormPlaces(faculties.getFreeFormPlaces() - 1);
            }
            faculties.setAllPlace(faculties.getAllPlace() - 1);

            //transaction

            Connection connection = DBManager.getInstance().getConnection();


            try {
                FacultiesDAO.getInstance().updateFaculties(connection, faculties);
                AccountDAO.getInstance().updateAccount(connection, account);

                DBManager.getInstance().commit(connection);
            } catch (DBException e) {
                req.getSession().setAttribute("errorMassage", e.getMessage());
                DBManager.getInstance().rollback(connection);
                return ERROR_PAGE;
            } finally {
                DBManager.getInstance().closeResource(connection);
            }


            account = AccountDAO.getInstance().findAccountByID(Long.parseLong(account_id));


            LOGGER.debug("finish command");

            req.getSession().removeAttribute("accountActList");

            req.setAttribute("passedOnFacultiesName", account.getFaculties_name());
            req.setAttribute("passedOnFacultiesStudyForm", status);
            return MY_ACCOUNT_ACT;

        }
        String errorMassage = "find status name is empty";
        LOGGER.error(errorMassage);
        req.setAttribute("errorMassage", errorMassage);
        return ERROR_PAGE;
    }
}

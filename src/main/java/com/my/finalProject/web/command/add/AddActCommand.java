package com.my.finalProject.web.command.add;

import com.my.finalProject.Path;
import com.my.finalProject.db.ActDAO;
import com.my.finalProject.db.DBManager;
import com.my.finalProject.db.entity.entityImpl.Act;
import com.my.finalProject.db.entity.entityImpl.Faculties;
import com.my.finalProject.db.entity.entityImpl.Order;
import com.my.finalProject.db.exception.DBException;
import com.my.finalProject.web.command.Command;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.util.List;

import static com.my.finalProject.Fields.FREE_FORM_STUDY;
import static com.my.finalProject.Fields.PAY_FORM_STUDY;
import static com.my.finalProject.Path.CREATE_ACT;

public class AddActCommand extends Command {
    private static final long serialVersionUID = -8963607423490635074L;
    private static final Logger LOGGER = Logger.getLogger(AddActCommand.class.getName());

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse resp) {
        LOGGER.trace("start command");
        String account_id = request.getParameter("account_id");
        String forward = Path.ERROR_PAGE;
        Act act;
        List<Act> ifExistAct;
        try {
            ifExistAct = ActDAO.getInstance().findAllActByAccountID(Long.parseLong(account_id));
        } catch (DBException e) {
            LOGGER.error("cant find acts", e);
            request.getSession().setAttribute("errorMassage", e.getMessage());
            return forward;
        }
        if (ifExistAct.size() > 0) {
            request.getSession().setAttribute("actsAlreadyExist", "account already has an act");
            return CREATE_ACT + "?account_id=" + account_id;
        }


        List<Faculties> faculties = (List<Faculties>) request.getSession().getAttribute("facultiesListGroupByOrder");
        List<Order> orders = (List<Order>) request.getSession().getAttribute("orderList");

// данные из листов : факультеты и заявки, отобранных по айди пользователя, добавляем в акт
// Транзакция
        Connection connection = DBManager.getInstance().getConnection();
        try {
            int counter = 0;
            for (Faculties item : faculties) {
                act = new Act();
                act.setAccount_id(Long.parseLong(account_id));
                act.setFaculties_id(item.getId());
                Long status_id = orders.get(counter).getStatus_id();
                if (status_id == 1) {
                    act.setStatus("REJECT");
                }
                if (status_id == 2) {
                    act.setStatus(PAY_FORM_STUDY);
                }
                if (status_id == 3) {
                    act.setStatus(FREE_FORM_STUDY);
                }
                act.setActFacultiesName(item.getName());
                counter++;
                ActDAO.getInstance().addAct(connection, act);
            }
            DBManager.getInstance().commit(connection);
        } catch (DBException e) {
            LOGGER.error("transaction fail ", e);
            request.getSession().setAttribute("errorMassage", e.getMessage());
            DBManager.getInstance().rollback(connection);
            return forward;
        } finally {
            DBManager.getInstance().closeResource(connection);
        }

        request.getSession().setAttribute("actSuccessfullyCreated", "act successfully created");
        LOGGER.trace("end command");
        return CREATE_ACT + "?account_id=" + account_id;
    }
}

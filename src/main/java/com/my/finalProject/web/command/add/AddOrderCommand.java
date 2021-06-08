package com.my.finalProject.web.command.add;

import com.my.finalProject.Path;
import com.my.finalProject.db.CertificateDAO;
import com.my.finalProject.db.exception.DBException;
import com.my.finalProject.db.FacultiesDAO;
import com.my.finalProject.db.OrderDAO;
import com.my.finalProject.db.entity.entityImpl.Certificate;
import com.my.finalProject.db.entity.entityImpl.Faculties;
import com.my.finalProject.db.entity.entityImpl.Order;
import com.my.finalProject.web.command.Command;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.my.finalProject.Fields.*;
import static com.my.finalProject.Path.CLIENT_PAGE;


public class AddOrderCommand extends Command {
    private static final long serialVersionUID = -434609823457635614L;
    private static final Logger LOGGER = Logger.getLogger(AddOrderCommand.class.getName());

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.trace("start command");
        String errorMassage;
        String forward = Path.ERROR_PAGE;
        Order order = new Order();
        Faculties faculties = null;
        Certificate certificate = null;
        String account_id = request.getParameter("account_id");
        String faculties_id = request.getParameter("faculties_id");
        Order orderIfExist;

        if (account_id != null) {
            try {
                orderIfExist =
                        OrderDAO.getInstance().findOrderByAccountIdAndFacultiesId(Long.parseLong(account_id), Long.parseLong(faculties_id));
            } catch (DBException e) {
                LOGGER.error("cant find order by account and faculty id", e);
                request.getSession().setAttribute("errorMassage", e.getMessage());
                return forward;
            }
            if (orderIfExist != null) {
                request.getSession().setAttribute("facultiesExistInOrder", faculties_id);
                return CLIENT_PAGE;
            }

            order.setAccount_id(Long.parseLong(account_id));
            try {
                certificate = CertificateDAO.getInstance().findCertificateByID(Long.parseLong(account_id));
            } catch (DBException e) {
                LOGGER.error("cant find certificate", e);
                request.getSession().setAttribute("errorMassage", e.getMessage());
                return forward;
            }
        }

        if (faculties_id != null) {
            order.setFaculties_id(Long.parseLong(faculties_id));

            try {
                faculties = FacultiesDAO.getInstance().findFacultiesByID(Long.parseLong(faculties_id));
            } catch (DBException e) {
                LOGGER.error("cant find faculties ", e);
                request.getSession().setAttribute("errorMassage", e.getMessage());
                return forward;
            }
        }

        long status = DEFAULT_ORDER_STATUS;
        if (certificate != null && faculties != null) {
            if (certificate.getAverageScore() >= faculties.getPassingScorePayForm()) {
                status = PAY_FORM_ORDER_STATUS;
            }
            if (certificate.getAverageScore() >= faculties.getPassingScoreFreeForm()) {
                status = FREE_FORM_ORDER_STATUS;
            }
        }

        order.setStatus_id(status);
        LOGGER.trace(order.getStatus_id());
        if (faculties_id == null || account_id == null || faculties_id.isEmpty()) {
            errorMassage = "Name is Empty";
            request.getSession().setAttribute("errorMassage", errorMassage);
            LOGGER.error("errorMassage " + errorMassage);
            return forward;
        }
        try {
            OrderDAO.getInstance().addOrder(order);

        } catch (DBException e) {
            LOGGER.error("cant add new order");
            request.getSession().setAttribute("errorMassage", e.getMessage());
            return forward;
        }
        LOGGER.debug("Command finish");

        return CLIENT_PAGE;
    }
}

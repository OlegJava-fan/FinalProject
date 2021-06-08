package com.my.finalProject.web.command.update;

import com.my.finalProject.db.exception.DBException;

import com.my.finalProject.db.OrderDAO;

import com.my.finalProject.db.entity.entityImpl.Order;
import com.my.finalProject.web.command.Command;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import static com.my.finalProject.Path.ERROR_PAGE;

public class UpdateOrderCommand extends Command {
    private static final long serialVersionUID = -8934567236469852454L;
    private static final Logger LOGGER = Logger.getLogger(UpdateOrderCommand.class.getName());

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse resp) {
        LOGGER.debug("start command");
        String errorMassage;
        Order order = null;

        String faculties_id = request.getParameter("faculties_id");
        String id = request.getParameter("id");
        String status_id = request.getParameter("status_id");
        if (!id.isEmpty()) {
            try {
                order = OrderDAO.getInstance().findOrderByID(Long.parseLong(id));
                LOGGER.trace("order" + order);
            } catch (DBException e) {
                request.getSession().setAttribute("errorMassage", e.getMessage());
                return ERROR_PAGE;
            }
        }
        if (order != null) {

            if (!status_id.isEmpty()) {

                order.setStatus_id(Long.parseLong(status_id));
            }
            if (!faculties_id.isEmpty()) {

                order.setFaculties_id(Long.parseLong(faculties_id));
            }

            try {
                OrderDAO.getInstance().UpdateOrder(order);
            } catch (DBException e) {
                request.getSession().setAttribute("errorMassage", e.getMessage());
                return ERROR_PAGE;
            }
            LOGGER.debug("finish command");
            request.getSession().removeAttribute("facultiesListGroupByOrder");
            return "controller?command=findAccountOrders";
        }
        errorMassage = "order == null";
        request.getSession().setAttribute("errorMassage", errorMassage);
        return ERROR_PAGE;
    }
}
package com.my.finalProject.web.command.find;

import com.my.finalProject.db.exception.DBException;
import com.my.finalProject.db.FacultiesDAO;
import com.my.finalProject.db.OrderDAO;
import com.my.finalProject.db.entity.entityImpl.Faculties;
import com.my.finalProject.db.entity.entityImpl.Order;
import com.my.finalProject.web.command.Command;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

import static com.my.finalProject.Path.ERROR_PAGE;
import static com.my.finalProject.Path.MY_ORDERS;

public class FindCurrentAccountOrdersCommand extends Command {
    private static final long serialVersionUID = -5654572544346736454L;
    private static final Logger LOGGER = Logger.getLogger(FindCurrentAccountOrdersCommand.class.getName());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        LOGGER.debug("start command");

        String account_id = req.getParameter("account_id");
        LOGGER.trace("account_id -->" + account_id);
        List<Order> orderList;
        List<Faculties> facultiesListGroupByOrder = new ArrayList<>();

        try {
            orderList = OrderDAO.getInstance().findAllOrderByAccountID(Long.parseLong(account_id));
            LOGGER.trace("order list " + orderList.size());
            req.getSession().setAttribute("orderList", orderList);
            for (Order orderItem : orderList) {
                facultiesListGroupByOrder.add(FacultiesDAO.getInstance().findFacultiesByID(orderItem.getFaculties_id()));
            }
            LOGGER.trace("faculties List Group By Order" + facultiesListGroupByOrder.size());
            req.getSession().setAttribute("facultiesListGroupByOrder", facultiesListGroupByOrder);
        } catch (DBException e) {
            LOGGER.error("cant create list faculties which the user applied",e);
            req.getSession().setAttribute("errorMassage", e.getMessage());
            return ERROR_PAGE;
        }
        LOGGER.debug("command finish");
        return MY_ORDERS;
    }
}

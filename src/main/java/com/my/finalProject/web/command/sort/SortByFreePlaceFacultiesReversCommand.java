package com.my.finalProject.web.command.sort;

import com.my.finalProject.db.entity.entityImpl.Account;
import com.my.finalProject.db.entity.entityImpl.Faculties;
import com.my.finalProject.db.entity.Role;
import com.my.finalProject.web.command.Command;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Comparator;
import java.util.List;

import static com.my.finalProject.Path.*;

public class SortByFreePlaceFacultiesReversCommand extends Command {
    private static final long serialVersionUID = 3367486875463523534L;
    private static final Logger LOGGER = Logger.getLogger(SortByFreePlaceFacultiesReversCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        LOGGER.debug("start command");

        try {
            List<Faculties> facultiesList = (List<Faculties>) req.getSession().getAttribute("facultiesList");
            Comparator<Faculties> comparator = Comparator.comparing(Faculties::getFreeFormPlaces).reversed();
            facultiesList.sort(comparator);
            req.getSession().setAttribute("facultiesList", facultiesList);
            Account account = (Account) req.getSession().getAttribute("account");
            Role role = Role.getRole(account);
            LOGGER.trace("role = " + role);
            if (role == Role.ADMIN) {
                return ADMIN_PAGE;
            }
            if (role == Role.USER) {
                return CLIENT_PAGE;
            }
        } catch (NullPointerException e) {
            LOGGER.debug("or your session expired");
            req.getSession().setAttribute("errorMassage", "or your session expired");
            return ERROR_PAGE;
        }
        LOGGER.error("you dont have a role");
        req.getSession().setAttribute("errorMassage", "you dont have a role");
        return ERROR_PAGE;
    }

}

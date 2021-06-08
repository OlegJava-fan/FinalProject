package com.my.finalProject.web.command.delete;

import com.my.finalProject.Path;
import com.my.finalProject.db.exception.DBException;
import com.my.finalProject.db.FacultiesDAO;
import com.my.finalProject.db.entity.entityImpl.Faculties;
import com.my.finalProject.web.command.Command;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

import static com.my.finalProject.Path.ADMIN_PAGE;

public class DeleteFacultiesCommand extends Command {
    private static final long serialVersionUID = -41970323457631214L;
    private static final Logger LOGGER = Logger.getLogger(DeleteFacultiesCommand.class.getName());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        LOGGER.debug("start command");
        String errorMassage;
        String forward = Path.ERROR_PAGE;
        String faculties_id = req.getParameter("faculties_id");

        try {
            FacultiesDAO.getInstance().deleteFacultiesByName(Long.parseLong(faculties_id));

            HttpSession session = req.getSession();
            session.removeAttribute("facultiesList");
            List<Faculties> facultiesList = FacultiesDAO.getInstance().findAllFaculties();
            session.setAttribute("facultiesList", facultiesList);


        } catch (DBException e) {
            errorMassage = " delete faculties by id fail";
            LOGGER.error(errorMassage, e);
            req.getSession().setAttribute("errorMassage", errorMassage);
            return forward;
        }

        LOGGER.debug("Command finish");
        return ADMIN_PAGE;
    }
}

package com.my.finalProject.web.command;

import com.my.finalProject.db.FacultiesDAO;
import com.my.finalProject.db.entity.entityImpl.Faculties;
import com.my.finalProject.db.exception.DBException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static com.my.finalProject.Path.ADMIN_PAGE;

public class ShowLessFiveCommand extends Command {
    private static final long serialVersionUID = -825346723865090904L;
    private static final Logger LOGGER = Logger.getLogger(ShowLessFiveCommand.class.getName());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        LOGGER.debug("start command");
        try {

            List<Faculties> lessFiveFaculties = FacultiesDAO.getInstance().findFacultiesLessFive();
            req.getSession().setAttribute("lessFiveFaculties", lessFiveFaculties);
        }catch (DBException e){
            LOGGER.error("cant find less then five faculties");
            req.setAttribute("errorMassage", e.getMessage());
        }

        LOGGER.debug("finish command");
        return "showLessFive.jsp";
    }
}

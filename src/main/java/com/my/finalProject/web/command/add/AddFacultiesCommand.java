package com.my.finalProject.web.command.add;

import com.my.finalProject.Path;

import com.my.finalProject.builder.impl.FacultiesBuilder;
import com.my.finalProject.db.exception.DBException;
import com.my.finalProject.db.FacultiesDAO;
import com.my.finalProject.db.entity.entityImpl.Faculties;


import com.my.finalProject.validator.impl.FacultiesValidator;
import com.my.finalProject.web.command.Command;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Map;

import static com.my.finalProject.Path.ADD_FACULTIES;
import static com.my.finalProject.Path.ADMIN_PAGE;

public class AddFacultiesCommand extends Command {
    private static final long serialVersionUID = 123344287645534565L;
    private static final Logger LOGGER = Logger.getLogger(AddFacultiesCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.debug("Start Command");

        String forward = Path.ERROR_PAGE;

        Faculties faculties = new Faculties();

        faculties = new FacultiesBuilder().build(request, faculties);

        Map<String, String> mapNotValidFields = new FacultiesValidator().validate(faculties);
        if (mapNotValidFields.size() > 0) {
            request.getSession().setAttribute("mapNotValidFields", mapNotValidFields);
            LOGGER.debug("not valid fields");
            return ADD_FACULTIES;
        }

        try {
            FacultiesDAO.getInstance().addFaculties(faculties);

        } catch (DBException e) {
            LOGGER.error("cant add new faculty");
            request.getSession().setAttribute("errorMassage", e.getMessage());
            return forward;
        }
        try {
            request.getSession().removeAttribute("facultiesList");
            List<Faculties> facultiesList = FacultiesDAO.getInstance().findAllFaculties();
            request.getSession().setAttribute("facultiesList", facultiesList);
        } catch (DBException e) {
            LOGGER.error("cant update list faculties",e);
            request.getSession().setAttribute("errorMassage", e.getMessage());
            return forward;
        }
        return ADMIN_PAGE;
    }
}

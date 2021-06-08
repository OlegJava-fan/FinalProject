package com.my.finalProject.manager;

import com.my.finalProject.builder.impl.FacultiesBuilder;
import com.my.finalProject.db.FacultiesDAO;
import com.my.finalProject.db.entity.entityImpl.Faculties;
import com.my.finalProject.db.exception.DBException;
import com.my.finalProject.validator.impl.FacultiesValidator;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

import static com.my.finalProject.Path.ADMIN_PAGE;
import static com.my.finalProject.Path.ERROR_PAGE;

public class FacultiesManager {
    private static final Logger LOGGER = Logger.getLogger(FacultiesManager.class.getName());

    public Faculties findFacultyById(String facultyId) {
        return FacultiesDAO.getInstance().findFacultiesByID(Long.parseLong(facultyId));
    }



    public String updateFaculty(HttpServletRequest request, Faculties faculties) {
        try {
            FacultiesDAO.getInstance().updateFaculties(null, faculties);
        } catch (DBException e) {
            LOGGER.error("cant update faculty ", e);
            request.getSession().setAttribute("errorMassage", e.getMessage());
            return ERROR_PAGE;
        }
        return ADMIN_PAGE;
    }

    public String findAllCurrentFaculties(HttpServletRequest request) {
        try {
            List<Faculties> facultiesList = FacultiesDAO.getInstance().findAllFaculties();
            request.getSession().setAttribute("facultiesList", facultiesList);
        } catch (DBException e) {
            LOGGER.error("cant find all faculties ", e);
            request.getSession().setAttribute("errorMassage", e.getMessage());
            return ERROR_PAGE;
        }
        return ADMIN_PAGE;
    }

}

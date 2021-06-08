package com.my.finalProject.web.command.update;


import com.my.finalProject.builder.impl.FacultiesBuilder;
import com.my.finalProject.manager.FacultiesManager;
import com.my.finalProject.db.entity.entityImpl.Faculties;

import com.my.finalProject.db.exception.DBException;
import com.my.finalProject.validator.impl.FacultiesValidator;
import com.my.finalProject.web.command.Command;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Map;

import static com.my.finalProject.Path.*;


public class UpdateFacultiesCommand extends Command {
    private static final long serialVersionUID = -2340234423445090904L;
    private static final Logger LOGGER = Logger.getLogger(UpdateFacultiesCommand.class.getName());

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse resp) {
        LOGGER.debug("start command");
        String forward;
        FacultiesManager facultiesService = new FacultiesManager();
        String faculties_id = request.getParameter("faculties_id");
        Faculties faculties;
        Faculties facultiesUpdate = null;
        try {
            faculties = facultiesService.findFacultyById(faculties_id);
        } catch (DBException e) {
            request.getSession().setAttribute("errorMassage", e.getMessage());
            return ERROR_PAGE;
        }

        if (faculties != null) {
            facultiesUpdate = new FacultiesBuilder().buildingUpdate(request, faculties);
            Map<String, String> mapNotValidToUpdateFields = new FacultiesValidator().validate(faculties);
            if (mapNotValidToUpdateFields.size() > 0) {

                request.getSession().setAttribute("mapNotValidToUpdateFields", mapNotValidToUpdateFields);
                return "updateFaculties.jsp?name=" + faculties.getName()
                        + "&passingScorePayForm=" + faculties.getPassingScorePayForm()
                        + "&passingScoreFreeForm=" + faculties.getPassingScoreFreeForm()
                        + "&allPlace=" + faculties.getAllPlace()
                        + "&freeFormPlaces=" + faculties.getFreeFormPlaces()
                        + "&payFormPlaces=" + faculties.getPayFormPlaces()
                        + "&faculties_id=" + faculties.getId();
            }
        }


        forward = facultiesService.updateFaculty(request, facultiesUpdate);
        if (ERROR_PAGE.equals(forward)) {
            return ERROR_PAGE;
        }
        request.getSession().removeAttribute("facultiesList");

        forward = facultiesService.findAllCurrentFaculties(request);

        return forward;
    }
}

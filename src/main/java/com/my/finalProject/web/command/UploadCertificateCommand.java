package com.my.finalProject.web.command;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import static com.my.finalProject.Path.ERROR_PAGE;
import static com.my.finalProject.Path.CLIENT_PAGE;

public class UploadCertificateCommand extends Command {
    private static final long serialVersionUID = -540328784362724357L;

    private static final Logger LOGGER = Logger.getLogger(UploadCertificateCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        try {

            Part part = req.getPart("file");
            LOGGER.trace(part);

            String fileName = part.getSubmittedFileName();
            String path = "files/" + fileName;

            path = req.getServletContext().getRealPath(path);

            LOGGER.trace(path);

            Files.copy(part.getInputStream(),
                    Paths.get(path),
                    StandardCopyOption.REPLACE_EXISTING);

        } catch (ServletException | IOException e) {
            LOGGER.error("cant upload file", e);
            req.getSession().setAttribute("errorMassage", e.getMessage());
            return ERROR_PAGE;
        }
        return CLIENT_PAGE;
    }
}

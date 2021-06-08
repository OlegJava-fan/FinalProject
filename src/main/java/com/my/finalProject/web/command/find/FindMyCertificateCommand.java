package com.my.finalProject.web.command.find;

import com.my.finalProject.db.CertificateDAO;
import com.my.finalProject.db.entity.entityImpl.Certificate;
import com.my.finalProject.db.exception.DBException;
import com.my.finalProject.web.command.Command;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.my.finalProject.Path.ERROR_PAGE;
import static com.my.finalProject.Path.MY_INFO;

public class FindMyCertificateCommand extends Command {
    private static final long serialVersionUID = -7364501289712023794L;
    private static final Logger LOGGER = Logger.getLogger(FindMyCertificateCommand.class.getName());


    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String certificate_id = req.getParameter("certificate_id");
        Certificate certificate;
        if (certificate_id.isEmpty()) {
            LOGGER.error("certificate is empty");
            return ERROR_PAGE;
        }
        try {
            certificate = CertificateDAO.getInstance().findCertificateByID(Long.parseLong(certificate_id));
            req.setAttribute("myCertificate", certificate);
        } catch (DBException | NumberFormatException e) {
            LOGGER.error("cant find account certificate", e);
            req.getSession().setAttribute("errorMassage", e.getMessage());
            return ERROR_PAGE;
        }
        return MY_INFO;
    }
}


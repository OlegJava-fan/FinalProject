package com.my.finalProject.web.command.find;

import com.my.finalProject.db.CertificateDAO;
import com.my.finalProject.db.exception.DBException;
import com.my.finalProject.db.entity.entityImpl.Certificate;
import com.my.finalProject.web.command.Command;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.my.finalProject.Path.ERROR_PAGE;
import static com.my.finalProject.Path.SHOW_ACCOUNT_CERTIFICATE;

public class FindAccountCertificateCommand extends Command {

    private static final long serialVersionUID = -430298537134234254L;
    private static final Logger LOGGER = Logger.getLogger(FindAccountCertificateCommand.class.getName());

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse resp) {
        LOGGER.debug("start command");
        String errorMassage;

        String certificate_id = request.getParameter("certificate_id");
        LOGGER.trace("certificate_id -->" + certificate_id);
        Certificate certificate;
        try {
            certificate = CertificateDAO.getInstance().findCertificateByID(Long.parseLong(certificate_id));
            request.setAttribute("certificate", certificate);
        } catch (DBException e) {
            errorMassage = "find certificate by id fail";
            LOGGER.error(errorMassage, e);
            request.getSession().setAttribute("errorMassage", errorMassage);
            return ERROR_PAGE;
        }
        LOGGER.debug("command finish");
        return SHOW_ACCOUNT_CERTIFICATE;
    }
}

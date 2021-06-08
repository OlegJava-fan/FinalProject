package com.my.finalProject.web.command;

import com.my.finalProject.builder.exception.BuildException;
import com.my.finalProject.builder.impl.AccountBuilder;
import com.my.finalProject.builder.impl.CertificateBuilder;
import com.my.finalProject.manager.AccountManager;
import com.my.finalProject.db.entity.entityImpl.Account;
import com.my.finalProject.db.entity.entityImpl.Certificate;
import com.my.finalProject.db.exception.DBException;
import com.my.finalProject.validator.impl.AccountValidator;
import com.my.finalProject.validator.impl.CertificateValidator;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

import static com.my.finalProject.Path.ERROR_PAGE;
import static com.my.finalProject.Path.REGISTRATION_PAGE;

public class RegistrationCommand extends Command {
    private static final long serialVersionUID = -924316421358356144L;
    private static final Logger LOGGER = Logger.getLogger(RegistrationCommand.class.getName());

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.debug("Start Command");
        String forward = ERROR_PAGE;

        AccountManager accountService = new AccountManager();

        Account account;
        Map<String, String> notValidAccountField ;
        try {
            account = new AccountBuilder().build(request, new Account());
            notValidAccountField = new AccountValidator().validate(account);
        } catch (BuildException |DBException e) {
            LOGGER.error("cant validate account",e);
            request.setAttribute("errorMassage", e.getMessage());
            return forward;
        }

        Certificate certificate = new CertificateBuilder().build(request, new Certificate());
        Map<String, String> notValidCertificateField = new CertificateValidator().validate(certificate);

        boolean isValidEntity = accountService.isValid(request, notValidAccountField, notValidCertificateField);
        if (!isValidEntity) {
            return REGISTRATION_PAGE;
        }

        forward = accountService.addAccount(request, certificate, account);
        LOGGER.debug("End Command");
        return forward;
    }
}


package com.my.finalProject.manager;

import com.my.finalProject.builder.impl.AccountBuilder;
import com.my.finalProject.db.AccountDAO;
import com.my.finalProject.db.CertificateDAO;
import com.my.finalProject.db.DBManager;
import com.my.finalProject.db.entity.entityImpl.Account;
import com.my.finalProject.db.entity.entityImpl.Certificate;
import com.my.finalProject.db.exception.DBException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

import static com.my.finalProject.Path.*;

public class AccountManager {
    private static final Logger LOGGER = Logger.getLogger(AccountManager.class.getName());

    public String addAccount(HttpServletRequest request, Certificate certificate, Account account) {
        Connection connection = DBManager.getInstance().getConnection();
        try {
            CertificateDAO.getInstance().addCertificate(connection, certificate);
        } catch (DBException e) {
            LOGGER.error("cant add certificate", e);
            request.getSession().setAttribute("errorMassage", e.getMessage());
            return ERROR_PAGE;
        }
        if (certificate.getId() != null) {
            account.setCertificate_id(certificate.getId());
        }

        try {
            AccountDAO.getInstance().addAccount(connection, account);
            DBManager.getInstance().commit(connection);
        } catch (DBException e) {
            LOGGER.error("cant add account", e);
            request.getSession().setAttribute("errorMassage", e.getMessage());
            return ERROR_PAGE;
        } finally {
            DBManager.getInstance().closeResource(connection);
        }

        request.getSession().setAttribute("regSuccessfully", "Registration successfully");

        return LOGIN_PAGE;
    }

    public boolean isValid(HttpServletRequest request, Map<String, String> notValidAccountField, Map<String, String> notValidCertificateField) {
        if (notValidAccountField.size() > 0 || notValidCertificateField.size() > 0) {
            request.getSession().setAttribute("notValidAccountField", notValidAccountField);

            if (notValidCertificateField.size() > 0) {
                request.getSession().setAttribute("notValidCertificateField", notValidCertificateField);

            }
            return false;
        }
        return true;
    }

    public String findAllAccountInfo(HttpServletRequest request) {
        List<Account> accountList;
        List<Certificate> certificateList;
        try {
            accountList = AccountDAO.getInstance().findAllAccount();
            request.getSession().setAttribute("accountList", accountList);
            certificateList = CertificateDAO.getInstance().findAllCertificate();
            request.getSession().setAttribute("certificateList", certificateList);
        } catch (DBException e) {
            LOGGER.error(" cant find all account info", e);
            request.getSession().setAttribute("errorMassage", e);
            return ERROR_PAGE;
        }
        return SHOW_ALL_ACCOUNT;
    }

    public String findAccountAndUpdateRole(HttpServletRequest request) {
        String account_id = request.getParameter("account_id");

        try {
            Account account = AccountDAO.getInstance().findAccountByID(Long.parseLong(account_id));
            account = new AccountBuilder().build(request, account);
            AccountDAO.getInstance().updateAccount(null, account);
        } catch (DBException e) {
            LOGGER.error("cant find account and update role");
            request.getSession().setAttribute("errorMassage", e.getMessage());
            return ERROR_PAGE;
        }
        return SHOW_ALL_ACCOUNT;
    }

}

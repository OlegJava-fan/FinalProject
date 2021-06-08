package com.my.finalProject.web.command;

import com.my.finalProject.Path;
import com.my.finalProject.builder.exception.BuildException;
import com.my.finalProject.db.AccountDAO;
import com.my.finalProject.db.exception.DBException;
import com.my.finalProject.db.FacultiesDAO;
import com.my.finalProject.db.entity.entityImpl.Account;
import com.my.finalProject.db.entity.entityImpl.Faculties;
import com.my.finalProject.db.entity.Role;
import com.my.finalProject.db.entity.StatusOrder;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

import static com.my.finalProject.utils.PasswordHash.hash;

public class LoginCommand extends Command {
    private static final long serialVersionUID = 1234234258534534535L;
    private static final Logger LOGGER = Logger.getLogger(LoginCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.debug("Start Command");
        String errorMassage;
        String forward = Path.ERROR_PAGE;
        HttpSession session = request.getSession();
        List<Faculties> facultiesList;
        String login = request.getParameter("login");
        LOGGER.trace("get login in request " + login);
        String password = request.getParameter("password");
        Account account;

        if (password.isEmpty() || login.isEmpty()) {
            errorMassage = "Login or password is Empty";
            request.getSession().setAttribute("errorMassage", errorMassage);
            LOGGER.error("errorMassage " + errorMassage);
            return "index.jsp";
        }

        try {
            account = AccountDAO.getInstance().findAccountByLogin(login);
            LOGGER.trace("found in DB account " + account);
        } catch (DBException e) {

            request.getSession().setAttribute("errorMassage", e.getMessage());
            return forward;
        }
        try {
            password = hash(password);

        } catch (BuildException e) {
            errorMassage = "Can not hash account password";
            request.getSession().setAttribute("errorMassage", e.getMessage());
            return forward;
        }
        if (account == null || !password.equals(account.getPassword())) {
            errorMassage = "Can not find user with login/password";
            request.getSession().setAttribute("errorMassage", errorMassage);
            LOGGER.error("errorMassage " + errorMassage);
            return "index.jsp";
        } else {
            Role role = Role.getRole(account);
            LOGGER.trace("role = " + role);
            if (role == Role.ADMIN) {
                forward = "admin.jsp";
                List<Role> roleList = Arrays.asList(Role.values());
                List<StatusOrder> statusOrderList = Arrays.asList(StatusOrder.values());
                session.setAttribute("roleList", roleList);
                session.setAttribute("statusOrderList", statusOrderList);
                LOGGER.trace(roleList);

            }
            if (role == Role.USER) {
                forward = "client.jsp";
            }
            if (role == Role.BANNED) {
                errorMassage = "You are blocked in system";
                request.getSession().setAttribute ("errorMassage", errorMassage);
                return forward;
            }
            try {
                facultiesList = FacultiesDAO.getInstance().findAllFaculties();
                session.setAttribute("facultiesList", facultiesList);
                LOGGER.trace("Set the session attribute: all faculties --> " + facultiesList.size());
            } catch (DBException e) {

                request.getSession().setAttribute("errorMassage", e.getMessage());
                return forward;
            }

            session.setAttribute("account", account);
            LOGGER.trace("Set the session attribute: user --> " + account);
            session.setAttribute("role", role);
            LOGGER.trace("Set the session attribute: userRole --> " + role);

        }
        LOGGER.debug("Command finish");
        return forward;
    }
}

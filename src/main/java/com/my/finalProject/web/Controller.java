package com.my.finalProject.web;

import com.my.finalProject.web.command.Command;
import com.my.finalProject.web.command.CommandContainer;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@MultipartConfig
@WebServlet("/controller")
public class Controller extends HttpServlet {
    private static final long serialVersionUID = 21312432421354345L;
    private static final Logger LOGGER = Logger.getLogger(Controller.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    protected void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        LOGGER.debug("Start controller");
        String commandName = req.getParameter("command");
        LOGGER.trace("Request parameter: command --> " + commandName);
        Command command = CommandContainer.getCommands(commandName);
        LOGGER.trace("Obtained command --> " + command);
        String forward = command.execute(req, resp);
        LOGGER.trace("Forward address --> " + forward);
        LOGGER.debug("Controller finished, now go to forward address --> " + forward);

        if (forward != null && req.getMethod().equals("POST")) {
            LOGGER.trace(req.getMethod());
            resp.sendRedirect(forward);
        } else {
            LOGGER.trace(req.getMethod());
            req.getRequestDispatcher(forward).forward(req, resp);
        }
    }
}

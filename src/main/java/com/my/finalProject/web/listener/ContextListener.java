package com.my.finalProject.web.listener;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextListener implements ServletContextListener {
    private static final Logger LOGGER = Logger.getLogger(ContextListener.class);

    public void contextDestroyed(ServletContextEvent event) {
        log("Servlet context destruction starts");
        // do nothing
        log("Servlet context destruction finished");
    }

    public void contextInitialized(ServletContextEvent event) {
        log("Servlet context initialization starts");

        ServletContext servletContext = event.getServletContext();
        initLog4J(servletContext);
        initCommandContainer();


        log("Servlet context initialization finished");
    }
    private void initLog4J(ServletContext servletContext) {
        log("Log4J initialization started");
        try {
            PropertyConfigurator.configure(servletContext.getRealPath(
                    "META-INF/log4j.properties"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        log("Log4J initialization finished");
    }
    private void initCommandContainer() {
        LOGGER.debug("Command container initialization started");

        try {
            Class.forName("com.my.finalProject.web.command.CommandContainer");
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }

        LOGGER.debug("Command container initialization finished");
    }

    private void log(String msg) {
        System.out.println("[ContextListener] " + msg);
    }
}

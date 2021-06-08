package com.my.finalProject.web.filter;

import com.my.finalProject.db.entity.entityImpl.Account;
import com.my.finalProject.db.entity.Role;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter("/index.jsp")
public class DoubleAuthenticationFilter implements Filter {

    private static final Logger LOGGER = Logger.getLogger(DoubleAuthenticationFilter.class.getName());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOGGER.debug("Filter initialization start");
        LOGGER.debug("Filter initialization end");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        Role role = null;
        Account account = (Account) httpServletRequest.getSession().getAttribute("account");
        LOGGER.debug("in session already contains" + account);
        if (account != null) {
            role = Role.getRole(account);
        }
        if (role == Role.ADMIN) {
            LOGGER.debug("forward to admin page");
            httpServletRequest.getRequestDispatcher("admin.jsp").forward(servletRequest, servletResponse);
        }
        if (role == Role.USER) {
            LOGGER.debug("forward to client page");
            httpServletRequest.getRequestDispatcher("client.jsp").forward(servletRequest, servletResponse);
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
        LOGGER.debug("Filter destroy start");
        LOGGER.debug("Filter destroy finish");
    }
}

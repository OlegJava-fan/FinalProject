package com.my.finalProject.web.filter;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter
public class EncodingFilter implements Filter {
    private static final Logger LOGGER = Logger.getLogger(EncodingFilter.class.getName());
    private String encoding;

    @Override
    public void destroy() {
        LOGGER.debug("Filter destroy start");
        LOGGER.debug("Filter destroy finish");
    }


    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain filterChain) throws IOException, ServletException {
        LOGGER.debug("Filter starts");

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        LOGGER.trace("Request URI==>" + httpServletRequest.getRequestURI());

        String requestEncoding = request.getCharacterEncoding();

        if (requestEncoding == null) {
            LOGGER.trace("encoding null, set encoding --> " + encoding);
            request.setCharacterEncoding(encoding);
        }

        LOGGER.debug("Filter finished");
        filterChain.doFilter(request, response);

    }

    @Override
    public void init(FilterConfig filterConfig) {
        LOGGER.debug("Filter initialization start");
        if(encoding ==null){
        encoding = filterConfig.getInitParameter("encoding");
        }

        LOGGER.trace("Get encoding param in web.xml --> " + encoding);
        LOGGER.debug("Filter initialization finish");
    }
}

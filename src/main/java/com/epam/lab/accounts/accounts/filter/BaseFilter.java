package com.epam.lab.accounts.accounts.filter;

import com.epam.lab.accounts.accounts.service.SessionService;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Component
public class BaseFilter implements Filter
{

    public static final String DEFAULT_AUTH_REQUIRED_REDIRECT_PATH = "/login";
    public static List<String> ALLOWED_URLS = ImmutableList.of(
            "/login",
            "/auth/login",
            "/register",
            "/auth/register"
    );

    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private SessionService sessionService;

    @Override
    public void doFilter(ServletRequest rq, ServletResponse rs, FilterChain chain) throws IOException, ServletException {


        final HttpServletRequest request = (HttpServletRequest) rq;

        final HttpServletResponse response = (HttpServletResponse) rs;

        log.info("REQUEST [{}] [{}]", request.getMethod(), request.getRequestURL().toString());
        log.info("CURRENT SESSION: " + request.getSession(true).getId());
        log.info("CURRENT USER: " + sessionService.getSessionUser());

        if(isAuthRequired(request)) {
            log.info("AUTH REQUIRED: [{}]", request.getRequestURL().toString());
            log.info("REND REDIRECT: [{}]", DEFAULT_AUTH_REQUIRED_REDIRECT_PATH);
            response.sendRedirect(DEFAULT_AUTH_REQUIRED_REDIRECT_PATH);
        } else {

            chain.doFilter(rq, rs);
        }

    }

    private boolean isAuthRequired(final HttpServletRequest request) {
        boolean isAuthRequired = true;
        if(request.getRequestURI().contains("/webjars/"))
        {
            isAuthRequired = false;
        }
        else if (request.getRequestURI().contains("/css/"))
        {
            isAuthRequired = false;
        }
        else if (request.getRequestURI().contains("/img/"))
        {
            isAuthRequired = false;
        }
        else
        {
            final String requestURI = request.getRequestURI();
            isAuthRequired = sessionService.isGuest() && !ALLOWED_URLS.contains(requestURI);
        }
        return isAuthRequired;
    }
}

package com.epam.lab.accounts.accounts.filter;

import com.epam.lab.accounts.accounts.model.UserRole;
import com.epam.lab.accounts.accounts.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class AuthFilter extends HttpFilter {


    @Autowired
    private SessionService session;

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        doFilter(request,response,chain);
    }
}

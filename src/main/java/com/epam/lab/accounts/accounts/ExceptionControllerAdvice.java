package com.epam.lab.accounts.accounts;

import com.epam.lab.accounts.accounts.consts.Redirects;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Arrays;

@ControllerAdvice
public class ExceptionControllerAdvice {


    private static final Logger LOG = LoggerFactory.getLogger(ExceptionControllerAdvice.class);

    @ExceptionHandler(IllegalStateException.class)
    public String handleIllegalStateError(IllegalStateException e) {
        LOG.warn("Illegal state error occurred: " + e.getMessage());
        LOG.warn("Illegal state error occurred: " + e);
        return Redirects.REDIRECT_HOME;
    }

    @ExceptionHandler(Exception.class)
    public String handleGeneralError(Exception e) {
        LOG.error("Unknown error occurred: " + e.getMessage() + StringUtils.SPACE + Arrays.toString(e.getStackTrace()));
        return Redirects.REDIRECT_ERROR;
    }
}

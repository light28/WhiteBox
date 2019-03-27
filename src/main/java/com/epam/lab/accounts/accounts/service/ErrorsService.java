package com.epam.lab.accounts.accounts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.NoSuchElementException;

import static java.lang.String.format;

@Component
public class ErrorsService {

    @Autowired
    private SessionService sessionService;

    public void propagateIllegalArgument(final String msg, final Object ... args) {
        final IllegalArgumentException e = new IllegalArgumentException(format(msg, args));
        sessionService.setLastError(e);
        throw e;
    }

    public void propagateIllegalState(final String msg, final Object ... args) {
        throw new IllegalStateException(format(msg, args));
    }
}

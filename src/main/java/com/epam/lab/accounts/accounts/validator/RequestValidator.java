package com.epam.lab.accounts.accounts.validator;

import com.epam.lab.accounts.accounts.service.ErrorsService;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class RequestValidator<T> {

    @Autowired
    private ErrorsService errorsService;

    public abstract void validate(T object) ;

    public ErrorsService getErrorsService() {
        return errorsService;
    }
}

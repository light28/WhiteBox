package com.epam.lab.accounts.accounts.validator;

import com.epam.lab.accounts.accounts.service.AccountService;
import com.epam.lab.accounts.accounts.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountAccessValidator extends RequestValidator<String> {

    @Autowired
    private AccountService accountService;
    @Autowired
    private SessionService sessionService;

    @Override
    public void validate(final String accountCode) {

        if (accountCode.equals("new"))
        {
            return;
        }

        if (!accountService.isAccountExistsForAccountCode(accountCode))
        {
            return;
        }

        if (!accountService.isAccountAssignedToUser(accountCode, sessionService.getSessionUserEmail())) {

            getErrorsService()
                    .propagateIllegalState("Current user %s does not have permissions to view account %s ",
                            sessionService.getSessionUserEmail(), accountCode);
        }
    }
}

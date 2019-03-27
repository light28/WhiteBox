package com.epam.lab.accounts.accounts;

import com.epam.lab.accounts.accounts.consts.Redirects;
import com.epam.lab.accounts.accounts.facade.AccountFacade;
import com.epam.lab.accounts.accounts.model.requests.CreateUpdateAccountRequest;
import com.epam.lab.accounts.accounts.validator.AccountAccessValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class AccountsPageController {

    private static final String EDIT_ACCOUNT_PAGE_JSP = "edit-account-page";
    private static final String NEW_ACCOUNT = "new";

    @Autowired
    private AccountFacade accountFacade;
    @Autowired
    private AccountAccessValidator accountAccessValidator;

    @RequestMapping("/accounts/edit/{account}")
    public String onGetEditAccountPage(@PathVariable final String account, final Map<String,Object> data)
    {
        accountAccessValidator.validate(account);

        if(!isNewAccount(account) && accountFacade.isAccountExistsForCode(account))
        {
            if (accountFacade.isAccountBelongsToCurrentUser(account))
            {
                accountFacade.populateAccountData(account, data);
            }
            else
            {
                return Redirects.REDIRECT_HOME;
            }
        }
        return EDIT_ACCOUNT_PAGE_JSP;
    }

    @RequestMapping("/accounts/edit")
    public String onEditAccountRequest(@Valid final CreateUpdateAccountRequest createUpdateAccountRequest)
    {
        accountAccessValidator.validate(createUpdateAccountRequest.getAccountCode());

        try
        {
            accountFacade.handleCreateOrUpdateAccountRequest(createUpdateAccountRequest);
            return Redirects.REDIRECT_HOME;
        }
        catch (IllegalArgumentException e)
        {
            return Redirects.REDIRECT_CREATE_EDIT_ERROR;
        }
    }

    private boolean isNewAccount(String account) {
        return account.equals(NEW_ACCOUNT);
    }
}

package com.epam.lab.accounts.accounts.validator;

import com.epam.lab.accounts.accounts.model.requests.CreateUpdateAccountRequest;
import com.epam.lab.accounts.accounts.service.AccountService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateAccountRequestRequestValidator extends RequestValidator<CreateUpdateAccountRequest> {

    public static final String ACCOUNT_CODE_SHOULD_NOT_CONTAIN_SPACES = "Account code should not contain spaces";
    public static final String NEW_IS_RESERVED_CODE_AND_CANNOT_BE_USED = "'new' is reserved code and cannot be used";
    public static final String IMAGE_URL_IS_NOT_VALID = "Image url is not valid";
    public static final String ACCOUNT_WITH_CODE_S_ALREADY_EXISTS = "Account with code %s already exists";
    public static final String NEW = "new";
    @Autowired
    private AccountService accountService;

    @Override
    public void validate(final CreateUpdateAccountRequest object) {

        if (object.getAccountCode().contains(StringUtils.SPACE))
        {
            getErrorsService()
                    .propagateIllegalArgument(ACCOUNT_CODE_SHOULD_NOT_CONTAIN_SPACES);
        }

        if (object.getAccountCode().equals(NEW))
        {
            getErrorsService()
                    .propagateIllegalArgument(NEW_IS_RESERVED_CODE_AND_CANNOT_BE_USED);
        }

        if(!UrlValidator.getInstance().isValid(object.getAccountImage()))
        {
            getErrorsService()
                    .propagateIllegalArgument(IMAGE_URL_IS_NOT_VALID);
        }

        if (accountService.isAccountExistsForAccountCode(object.getAccountCode())) {
            getErrorsService()
                    .propagateIllegalArgument(
                            ACCOUNT_WITH_CODE_S_ALREADY_EXISTS, object.getAccountCode());
        }
    }
}

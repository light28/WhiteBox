package com.epam.lab.accounts.accounts.facade;

import com.epam.lab.accounts.accounts.converter.AccountConverter;
import com.epam.lab.accounts.accounts.dto.AccountDTO;
import com.epam.lab.accounts.accounts.dto.UserDTO;
import com.epam.lab.accounts.accounts.model.AccountModel;
import com.epam.lab.accounts.accounts.model.UserModel;
import com.epam.lab.accounts.accounts.model.requests.CreateUpdateAccountRequest;
import com.epam.lab.accounts.accounts.service.AccountService;
import com.epam.lab.accounts.accounts.service.SessionService;
import com.epam.lab.accounts.accounts.service.UserService;
import com.epam.lab.accounts.accounts.validator.AccountAccessValidator;
import com.epam.lab.accounts.accounts.validator.CreateAccountRequestRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class AccountFacade {

    public static final String ACCOUNT_SHORT_NAME = "acc";
    @Autowired
    private SessionService sessionService;
    @Autowired
    private UserService userService;
    @Autowired
    private AccountConverter accountConverter;
    @Autowired
    private AccountService accountService;
    @Autowired
    private CreateAccountRequestRequestValidator createAccountRequestValidator;

    public void handleCreateOrUpdateAccountRequest(final CreateUpdateAccountRequest createUpdateAccountRequest) {

        final AccountDTO accountDTO = getAccountDtoForRequest(createUpdateAccountRequest);
        if (isAccountExistsForCode(accountDTO.getCode()))
        {
            if (isAccountDiffers(accountDTO))
            {
                accountService.updateAccount(accountDTO);
            }
        }
        else
        {
            createAccountRequestValidator.validate(createUpdateAccountRequest);
            accountService.createAccountForCurrentUser(accountDTO);
        }
    }

    private boolean isAccountDiffers(AccountDTO accountDTO) {
        return !accountService.getAccountForAccountCode(accountDTO.getCode()).equals(accountDTO);
    }


    public List<AccountDTO> getAccountsForCurrentUser() {
        final UserDTO sessionUser = sessionService.getSessionUser();
        final UserModel userModel = userService.getUserModelForEmail(sessionUser.getEmail());
        final List<AccountModel> accounts = userModel.getAccounts();
        return accounts.stream().map(accountConverter::convert).collect(Collectors.toList());
    }

    public boolean isAccountExistsForCode(final String accountCode) {
        return accountService.isAccountExistsForAccountCode(accountCode);
    }

    public void populateAccountData(final String accountCode, Map<String, Object> data) {
        final AccountDTO account = accountService.getAccountForAccountCode(accountCode);
        data.put(ACCOUNT_SHORT_NAME, account);
    }

    private AccountDTO getAccountDtoForRequest(CreateUpdateAccountRequest createUpdateAccountRequest) {
        final AccountDTO accountDTO = new AccountDTO();
        accountDTO.setCode(createUpdateAccountRequest.getAccountCode());
        accountDTO.setName(createUpdateAccountRequest.getAccountName());
        accountDTO.setImg(createUpdateAccountRequest.getAccountImage());
        accountDTO.setBalance(createUpdateAccountRequest.getAccountBalance());
        return accountDTO;
    }

    public boolean isAccountBelongsToCurrentUser(final String accountCode) {
        return accountService.isAccountAssignedToUser(accountCode, sessionService.getSessionUserEmail());
    }
}

package com.epam.lab.accounts.accounts.service;

import com.epam.lab.accounts.accounts.converter.AccountConverter;
import com.epam.lab.accounts.accounts.dto.AccountDTO;
import com.epam.lab.accounts.accounts.model.AccountModel;
import com.epam.lab.accounts.accounts.model.UserModel;
import com.epam.lab.accounts.accounts.repository.AccountModelRepository;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountService {

    @Autowired
    private AccountModelRepository accountModelRepository;
    @Autowired
    private SessionService sessionService;
    @Autowired
    private UserService userService;
    @Autowired
    private AccountConverter accountConverter;

    @Transactional
    public AccountDTO createAccountForCurrentUser(final AccountDTO accountDTO) {

        final AccountModel accountModel = getAccountModelForAccountDto(accountDTO);
        populateDefaultStartingBalance(accountModel);
        accountModelRepository.save(accountModel);

        final UserModel currentUser = userService.getUserModelForEmail(sessionService.getSessionUserEmail());
        userService.addUserAccount(currentUser, accountModel);

        return accountConverter.convert(accountModel);
    }

    private void populateDefaultStartingBalance(AccountModel accountModel) {
        accountModel.setBalance(BigDecimal.ZERO);
    }

    public List<AccountDTO> getAccountsForUserEmail(final String userEmail) {
        return accountModelRepository.findAccountModelByUserEmail(userEmail)
                .stream()
                .map(accountConverter::convert)
                .collect(Collectors.toList());
    }

    public void updateAccount(final AccountDTO accountDTO) {
        final AccountModel accountModel = getAccountModelForAccountDto(accountDTO);
        accountModelRepository.save(accountModel);
    }

    public AccountDTO getAccountForAccountCode(final String accountCode) {
        return accountConverter.convert(getAccountModelForAccountCode(accountCode));
    }

    public AccountModel getAccountModelForAccountCode(final String accountCode) {
        return accountModelRepository.findAccountModelByCode(accountCode)
                .orElseThrow(() -> new IllegalArgumentException("No accounts found for code: " + accountCode));
    }

    public boolean isAccountExistsForAccountCode(String accountCode) {
        return accountModelRepository.existsAccountModelByCode(accountCode);
    }

    private AccountModel getAccountModelForAccountDto(AccountDTO accountDTO) {

        final AccountModel accountModel = accountModelRepository
                .findAccountModelByCode(accountDTO.getCode()).orElseGet(AccountModel::new);

        accountModel.setCode(accountDTO.getCode());
        accountModel.setName(accountDTO.getName());
        accountModel.setUrlToImage(accountDTO.getImg());
        accountModel.setBalance(accountDTO.getBalance());
        return accountModel;
    }



    public boolean isAccountAssignedToUser(String accountCode, String userEmail) {
        return accountModelRepository.existsAccountModelByCodeAndUserEmail(accountCode, userEmail);
    }
}

package com.epam.lab.accounts.accounts;

import com.epam.lab.accounts.accounts.dto.TransactionDTO;
import com.epam.lab.accounts.accounts.service.TransactionService;
import com.epam.lab.accounts.accounts.validator.AccountAccessValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
public class AccountTransactionsPageController {

    public static final String ACCOUNTS_TRANSACTIONS_JSP = "accounts-transactions";

    @Autowired
    private TransactionService transactionService;
    @Autowired
    private AccountAccessValidator accountAccessValidator;


    @RequestMapping("/accounts/{accountCode}/transactions")
    public String onGetAccountTransactionsPage(@PathVariable String accountCode, final Map<String, Object> attrs) {

        accountAccessValidator.validate(accountCode);

        final List<TransactionDTO> transactions = transactionService.getTransactionsForAccount(accountCode);
        attrs.put("accountCode", accountCode);
        attrs.put("transactions", transactions);
        return ACCOUNTS_TRANSACTIONS_JSP;
    }
}

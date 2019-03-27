package com.epam.lab.accounts.accounts.service;

import com.epam.lab.accounts.accounts.converter.TransactionConverter;
import com.epam.lab.accounts.accounts.dto.TransactionDTO;
import com.epam.lab.accounts.accounts.model.AccountModel;
import com.epam.lab.accounts.accounts.model.TransactionModel;
import com.epam.lab.accounts.accounts.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Component
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private AccountService accountService;
    @Autowired
    private TransactionConverter transactionConverter;

    public List<TransactionDTO> getTransactionsForAccount(String accountCode) {
        final List<TransactionModel> transactionModels = transactionRepository.findAllAccountTransactions(accountCode);
        return transactionModels.stream().map(transactionConverter::convert).collect(toList());
    }
}

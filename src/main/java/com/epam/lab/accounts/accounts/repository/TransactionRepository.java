package com.epam.lab.accounts.accounts.repository;

import com.epam.lab.accounts.accounts.model.AccountModel;
import com.epam.lab.accounts.accounts.model.TransactionModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends CrudRepository<TransactionModel, Long> {

    @Query("select t from TransactionModel as t where t.toAccount.code = :account or t.fromAccount.code = :account order by t.created")
    List<TransactionModel> findAllAccountTransactions(@Param("account") final String account);
}

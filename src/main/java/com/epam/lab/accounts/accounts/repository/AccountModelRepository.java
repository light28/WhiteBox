package com.epam.lab.accounts.accounts.repository;

import com.epam.lab.accounts.accounts.model.AccountModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountModelRepository extends CrudRepository<AccountModel, String> {

    @Query("select acc from UserModel as u  join u.accounts as acc where u.email = :email")
    List<AccountModel> findAccountModelByUserEmail(@Param("email") final String email);

    boolean existsAccountModelByCode(final String code);

    Optional<AccountModel> findAccountModelByCode(String accountCode);

    @Query("select case when count(acc) > 0  then true else false end " +
            "from UserModel as u " +
            "join u.accounts as acc " +
            "where acc.code = ?1 and u.email = ?2")
    boolean existsAccountModelByCodeAndUserEmail(
            @Param("accountCode")final String accountCode,
            @Param("email")final String email);
}

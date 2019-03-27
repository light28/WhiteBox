package com.epam.lab.accounts.accounts.repository;

import com.epam.lab.accounts.accounts.model.UserModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserModelRepository extends CrudRepository<UserModel, String> {

    Optional<UserModel> findUserModelByEmail(final String email);

    List<UserModel> findUserModelsByAccountsCode(final String code);
}

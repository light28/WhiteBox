package com.epam.lab.accounts.accounts.service;

import com.epam.lab.accounts.accounts.converter.UserConverter;
import com.epam.lab.accounts.accounts.dto.AccountDTO;
import com.epam.lab.accounts.accounts.dto.UserDTO;
import com.epam.lab.accounts.accounts.model.AccountModel;
import com.epam.lab.accounts.accounts.model.UserModel;
import com.epam.lab.accounts.accounts.repository.UserModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
public class UserService {

    @Autowired
    private UserConverter converter;

    @Autowired
    private UserModelRepository userModelRepository;

    public boolean isUserExistsForEmail(final String email) {
        return userModelRepository.findUserModelByEmail(email).isPresent();
    }

    public UserDTO getUserForEmail(final String email) {
        final UserModel userModel = getUserModelForEmail(email);
        return converter.convert(userModel);
    }

    public UserModel getUserModelForEmail(String email) {
        return userModelRepository.findUserModelByEmail(email)
                    .orElseThrow(() -> new IllegalArgumentException(
                            format("User with email %s does not exist", email)));
    }

    public boolean isUserPasswordMatch(final String email, final String password) {
        final UserModel userModel = getUserModelForEmail(email);
        return userModel.getPassword().equals(password);
    }

    public UserDTO createUser(final UserDTO userDTO) {
        final UserModel userModel = new UserModel();
        userModel.setFirstName(userDTO.getFirstName());
        userModel.setLastName(userDTO.getLastName());
        userModel.setEmail(userDTO.getEmail());
        userModel.setUserRole(userDTO.getUserRole());
        userModelRepository.save(userModel);
        return converter.convert(userModel);
    }

    public void updateUserPassword(String email, String password) {
        final UserModel userModel = getUserModelForEmail(email);
        userModel.setPassword(password);
        userModelRepository.save(userModel);
    }

    public void addUserAccount(UserModel userModel, AccountModel accountModel) {
        userModel.getAccounts().add(accountModel);
        userModelRepository.save(userModel);
    }
}

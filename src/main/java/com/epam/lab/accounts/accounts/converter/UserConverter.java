package com.epam.lab.accounts.accounts.converter;

import com.epam.lab.accounts.accounts.dto.UserDTO;
import com.epam.lab.accounts.accounts.model.UserModel;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import static com.google.common.base.Preconditions.checkNotNull;

@Component
public class UserConverter implements Converter<UserModel, UserDTO> {
    @Override
    public UserDTO convert(final UserModel source) {

        checkNotNull(source);

        final UserDTO target = new UserDTO();

        target.setFirstName(source.getFirstName());
        target.setLastName(source.getLastName());
        target.setEmail(source.getEmail());
        target.setUserRole(source.getUserRole());


        return target;
    }
}

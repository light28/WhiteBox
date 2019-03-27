package com.epam.lab.accounts.accounts.converter;

import com.epam.lab.accounts.accounts.dto.AccountDTO;
import com.epam.lab.accounts.accounts.model.AccountModel;
import com.google.common.base.Preconditions;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AccountConverter implements Converter<AccountModel, AccountDTO> {

    @Override
    public AccountDTO convert(AccountModel source) {
        Preconditions.checkNotNull(source);

        final AccountDTO target = new AccountDTO();
        target.setCode(source.getCode());
        target.setName(source.getName());
        target.setImg(source.getUrlToImage());
        target.setBalance(source.getBalance());
        return target;
    }
}

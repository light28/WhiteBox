package com.epam.lab.accounts.accounts.converter;

import com.epam.lab.accounts.accounts.dto.TransactionDTO;
import com.epam.lab.accounts.accounts.model.TransactionModel;
import com.google.common.base.Preconditions;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TransactionConverter implements Converter<TransactionModel, TransactionDTO> {

    public static final String TRANSACTION_PREFIX = "TX";
    public static final String TRANSACTION_DATE_FORMAT = "yyyy-MM-dd hh:mm";

    @Override
    public TransactionDTO convert(final TransactionModel source) {
        Preconditions.checkNotNull(source);
        final TransactionDTO target = new TransactionDTO();
        target.setTransactionCode(source.getTransactionCode());
        target.setFromAccountCode(source.getFromAccount().getCode());
        target.setFromAccountName(source.getFromAccount().getName());
        target.setToAccountCode(source.getToAccount().getCode());
        target.setToAccountName(source.getToAccount().getName());
        target.setValue(source.getTransactionValue());
        target.setFromCurrentBalance(source.getFromCurrentBalance());
        target.setToCurrentBalance(source.getToCurrentBalance());
        target.setFromTargetBalance(source.getFromTargetBalance());
        target.setToTargetBalance(source.getToTargetBalance());
        target.setTransactionStatus(source.getAppTransactionStatus().name());
        target.setCreatedDate(DateFormatUtils.format(source.getCreated(), TRANSACTION_DATE_FORMAT));
        return target;
    }
}

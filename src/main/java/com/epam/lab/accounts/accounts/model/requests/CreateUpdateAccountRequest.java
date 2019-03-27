package com.epam.lab.accounts.accounts.model.requests;

import com.epam.lab.accounts.accounts.dto.DataClass;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

public class CreateUpdateAccountRequest extends DataClass {

    @NotEmpty
    private String accountCode;
    @NotEmpty
    private String accountName;
    @NotEmpty
    private String accountImage;
    @PositiveOrZero
    private BigDecimal accountBalance = BigDecimal.ZERO;

    public String getAccountCode() {
        return accountCode;
    }

    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountImage() {
        return accountImage;
    }

    public void setAccountImage(String accountImage) {
        this.accountImage = accountImage;
    }

    public BigDecimal getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(BigDecimal accountBalance) {
        this.accountBalance = accountBalance;
    }
}

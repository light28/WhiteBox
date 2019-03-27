package com.epam.lab.accounts.accounts.dto;

import java.math.BigDecimal;

public class AccountDTO extends DataClass {

    public static final String ACCOUNT_CODE = "accountCode";
    public static final String ACCOUNT_NAME = "accountName";
    public static final String ACCOUNT_IMG = "accountImg";

    private String code;
    private String name;
    private String img;
    private BigDecimal balance = BigDecimal.ZERO;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}

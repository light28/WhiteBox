package com.epam.lab.accounts.accounts.dto;

import java.math.BigDecimal;

public class TransactionDTO extends DataClass {

    private String transactionCode;
    private String fromAccountCode;
    private String toAccountCode;
    private String fromAccountName;
    private String toAccountName;
    private String transactionStatus;
    private String createdDate;
    private BigDecimal value;
    private BigDecimal fromCurrentBalance;
    private BigDecimal toCurrentBalance;
    private BigDecimal fromTargetBalance;
    private BigDecimal toTargetBalance;

    public String getTransactionCode() {
        return transactionCode;
    }

    public void setTransactionCode(String transactionCode) {
        this.transactionCode = transactionCode;
    }

    public String getFromAccountCode() {
        return fromAccountCode;
    }

    public void setFromAccountCode(String fromAccountCode) {
        this.fromAccountCode = fromAccountCode;
    }

    public String getToAccountCode() {
        return toAccountCode;
    }

    public void setToAccountCode(String toAccountCode) {
        this.toAccountCode = toAccountCode;
    }

    public String getFromAccountName() {
        return fromAccountName;
    }

    public void setFromAccountName(String fromAccountName) {
        this.fromAccountName = fromAccountName;
    }

    public String getToAccountName() {
        return toAccountName;
    }

    public void setToAccountName(String toAccountName) {
        this.toAccountName = toAccountName;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getFromCurrentBalance() {
        return fromCurrentBalance;
    }

    public void setFromCurrentBalance(BigDecimal fromCurrentBalance) {
        this.fromCurrentBalance = fromCurrentBalance;
    }

    public BigDecimal getToCurrentBalance() {
        return toCurrentBalance;
    }

    public void setToCurrentBalance(BigDecimal toCurrentBalance) {
        this.toCurrentBalance = toCurrentBalance;
    }

    public BigDecimal getFromTargetBalance() {
        return fromTargetBalance;
    }

    public void setFromTargetBalance(BigDecimal fromTargetBalance) {
        this.fromTargetBalance = fromTargetBalance;
    }

    public BigDecimal getToTargetBalance() {
        return toTargetBalance;
    }

    public void setToTargetBalance(BigDecimal toTargetBalance) {
        this.toTargetBalance = toTargetBalance;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }
}

package com.epam.lab.accounts.accounts.model;

import com.epam.lab.accounts.accounts.dto.DataClass;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public class TransactionModel extends DataClass {

    public static final String CREATED = "created";
    public static final String COMMITTED = "committed";
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(targetEntity = AccountModel.class)
    private AccountModel fromAccount;
    @ManyToOne(targetEntity = AccountModel.class)
    private AccountModel toAccount;
    private BigDecimal transactionValue;
    private BigDecimal fromCurrentBalance;
    private BigDecimal toCurrentBalance;
    private BigDecimal fromTargetBalance;
    private BigDecimal toTargetBalance;
    private String transactionCode;
    @Enumerated(EnumType.STRING)
    private AppTransactionStatus appTransactionStatus;
    @Basic(optional = false)
    @Column(name = CREATED, insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @Basic(optional = false)
    @Column(name = COMMITTED, insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date committed;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AccountModel getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(AccountModel fromAccount) {
        this.fromAccount = fromAccount;
    }

    public AccountModel getToAccount() {
        return toAccount;
    }

    public void setToAccount(AccountModel toAccount) {
        this.toAccount = toAccount;
    }

    public BigDecimal getTransactionValue() {
        return transactionValue;
    }

    public void setTransactionValue(BigDecimal transactionValue) {
        this.transactionValue = transactionValue;
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

    public String getTransactionCode() {
        return transactionCode;
    }

    public void setTransactionCode(String transactionCode) {
        this.transactionCode = transactionCode;
    }

    public AppTransactionStatus getAppTransactionStatus() {
        return appTransactionStatus;
    }

    public void setAppTransactionStatus(AppTransactionStatus appTransactionStatus) {
        this.appTransactionStatus = appTransactionStatus;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getCommitted() {
        return committed;
    }

    public void setCommitted(Date committed) {
        this.committed = committed;
    }
}

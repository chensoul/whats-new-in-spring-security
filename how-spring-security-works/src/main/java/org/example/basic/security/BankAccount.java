package org.example.basic.security;

import java.math.BigDecimal;
import java.util.Objects;

public class BankAccount {
    final long id;
    final String owner;
    final String accountNumber;
    final BigDecimal balance;

    public BankAccount(long id, String owner, String accountNumber, BigDecimal balance) {
        this.id = id;
        this.owner = owner;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public long getId() {
        return id;
    }

    public String getOwner() {
        return owner;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "id=" + id +
                ", owner='" + owner + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", balance=" + balance +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankAccount that = (BankAccount) o;
        return id == that.id && Objects.equals(owner, that.owner) && Objects.equals(accountNumber, that.accountNumber) && Objects.equals(balance, that.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, owner, accountNumber, balance);
    }
}

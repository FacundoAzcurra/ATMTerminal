package com.solvd.atm.bin;

import java.util.Objects;

public class Account {
    int idAccounts;
    double balance;
    String fullName;
    int cardNumber;
    int userId;
    int bankId;

    public Account() {
    }

    public Account(int idAccounts, double balance, String fullName, int cardNumber, int userId, int bankId) {
        this.idAccounts = idAccounts;
        this.balance = balance;
        this.fullName = fullName;
        this.cardNumber = cardNumber;
        this.userId = userId;
        this.bankId = bankId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBankId() {
        return bankId;
    }

    public void setBankId(int bankId) {
        this.bankId = bankId;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getIdAccounts() {
        return idAccounts;
    }

    public void setIdAccounts(int idAccounts) {
        this.idAccounts = idAccounts;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return idAccounts == account.idAccounts && Double.compare(account.balance, balance) == 0 && cardNumber == account.cardNumber && Objects.equals(fullName, account.fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAccounts, balance, fullName, cardNumber);
    }

    @Override
    public String toString() {
        return "Account required: " +
                "Account id=" + idAccounts +
                ", current balance=" + balance +
                ", name='" + fullName + '\'' +
                ", card number=" + cardNumber;
    }
}

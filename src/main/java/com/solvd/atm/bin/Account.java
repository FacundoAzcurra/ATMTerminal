package com.solvd.atm.bin;

import java.util.Objects;

public class Account {
    int idAccounts;
    double balance;
    String fullName;
    int cardNumber;

    public Account() {
    }

    public Account(int idAccounts, double balance, String fullName,int cardNumber) {
        this.idAccounts = idAccounts;
        this.balance = balance;
        this.fullName = fullName;
        this.cardNumber = cardNumber;
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
        return "Account{" +
                "idAccounts=" + idAccounts +
                ", balance=" + balance +
                ", fullName='" + fullName + '\'' +
                ", cardNumber=" + cardNumber +
                '}';
    }
}

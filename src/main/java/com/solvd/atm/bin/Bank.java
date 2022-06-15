package com.solvd.atm.bin;

import java.util.Objects;

public class Bank {
    private int bankId;
    private String name;

    public  Bank(){
    }

    public Bank(int bankId, String name) {
        this.bankId = bankId;
        this.name = name;
    }

    public int getBankId() {
        return bankId;
    }

    public void setBankId(int bankId) {
        this.bankId = bankId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bank bank = (Bank) o;
        return bankId == bank.bankId && Objects.equals(name, bank.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bankId, name);
    }

    @Override
    public String toString() {
        return "Bank{" +
                "bankId=" + bankId +
                ", name='" + name + '\'' +
                '}';
    }
}

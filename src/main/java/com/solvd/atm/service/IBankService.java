package com.solvd.atm.service;

import com.solvd.atm.bin.Bank;

import java.net.ConnectException;
import java.util.List;

public interface IBankService {
    Bank getBank(int id) throws ConnectException;

    void delete(int id) throws ConnectException;

    void update(Bank bank) throws ConnectException;

    void create(Bank bank) throws ConnectException;

    List<Bank> getAllBanks() throws ConnectException;
}

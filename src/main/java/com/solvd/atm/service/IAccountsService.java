package com.solvd.atm.service;

import com.solvd.atm.bin.Account;

import java.net.ConnectException;
import java.util.List;

public interface IAccountsService {
    Account getAccount(int id) throws ConnectException;

    void delete(int id) throws ConnectException;

    void update(Account account) throws ConnectException;

    void create(Account account) throws ConnectException;

    List<Account> getAllAccounts() throws ConnectException;
}

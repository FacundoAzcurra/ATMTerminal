package com.solvd.atm.service.jdbc;

import com.solvd.atm.DAO.DAOException;
import com.solvd.atm.DAO.IAccountsDAO;
import com.solvd.atm.DAO.impl.AccountsImpl;
import com.solvd.atm.bin.Account;
import com.solvd.atm.service.IAccountsService;

import java.net.ConnectException;
import java.sql.SQLException;
import java.util.List;

public class AccountsServiceImpl implements IAccountsService {

    @Override
    public Account getAccount(int id) throws ConnectException {
        IAccountsDAO accountsDAO=new AccountsImpl();
        return accountsDAO.getObject(id);
    }

    @Override
    public void delete(int id) throws ConnectException {
        IAccountsDAO accountsDAO=new AccountsImpl();
        accountsDAO.delete(id);
    }

    @Override
    public void update(Account account) throws ConnectException {
        IAccountsDAO accountsDAO=new AccountsImpl();
        accountsDAO.update(account);
    }

    @Override
    public void create(Account account) throws ConnectException {
        IAccountsDAO accountsDAO=new AccountsImpl();
        accountsDAO.insert(account);
    }

    @Override
    public List<Account> getAllAccounts() throws ConnectException {
        IAccountsDAO accountsDAO=new AccountsImpl();
        return accountsDAO.getList();
    }
}

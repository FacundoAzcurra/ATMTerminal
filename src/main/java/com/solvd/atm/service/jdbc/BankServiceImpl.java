package com.solvd.atm.service.jdbc;

import com.solvd.atm.DAO.IBankDAO;
import com.solvd.atm.DAO.impl.BankImpl;
import com.solvd.atm.bin.Bank;
import com.solvd.atm.service.IBankService;

import java.net.ConnectException;
import java.util.List;

public class BankServiceImpl implements IBankService {
    @Override
    public Bank getBank(int id) throws ConnectException {
        IBankDAO bankDAO= new BankImpl();
        return bankDAO.getObject(id);
    }

    @Override
    public void delete(int id) throws ConnectException {
        IBankDAO bankDAO= new BankImpl();
        bankDAO.delete(id);
    }

    @Override
    public void update(Bank bank) throws ConnectException {
        IBankDAO bankDAO= new BankImpl();
        bankDAO.update(bank);
    }

    @Override
    public void create(Bank bank) throws ConnectException {
        IBankDAO bankDAO= new BankImpl();
        bankDAO.insert(bank);
    }

    @Override
    public List<Bank> getAllBanks() throws ConnectException {
        IBankDAO bankDAO= new BankImpl();
        return bankDAO.getList();
    }
}

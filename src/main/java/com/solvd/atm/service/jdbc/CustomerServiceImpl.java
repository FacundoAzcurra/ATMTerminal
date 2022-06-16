package com.solvd.atm.service.jdbc;

import com.solvd.atm.DAO.DAOException;
import com.solvd.atm.DAO.ICustomerDAO;
import com.solvd.atm.DAO.impl.CustomerImpl;
import com.solvd.atm.bin.Customer;
import com.solvd.atm.service.ICustomerService;

import java.net.ConnectException;
import java.util.List;

public class CustomerServiceImpl implements ICustomerService {


    @Override
    public Customer getCustomer(int id) throws ConnectException {
        ICustomerDAO customerDAO = new CustomerImpl();
        return customerDAO.getObject(id);
    }

    @Override
    public void delete(int id) throws ConnectException {
        ICustomerDAO customerDAO = new CustomerImpl();
        customerDAO.delete(id);
    }

    @Override
    public void update(Customer customer) throws ConnectException {
        ICustomerDAO customerDAO = new CustomerImpl();
        customerDAO.update(customer);
    }

    @Override
    public void create(Customer customer) throws ConnectException {
        ICustomerDAO customerDAO = new CustomerImpl();
        customerDAO.insert(customer);
    }

    @Override
    public List<Customer> getAllCustomers() throws ConnectException {
        ICustomerDAO customerDAO = new CustomerImpl();
        return customerDAO.getList();
    }
}

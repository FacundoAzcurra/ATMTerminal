package com.solvd.atm.service;

import com.solvd.atm.bin.Customer;

import java.net.ConnectException;
import java.util.List;

public interface ICustomerService {
    Customer getCustomer(int id) throws ConnectException;

    void delete(int id) throws ConnectException;

    void update(Customer customer) throws ConnectException;

    void create(Customer customer) throws ConnectException;

    List<Customer> getAllCustomers() throws ConnectException;
}

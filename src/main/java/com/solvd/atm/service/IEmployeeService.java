package com.solvd.atm.service;

import com.solvd.atm.bin.Employee;

import java.net.ConnectException;
import java.util.List;

public interface IEmployeeService {
    Employee getEmployee(int id) throws ConnectException;

    void delete(int id) throws ConnectException;

    void update(Employee employee) throws ConnectException;

    void create(Employee employee) throws ConnectException;

    List<Employee> getAllEmployees() throws ConnectException;
}

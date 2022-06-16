package com.solvd.atm.service.jdbc;

import com.solvd.atm.DAO.IEmployeeDAO;
import com.solvd.atm.DAO.impl.EmployeeImpl;
import com.solvd.atm.bin.Employee;
import com.solvd.atm.service.IEmployeeService;

import java.net.ConnectException;
import java.util.List;

public class EmployeeServiceImpl implements IEmployeeService {
    @Override
    public Employee getEmployee(int id) throws ConnectException {
        IEmployeeDAO employeeDAO = new EmployeeImpl();
        return employeeDAO.getObject(id);
    }

    @Override
    public void delete(int id) throws ConnectException {
        IEmployeeDAO employeeDAO = new EmployeeImpl();
        employeeDAO.delete(id);
    }

    @Override
    public void update(Employee employee) throws ConnectException {
        IEmployeeDAO employeeDAO = new EmployeeImpl();
        employeeDAO.update(employee);
    }

    @Override
    public void create(Employee employee) throws ConnectException {
        IEmployeeDAO employeeDAO = new EmployeeImpl();
        employeeDAO.insert(employee);
    }

    @Override
    public List<Employee> getAllEmployees() throws ConnectException {
        IEmployeeDAO employeeDAO = new EmployeeImpl();
        return employeeDAO.getList();
    }
}

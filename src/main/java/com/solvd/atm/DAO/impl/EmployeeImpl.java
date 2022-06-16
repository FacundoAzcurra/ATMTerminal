package com.solvd.atm.DAO.impl;

import com.solvd.atm.DAO.DAOException;
import com.solvd.atm.DAO.IEmployeeDAO;
import com.solvd.atm.bin.Employee;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class EmployeeImpl extends AbstractDAO implements IEmployeeDAO {
    private final static String GET_ONE = "SELECT * FROM employee WHERE id_employee = ?";

    @Override
    public void insert(Employee a) throws DAOException, ConnectException {
        throw new UnsupportedOperationException("This function is not available");
    }

    @Override
    public void update(Employee a) throws DAOException, ConnectException {
        throw new UnsupportedOperationException("This function is not available");
    }

    @Override
    public void delete(int a) throws DAOException, ConnectException {
        throw new UnsupportedOperationException("This function is not available");
    }

    @Override
    public List<Employee> getList() throws DAOException, ConnectException {
        throw new UnsupportedOperationException("This function is not available");
    }

    @Override
    public Employee getObject(Integer id) throws DAOException, ConnectException {
        PreparedStatement pr = null;
        ResultSet rs = null;
        Connection con = getConnection();
        Employee employee = new Employee();
        try {
            pr = con.prepareStatement(GET_ONE);
            pr.setInt(1, id);
            rs = pr.executeQuery();
            rs.next();
            employee.setEmployeeId(id);
            employee.setFirstName(rs.getString("name"));
            employee.setLastName(rs.getString("lastname"));
            employee.setBirthday(rs.getDate("birthday"));
            employee.setBankId(rs.getInt("bank_id"));
        } catch (SQLException e) {
            throw new DAOException("SQL Error.", e);
        } finally {
            returnConnection(con);
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new DAOException("SQL Error.", e);
                }
            }
            if (pr != null) {
                try {
                    pr.close();
                } catch (SQLException e) {
                    throw new DAOException("SQL Error.", e);
                }
            }
        }
        return employee;
    }
}

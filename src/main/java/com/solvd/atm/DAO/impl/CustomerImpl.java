package com.solvd.atm.DAO.impl;

import com.solvd.atm.DAO.DAOException;
import com.solvd.atm.DAO.ICustomerDAO;
import com.solvd.atm.DAO.IUserDAO;
import com.solvd.atm.bin.Customer;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerImpl extends AbstractDAO implements ICustomerDAO {
    private final static String INSERT = "INSERT INTO customer(id_customer, name, lastname, birthday, user_id) VALUES (?,?,?,?,?) ";
    private final static String UPDATE = "UPDATE customer SET name = ?, lastname = ?, birthday = ? WHERE id_customer = ?";
    private final static String DELETE = "DELETE FROM customer where id_customer = ?";
    private final static String GET_ALL = "SELECT * FROM customer";
    private final static String GET_ONE = "SELECT * FROM customer WHERE id_customer = ?";

    @Override
    public void insert(Customer a) throws DAOException, ConnectException {
        PreparedStatement stat = null;
        Connection conn = getConnection();
        try {
            stat = conn.prepareStatement(INSERT);
            stat.setInt(1, a.getCustomerId());
            stat.setString(2, a.getFirstName());
            stat.setString(3, a.getLastName());
            stat.setDate(4, a.getBirthday());
            stat.setInt(5, a.getUser().getUserId());
            if (stat.executeUpdate() == 0) {
                throw new DAOException("It may not have saved");
            }
        } catch (SQLException e) {
            throw new DAOException("SQL ERROR", e);
        } finally {
            returnConnection(conn);
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException e) {
                    throw new DAOException("SQL ERROR", e);
                }
            }
        }
    }

    @Override
    public void update(Customer a) throws DAOException, ConnectException {
        PreparedStatement stat = null;
        Connection conn = getConnection();
        try {
            stat = conn.prepareStatement(UPDATE);
            stat.setString(1, a.getFirstName());
            stat.setString(2, a.getFirstName());
            stat.setString(3, a.getLastName());
            stat.setDate(4, a.getBirthday());
            if (stat.executeUpdate() == 0) {
                throw new DAOException("It may not have saved");
            }
        } catch (SQLException e) {
            throw new DAOException("SQL ERROR", e);
        } finally {
            returnConnection(conn);
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException e) {
                    throw new DAOException("SQL ERROR", e);
                }
            }
        }
    }

    @Override
    public void delete(int a) throws DAOException, ConnectException {
        PreparedStatement stat = null;
        Connection conn = getConnection();
        try {
            stat = conn.prepareStatement(DELETE);
            stat.setInt(1, a);

            if (stat.executeUpdate() == 0) {
                throw new DAOException("It may not have saved");
            }

        } catch (SQLException e) {
            throw new DAOException("SQL ERROR", e);
        } finally {
            returnConnection(conn);
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException e) {
                    throw new DAOException("SQL ERROR", e);
                }
            }
        }
    }

    private Customer convert(ResultSet rs) throws SQLException, ConnectException {
        Customer cus = new Customer();
        cus.setCustomerId(rs.getInt("id"));
        cus.setFirstName(rs.getString("name"));
        cus.setLastName(rs.getString("lastname"));
        cus.setBirthday(rs.getDate("birthday"));
        UserImpl userDao = new UserImpl();
        cus.setUser(userDao.getObject((rs.getInt("user_id"))));
        return cus;
    }

    @Override
    public List<Customer> getList() throws DAOException, ConnectException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Customer> customerList = new ArrayList<>();
        Connection conn = getConnection();
        try{
            stat = conn.prepareStatement(GET_ALL);
            rs = stat.executeQuery();
            while(rs.next()) {
                customerList.add(convert(rs));
            }
        } catch (SQLException e) {
            throw new DAOException("SQL Error.",e);
        } finally {
            returnConnection(conn);
            if(rs!=null){
                try{
                    rs.close();
                }catch (SQLException e){
                    throw new DAOException("SQL Error.",e);
                }
            }
            if (stat != null){
                try {
                    stat.close();
                } catch (SQLException e){
                    throw new DAOException("SQL Error.",e);
                }
            }
        } return customerList;
    }

    @Override
    public Customer getObject(Integer id) throws DAOException, ConnectException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        Connection conn = getConnection();
        Customer cus = null;
        try {
            stat = conn.prepareStatement(GET_ONE);
            stat.setInt(1, id);
            rs = stat.executeQuery();
            if (rs.next()) {
                cus = convert(rs);
            } else {
                throw new DAOException("Register not found.");
            }

        } catch (SQLException e) {
            throw new DAOException("SQL Error.", e);
        } finally {
            returnConnection(conn);
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new DAOException("SQL Error.", e);
                }
            }
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException e) {
                    throw new DAOException("SQL Error.", e);
                }
            }
        }
        return cus;
    }
}

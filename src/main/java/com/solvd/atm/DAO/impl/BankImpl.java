package com.solvd.atm.DAO.impl;

import com.solvd.atm.DAO.DAOException;
import com.solvd.atm.DAO.IBankDAO;
import com.solvd.atm.bin.Bank;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BankImpl extends AbstractDAO implements IBankDAO {
    private final static String GET_ONE = "SELECT * FROM bank WHERE id_Bank = ?";
    private final static String GET_ALL = "SELECT * FROM bank";

    @Override
    public void insert(Bank a) throws DAOException, ConnectException {
        throw new UnsupportedOperationException("This function is not available");
    }

    @Override
    public void update(Bank a) throws DAOException, ConnectException {
        throw new UnsupportedOperationException("This function is not available");
    }

    @Override
    public void delete(int a) throws DAOException, ConnectException {
        throw new UnsupportedOperationException("This function is not available");
    }

    private Bank convert(ResultSet rs) throws SQLException {
        int idBank = rs.getInt("id_bank");
        String name = rs.getString("name");
        Bank banks = new Bank(idBank, name);
        return banks;
    }

    @Override
    public List<Bank> getList() throws DAOException, ConnectException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Bank> banks = new ArrayList<>();
        Connection conn = getConnection();
        try {
            stat = conn.prepareStatement(GET_ALL);
            rs = stat.executeQuery();
            while (rs.next()) {
                banks.add(convert(rs));
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
        return banks;
    }

    @Override
    public Bank getObject(Integer id) throws DAOException, ConnectException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        Bank b = null;
        Connection conn = getConnection();
        try {
            stat = conn.prepareStatement(GET_ONE);
            stat.setInt(1, id);
            rs = stat.executeQuery();
            if (rs.next()) {
                b = convert(rs);
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
        return b;
    }

}

package com.solvd.atm.DAO.impl;

import com.solvd.atm.DAO.DAOException;
import com.solvd.atm.DAO.IAccountsDAO;
import com.solvd.atm.bin.Account;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountsImpl extends AbstractDAO implements IAccountsDAO {

    private final static String INSERT = "INSERT INTO Accounts(idAccounts, balance, fullName, cardNumber, user_id, bank_id) VALUES (?,?,?,?,?,?) ";
    private final static String UPDATE = "UPDATE Accounts SET balance = ?, fullName = ?, cardNumber = ? WHERE idAccounts = ?";
    private final static String DELETE = "DELETE FROM Accounts where idAccounts = ?";
    private final static String GET_ALL = "SELECT idAccounts, balance, fullName, cardNumber FROM Accounts";
    private final static String GET_ONE = "SELECT idAccounts, balance, fullName, cardNumber FROM Accounts WHERE idAccounts = ?";


    @Override
    public void insert(Account a) throws DAOException, ConnectException {
        PreparedStatement stat = null;
        Connection conn = getConnection();
        try{
            stat = conn.prepareStatement(INSERT);
            stat.setInt(1,a.getIdAccounts());
            stat.setDouble(2,a.getBalance());
            stat.setString(3,a.getFullName());
            stat.setInt(4,a.getCardNumber());
            stat.setInt(5,a.getUserId());
            stat.setInt(6,a.getBankId());
            if(stat.executeUpdate() == 0 ){
                throw new DAOException("It may not have saved");
            }
        } catch (SQLException e) {
            throw new DAOException("SQL ERROR",e);
        } finally {
            returnConnection(conn);
            if (stat != null){
                try{
                    stat.close();
                } catch (SQLException e){
                    throw new DAOException("SQL ERROR",e);
                }
            }
        }
    }

    @Override
    public void update(Account a) throws DAOException, ConnectException {
        PreparedStatement stat = null;
        Connection conn = getConnection();
        try{
            stat = conn.prepareStatement(UPDATE);
            stat.setDouble(1,a.getBalance());
            stat.setString(2,a.getFullName());
            stat.setInt(3,a.getCardNumber());
            stat.setInt(4,a.getIdAccounts());
            if(stat.executeUpdate() == 0 ){
                throw new DAOException("It may not have saved");
            }
        } catch (SQLException e) {
            throw new DAOException("SQL ERROR",e);
        } finally {
            returnConnection(conn);
            if (stat != null){
                try{
                    stat.close();
                } catch (SQLException e){
                    throw new DAOException("SQL ERROR",e);
                }
            }
        }
    }

    @Override
    public void delete(int a) throws DAOException, ConnectException {
        PreparedStatement stat = null;
        Connection conn = getConnection();
        try{
            stat = conn.prepareStatement(DELETE);
            stat.setInt(1,a);

            if(stat.executeUpdate() == 0 ){
                throw new DAOException("It may not have saved");
            }

        } catch (SQLException e) {
            throw new DAOException("SQL ERROR",e);
        } finally {
            returnConnection(conn);
            if (stat != null){
                try{
                    stat.close();
                } catch (SQLException e){
                    throw new DAOException("SQL ERROR",e);
                }
            }
        }
    }

    private Account convert (ResultSet rs) throws SQLException {
        int idAccounts = rs.getInt("idAccounts");
        double balance = rs.getDouble("balance");
        String fullName = rs.getString("fullName");
        int cardNumber = rs.getInt("cardNumber");
        int userId = rs.getInt("user_id");
        int bankId = rs.getInt("bank_id");
        Account account = new Account(idAccounts, balance, fullName, cardNumber,userId,bankId);
        return account;
    }

    @Override
    public List<Account> getList() throws DAOException, ConnectException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Account> accountList = new ArrayList<>();
        Connection conn = getConnection();
        try{
            stat = conn.prepareStatement(GET_ALL);
            rs = stat.executeQuery();
            while(rs.next()) {
                accountList.add(convert(rs));
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
        } return accountList;
    }

    @Override
    public Account getObject(Integer id) throws DAOException, ConnectException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        Account a = null;
        Connection conn = getConnection();
        try{
            stat = conn.prepareStatement(GET_ONE);
            stat.setInt(1,id);
            rs = stat.executeQuery();
            if (rs.next()) {
                a = convert(rs);

            } else {
                throw new DAOException("Register not found.");
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
        } return a;
    }
}

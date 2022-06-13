package com.solvd.atm.DAO.impl;

import com.solvd.atm.DAO.DAOException;
import com.solvd.atm.DAO.ICardsDAO;
import com.solvd.atm.bin.Cards;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CardsImpl extends AbstractDAO implements ICardsDAO {
    private final static String INSERT = "INSERT INTO Cards(idCards, paymentNetwork, accountNumber) VALUES (?,?,?) ";
    private final static String UPDATE = "UPDATE Cards SET paymentNetwork = ?, accountNumber = ? WHERE idCards = ?";
    private final static String DELETE = "DELETE FROM Cards where idCards = ?";
    private final static String GET_ALL = "SELECT idCards, paymentNetwork, accountNumber FROM Cards";
    private final static String GET_ONE = "SELECT idCards, paymentNetwork, accountNumber FROM Cards WHERE idCards = ?";

    @Override
    public void insert(Cards a) throws DAOException, ConnectException {
        PreparedStatement stat = null;
        Connection conn = getConnection();
        try {
            stat = conn.prepareStatement(INSERT);
            stat.setInt(1, a.getIdCards());
            stat.setString(2, a.getPaymentNetwork());
            stat.setInt(3, a.getAccountNumber());
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
    public void update(Cards a) throws DAOException, ConnectException {
        PreparedStatement stat = null;
        Connection conn = getConnection();
        try {
            stat = conn.prepareStatement(UPDATE);
            stat.setString(1, a.getPaymentNetwork());
            stat.setInt(2, a.getAccountNumber());
            stat.setInt(3, a.getIdCards());
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

    private Cards convert (ResultSet rs) throws SQLException {
        int idCards = rs.getInt("idCards");
        String paymentNetwork = rs.getString("paymentNetwork");
        int accountNumber = rs.getInt("accountNumber");
        Cards cards = new Cards(idCards, paymentNetwork, accountNumber);
        return cards;
        }

    @Override
    public List<Cards> getList() throws DAOException, ConnectException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Cards> cardsList = new ArrayList<>();
        Connection conn = getConnection();
        try{
            stat = conn.prepareStatement(GET_ALL);
            rs = stat.executeQuery();
            while(rs.next()) {
                cardsList.add(convert(rs));
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
        } return cardsList;
    }

    @Override
    public Cards getObject(Integer id) throws DAOException, ConnectException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        Cards a = null;
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


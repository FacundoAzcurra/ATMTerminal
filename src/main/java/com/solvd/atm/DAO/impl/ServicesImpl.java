package com.solvd.atm.DAO.impl;

import com.solvd.atm.DAO.DAOException;
import com.solvd.atm.DAO.IServicesDAO;
import com.solvd.atm.bin.Services;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServicesImpl extends AbstractDAO implements IServicesDAO {
    private final static String UPDATE = "UPDATE sevices SET amount WHERE id_services = ?";
    private final static String GET_ALL = "SELECT * FROM services";
    private final static String GET_ONE = "SELECT * FROM services WHERE id_services = ?";

    @Override
    public void insert(Services a) throws DAOException, ConnectException {
        throw new UnsupportedOperationException("This function is not available");
    }

    @Override
    public void update(Services a) throws DAOException, ConnectException {
        PreparedStatement stat = null;
        Connection conn = getConnection();
        try {
            stat = conn.prepareStatement(UPDATE);
            stat.setInt(1, a.getAmount());
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
        throw new UnsupportedOperationException("This function is not available");
    }

    private Services convert(ResultSet rs) throws SQLException {
        int serviceId = rs.getInt("id_service");
        String serviceName = rs.getString("service_name");
        int amount = rs.getInt("amount");
        int accountNumber = rs.getInt("account_id");
        Services services = new Services(serviceId, serviceName, amount, accountNumber);
        return services;
    }

    @Override
    public List<Services> getList() throws DAOException, ConnectException {
        PreparedStatement stat=null;
        ResultSet rs=null;
        List<Services> servicesList= new ArrayList<>();
        Connection conn = null;
        try{
            stat = conn.prepareStatement(GET_ALL);
            rs = stat.executeQuery();
            while (rs.next()) {
                servicesList.add(convert(rs));
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
        return servicesList;
    }

    @Override
    public Services getObject(Integer id) throws DAOException, ConnectException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        Services a = null;
        Connection conn = getConnection();
        try {
            stat = conn.prepareStatement(GET_ONE);
            stat.setInt(1, id);
            rs = stat.executeQuery();
            if (rs.next()) {
                a = convert(rs);

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
        return a;
    }
}

package com.solvd.atm.DAO.impl;

import com.solvd.atm.util.ConnectionPool;

import java.net.ConnectException;
import java.sql.Connection;

public abstract class AbstractDAO {

    public synchronized Connection getConnection() throws ConnectException {
        return ConnectionPool.getInstance().getConnection();
    }

    public synchronized void returnConnection(Connection connection){
        ConnectionPool.getInstance().returnConnection(connection);
    }
}

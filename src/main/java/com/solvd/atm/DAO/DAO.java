package com.solvd.atm.DAO;

import java.net.ConnectException;
import java.util.List;

public interface DAO<T,K> {
    void insert(T a) throws DAOException, ConnectException;

    void update(T a) throws DAOException, ConnectException;

    void delete(int a) throws DAOException, ConnectException;

    List<T> getList() throws DAOException, ConnectException;

    T getObject(Integer id) throws DAOException, ConnectException;
}


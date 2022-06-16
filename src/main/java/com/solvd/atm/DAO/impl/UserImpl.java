package com.solvd.atm.DAO.impl;

import com.solvd.atm.DAO.DAOException;
import com.solvd.atm.DAO.IUserDAO;
import com.solvd.atm.bin.User;

import java.net.ConnectException;
import java.util.List;

public class UserImpl extends AbstractDAO implements IUserDAO {
    private final static String INSERT = "INSERT INTO user(id_user, username, password) VALUES (?,?,?) ";
    private final static String UPDATE = "UPDATE user SET username = ?, password = ? WHERE id_user = ?";
    private final static String DELETE = "DELETE FROM user where id_user = ?";
    private final static String GET_ALL = "SELECT * FROM user";
    private final static String GET_ONE = "SELECT * FROM user WHERE id_user = ?";

    @Override
    public void insert(User a) throws DAOException, ConnectException {

    }

    @Override
    public void update(User a) throws DAOException, ConnectException {

    }

    @Override
    public void delete(int a) throws DAOException, ConnectException {

    }

    @Override
    public List<User> getList() throws DAOException, ConnectException {
        return null;
    }

    @Override
    public User getObject(Integer id) throws DAOException, ConnectException {
        return null;
    }
}

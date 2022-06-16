package com.solvd.atm.service.jdbc;

import com.solvd.atm.DAO.IUserDAO;
import com.solvd.atm.DAO.impl.UserImpl;
import com.solvd.atm.bin.User;
import com.solvd.atm.service.IUserService;

import java.net.ConnectException;
import java.util.List;

public class UserServiceImpl implements IUserService {

    @Override
    public User getUser(int id) throws ConnectException {
        IUserDAO userDAO = new UserImpl();
        return userDAO.getObject(id);
    }

    @Override
    public void delete(int id) throws ConnectException {
        IUserDAO userDAO = new UserImpl();
        userDAO.delete(id);
    }

    @Override
    public void update(User user) throws ConnectException {
        IUserDAO userDAO = new UserImpl();
        userDAO.update(user);
    }

    @Override
    public void create(User user) throws ConnectException {
        IUserDAO userDAO = new UserImpl();
        userDAO.insert(user);
    }

    @Override
    public List<User> getAllUsers() throws ConnectException {
        IUserDAO userDAO = new UserImpl();
        return userDAO.getList();
    }
}

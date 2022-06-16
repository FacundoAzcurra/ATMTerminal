package com.solvd.atm.service;

import com.solvd.atm.bin.User;

import java.net.ConnectException;
import java.util.List;

public interface IUserService {
    User getUser(int id) throws ConnectException;

    void delete(int id) throws ConnectException;

    void update(User user) throws ConnectException;

    void create(User user) throws ConnectException;

    List<User> getAllUsers() throws ConnectException;
}

package com.solvd.atm.service;

import com.solvd.atm.bin.Services;

import java.net.ConnectException;
import java.util.List;

public interface IServicesService {
    Services getServices(int id) throws ConnectException;

    void delete(int id) throws ConnectException;

    void update(Services services) throws ConnectException;

    void create(Services services) throws ConnectException;

    List<Services> getAllServices() throws ConnectException;
}

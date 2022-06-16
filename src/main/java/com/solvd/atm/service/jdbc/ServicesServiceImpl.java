package com.solvd.atm.service.jdbc;

import com.solvd.atm.DAO.IServicesDAO;
import com.solvd.atm.DAO.impl.ServicesImpl;
import com.solvd.atm.bin.Services;
import com.solvd.atm.service.IServicesService;

import java.net.ConnectException;
import java.util.List;

public class ServicesServiceImpl implements IServicesService {
    @Override
    public Services getServices(int id) throws ConnectException {
        IServicesDAO servicesDAO = new ServicesImpl();
        return servicesDAO.getObject(id);
    }

    @Override
    public void delete(int id) throws ConnectException {
        IServicesDAO servicesDAO = new ServicesImpl();
        servicesDAO.delete(id);
    }

    @Override
    public void update(Services services) throws ConnectException {
        IServicesDAO servicesDAO = new ServicesImpl();
        servicesDAO.update(services);
    }

    @Override
    public void create(Services services) throws ConnectException {
        IServicesDAO servicesDAO = new ServicesImpl();
        servicesDAO.insert(services);
    }

    @Override
    public List<Services> getAllServices() throws ConnectException {
        IServicesDAO servicesDAO = new ServicesImpl();
        return servicesDAO.getList();
    }
}

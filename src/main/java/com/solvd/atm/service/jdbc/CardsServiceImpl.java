package com.solvd.atm.service.jdbc;

import com.solvd.atm.DAO.ICardsDAO;
import com.solvd.atm.DAO.impl.CardsImpl;
import com.solvd.atm.bin.Cards;
import com.solvd.atm.service.ICardsService;

import java.net.ConnectException;
import java.util.List;

public class CardsServiceImpl implements ICardsService {
    @Override
    public Cards getCard(int id) throws ConnectException {
        ICardsDAO cardsDAO = new CardsImpl();
        return cardsDAO.getObject(id);
    }

    @Override
    public void delete(int id) throws ConnectException {
        ICardsDAO cardsDAO = new CardsImpl();
        cardsDAO.delete(id);
    }

    @Override
    public void update(Cards card) throws ConnectException {
        ICardsDAO cardsDAO = new CardsImpl();
        cardsDAO.update(card);
    }

    @Override
    public void create(Cards card) throws ConnectException {
        ICardsDAO cardsDAO = new CardsImpl();
        cardsDAO.insert(card);
    }

    @Override
    public List<Cards> getAllCards() throws ConnectException {
        ICardsDAO cardsDAO = new CardsImpl();
        return cardsDAO.getList();
    }
}

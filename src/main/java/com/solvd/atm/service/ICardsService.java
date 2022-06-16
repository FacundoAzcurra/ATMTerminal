package com.solvd.atm.service;

import com.solvd.atm.bin.Cards;

import java.net.ConnectException;
import java.util.List;

public interface ICardsService {
    Cards getCard(int id) throws ConnectException;

    void delete(int id) throws ConnectException;

    void update(Cards card) throws ConnectException;

    void create(Cards card) throws ConnectException;

    List<Cards> getAllCards() throws ConnectException;
}

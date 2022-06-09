package com.solvd.atm.bin;

import java.util.Objects;

public class Cards {
    int idCards;
    String paymentNetwork;
    int accountNumber;

    public Cards() {
    }

    public Cards(int idCards, String paymentNetwork, int accountNumber) {
        this.idCards = idCards;
        this.paymentNetwork = paymentNetwork;
        this.accountNumber = accountNumber;
    }

    public int getIdCards() {
        return idCards;
    }

    public void setIdCards(int idCards) {
        this.idCards = idCards;
    }

    public String getPaymentNetwork() {
        return paymentNetwork;
    }

    public void setPaymentNetwork(String paymentNetwork) {
        this.paymentNetwork = paymentNetwork;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cards cards = (Cards) o;
        return idCards == cards.idCards && accountNumber == cards.accountNumber && Objects.equals(paymentNetwork, cards.paymentNetwork);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCards, paymentNetwork, accountNumber);
    }

    @Override
    public String toString() {
        return "Cards{" +
                "idCards=" + idCards +
                ", paymentNetwork='" + paymentNetwork + '\'' +
                ", accountNumber=" + accountNumber +
                '}';
    }
}

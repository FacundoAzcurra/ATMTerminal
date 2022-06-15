package com.solvd.atm.bin;

import com.solvd.atm.bin.common.Person;

import java.sql.Date;
import java.util.Objects;

public class Customer extends Person {
    private int customerId;
    private User user;

    public Customer() {
    }

    public Customer(String firstName, String lastName, Date birthday, char sex, int customerId, User user) {
        super(firstName, lastName, birthday, sex);
        this.customerId = customerId;
        this.user = user;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Customer customer = (Customer) o;
        return customerId == customer.customerId && Objects.equals(user, customer.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), customerId, user);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", user=" + user +
                '}';
    }
}

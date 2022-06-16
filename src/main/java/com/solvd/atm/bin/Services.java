package com.solvd.atm.bin;

import java.util.Objects;

public class Services {
    private int serviceId;
    private String serviceName;
    private double amount;
    private int accountNumber;

    public Services(){
    }

    public Services(int serviceId, String serviceName, double amount, int accountNumber) {
        this.serviceId = serviceId;
        this.serviceName = serviceName;
        this.amount = amount;
        this.accountNumber = accountNumber;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
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
        Services services = (Services) o;
        return serviceId == services.serviceId && amount == services.amount && accountNumber == services.accountNumber && Objects.equals(serviceName, services.serviceName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serviceId, serviceName, amount, accountNumber);
    }

    @Override
    public String toString() {
        return serviceName+ ": " + "service with serial number id: " + serviceId + ".\n" +
                "The amount will be: " + amount + ".\n" +
                "Will credit from account number: " + accountNumber;
    }
}

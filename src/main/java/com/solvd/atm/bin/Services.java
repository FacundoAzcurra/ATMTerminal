package com.solvd.atm.bin;

import java.util.Objects;

public class Services {
    private int serviceId;
    private String serviceName;
    private int amount;
    private int accountNumber;

    public Services(){
    }

    public Services(int serviceId, String serviceName, int amount, int accountNumber) {
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
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
        return "Services{" +
                "serviceId=" + serviceId +
                ", serviceName='" + serviceName + '\'' +
                ", amount=" + amount +
                ", accountNumber=" + accountNumber +
                '}';
    }
}

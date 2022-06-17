package com.solvd.atm.bin;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.solvd.atm.bin.common.Person;

import java.sql.Date;
import java.util.Objects;

public class Employee extends Person {

    @JsonProperty
    private int employeeId;
    @JsonProperty
    private int bankId;

    public Employee() {
    }

    public Employee(int employeeId) {
        this.employeeId = employeeId;
    }

    public Employee(String firstName, String lastName, Date birthday, char sex, int employeeId) {
        super(firstName, lastName, birthday, sex);
        this.employeeId = employeeId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getBankId() {
        return bankId;
    }

    public void setBankId(int bankId) {
        this.bankId = bankId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Employee employee = (Employee) o;
        return employeeId == employee.employeeId && bankId == employee.bankId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), employeeId, bankId);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", bankId=" + bankId +
                '}';
    }
}

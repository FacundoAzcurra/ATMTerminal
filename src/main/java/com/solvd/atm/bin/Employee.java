package com.solvd.atm.bin;

import com.solvd.atm.bin.common.Person;

import java.sql.Date;
import java.util.Objects;

public class Employee extends Person {

    private int employeeId;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Employee employee = (Employee) o;
        return employeeId == employee.employeeId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), employeeId);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                '}';
    }
}

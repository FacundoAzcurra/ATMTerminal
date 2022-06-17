package com.solvd.atm.service.Json;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.solvd.atm.bin.Account;
import com.solvd.atm.bin.Employee;
import com.solvd.atm.bin.common.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Json {

    private final static Logger LOGGER = LogManager.getLogger(Json.class);

    public static List<Employee> jsonService(File file){

        List<Employee> employeeList = new ArrayList<>();
        ObjectMapper om = new ObjectMapper();

        try{
            JavaType type = om.getTypeFactory().constructCollectionType(List.class, Person.class);
            employeeList = om.readValue(file, type);
        } catch (StreamReadException e) {
            e.printStackTrace();
        } catch (DatabindException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return employeeList;
    }
}

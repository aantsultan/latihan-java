package com.latihan.java.datastructure.list;

import com.latihan.java.datastructure.list.model.Employee;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VectorTest {

    @Test
    void vector(){
        // vector using syncronized on .add() function and it is safe-thread
        List<Employee> employees = new Vector<>();
        employees.add(new Employee("aant", "sultan", 1));
        employees.add(new Employee("sultan", "elaina", 2));
        employees.add(new Employee("rahmanya", "kiana", 3));
        employees.add(new Employee("ki", "ana", 4));
        employees.add(new Employee("el", "ina", 5));
        assertEquals(5, employees.size());
    }

}

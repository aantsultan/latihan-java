package com.latihan.java.datastructure.list;

import com.latihan.java.datastructure.list.model.Employee;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArrayListTest {

    @Test
    void arrayList(){
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("aant", "sultan", 1));
        employees.add(new Employee("sultan", "elaina", 2));
        employees.add(new Employee("rahmanya", "kiana", 3));
        employees.add(new Employee("ki", "ana", 4));
        employees.add(new Employee("el", "ina", 5));

        employees.forEach(System.out::println);

        // get data
        assertEquals(1, employees.get(0).getId());
        assertFalse(employees.isEmpty());

        // change data
        employees.set(0, new Employee("aant edit", "sultan edit", 11));
        assertEquals(11, employees.get(0).getId());

        // size list
        assertEquals(5, employees.size());

        // collection is shift up
        employees.add(1, new Employee("aant add", "sultan add", 12));
        assertEquals(12, employees.get(1).getId());
        assertEquals(2, employees.get(2).getId());
        assertEquals(6, employees.size());

        Employee[] employeeArray = employees.toArray(new Employee[employees.size()]);
        assertEquals(6, employeeArray.length);

        // true, because there is function  public boolean equals(Object o) on Employee Class
        assertTrue(employees.contains(new Employee("aant add", "sultan add", 12)));
        assertEquals(1, employees.indexOf(new Employee("aant add", "sultan add", 12)));

        // remove
        employees.remove(1);
        assertFalse(employees.contains(new Employee("aant add", "sultan add", 12)));
        assertEquals(5, employees.size());
    }

}

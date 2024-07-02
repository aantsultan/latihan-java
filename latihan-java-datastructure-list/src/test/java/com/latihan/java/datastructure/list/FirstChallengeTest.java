package com.latihan.java.datastructure.list;

import com.latihan.java.datastructure.list.challange1.Employee;
import com.latihan.java.datastructure.list.challange1.EmployeeDoublyLinkedList;
import org.junit.jupiter.api.Test;

class FirstChallengeTest {

    @Test
    void firstChallenge(){
        Employee janeJones = new Employee("Jane", "Jones", 123);
        Employee johnDoe = new Employee("John", "Doe", 4567);
        Employee marySmith = new Employee("Mary", "Smith", 22);
        Employee mikeWilson = new Employee("Mike", "Wilson", 3245);
        Employee billEnd = new Employee("Bill", "End", 78);

        EmployeeDoublyLinkedList list = new EmployeeDoublyLinkedList();

        list.addToFront(janeJones);
        list.addToFront(johnDoe);
        list.addToFront(marySmith);
        list.addToFront(mikeWilson);
        list.printList();

        boolean b = list.addBefore(billEnd, johnDoe);
        System.out.println(b);
        list.printList();
        boolean b1 = list.addBefore(new Employee("Someone", "Else", 1111), mikeWilson);
        System.out.println(b1);
        list.printList();
    }

}

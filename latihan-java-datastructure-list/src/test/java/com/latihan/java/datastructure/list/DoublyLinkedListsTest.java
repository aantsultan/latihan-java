package com.latihan.java.datastructure.list;

import com.latihan.java.datastructure.list.model.Employee;
import com.latihan.java.datastructure.list.model.doubly.EmployeeLinkedList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DoublyLinkedListsTest {

    @Test
    void doublyLinkedLists(){
        Employee aantSultan = new Employee("aant", "sultan", 1);
        Employee aantRahmanya = new Employee("aant", "rahmanya", 2);
        Employee sultanRahmanya = new Employee("sultan", "rahmanya", 3);

        EmployeeLinkedList list = new EmployeeLinkedList();
        /*
         * tail : {
         *  employee : {
         *   firstName: 'aant',
         *   lastName: 'sultan',
         *   id : 1
         *  },
         *  next: null,
         *  previous: null
         * },
         * head : {
         *  employee : {
         *   firstName: 'aant',
         *   lastName: 'sultan',
         *   id : 1
         *  },
         *  next: null,
         *  previous: null
         * }
         */
        list.addToFront(aantSultan);

        /*
         * tail : {
         *  employee : {
         *   firstName: 'aant',
         *   lastName: 'sultan',
         *   id : 1
         *  },
         *  next: null,
         *  previous: null
         * },
         * head : {
         *  employee : {
         *   firstName: 'aant',
         *   lastName: 'rahmanya',
         *   id: 2
         *  },
         *  next: {
         *   employee : {
         *    firstName: 'aant',
         *    lastName: 'sultan',
         *    id: 1
         *   },
         *   next:null,
         *   previous: null
         *  },
         *  previous: null
         * }
         */
        list.addToFront(aantRahmanya);

        /*
         * tail : {
         *  employee : {
         *   firstName: 'aant',
         *   lastName: 'sultan',
         *   id : 1
         *  },
         *  next: null,
         *  previous: null
         * },
         * head : {
         *  employee : {
         *   firstName: 'sultan',
         *   lastName: 'rahmanya',
         *   id: 3
         *  },
         *  next: {
         *   employee : {
         *    firstName: 'aant',
         *    lastName: 'rahmanya',
         *    id: 2
         *   },
         *   next: {
         *    employee : {
         *     firstName: 'aant',
         *     lastName: 'sultan',
         *     id: 1
         *    },
         *    next:null,
         *    previous: null
         *   },
         *   previous: null
         *  },
         *  previous: null
         * }
         */
        list.addToFront(sultanRahmanya);

        assertEquals(3, list.getSize());
        list.print();

        Employee elainaAant = new Employee("elaina", "aant", 4);

        list.addToEnd(elainaAant);
        assertEquals(4, list.getSize());
        list.print();

        list.removeFromFront();
        assertEquals(3, list.getSize());
        list.print();

        list.removeFromEnd();
        assertEquals(2, list.getSize());
        list.print();

    }

    @Test
    void doublyTest(){
        Employee aantSultan = new Employee("aant", "sultan", 1);
        Employee aantRahmanya = new Employee("aant", "rahmanya", 2);
        Employee elainaEl = new Employee("elaina", "el", 3);
        EmployeeLinkedList list = new EmployeeLinkedList();

        list.addToFront(elainaEl);
        list.addToEnd(aantSultan);
        list.addToEnd(aantRahmanya);
        list.print();
    }
}

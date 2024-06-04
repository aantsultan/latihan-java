package com.latihan.java.datastructure.list;

import com.latihan.java.datastructure.list.model.Employee;
import com.latihan.java.datastructure.list.model.singly.EmployeeLinkedList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SinglyLinkedListsTest {

    @Test
    void singlyLinkedLists(){
        Employee aantSultan = new Employee("aant", "sultan", 1);
        Employee aantRahmanya = new Employee("aant", "rahmanya", 2);
        Employee sultanRahmanya = new Employee("sultan", "rahmanya", 3);

        EmployeeLinkedList list = new EmployeeLinkedList();
        assertTrue(list.isEmpty());

        /*
        * head : {
        *  employee : {
        *   firstName: 'aant',
        *   lastName: 'sultan',
        *   id : 1
        *  },
        *  next:null
        * }
        */
        list.addToFront(aantSultan);

        /*
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
         *   next:null
         *  }
         * }
         */
        list.addToFront(aantRahmanya);

        /*
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
         *    next:null
         *   }
         *  }
         * }
         */
        list.addToFront(sultanRahmanya);

        assertFalse(list.isEmpty());
        assertEquals(3, list.getSize());
        list.print();

        list.removeFromFront();
        assertEquals(2, list.getSize());
        list.print();
    }

}

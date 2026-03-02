package com.latihan.java.logic.udemy.dsa;

import com.latihan.java.logic.udemy.dsa.linkedlist.LinkedList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LinkedListTest {

    @Test
    void printList_Test() {
        int value = 4;
        int length = 1;
        LinkedList myLinkedList = new LinkedList(value);
        Assertions.assertEquals(value, myLinkedList.getHead());
        Assertions.assertEquals(value, myLinkedList.getTail());
        Assertions.assertEquals(length, myLinkedList.getLength());
        myLinkedList.printList();
    }

    @Test
    void printList_Append() {
        LinkedList myLinkedList = new LinkedList(1);
        int expectedValue = 2;
        int resultValue = myLinkedList.append(expectedValue).value;
        Assertions.assertEquals(expectedValue, resultValue);
        myLinkedList.printList();
    }

    @Test
    void printList_RemoveLast_Only_1() {
        int expected = 1;
        LinkedList myLinkedList = new LinkedList(expected);
        int result = myLinkedList.removeLast().value;
        Assertions.assertEquals(expected, result);
        myLinkedList.printList();
    }

    @Test
    void printList_RemoveLast_ContaintsElement() {
        LinkedList myLinkedList = new LinkedList(1);
        int expectedValue = 4;
        myLinkedList.append(2);
        myLinkedList.append(3);
        myLinkedList.append(expectedValue);
        int removedValue = myLinkedList.removeLast().value;
        Assertions.assertEquals(expectedValue, removedValue);
        System.out.println("Removed : " + removedValue);
        myLinkedList.printList();
    }

    @Test
    void printList_Prepend() {
        LinkedList myLinkedList = new LinkedList(2);
        myLinkedList.append(3);
        myLinkedList.append(4);

        int expectedValue = 1;
        int resultValue = myLinkedList.prepend(expectedValue).value;
        Assertions.assertEquals(expectedValue, resultValue);
        myLinkedList.printList();
    }

    @Test
    void printList_RemoveFirst() {
        int expected = 1;
        LinkedList myLinkedList = new LinkedList(expected);
        myLinkedList.append(2);
        myLinkedList.append(3);

        int result = myLinkedList.removeFirst().value;
        Assertions.assertEquals(expected, result);
        myLinkedList.printList();
    }

    @Test
    void get_MiddleIndex() {
        int expected = 3;
        int index = 2;
        LinkedList myLinkedList = new LinkedList(1);
        myLinkedList.append(2);
        myLinkedList.append(expected);
        myLinkedList.append(4);

        int result = myLinkedList.get(index).value;
        Assertions.assertEquals(expected, result);
    }

    @Test
    void get_FirstIndex() {
        int expected = 1;
        int index = 0;
        LinkedList myLinkedList = new LinkedList(expected);
        myLinkedList.append(2);

        int result = myLinkedList.get(index).value;
        Assertions.assertEquals(expected, result);
    }

    @Test
    void get_OutOfRangeIndex() {
        int indexBelowZero = -1;
        int indexMoreThenLength = 3;
        LinkedList myLinkedList = new LinkedList(1);
        myLinkedList.append(2);

        Assertions.assertNull(myLinkedList.get(indexBelowZero));
        Assertions.assertNull(myLinkedList.get(indexMoreThenLength));
    }

    @Test
    void set_MiddleRange() {
        int expected = 3;
        LinkedList myLinkedList = new LinkedList(1);
        myLinkedList.append(2);
        myLinkedList.append(22);
        myLinkedList.append(33);
        int result = myLinkedList.set(1, expected).value;
        Assertions.assertEquals(expected, result);
        myLinkedList.printList();
    }

    @Test
    void set_OutOfRange() {
        LinkedList myLinkedList = new LinkedList(1);
        myLinkedList.append(2);
        Assertions.assertNull(myLinkedList.set(-1, 3));
        Assertions.assertNull(myLinkedList.set(3, 3));
    }

    @Test
    void insert_MiddleRange() {
        LinkedList myLinkedList = new LinkedList(1);
        myLinkedList.append(2);
        myLinkedList.append(3);
        int indexExpected = 1;
        int result = myLinkedList.insert(indexExpected, 4).value;
        int expected = myLinkedList.get(indexExpected).value;
        Assertions.assertEquals(expected, result);

        myLinkedList.printList();
    }

}

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


}

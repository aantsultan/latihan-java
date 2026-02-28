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
        myLinkedList.append(2);
        myLinkedList.printList();
    }

    @Test
    void printList_RemoveLast_Only_1() {
        LinkedList myLinkedList = new LinkedList(1);
        myLinkedList.removeLast();
        myLinkedList.printList();
    }

    @Test
    void printList_RemoveLast_ContaintsElement() {
        LinkedList myLinkedList = new LinkedList(1);
        myLinkedList.append(2);
        myLinkedList.append(3);
        myLinkedList.append(4);
        System.out.println("Removed : " + myLinkedList.removeLast().value);
        myLinkedList.printList();
    }
}

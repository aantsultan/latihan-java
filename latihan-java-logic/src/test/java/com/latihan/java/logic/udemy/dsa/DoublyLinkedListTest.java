package com.latihan.java.logic.udemy.dsa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DoublyLinkedListTest {

    @Test
    void constructor() {
        int expected = 7;
        DoublyLinkedList myDLL = new DoublyLinkedList(expected);
        Assertions.assertEquals(expected, myDLL.getHead());
        Assertions.assertEquals(expected, myDLL.getTail());
        Assertions.assertEquals(1, myDLL.getLength());
        myDLL.printList();
    }

    @Test
    void append() {
        DoublyLinkedList myDLL = new DoublyLinkedList(7);
        DoublyLinkedList.Node appended = myDLL.append(8);
        Assertions.assertEquals(8, appended.value);
        Assertions.assertEquals(7, appended.prev.value);
        Assertions.assertNull(appended.next);
        myDLL.printList();
    }
}

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

}

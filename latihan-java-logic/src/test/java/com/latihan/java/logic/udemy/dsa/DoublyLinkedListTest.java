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

    @Test
    void removeLast() {
        DoublyLinkedList myDLL = new DoublyLinkedList(1);
        myDLL.append(2);
        myDLL.append(3);
        DoublyLinkedList.Node node = myDLL.removeLast();
        Assertions.assertEquals(3, node.value);
        Assertions.assertNull(node.prev);
        Assertions.assertNull(node.next);

        myDLL.printList();
    }

    @Test
    void removeLast_OneElement() {
        DoublyLinkedList myDLL = new DoublyLinkedList(1);
        DoublyLinkedList.Node node = myDLL.removeLast();
        Assertions.assertEquals(1, node.value);
        Assertions.assertNull(node.prev);
        Assertions.assertNull(node.next);
        Assertions.assertNull(myDLL.removeLast());

        myDLL.printList();
    }

    @Test
    void prepend() {
        DoublyLinkedList myDLL = new DoublyLinkedList(1);
        DoublyLinkedList.Node prepend = myDLL.prepend(0);
        Assertions.assertEquals(0, prepend.value);
        Assertions.assertNull(prepend.prev);
        Assertions.assertEquals(1, prepend.next.value);

        myDLL.printList();
    }

    @Test
    void removeFirst() {
        DoublyLinkedList myDLL = new DoublyLinkedList(1);
        myDLL.append(2);
        myDLL.append(3);
        DoublyLinkedList.Node node = myDLL.removeFirst();
        Assertions.assertEquals(1, node.value);
        Assertions.assertNull(node.prev);
        Assertions.assertNull(node.next);

        myDLL.printList();
    }

    @Test
    void removeFirst_OneElement() {
        DoublyLinkedList myDLL = new DoublyLinkedList(1);
        DoublyLinkedList.Node node = myDLL.removeFirst();
        Assertions.assertEquals(1, node.value);
        Assertions.assertNull(node.prev);
        Assertions.assertNull(node.next);
        DoublyLinkedList.Node node1 = myDLL.removeFirst();
        Assertions.assertNull(node1);

        myDLL.printList();
    }

    @Test
    void get() {
        DoublyLinkedList myDLL = new DoublyLinkedList(1);
        myDLL.append(2);
        myDLL.append(3);
        DoublyLinkedList.Node node = myDLL.get(1);
        Assertions.assertEquals(2, node.value);
    }

    @Test
    void get_OutOfRange() {
        DoublyLinkedList myDLL = new DoublyLinkedList(1);
        myDLL.append(2);
        myDLL.append(3);
        Assertions.assertNull(myDLL.get(-1));
        Assertions.assertNull(myDLL.get(3));
    }

    @Test
    void get_LargeDataSet() {
        DoublyLinkedList myDLL = new DoublyLinkedList(1);
        myDLL.append(2);
        myDLL.append(3);
        myDLL.append(4);
        myDLL.append(5);
        myDLL.append(6);
        myDLL.append(7);
        myDLL.append(8);
        myDLL.append(9);
        myDLL.append(10);
        DoublyLinkedList.Node node = myDLL.get(8);
        Assertions.assertEquals(9, node.value);
    }

    @Test
    void set() {
        DoublyLinkedList myDLL = new DoublyLinkedList(1);
        myDLL.append(2);
        myDLL.append(3);
        DoublyLinkedList.Node set = myDLL.set(1, 10);
        Assertions.assertEquals(10, set.value);
        myDLL.printList();
    }

    @Test
    void set_OutOfRange() {
        DoublyLinkedList myDLL = new DoublyLinkedList(1);
        myDLL.append(2);
        myDLL.append(3);
        DoublyLinkedList.Node set = myDLL.set(10, 10);
        Assertions.assertNull(set);
        myDLL.printList();
    }

    @Test
    void insert() {
        DoublyLinkedList myDLL = new DoublyLinkedList(1);
        myDLL.append(2);
        myDLL.append(3);
        DoublyLinkedList.Node insertZeroIndex = myDLL.insert(0, 0);
        Assertions.assertEquals(0, insertZeroIndex.value);
        DoublyLinkedList.Node insertLengthIndex = myDLL.insert(4, 10);
        Assertions.assertEquals(10, insertLengthIndex.value);
        DoublyLinkedList.Node insert = myDLL.insert(2, 7);
        Assertions.assertEquals(7, insert.value);

        myDLL.printList();
    }

    @Test
    void insert_OutOfRange() {
        DoublyLinkedList myDLL = new DoublyLinkedList(1);
        myDLL.append(2);
        myDLL.append(3);
        DoublyLinkedList.Node insert = myDLL.insert(-1, 0);
        Assertions.assertNull(insert);
        DoublyLinkedList.Node insert1 = myDLL.insert(100, 9);
        Assertions.assertNull(insert1);

        myDLL.printList();
    }

    @Test
    void remove() {
        DoublyLinkedList myDLL = new DoublyLinkedList(1);
        myDLL.append(2);
        myDLL.append(3);

        DoublyLinkedList.Node remove = myDLL.remove(1);
        Assertions.assertEquals(2, remove.value);
        Assertions.assertNull(remove.next);
        Assertions.assertNull(remove.prev);

        myDLL.printList();
    }

    @Test
    void remove_OutOfRange() {
        DoublyLinkedList myDLL = new DoublyLinkedList(1);
        myDLL.append(2);
        myDLL.append(3);
        Assertions.assertNull(myDLL.remove(-1));
        Assertions.assertNull(myDLL.remove(100));

        myDLL.printList();
    }

    @Test
    void isPalindrome() {
        DoublyLinkedList myDLL = new DoublyLinkedList(1);
        myDLL.append(2);
        myDLL.append(3);
        myDLL.append(2);
        myDLL.append(1);
        Assertions.assertTrue(myDLL.isPalindrome());
    }

    @Test
    void isNotPalindrome() {
        DoublyLinkedList myDLL = new DoublyLinkedList(1);
        myDLL.append(2);
        myDLL.append(3);
        myDLL.append(4);
        myDLL.append(1);
        Assertions.assertFalse(myDLL.isPalindrome());
    }

    @Test
    void reverse() {
        DoublyLinkedList myDLL = new DoublyLinkedList(1);
        myDLL.append(2);
        myDLL.append(3);
        myDLL.append(4);
        myDLL.append(5);
        myDLL.reverse();
        myDLL.printList();
    }
}

package com.latihan.java.logic.udemy.dsa;

public class DoublyLinkedList {

    public class Node {
        int value;
        Node next;
        Node prev;

        public Node(int value) {
            this.value = value;
        }
    }

    private Node head;
    private Node tail;
    private int length;

    public DoublyLinkedList(int value) {
        Node newNode = new Node(value);
        head = newNode;
        tail = newNode;
        length = 1;
    }

    /// List of Logic
    public Node append(int value) {
        Node newNode = new Node(value);
        if (length == 0) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }

        length++;
        return newNode;
    }

    public int getHead() {
        System.out.println("Head : " + head.value);
        return head.value;
    }

    public int getTail() {
        System.out.println("Tail : " + tail.value);
        return tail.value;
    }

    public int getLength() {
        System.out.println("Length : " + length);
        return length;
    }

    public void printList() {
        Node temp = head;
        while (temp != null) {
            System.out.println(temp.value);
            temp = temp.next;
        }
    }
}

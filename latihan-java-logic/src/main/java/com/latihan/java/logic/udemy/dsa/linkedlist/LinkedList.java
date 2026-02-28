package com.latihan.java.logic.udemy.dsa.linkedlist;

public class LinkedList {

    class Node {
        int value;
        Node next;

        Node(int value) {
            this.value = value;
        }
    }

    private Node head;
    private Node tail;
    private int length;

    public LinkedList(int value) {
        Node newNode = new Node(value);
        head = newNode;
        tail = newNode;
        length = 1;
    }

    public void append(int value) {
        Node newNode = new Node(value);
        if (this.length == 0) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        length++;
    }

    public void prepend(int value) {

    }

    public boolean insert(int index, int value) {
        return true;
    }

    public Node removeLast(){
        if(length <= 1){
            tail = null;
        } else {
            Node temp = head;
            Node pre = head;
            while (temp.next != null) {
                pre = temp;
                temp = temp.next;
            }
            tail = pre;
            tail.next = null;
        }
        return tail;
    }

    public void printList() {
        Node temp = head;
        while (temp != null) {
            System.out.println(temp.value);
            temp = temp.next;
        }
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
}


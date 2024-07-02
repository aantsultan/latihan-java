package com.latihan.java.datastructure.list.model.doubly;

import com.latihan.java.datastructure.list.model.Employee;

public class EmployeeLinkedList {

    private EmployeeNode head;
    private EmployeeNode tail;
    private int size;

    public void addToFront(Employee employee) {
        EmployeeNode node = new EmployeeNode(employee);
        node.setNext(this.head);

        if(this.head == null){
            this.tail = node;
        }
        else {
            this.head.setPrevious(node);
            node.setNext(this.head);
        }

        this.head = node;
        this.size++;
    }

    public void addToEnd(Employee employee){
        EmployeeNode node = new EmployeeNode(employee);
        if(this.tail == null){
            this.head = node;
        } else {
            this.tail.setNext(node);
            node.setPrevious(this.tail);
        }
        this.tail = node;
        this.size ++;
    }

    // function from tutorial udemy
    public EmployeeNode removeFromFront(){
        if(isEmpty()){
            return null;
        }
        EmployeeNode removedNode = this.head;

        if(this.head.getNext() == null){
            this.tail = null;
        } else {
            this.head.getNext().setPrevious(null);
        }

        this.head = this.head.getNext();
        size--;
        removedNode.setNext(null);
        return removedNode;
    }

    public EmployeeNode removeFromEnd(){
        if(isEmpty()){
            return null;
        }
        EmployeeNode removedNode = this.tail;
        if(this.tail.getPrevious() == null){
            this.head = null;
        } else {
            this.tail.getPrevious().setNext(null);
        }

        this.tail = this.tail.getPrevious();
        size--;
        removedNode.setPrevious(null);
        return removedNode;
    }

//    public void removeFromFront() {
//        if (!isEmpty()) {
//            this.head = this.head.getNext();
//            this.size--;
//        }
//    }

    public int getSize() {
        return this.size;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void print() {
        EmployeeNode current = this.head;

        System.out.print("HEAD ->");
        while (current != null) {
            System.out.print(current);
            System.out.print(" <=> ");
            current = current.getNext();
        }
        System.out.println("null");
    }
}

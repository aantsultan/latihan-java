package com.latihan.java.datastructure.list.model.singly;

import com.latihan.java.datastructure.list.model.Employee;

public class EmployeeLinkedList {

    private EmployeeNode head;
    private int size;

    public void addToFront(Employee employee) {
        EmployeeNode node = new EmployeeNode(employee);
        node.setNext(this.head);
        this.head = node;
        this.size++;
    }

    // function from tutorial udemy
//    public EmployeeNode removeFromFront(){
//        if(isEmpty()){
//            return null;
//        }
//        EmployeeNode removedNode = this.head;
//        this.head = this.head.getNext();
//        size--;
//        removedNode.setNext(null);
//        return removedNode;
//    }

    public void removeFromFront() {
        if (!isEmpty()) {
            this.head = this.head.getNext();
            this.size--;
        }
    }

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
            System.out.print(" -> ");
            current = current.getNext();
        }
        System.out.println("null");
    }
}

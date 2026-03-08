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

    /// Start List of Logic
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

    public Node removeLast() {
        if (length == 0) return null;
        Node temp = tail;
        if (length == 1) {
            head = null;
            tail = null;
        } else {
            tail = tail.prev;
            tail.next = null;
            temp.prev = null;
        }
        length--;
        return temp;
    }

    public Node prepend(int value) {
        Node newNode = new Node(value);
        if (length == 0) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        length++;
        return newNode;
    }

    public Node removeFirst() {
        if (length == 0) return null;
        Node temp = head;
        if (length == 1) {
            head = null;
            tail = null;
        } else {
            head = head.next;
            temp.next = null;
            head.prev = null;
        }
        length--;
        return temp;
    }

    public Node get(int index) {
        if (index < 0 || index >= length) return null;
        Node temp = head;
        if (index < length / 2) {
            for (int i = 0; i < index; i++) {
                temp = temp.next;
            }
        } else {
            temp = tail;
            for (int i = length - 1; i > index; i--) {
                temp = temp.prev;
            }
        }
        return temp;
    }

    public Node set(int index, int value) {
        Node temp = get(index);
        if (temp != null) {
            temp.value = value;
        }
        return temp;
    }

    public Node insert(int index, int value) {
        if (index < 0 || index > length) return null;
        if (index == 0) return prepend(value);
        if (index == length) return append(value);
        Node newNode = new Node(value);
        Node before = get(index - 1);
        Node after = before.next;

        newNode.prev = before;
        newNode.next = after;

        before.next = newNode;
        after.prev = newNode;

        length++;
        return newNode;
    }

    public Node remove(int index) {
        if (index < 0 || index >= length) return null;
        if (index == 0) return removeFirst();
        if (index == length - 1) return removeLast();
        Node temp = get(index);
        temp.next.prev = temp.prev;
        temp.prev.next = temp.next;
        temp.next = null;
        temp.prev = null;
        length--;
        return temp;
    }

    public boolean isPalindrome() {
        if (length <= 1) return true;

        Node forwardNode = head;
        Node backwardNode = tail;
        for (int i = 0; i < length / 2; i++) {
            if (forwardNode.value != backwardNode.value) return false;
            forwardNode = forwardNode.next;
            backwardNode = backwardNode.prev;
        }
        return true;
    }

    public void reverse() {
        if (length <= 1) return;
        Node temp;
        Node current = head;
        while (current != null) {
            temp = current.prev;
            current.prev = current.next;
            current.next = temp;
            current = current.prev;
        }
        temp = head;
        head = tail;
        tail = temp;
    }

    public void partitionList(int x) {
        if (head == null) return;

        Node d1 = new Node(0);
        Node d2 = new Node(0);
        Node prev1 = d1;
        Node prev2 = d2;

        Node temp = head;
        while (temp != null) {
            int tempValue = temp.value;
            if (tempValue < x) {
                prev1.next = temp;
                temp.prev = prev1;
                prev1 = temp;
            } else {
                prev2.next = temp;
                temp.prev = prev2;
                prev2 = temp;
            }
            temp = temp.next;
        }

        prev2.next = null;
        prev1.next = d2.next;
        if (d2.next != null) d2.next.prev = prev1;
        head = d1.next;
        if (head != null) head.prev = null;

    }

    public void reverseBetween(int startIndex, int endIndex) {
        if (head == null || startIndex == endIndex) return;

        Node d1 = new Node(0);
        d1.next = head;
        head.prev = d1;
        Node prev = d1;

        for (int i = 0; i < startIndex; i++) {
            prev = prev.next;
        }
        Node current = prev.next;
        for (int i = 0; i < endIndex - startIndex; i++) {
            Node toMove = current.next;
            current.next = toMove.next;
            if (toMove.next != null) toMove.next.prev = current;

            toMove.next = prev.next;
            prev.next.prev = toMove;

            prev.next = toMove;
            toMove.prev = prev;
        }

        head = d1.next;
        if (head != null) head.prev = null;
    }

    public void swapPairs() {
        if (head == null) return;

        Node d1 = new Node(0);
        d1.next = head;
        head.prev = d1;
        Node prev = d1;

        Node first = prev.next;
        Node second = first.next;
        if (second == null) {
            head.prev = null;
            return;
        }
        first.prev = prev;
        second.prev = first;

        while (first.next != null) {
            first.next = second.next;
            if (second.next != null) second.next.prev = first;
            second.next = prev.next;
            prev.next.prev = second;

            prev.next = second;
            second.prev = prev;

            prev = first;
            first = prev.next;
            if (first == null) break;
            second = first.next;
        }

        head = d1.next;
        head.prev = null;

    }

    /// End List of Logic

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

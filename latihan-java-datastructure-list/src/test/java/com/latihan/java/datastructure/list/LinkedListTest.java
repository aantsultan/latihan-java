package com.latihan.java.datastructure.list;

import com.latihan.java.datastructure.list.model.Employee;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.LinkedList;

class LinkedListTest {

    @Test
    void linkedList(){
        Employee aantSultan = new Employee("aant", "sultan", 1);
        Employee aantRahmanya = new Employee("aant", "rahmanya", 2);
        Employee sultanRahmanya = new Employee("sultan", "rahmanya", 3);
        Employee elainaEnd = new Employee("elaina", "end", 4);

        LinkedList<Employee> list = new LinkedList<>();
        list.addFirst(aantSultan);
        list.addFirst(aantRahmanya);
        list.addFirst(sultanRahmanya);

        Iterator<Employee> iterator = list.iterator();
        System.out.print("HEAD ->");
        while (iterator.hasNext()){
            System.out.print(iterator.next());
            System.out.print("<=>");
        }
        System.out.println("null");

//        list.add(elainaEnd); // adding element on last index
        list.addLast(elainaEnd);
        iterator = list.iterator();
        System.out.print("HEAD ->");
        while (iterator.hasNext()){
            System.out.print(iterator.next());
            System.out.print("<=>");
        }
        System.out.println("null");

        list.removeFirst();
        iterator = list.iterator();
        System.out.print("HEAD ->");
        while (iterator.hasNext()){
            System.out.print(iterator.next());
            System.out.print("<=>");
        }
        System.out.println("null");

        list.removeLast();
        iterator = list.iterator();
        System.out.print("HEAD ->");
        while (iterator.hasNext()){
            System.out.print(iterator.next());
            System.out.print("<=>");
        }
        System.out.println("null");
    }

}

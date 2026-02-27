package com.latihan.java.logic.model;

public class Person {

    String name;
    int age = 25;

    public Person(String name){
//        this(); // error
        setName(name);
    }

    public Person(String name, int age){
//        Person (name); // error
        setAge(age);
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

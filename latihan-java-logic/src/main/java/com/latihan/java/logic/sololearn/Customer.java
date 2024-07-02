package com.latihan.java.logic.sololearn;

public class Customer {

    public String firstName;
    public String lastName;
    public int age;
    public int roomNumber;

    public int age2;

    public int getAge2() {
        return age2;
    }

    public void setAge2(int age2) {
        this.age2 = age2;
    }

    public void saveCustomer(){
        System.out.println("firstName : "+firstName);
        System.out.println("lastName : "+lastName);
        System.out.println("age : "+age);
        System.out.println("roomNumber : "+roomNumber);
    }

}

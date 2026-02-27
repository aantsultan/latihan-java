package com.latihan.java.logic.leet.interface1;

public interface Animal {

//    default boolean equals(Object o){
//        return true;
//    } // ERROR

    default boolean equals1(Object o){
        return true;
    }

    int x = 10;
    void sound();


}

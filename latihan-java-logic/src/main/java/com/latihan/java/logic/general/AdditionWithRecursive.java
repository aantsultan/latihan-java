package com.latihan.java.logic.general;

public class AdditionWithRecursive {

    public static void main (String[] args){
        int input = 9;
        System.out.println(total(input));
    }

    static int total(int input){
        if (input == 1){
            return input;
        } else {
            return input + total(input -1);
        }
    }
}

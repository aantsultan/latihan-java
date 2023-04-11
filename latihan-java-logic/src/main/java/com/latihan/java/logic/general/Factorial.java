package com.latihan.java.logic.general;

public class Factorial {

    public static void main(String[] args) {
        int input = 5;
        System.out.println(doFaktorial(input));
    }

    static int doFaktorial(int input) {
        if (input == 0) {
            return 1;
        } else {
            return input * doFaktorial(input - 1);
        }
    }
}

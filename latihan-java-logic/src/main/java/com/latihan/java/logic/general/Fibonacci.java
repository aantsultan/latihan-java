package com.latihan.java.logic.general;

import java.util.ArrayList;
import java.util.List;

public class Fibonacci {

    public static void main(String[] args) {
        int input = 10;
        doFibonacci(input);
    }

    static void doFibonacci(int input) {
        List<Integer> outputList = new ArrayList<>();
        for (int i = 0; i < input; i++) {
            if (i == 0 || i == 1) {
                outputList.add(i);
            } else {
                int a = outputList.get(i - 2);
                int b = outputList.get(i - 1);
                outputList.add(a + b);
            }
        }

        for (Integer output : outputList){
            System.out.println(output);
        }
    }
}

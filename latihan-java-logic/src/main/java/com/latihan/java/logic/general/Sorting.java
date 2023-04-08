package com.latihan.java.logic.general;

public class Sorting {

    public static void main(String[] args) {
        int[] input = {7, 3, 4, 6};
        doSimpleSorting(input);
    }

    static void doSimpleSorting(int[] input) {
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input.length - 1; j++) {
                if (input[j] > input[j + 1]) {
                    int temp = input[j + 1];
                    input[j + 1] = input[j];
                    input[j] = temp;
                }
            }
        }

        for (int output : input) {
            System.out.println(output);
        }
    }
}

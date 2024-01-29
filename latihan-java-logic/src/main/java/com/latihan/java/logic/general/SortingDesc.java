package com.latihan.java.logic.general;

public class SortingDesc {

    public static void main(String[] args) {
        int[] input = {19, 0, 4, 15, 9};
        doSortingDesc(input);
    }

    static void doSortingDesc(int[] input) {
        for (int i = 0; i < input.length; i++) {
            for (int j = input.length - 1; j > 0; j--) {
                int temp = 0;
                if (input[j] > input[j - 1]) {
                    temp = input[j - 1];
                    input[j - 1] = input[j];
                    input[j] = temp;
                }
            }
        }

        for(int output : input){
            System.out.println(output);
        }
    }
}

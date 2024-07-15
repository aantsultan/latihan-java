package com.latihan.java.logic.general;

public class Sorting {

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

    static int[] doWhileSorting(int[] input1, int a, int[] input2, int b) {
        int[] merge = new int[a + b];
        int index = 0;

        for (int data : input1) {
            if (data != 0) {
                merge[index] = data;
                index++;
            }
        }

        for (int data : input2) {
            if (data != 0) {
                merge[index] = data;
                index++;
            }
        }

//        for (int i = 0; i < merge.length; i++) {
//            if (i + 1 != merge.length && (merge[i] >= merge[i + 1])) {
//                int temp = merge[i + 1];
//                merge[i + 1] = merge[i];
//                merge[i] = temp;
//            }
//        }

        return merge;
    }
}

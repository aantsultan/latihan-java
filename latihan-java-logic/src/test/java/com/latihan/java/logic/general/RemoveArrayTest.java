package com.latihan.java.logic.general;

public class RemoveArrayTest {

    public static void main(String[] args) {

        int[] x = {1, 5, 9, 0, 4};
        int index = 1;

        remove(x, index);

    }

    static void remove(int[] arr, int index) {
        int length = arr.length;
        for (int i = index; i < length - 1; i++) {
            arr[i] = arr[i + 1];
        }

        for (int i = 0; i < arr.length - 1; i++) {
            System.out.print(arr[i] + " ");
        }
    }


}

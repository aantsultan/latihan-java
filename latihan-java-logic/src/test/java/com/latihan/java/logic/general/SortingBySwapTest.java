package com.latihan.java.logic.general;

public class SortingBySwapTest {

    public static void main(String[] args) {
        int[] x = {1, 5, 3, 9, 0};
        sort(x);
    }

    static void sort(int[] arr) {
        for(int i = 0; i < arr.length ; i++){
            for (int j = i + 1; j < arr.length; j++) {
                if(arr[i] > arr[j]){
                    int temp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = temp;
                }
            }
        }

        for(int x : arr){
            System.out.print(x + " ");
        }
    }

}

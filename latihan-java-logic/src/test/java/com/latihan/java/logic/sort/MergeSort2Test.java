package com.latihan.java.logic.sort;

public class MergeSort2Test {

    public static void main(String[] args) {
        int[] x = {1, 4, -1, 6, 9};
        mergeSort(x);
        show(x);
    }

    static void show(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    static void mergeSort(int[] arr) {
        int length = arr.length;
        if (length < 2) return;

        int mid = length / 2;
        int[] left = new int[mid];
        int[] right = new int[length - mid];

        for (int i = 0; i < mid; i++) {
            left[i] = arr[i];
        }
        for (int i = 0; i < length - mid; i++) {
            right[i] = arr[mid + i];
        }

        mergeSort(left);
        mergeSort(right);
        merge(arr, left, right);
    }

    static void merge(int[] arr, int[] left, int[] right) {
        int i = 0;
        int j = 0;
        int k = 0;
        int leftLength = left.length;
        int rightLength = right.length;

        while (i < leftLength && j < rightLength) {
            if (left[i] <= right[j]) {
                arr[k] = left[i];
                i++;
            } else {
                arr[k] = right[j];
                j++;
            }
            k++;
        }

        while (i < leftLength) {
            arr[k] = left[i];
            k++;
            i++;
        }

        while (j < rightLength) {
            arr[k] = right[j];
            k++;
            j++;
        }

    }

}

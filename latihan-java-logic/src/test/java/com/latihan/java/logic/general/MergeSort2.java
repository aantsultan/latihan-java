package com.latihan.java.logic.general;

public class MergeSort2 {

    public static void main(String[] args) {
        int[] x = {1, 5, 9, 0, 3};
        mergeSort(x);
        show(x);
    }

    static void show(int[] arr) {
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
        int leftL = left.length;
        int rightL = right.length;

        while (i < leftL && j < rightL) {
            if (left[i] >= right[j]) {
                arr[k++] = left[i++];
            } else {
                arr[k++] = right[j++];
            }
        }

        while (i < leftL) {
            arr[k++] = left[i++];
        }

        while (j < rightL) {
            arr[k++] = right[j++];
        }
    }

}

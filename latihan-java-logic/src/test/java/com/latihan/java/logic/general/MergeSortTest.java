package com.latihan.java.logic.general;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

class MergeSortTest {

    @Test
    void task(){
        int[] arr = {1,2,3, 4};
        int[] arrDes = new int[4];
        //System.out.println(Arrays.toString(arr));
        System.arraycopy(arr, 0, arrDes, 1, 2);
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(arrDes));
    }

    @Test
    void loop(){
//        int[] arr = {20, 35, -15, 7, 55, 1, -22, 20, 35, -15, 7, 55, 1, -22};
        int[] arr = {20, 35, -15, 7, 55, 1, -22};
        //int[] arr = {5,5,5,1};
        mergeSort(arr, 0, arr.length);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    //{20, 35, -15, 7, 55, 1, -22};
    // 0    1   2   3   4   5   6
    public static void mergeSort(int[] input, int start, int end){
        if((end - start) < 2){
            return;
        }
        int mid = (start + end) / 2; //     7/2 = 3
        mergeSort(input, start, mid); //    bagian kiri, 0 - 3
        mergeSort(input, mid, end); //      bagian kanan, 3 - 7

        System.out.println("input : "+ Arrays.toString(input) + ", start : "+start+", mid : "+mid+", end : "+end);
        merge(input, start, mid, end);
    }

    public static void merge(int[] input, int start, int mid, int end){
        if(input[mid - 1] <= input[mid]){
            return;
        }
        int i = start;
        int j = mid;
        int tempIndex = 0;
        int[] temp = new int[end - start];

        while(i < mid && j < end){
            temp[tempIndex++] = input[i] <= input[j] ? input[i++] : input[j++];
        }

        System.arraycopy(input, i, input, start + tempIndex, mid - i); // handling left array
        System.arraycopy(temp, 0, input, start, tempIndex);
    }

    @Test
    void loop2(){
        int[] arr = {20, 35, -15, 7, 55, 1, -22};
        //int[] arr = {5,5,5,1};
        mergeSort2(arr, arr.length);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    public static void mergeSort2(int[] input, int length){
        if(length < 2){
            return; // unit terkecil array terdiri dari 2 element
        }
        int mid = length / 2;
        int [] left = new int[mid]; // left
        int [] right = new int[length - mid]; // right
        System.arraycopy(input, 0, left, 0, mid);
        for (int i = mid; i < length; i++){
            right[i-mid] = input[i];
        }
        mergeSort2(left, mid); // left arr , jumlah data dalam left = mid
        mergeSort2(right, length - mid); // right arr, jumlah data dalam right = length = mid
        merge2(input, left, right, mid, length - mid);
    }

    public static void merge2 (int[] input, int[] left, int[] right, int iLeft , int iRight){
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < iLeft && j < iRight){
            if(left[i] <= right[j]) {
                input[k] = left[i];
                k++;
                i++;
            } else {
                input[k] = right[j];
                k++;
                j++;
            }
        }
        while (i < iLeft){
            input[k] = left[i++];
        }
        while (j < iRight){
            input[k] = right[j++];
        }
    }
}

package com.latihan.java.logic.sort;

import org.junit.jupiter.api.Test;

import java.util.Random;

public class MergeSortTest {

    private static int step = 0;

    @Test
    void solution1(){
//        int[] arr = new int[5];
//        Random random = new Random();
//        for(int i = 0; i < arr.length; i++){
//            arr[i] = random.nextInt(1000);
//        }
        int[] arr = {5,2,3,4,1};
        show(arr);
        System.out.println("start\n");
        mergeSort(arr);
        System.out.println("\nafter");
        show(arr);

    }

    public static void show(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    private static void mergeSort(int[] arr){
        step ++;
        System.out.println("step : " + step);
        show(arr);
        System.out.println("========\n");
        int length = arr.length;
        if(length < 2) return; // flag selesai jika arr mencapai length = 1

        // pecah array menjadi 2 bagian
        int mid = length / 2;
        int[] left = new int[mid];
        int[] right = new int[length - mid];

        // masukkan data bagian kiri
        for(int i = 0; i < mid ; i ++){
            left[i] = arr[i];
        }

        // masukkan data bagian kanan
        for(int i = 0; i < length - mid; i++){
            right[i] = arr[mid + i];
        }

        // lakukan looping data left hingga menjadi angka terkecil
        System.out.println("\nstart left");
        show(left);
        mergeSort(left);
        //System.out.println("\nleft selesai \n");
        // lakukan looping data right hingga menjadi angka terkecil
        System.out.println("\nstart right");
        show(right);
        mergeSort(right);
        //System.out.println("\nright selesai \n");
        show(arr);
        System.out.println("merge mulai");
        merge(arr, left, right);
        show(arr);
        System.out.println("merge selesai\n");
    }

    private static void merge(int[] arr, int[] left , int[] right){
        int i = 0; // left
        int j = 0; // right
        int k = 0; // arr
        int leftL = left.length;
        int rightR = right.length;

        // tukar angka antara yang besar dan kecil
        while (i < leftL && j < rightR){
            if(left[i] <= right[j]){
                arr[k] = left[i];
                i++;
            } else {
                arr[k] = right[j];
                j++;
            }
            k++;
        }

        // jika terdapat angka yang belum masuk ke arr bagian kiri
        while (i < leftL){
            arr[k] = left[i];
            k++;
            i++;
        }

        // jika terdapat angka yang belum masuk ke arr bagian kanan
        while (j < rightR){
            arr[k] = right[j];
            k++;
            j++;
        }
    }

}

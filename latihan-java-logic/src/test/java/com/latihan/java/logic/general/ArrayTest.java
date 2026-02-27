package com.latihan.java.logic.general;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class ArrayTest {

    @Test
    void solution1(){
        int[] a = {1,2,3,4,5,6};
        int i = a.length - 1;
        while (i >= 0){
            if(i == 2) continue;
            System.out.println(a[i]);
            i--;
        }
    }

    @Test
    void solution2(){
        int[] a = {1,2,3};
        for(int j : a){
            if(j == 2) continue;
            for (int x = 0; x < 3; System.out.print(x)){
                x++;
            }
        }
    }

    @Test
    void solution3(){
        int array[][] = {{3,2,1},{5,4,2},{0,8,7}};

        outer:for(int x = 0, k=0; x<3; x++){
            k=0;
            inner:while(true){
                System.out.print(array[x][k++]);
                // insert here
            }
        }
    }

    @Test
    void solution4(){
//        int [] arr [] = new int[][]; // compile error
        int a[ ] [] = { {1,2,4} , {5,2,1},{0,43,2}};
        int b[ ] = a[2];
        System.out.print(b[1]);
    }

    @Test
    void solution5(){
        int[][] a = new int[3][];
        int[] b = a[0];
//        int length = b.length;
//        System.out.println(length);
        Assertions.assertEquals(null, b);

        // int[] => Object
        // int => primitive
//        Map<String, int[]> map = new HashMap<>();
    }

    @Test
    void solution6(){
        int a[ ][ ] = new int[3][];
        a[1] = new int[]{1,2,3};
        a[2] = new int[]{4,5};
        System.out.print(a[1][2]);
    }

    @Test
    void solution7(){
        int [ ][ ] ints = new int[2][];
        Arrays.sort(ints[1]);
        System.out.print(Arrays.toString(ints[1]));
    }

    @Test
    void solution8(){
        int[ ] ints = {3,6,1,4,0};
        Arrays.sort(ints,0,3);
        for(int i :  ints) {
            System.out.print(i);
        }
    }
}

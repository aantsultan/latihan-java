package com.latihan.java.logic.general;

import org.junit.jupiter.api.Test;

public class DoWhileTest {

    @Test
    void task(){
        int[] arr = {1,2,3,4};
        int i = 0;
        do{
            System.out.print(arr[i] + " ");
            i++;
        } while (i < arr.length + 1);
    }

}

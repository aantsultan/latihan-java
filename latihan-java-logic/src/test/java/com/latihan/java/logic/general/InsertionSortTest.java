package com.latihan.java.logic.general;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class InsertionSortTest {
    private final int[] input = {3, 4, 1, 2};

    @Test
    void create() {
        int[] expected = {1, 2, 3, 4};
        Assertions.assertArrayEquals(expected, insertSort(input));
    }

    @Test
    void maxTest() {
        Assertions.assertEquals(4, max(input));
    }

    static int[] insertSort(int[] input) {
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input.length - 1; j++) {
                if (input[j] > input[j + 1]) {
                    int temp = input[j + 1];
                    input[j + 1] = input[j];
                    input[j] = temp;
                }
            }
        }
        return input;
    }

    static int max(int[] input) {
        int result = -1;
        for (int data : input) {
            if (result < data) {
                result = data;
            }
        }
        return result;
    }

}

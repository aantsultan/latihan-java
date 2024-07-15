package com.latihan.java.logic.general;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static com.latihan.java.logic.general.Sorting.doSimpleSorting;
import static com.latihan.java.logic.general.Sorting.doWhileSorting;

class SortingTest {

    @Test
    void sorting() {
        int[] input = {7, 3, 4, 2, 1};
        doSimpleSorting(input);
    }

    @Test
    void mergeSorting() {
        int[] input1 = {3, 2, 1, 0, 0};
        int[] input2 = {5, 4};
        int a = 3;
        int b = 2;
        int[] expected = {1, 2, 3, 4, 5};

        int[] result = doWhileSorting(input1, a, input2, b);
        System.out.println(Arrays.toString(result));
        Assertions.assertThat(result).containsExactly(expected);
    }

}

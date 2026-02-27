package com.latihan.java.logic.javaquiz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.function.IntFunction;
import java.util.function.IntUnaryOperator;
import java.util.stream.IntStream;

class JavaQuizTest {

    @Test
    void modulus() {
        int x = 10 % 3;
        Assertions.assertEquals(1, x);
    }

    @Test
    void operator() {
        int x = 5;
        int result = x > 2 ? x < 4 ? 10 : 8 : 7;
        Assertions.assertEquals(8, result);
    }

    @Test
    void intStream() {
        IntStream stream = IntStream.of(1, 2, 3);
        IntFunction<IntUnaryOperator> inFu = x -> y -> x * y; //line n1
        IntStream newStream = stream.map(inFu.apply(10)); //line n2 newStream.forEach(System.output::print);
    }

    @Test
    void map() {
        Map<Integer, String> unsortMap = new HashMap<>();
        unsortMap.put(10, "z");
        unsortMap.put(5, "b");
        unsortMap.put(1, "d");
        unsortMap.put(7, "e");
        unsortMap.put(50, "j");
        Comparator<Integer> comparator = new
                Comparator<Integer>() {
                    @Override
                    public int compare(Integer o1, Integer o2) {
                        return o2.compareTo(o1);
                    }
                };
//        Map<Integer, String> treeMap = new TreeMap<>() (comparator);
//        treeMap.putAll(unsortMap);
//        for (Map.Entry<Integer, String> entry : treeMap.entrySet()) {
//            System.out.print(entry.getValue() + " ");
//        }
    }

    @Test
    void increament() {
        int x = 1;
        int y = 0;

        if (x++ > ++y) {
            System.out.println("Hello ");
        } else {
            System.out.println("World ");
        }

        System.out.println("Log " + x + ":" + y);
    }

    @Test
    void looping() {
        int[] angkas = {1, 2, 3};
        int i = 0;
        for (; i < angkas.length; i++) {
            System.out.println(angkas[i]);
        }
    }

    @Test
    void additionalLogic() {
        int i = 10;
        int j = 20;
        int k = j += i / 5;

        System.out.println(i + " : " + j + " : " + k);

        Assertions.assertEquals(10, i);
        Assertions.assertEquals(22, j);
        Assertions.assertEquals(22, k);
    }
}

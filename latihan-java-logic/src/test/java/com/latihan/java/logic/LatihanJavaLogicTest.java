package com.latihan.java.logic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class LatihanJavaLogicTest {

    private String a = "Java";
    private String b = "Java";
    private String c = new String("Java");

    @Test
    void equalString(){
        Assertions.assertSame(a, b);
    }

    @Test
    void equalNewString() {
        Assertions.assertSame(a, c);
    }

    @Test
    void arrayTest(){
        List<String> datas = Arrays.asList("Java", "is", "fun", "and", "challenging");
        int count = (int)datas.stream()
                .filter(data -> data.length() > 3)
                .map(String::length)
                .count();
        Assertions.assertEquals(2, count);
    }
}

package com.latihan.java.logic.general;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class StringBuilderTest {

    @Test
    void solution1(){
        StringBuilder s =new StringBuilder("Java");
        s.insert(0, "The latest").append("version is").append(" 1.7").delete(27, 28).append("8").substring(4);
        Assertions.assertEquals("The latestJavaversion is 1.8", s.toString());
    }

    @Test
    void solution2(){
        StringBuilder s =new StringBuilder("Java");
        String x = s.insert(0, "The latest").append("version is").append(" 1.7").delete(27, 28).append("8").substring(4);
        Assertions.assertEquals("latestJavaversion is 1.8", x);
    }

}

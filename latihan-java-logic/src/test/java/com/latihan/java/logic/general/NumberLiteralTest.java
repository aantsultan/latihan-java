package com.latihan.java.logic.general;

import org.junit.jupiter.api.Test;

class NumberLiteralTest {

    static int x = 0b1; // binary
    static int y = 0xAF; //
    static int z = 017;

    @Test
    void solution1(){
        System.out.println(x);
        System.out.println(y);
        System.out.println(z);
    }

    @Test
    void solution2(){
        System.out.println(0);
    }

}

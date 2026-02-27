package com.latihan.java.logic.general;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static java.lang.Math.abs;

class StaticTest {

    static int x = 10;
    static int z;

    @Test
    void solution1(){
        if(x > 2){
            x ++;
            int x = 4;
        }
        Assertions.assertEquals(11, x);
        final int x = 10;
    }

    @Test
    void solution2(){
        Assertions.assertEquals(13, x + z);
    }

    static {
        int x = 3;
        z = x;
    }

}

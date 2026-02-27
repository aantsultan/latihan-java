package com.latihan.java.logic.general;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class OperatorTest {

    @Test
    void solution1(){
        int x = 0, y =10;

        if (x++ > 1 && ++y >10)
            System.out.print(x + y);
        if (++y > 10 || ++x >10)
            System.out.println(x);
        System.out.println(y);
            System.out.print(x + y);
    }

    @Test
    void solution2(){
        int i = 3, j = 2;
        Assertions.assertEquals(7, i-- + --j + ++i);
    }

    @Test
    void solution3(){
        int a = 0;
        int b = 0;
        if(a++ > 10 ^ b == 0);
    }

}

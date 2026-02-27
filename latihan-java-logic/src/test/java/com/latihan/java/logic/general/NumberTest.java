package com.latihan.java.logic.general;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class NumberTest {

    @Test
    void solution1(){
        int array[] = {1,2,3};
//        double dbls[] = array; //ERROR
        double sum = 0;
        for (int i = 0; i < array.length; ++i)
//            sum += dbls[i]; //ERROR
        System.out.println(sum);
    }

    @Test
    void solution2(){
        int a = 1;
        long b = a;
        float c = b;
        double d = c;
        int x = (int) d;
//        byte zz = 1;
    }

    @Test
    void solution3(){
        Double d = 10.0;
        int i = 10;
        Integer wi = 10;
//        Assertions.assertFalse(wi == d); // compile Error
        Assertions.assertTrue(i == d);
        Assertions.assertFalse(d.equals(i));
        Assertions.assertFalse(d.equals(wi));
        Assertions.assertFalse(wi.equals(d));
    }

    @Test
    void solution4(){
        int a = Integer.parseInt("");
        System.out.println(a);
    }

    @Test
    void solution5(){
        int n = 808;
        System.out.format("%d", n);
        System.out.format("%06d", n);
    }

    static int i;
    int j;
    NumberTest(){
        j = i++;
    }

    @Test
    void solution6(){
        NumberTest a = new NumberTest();
        NumberTest a1 = new NumberTest();
        NumberTest a2 = new NumberTest();
        System.out.println("i ="+a.i);
        System.out.println("i ="+a.j);
    }

    @Test
    void solution7(){
        final int i;
        i = 2;
        System.out.println(i);
    }

    @Test
    void solution8(){
        Double d = 8.08;
        System.out.print(d instanceof Object);
    }

}

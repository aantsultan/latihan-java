package com.latihan.java.logic.other;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class OtherTask {

    @Test
    public void test() {
        System.out.println("hello other task");
    }

    @Test
    public void solution1(){
        int x = 0;
        int a = x++ + ++x;
        Assertions.assertEquals(2, a);
    }

    @Test
    public void solution2(){
        int a = 10;
        int b = 5;
        a += b += a;
        Assertions.assertEquals(15, b);
        Assertions.assertEquals(25, a);
    }

    @Test
    public void solution3(){
        int x = 5;
        int a = 0;
        if(x++ > 5) {
            a = 1;
        }
        Assertions.assertEquals(0, a);
    }

    @Test
    void solution4(){
        String a = "aant";
        String b = "aant";
        Assertions.assertTrue(a == b);
        Assertions.assertTrue(a.equals(b));
    }

    @Test
    void solution5(){
        String a = new String("aant");
        String b = new String("aant");
        Assertions.assertFalse(a == b);
        Assertions.assertTrue(a.equals(b));
    }

    @Test
    void solution6(){
        String a = "aant";
        String b = new String("aant");
        Assertions.assertFalse(a == b);
        Assertions.assertTrue(a.equals(b));
    }

    @Test
    void solution7(){
        String a = "aant";
        String b = new String("aant");
        Assertions.assertFalse("aant" == b);
        Assertions.assertTrue("aant" == a);
    }
}

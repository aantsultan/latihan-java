package com.latihan.java.logic.general;

import org.junit.jupiter.api.Test;

class SwitchTest {

    @Test
    void solution1(){
        // insert here
        final int x = -1;
        final int y = 2;

        switch(x+y) {
            case x+1    : {System.out.print("A");}
            case 1        : System.out.print("B");
            default       : System.out.print("default"); break;
            case y        : System.out.print("C");
        }
    }

    @Test
    void solution2(){
        final int array [] = {1,2,3};
        final int x = 1;
        switch(3) {
            case x - 1     : {System.out.print("A");}
            case 2     : System.out.print("B");
            default                : System.out.print("default"); break;
            case 3     : System.out.print("C");
        }
//        switch(2) { // error
//            case array[0]      : {System.out.print("A");}
//            case array[1]      : System.out.print("B");
//            default                : System.out.print("default"); break;
//            case array[2]      : System.out.print("C");
//        }
    }

}

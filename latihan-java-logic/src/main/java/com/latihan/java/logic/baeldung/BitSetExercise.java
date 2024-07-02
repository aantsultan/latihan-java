package com.latihan.java.logic.baeldung;

import java.util.BitSet;

public class BitSetExercise {

    public static void main(String[] args) {
        int n = 5;
        and(n);
        or(n);
        set(n);
    }

    public static void and(int n){
        BitSet b1 = new BitSet(n);
        BitSet b2 = new BitSet(n);
        b1.and(b2);
        System.out.println(b1.length());
        System.out.println(b2.length());
    }

    public static void or(int n){
        BitSet b1 = new BitSet(n);
        BitSet b2 = new BitSet(n);
        b2.or(b1);
        System.out.println(b1.length());
        System.out.println(b2.length());
    }

    public static void set(int n){
        BitSet b1 = new BitSet(n);
        BitSet b2 = new BitSet(n);
        b1.set(4, true);
        System.out.println(b1.cardinality());
        System.out.println(b2.cardinality());
    }

    public static void flip(int n){
        BitSet b1 = new BitSet(n);
        BitSet b2 = new BitSet(n);
        b1.flip(4);
        System.out.println(b1.cardinality());
        System.out.println(b2.cardinality());
    }

}

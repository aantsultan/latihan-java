package com.latihan.java.logic.leet.main1;

public class Abs {

//    static int i;
//    int j;
//    int x;
//    Abs() { j=i++; }

//    public static void main(String args[]) {
//        Abs s = new Abs();
//        Abs s1= new Abs();
//        Abs s2= new Abs();
//        System.out.print( "i = "+s.i);
//        System.out.println( ", j = "+s.j);
//        System.out.println(s.x);
//    }
//    public static void main(String [ ] args) {
//        int x = 0;
//        do {
//            System.out.println(x);
//        }while(x++ > 0);
//    }

    int x = 012;
    public static void main(String [] args){
        Abs pr = new Abs();
        pr.go(20);
    }
    void go(final int i){
        System.out.print(i/x);
    }

}

package com.latihan.java.logic.general;

public class Palindrom {

    public static void main(String[] args){
        String input = "Pali ilaP";
        System.out.println(isPalindrom(input));
    }

    static boolean isPalindrom(String input){
        int left = 0;
        int right = input.length()-1;
        while(left < right){
            if(input.charAt(left) != input.charAt(right)){
                return false;
            }
            left ++;
            right --;
        }
        return true;
    }
}

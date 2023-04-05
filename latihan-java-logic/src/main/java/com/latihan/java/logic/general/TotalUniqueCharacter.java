package com.latihan.java.logic.general;

import java.util.stream.Collectors;

public class TotalUniqueCharacter {

    public static void main(String[] args) {
        String input = "aaAAaazzzzZz";
        System.out.println(totalCharacter2(input));
    }

    static int totalCharacter(String input) {
        return input.toLowerCase()
                .chars()
                .mapToObj(c -> c)
                .collect(Collectors.toSet()).size();
    }

    static int totalCharacter2(String input){
        char[] inputArr = input.toLowerCase().toCharArray();
        int total = 0;
        String newInput = "";
        for(int i = 0; i < inputArr.length; i++){
            if(!newInput.contains(String.valueOf(inputArr[i]))){
                newInput += String.valueOf(inputArr[i]);
                total ++;
            }
        }
        return total;
    }
}

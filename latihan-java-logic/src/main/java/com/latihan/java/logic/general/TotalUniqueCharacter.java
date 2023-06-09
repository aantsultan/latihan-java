package com.latihan.java.logic.general;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TotalUniqueCharacter {

    public static void main(String[] args) {
        String input = "aaAAaazzzzZz";
        System.out.println(totalCharacter4(input));
    }

    static int totalCharacter(String input) {
        return input.toLowerCase()
                .chars()
                .mapToObj(c -> c)
                .collect(Collectors.toSet()).size();
    }

    static int totalCharacter2(String input) {
        char[] inputArr = input.toLowerCase().toCharArray();
        int total = 0;
        String newInput = "";
        for (int i = 0; i < inputArr.length; i++) {
            if (!newInput.contains(String.valueOf(inputArr[i]))) {
                newInput += String.valueOf(inputArr[i]);
                total++;
            }
        }
        return total;
    }

    static int totalCharacter3(String input) {
        char[] inputArr = input.toLowerCase().toCharArray();
        int total = 0;
        List<Character> list = new ArrayList<>();
        for (int i = 0; i < inputArr.length; i++) {
            if (!list.contains(inputArr[i])) {
                list.add(inputArr[i]);
                total++;
            }
        }
        return total;
    }

    static int totalCharacter4(String input) {
        if (input.length() == 0) {
            return 0;
        } else {
            String lower = input.toLowerCase();
            return 1 + totalCharacter4(lower.replace(String.valueOf(lower.charAt(0)), ""));
        }
    }
}

package com.latihan.java.logic.general;

import java.util.stream.Collectors;

public class TotalUniqueCharacter {

    public static void main(String[] args) {
        String input = "aaAAaazzzzZz";
        System.out.println(totalCharacter(input));
    }

    static int totalCharacter(String input) {
        return input.toLowerCase()
                .chars()
                .mapToObj(c -> c)
                .collect(Collectors.toSet()).size();
    }
}

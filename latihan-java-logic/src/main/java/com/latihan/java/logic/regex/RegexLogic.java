package com.latihan.java.logic.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegexLogic {

    public static void main(String[] args) {
        contoh2();
    }

    static void contoh1(){
        String regex = "\\b(\\w+)\\b(?:\\s+\\1\\b)+";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        String input = "Goodbye bye bye world world world";
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()){
            input = input.replaceAll(matcher.group(), matcher.group(1));
        }

        System.out.println(input);
    }

    static void contoh2(){
        String input = "A-Ant Sultan Rahmanya";
        Pattern pattern = Pattern.compile("[a-zA-Z]*a[a-zA-Z]");
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()){
            String result = matcher.group();
            System.out.println(result);
        }
    }

}

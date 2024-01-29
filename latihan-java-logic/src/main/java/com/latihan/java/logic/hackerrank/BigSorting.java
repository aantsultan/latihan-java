package com.latihan.java.logic.hackerrank;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class BigSorting {

    public static void main(String[] args) {
        List<String> input = new ArrayList<>();
        input.add("12121212121111111111111111");
        input.add("55555555");
        input.add("2223");
        input.add("334");
        for(String data : doTask(input)){
            log.info(data);
        }
    }

    static List<String> doTask(List<String> input){
        Comparator<String> comparator = (String input1, String input2) -> {
            if(input1.length() != input2.length()){
                return input1.length() - input2.length();
            }
            return input1.compareTo(input2);
        };
        return input.stream().sorted(comparator).collect(Collectors.toList());
    }
}

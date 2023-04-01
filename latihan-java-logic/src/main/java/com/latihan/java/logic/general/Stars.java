package com.latihan.java.logic.general;

public class Stars {

    public static void main(String[] args){
        int input = 4;
        createStars(input);
    }

    static void createStars(int input){
        for(int i = 0; i<input; i++){
            for(int j = 0; j<input; j++){
                if(i >= j){
                    System.out.print("*");
                }
            }
            System.out.println("");
        }
    }
}

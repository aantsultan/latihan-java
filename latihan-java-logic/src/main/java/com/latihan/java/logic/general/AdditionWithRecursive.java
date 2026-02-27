package com.latihan.java.logic.general;

public class AdditionWithRecursive {

//    public static void main (String[] args){
//        int input = 9;
//        System.out.println(total(input));
//    }
//
//    static int total(int input){
//        if (input == 1){
//            return input;
//        } else {
//            return input + total(input -1);
//        }
//    }

//    public static void main(String... method){
//        for (int i = 1; i < method. ; i++) {
//
//        }
//    }

//    public static void main(String[] main) {
//        for (int i = 1; i < main.length && i < 6; i = i + 2) {
//            System.out.println(main[i]);
//        }
//    }

//    public static void main(String... arguments){
//        int ctr = 0;
//        while (ctr < arguments.length){
//            if(ctr >= 4) break;
//            if(ctr % 2 != 0){
//                System.out.println(arguments[ctr]);
//            }
//            ++ctr;
//        }
//    }
//    public static void main(String[] arguments){
//        int ctr = 1;
//        while (ctr < arguments.length){
//            if (ctr >= 4) break;
//            if (ctr%2 == 0){
//                System.out.println(arguments[ctr]);
//            }
//            ++ctr;
//        }
//    }

    public static void main(String[] args) {
        char[][] grid = {{'7', ' ', ' ', ' '}
                ,{'5', '7', ' ', '5'}
                ,{'7', '7', '5', '5'}
                ,{'5', '7', '7', '5'}};
//        grid[0] = new char[] {'7','7',' ', ' '};
//        grid[0] = new char[] {'7','7',' ', ' '};
//        grid[0][1] = '7';
        grid[0] = new char[] {'7','7',' ', ' '};
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                System.out.print(grid[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}

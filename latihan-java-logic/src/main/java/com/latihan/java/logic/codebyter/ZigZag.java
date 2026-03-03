package com.latihan.java.logic.codebyter;

public class ZigZag {

    public static void main(String[] args) {
        String[] x = {"cat", "2"};
        char[] words = x[0].toCharArray();
        int rows = Integer.parseInt(x[1]);
        StringBuilder[] sbs = new StringBuilder[rows];

        int down = 0;
        boolean isGoDown = true;
        for (char word : words) {
            if (sbs[down] == null) {
                sbs[down] = new StringBuilder();
            }
            sbs[down].append(word);
            if (down == rows - 1) {
                isGoDown = false; // up
            }
            if (down == 0) {
                isGoDown = true;
            }
            down = isGoDown ? down + 1 : down - 1;
        }
        StringBuilder finalSb = new StringBuilder();
        for (StringBuilder sb : sbs) {
            finalSb.append(sb);
        }
        System.out.println(finalSb);
    }

}

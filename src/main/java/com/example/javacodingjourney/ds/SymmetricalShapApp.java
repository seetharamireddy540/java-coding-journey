package com.example.javacodingjourney.ds;

import java.util.Arrays;

/**
 * Write a function that, given coordinates for four points on an x-y graph, determines
 * if the points make a symmetrical shape around some vertical line.
 * 8|. . . . . . . . . .
 * 7|. . . . . . . . . .
 * 6|. . . . . . . . . .
 * 5|. X . . . . . X . .
 * 4|. . . . . . . . . .
 * 3|. . . . . . . . . .
 * 2|. . . . . . . . . .
 * 1|. . . X . X . . . .
 * 0|_ _ _ _ _ _ _ _ _ _
 * 0 1 2 3 4 5 6 7 8 9
 */
public class SymmetricalShapApp {

    public static void main(String[] args) {

        int[][] data = {
                {3, 1},
                {5, 1},
                {1, 5},
                {7, 5}
        };

        printArrayData(data);

        // O(nLog(n) for merge / quick /heap ... sorting algorithms
        Arrays.sort(data, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o1[1] - o2[1];
            } else {
                return o1[0] - o2[0];
            }
        });

        printArrayData(data);

        int n = data.length;
        for (int i = 0; i < n / 2; i++) {
            if (data[i][1] != data[n - 1 - i][1]) {
                System.out.println(" not symmetric");
            }
        }
        int average = 0;
        for (int i = 0; i < data.length; i++) {
            average += data[i][0];
        }
        System.out.println("symmetric line is " + average / data.length);
    }

    private static void printArrayData(int[][] data) {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                System.out.print(data[i][j] + ",");
            }
            System.out.println("");
        }
    }
}

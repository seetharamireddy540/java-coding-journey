package com.example.javacodingjourney.ds;

import java.util.ArrayList;

public class RiverSizes {

    public static void main(String[] args) {
        int[][] matrix = {
                {1,0,0,1,0},
                {1,0,1,0,0},
                {0,0,1,0,1},
                {1,0,1,0,1},
                {1,0,1,1,0}
        };
        System.out.println(RiverSizes.riverSizes(matrix)); // Output: [1, 2, 2, 2, 5]

    }
    public static ArrayList<Integer> riverSizes(int[][] matrix) {
        ArrayList<Integer> sizes = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 1) {
                    sizes.add(dfs(matrix, i, j));
                }
            }
        }
        return sizes;
    }

    private static int dfs(int[][] matrix, int i, int j) {
        if (i < 0 || i >= matrix.length ||
                j < 0 || j >= matrix[0].length ||
                matrix[i][j] != 1) {
            return 0;
        }

        matrix[i][j] = 0; // Mark as visited
        int size = 1;

        // Explore all four directions
        size += dfs(matrix, i + 1, j);
        size += dfs(matrix, i - 1, j);
        size += dfs(matrix, i, j + 1);
        size += dfs(matrix, i, j - 1);

        return size;
    }
}

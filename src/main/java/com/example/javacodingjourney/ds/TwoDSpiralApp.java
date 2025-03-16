package com.example.javacodingjourney.ds;

public class TwoDSpiralApp {

    public static void main(String[] args) {

        int m = 8;
        int n = 8;
        printSpiral(m, n);
    }

    private static void printSpiral(int m, int n) {

        if (m <= 0 || n <= 0) {
            return;
        }
        int[][] matrix = new int[m][n];
        int rowStart = 0;
        int colStart = 0;
        int rowEnd = m - 1;
        int colEnd = n - 1;
        int value = 1;
        while (rowStart <= rowEnd && colStart <= colEnd) {
            // Top row
            for (int i = colStart; i <= colEnd; i++) {
                matrix[rowStart][i] = value++;
            }
            rowStart++;
            for (int i = rowStart; i <= rowEnd; i++) {
                matrix[i][colEnd] = value++;
            }
            colEnd--;

            if (rowStart <= rowEnd) {
                for (int i = colEnd; i >= colStart; i--) {
                    matrix[rowEnd][i] = value++;
                }
                rowEnd--;
            }
            if (colStart <= colEnd) {
                for (int i = rowEnd; i >= rowStart; i--) {
                    matrix[i][colStart] = value++;
                }
                colStart++;
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}

package com.example.javacodingjourney.leet;

public class SudokuBoxExplanation {

    public static void explainBoxCalculation() {
        // Let's say we want to check position (5, 7)
        int row = 5;  // Row index 5
        int col = 7;  // Column index 7

        // Step 1: Divide by 3 to find which box (0, 1, or 2)
        int boxRowIndex = row / 3;  // 5 / 3 = 1 (integer division)
        int boxColIndex = col / 3;  // 7 / 3 = 2 (integer division)

        // Step 2: Multiply by 3 to get the top-left corner of the box
        int boxRow = boxRowIndex * 3;  // 1 * 3 = 3
        int boxCol = boxColIndex * 3;  // 2 * 3 = 6

        System.out.println("Position (" + row + ", " + col + ")");
        System.out.println("Belongs to box at row group " + boxRowIndex +
                ", col group " + boxColIndex);
        System.out.println("Box starts at position (" + boxRow + ", " + boxCol + ")");
    }

    public static void main(String[] args) {
        explainBoxCalculation();
    }
}
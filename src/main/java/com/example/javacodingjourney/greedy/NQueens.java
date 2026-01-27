package com.example.javacodingjourney.greedy;

import java.util.concurrent.atomic.AtomicInteger;

public class NQueens {
    private final int N;
    public static AtomicInteger bCounter = new AtomicInteger(0);

    public NQueens(int n) {
        this.N = n;
    }

    public void solve() {
        int[][] board = new int[N][N];

        if (backtrack(board, 0)) {
            printBoard(board);
        } else {
            System.out.println("No solution exists.");
        }
    }

    private boolean backtrack(int[][] board, int col) {
        // Goal: All queens are placed
        if (col >= N) return true;

        for (int row = 0; row < N; row++) {
            if (isSafe(board, row, col)) {
                // Make the Choice
                board[row][col] = 1;

                // Explore (Recur)
                if (backtrack(board, col + 1)) return true;
                bCounter.incrementAndGet();
                System.out.println("No soution . i am backinging");
                // Backtrack (Undo the choice)
                board[row][col] = 0;
            }
        }
        return false;
    }

    private boolean isSafe(int[][] board, int row, int col) {
        // Check horizontal row on left side
        for (int i = 0; i < col; i++)
            if (board[row][i] == 1) return false;

        // Check upper diagonal on left side
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--)
            if (board[i][j] == 1) return false;

        // Check lower diagonal on left side
        for (int i = row, j = col; j >= 0 && i < N; i++, j--)
            if (board[i][j] == 1) return false;

        return true;
    }

    private void printBoard(int[][] board) {
        for (int[] row : board) {
            for (int cell : row) {
                System.out.print(cell == 1 ? " Q " : " . ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        new NQueens(16).solve();
        System.out.println("bCounter ->" + bCounter.get());
    }
}
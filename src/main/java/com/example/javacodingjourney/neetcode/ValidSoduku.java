package com.example.javacodingjourney.neetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class ValidSoduku {

    public static void main(String[] args) {
        int[][] board = new int[9][9];


    }

    public boolean isValidSudoku(int[][] board) {

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    if (!isValid(board, i, j, board[i][j])) {
                        return false;
                    }
                }
            }
        }
        return false;
    }

    public static int[][] generateRandomSudokuBoard(){
        int[][] board = new int[9][9];
        // Populate random sudoku board
        for(int i = 0;i<9;i++){
            for(int j = 0;j<9;j++){
                board[i][j] = (int)(Math.random()*9)+1;
            }
        }
        return board;
    }

    public boolean isValid(int[][] board, int row, int col, int c) {
        for (int i = 0; i < 9; i++) {
            if (board[i][col] == c) return false; // check row
            if (board[row][i] == c) return false; // check column
            if (board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] == c) return false; // check 3*3 block
        }
        return true;
    }

    public boolean isValidSudoku1(char[][] board) {
        Map<Integer, HashSet<String>> rows = new HashMap<>();
        Map<Integer, HashSet<String>> columns = new HashMap<>();
        Map<Integer, HashSet<String>> boxes = new HashMap<>();
        for (int i = 0; i < 9; i++) {
            rows.put(i, new HashSet<>());
            columns.put(i, new HashSet<>());
            boxes.put(i, new HashSet<>());
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    continue;
                }
                int boxIndex = (i / 3) * 3 + j / 3;
                if (rows.get(i).contains(String.valueOf(board[i][j])) ||
                        columns.get(j).contains(String.valueOf(board[i][j])) ||
                        boxes.get(boxIndex).contains(String.valueOf(board[i][j]))) {
                    return false;
                }
                rows.get(i).add(String.valueOf(board[i][j]));
                columns.get(j).add(String.valueOf(board[i][j]));
                boxes.get(boxIndex).add(String.valueOf(board[i][j]));
            }
        }
        return true;
    }
}

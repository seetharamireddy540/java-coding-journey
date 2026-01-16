package com.example.javacodingjourney.arrays;

import java.util.Scanner;

public class TwoDimensionalArray {
    public static void main(String[] args){
        int[][] arr = new int[3][3];
        Scanner sc  = new Scanner(System.in);
        System.out.println("Enter array elements");
        for(int i =0; i<arr.length; i++){
            for(int j =0; j<arr[i].length; j++){
                arr[i][j] = sc.nextInt();
            }
        }

        System.out.println("Array elements are");
        for(int i =0; i<arr.length; i++){
            for (int j =0; j<arr[i].length;j++){
                System.out.print(arr[i][j]+ " ");
            }
            System.out.println();
        }

    }
}

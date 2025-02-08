package com.example.javacodingjourney.ds;

/**
 * Number of subarrays generated: n(n+1)/2
 */
public class SubArrayPrinter {

    public static void main(String[] args) {

        int array[] = {1, 2, 3};
        printSubarray(array);
    }

    private static void printSubarray(int[] array) {
        int n = array.length;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                System.out.print("[");
                for (int k = i; k <= j; k++) {
                    System.out.print(array[k]);
                    if (k < j) {
                        System.out.print(", ");
                    }
                }
                System.out.print("]");
                System.out.println("");
            }
        }
    }
}

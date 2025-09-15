package com.example.javacodingjourney.ds;

import java.util.Arrays;

/**
 * Given tw arrays that are sorted and distinct,
 * find the number of common elements
 */
public class DistinctArrays {

    public static void main(String[] args) {

        int[] array1 = {2, 4, 8, 9, 10, 39};
        int[] array2 = {1, 8, 55, 34};

        int n1 = array1.length;
        int n2 = array2.length;

        int[] commonElements = findCommonElements(array1, array2);
        System.out.println(Arrays.toString(commonElements));
    }

    public static int[] findCommonElements(int[] array1, int[] array2) {
        if (array1 == null || array2 == null) {
            return array1 == null ? array2 : array1;
        }
        int n1 = array1.length;
        int n2 = array2.length;
        int resultSize = Math.max(n1, n2);
        int[] result = new int[resultSize];
        int i = 0, j = 0;
        int k = 0;
        while (i < n1 && j < n2) {
            if (array1[i] == array2[j]) {
                result[k] = array1[i];
                i++;
                j++;
                k++;
            }
            if (array1[i] < array2[j]) {
                i++;
            } else {
                j++;
            }
        }
        return result;
    }

}

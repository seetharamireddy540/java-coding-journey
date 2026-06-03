package com.example.orcale;

public class Sorting {

    public static void main(String arg[]) {

    }

    public int[] bubbleSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return arr;
        }
        boolean flag = true;
        for(int i = 0; i< arr.length; i++){
            for(int j = i+1; j< arr.length; j++){
                if(arr[i] > arr[j]){
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                    flag = false;
                }
            }
            if(flag){
                break;
            }
        }
        return arr;
    }
    public int[] selectionSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return arr;
        }
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            int temp = arr[min];
            arr[min] = arr[i];
            arr[i] = temp;
        }
        return arr;
    }

    public int[] insertionSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return arr;
        }
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j -1;
            }
            arr[j + 1] = key;
        }
        return arr;
    }
}

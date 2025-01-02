package com.example.javacodingjourney.arrays;

public class ArrayExamples {
    public static void main(String[] args) {

        int[] numbers = {10, 20, 30, 40, 50, 100};


        // Modify an element
        numbers[2] = 35;

        ArrayExamples arrayExamples = new ArrayExamples();
        // Print all elements using a for loop
        System.out.println("Array elements: ");
        arrayExamples.printArray(numbers);

    }

    // Print all elements using an enhanced for loop
    private void printArray(int[] nums){
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }

}
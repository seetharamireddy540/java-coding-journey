package com.example.javacodingjourney.ananya;

import java.util.Random;
import java.util.Scanner;

public class UserInputApp {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter user choice (1- 3)- 1 - Rock, 2 Paper 3-> Scissors");
        int input = scanner.nextInt();
        System.out.println("You entered: " + input);
        scanner.close();
        if (input == 1) {
            System.out.println("You chose Rock");
        } else if (input == 2) {
            System.out.println("You chose Paper");
        } else if (input == 3) {
            System.out.println("You chose Scissors");
        } else {
            System.out.println("Invalid choice");
        }

        Random random = new Random();
        int computeChoice = random.nextInt(3);
    }
}

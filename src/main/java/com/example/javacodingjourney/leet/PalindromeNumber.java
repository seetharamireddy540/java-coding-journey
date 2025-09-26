package com.example.javacodingjourney.leet;

public class PalindromeNumber {

    public static void main(String[] args) {

        int x = 121;

        if (x < 0 || (x % 10 == 0 && x != 0)) {
            System.out.println("Not a Palindrome");
        }
        int reversedHalf = 0;
        while (x > reversedHalf) {
            reversedHalf = reversedHalf * 10 + (x % 10);
            x = x / 10;
        }
        if (x == reversedHalf || x == reversedHalf / 10) {
            System.out.println("Give number is Palindrome");
        }
    }
}

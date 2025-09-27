package com.example.javacodingjourney.leet;

public class PalindromeApp {

    String data = "racecar";

    public static void main(String[] args) {
        PalindromeApp app = new PalindromeApp();
        System.out.println(app.isPalindrome(app.data));
    }

    public boolean isPalindrome(String data) {
        int left = 0;
        int right = data.length() - 1;
        while (left < right) {
            while (left < right && !Character.isLetterOrDigit(data.charAt(left))) {
                left++;
            }
            while (left < right && !Character.isLetterOrDigit(data.charAt(right))) {
                right--;
            }

            if (Character.toLowerCase(data.charAt(left))
                    != Character.toLowerCase(data.charAt(right))) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}

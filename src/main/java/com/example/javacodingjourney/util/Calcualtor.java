package com.example.javacodingjourney.util;

import java.util.ArrayDeque;
import java.util.Deque;

public class Calcualtor {


    public static void main(String[] args) {
        Deque<String> stack = new ArrayDeque<>();
        String input = "2 3 + 5 *"; // Input is string and each token seprated by space
        String tokens[] = input.split(" ");
        for(String token : tokens){
            System.out.println(token);
            switch (token){
                case "+":
                    if (stack.isEmpty() || stack.size()  < 2){
                        throw new IllegalArgumentException();
                    }
                    double num1 = Double.parseDouble(stack.pop());
                    double num2 = Double.parseDouble(stack.pop());
            }
        }
    }
}

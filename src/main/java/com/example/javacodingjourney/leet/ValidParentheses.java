package com.example.javacodingjourney.leet;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * Valid Parentheses - Facebook Interview Question
 * <p>
 * Problem: Given a string containing just the characters '(', ')', '{', '}', '[' and ']',
 * determine if the input string is valid.
 * <p>
 * An input string is valid if:
 * 1. Open brackets must be closed by the same type of brackets.
 * 2. Open brackets must be closed in the correct order.
 * 3. Every close bracket has a corresponding open bracket of the same type.
 * <p>
 * Time Complexity: O(n) where n is the length of the string
 * Space Complexity: O(n) in worst case when all characters are opening brackets
 */
public class ValidParentheses {

    public static void main(String[] args) {

    }

    public static boolean isValid(String data) {
        if (data == null || data.isEmpty()) {
            return true;
        }
        if (data.length() % 2 != 0) {
            return false;
        }
        Deque<Character> stack = new ArrayDeque<>();
        Map<Character, Character> mapping = new HashMap<>();
        mapping.put(')', '(');
        mapping.put('}', '{');
        mapping.put(']', '[');
        for (char ch : data.toCharArray()) {
            if (mapping.containsKey(ch)) {
                // It's a closing bracket
                if (stack.isEmpty() || stack.poll() != mapping.get(ch)) {
                    return false;
                }
            } else {
                stack.push(ch);
            }
        }
        return stack.isEmpty();
    }

    /**
     * Solution 2: Using Stack with switch statement (slightly faster)
     */
    public static boolean isValidOptimized(String s) {
        if (s == null || s.length() == 0) return true;
        if (s.length() % 2 != 0) return false;

        Deque<Character> stack = new ArrayDeque<>();

        for (char c : s.toCharArray()) {
            switch(c) {
                case '(':
                case '{':
                case '[':
                    stack.push(c);
                    break;
                case ')':
                    if (stack.isEmpty() || stack.pop() != '(') return false;
                    break;
                case '}':
                    if (stack.isEmpty() || stack.pop() != '{') return false;
                    break;
                case ']':
                    if (stack.isEmpty() || stack.pop() != '[') return false;
                    break;
                default:
                    return false; // Invalid character
            }
        }

        return stack.isEmpty();
    }

}
;
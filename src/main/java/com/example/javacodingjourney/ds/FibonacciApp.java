package com.example.javacodingjourney.ds;

import java.util.HashMap;
import java.util.Map;

public class FibonacciApp {

    public static void main(String[] args) {
        Map<Integer, Integer> mem = new HashMap<>();
        int res = fib(25, mem);

        System.out.println(" Fib of 10 is " + res);
    }

    public static int fib(int n, Map<Integer, Integer> mem) {
        if (mem.containsKey(n)) {
            return mem.get(n);
        }
        if (n == 0 || n == 1) {
            mem.put(n, n);
            return n;
        }
        int res = fib(n - 1, mem) + fib(n - 2, mem);
        mem.put(n, res);
        return res;
    }
}

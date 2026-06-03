package com.example.orcale;

import java.math.BigDecimal;

public class Floats {

    public static void main(String[] args) {

        double a  = 0.1D;
        double b  = 0.2D;
        double c  = a + b;
        System.out.println("Result =" +a);
        System.out.println("Result =" +b);
        System.out.println("Result =" +c);
        float d = 0.1f + 0.2f;
        System.out.println("Result =" +d);

        System.out.println("What Java prints: " + d);

        // Passing a float into BigDecimal reveals its exact value in memory
        BigDecimal trueFloatValue = new BigDecimal(d);
        // Passing a float into BigDecimal reveals its exact value in memory
        BigDecimal trueFloatValue1 = new BigDecimal(c);
        System.out.println("What is ACTUALLY in memory: " + trueFloatValue);
        System.out.println("What is ACTUALLY in memory: " + trueFloatValue1);
    }
}

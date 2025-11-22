package com.example.javacodingjourney.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class JavaPrimitives {
    public static void main(String[] args) {

        byte byteValue = Byte.MAX_VALUE;
        short shortValue = Short.MAX_VALUE;
        int intValue = Integer.MAX_VALUE;
        System.out.println("byteValue: " + byteValue);
        System.out.println("shortValue: " + shortValue);
        System.out.println("intValue: " + intValue);
        BigInteger a = new BigInteger("12345678901234567890");
        BigInteger b = new BigInteger("98765432109876543210");

        BigInteger sum = a.add(b);
        BigInteger diff = b.subtract(a);
        BigInteger product = a.multiply(b);
        BigInteger quotient = b.divide(a);
        BigInteger remainder = b.remainder(a);
        BigInteger power = a.pow(3);

        System.out.println("\n=== BigInteger Operations ===");
        System.out.println("a + b = " + sum);
        System.out.println("b - a = " + diff);
        System.out.println("a * b = " + product);
        System.out.println("b / a = " + quotient);
        System.out.println("b % a = " + remainder);
        System.out.println("a^3 = " + power);
        System.out.println("\n=== Compound Interest ===");
        BigDecimal principal = new BigDecimal("100000.00");
        BigDecimal rate = new BigDecimal("0.05");  // 5%
        int years = 10;

        BigDecimal amount = principal.multiply(
                BigDecimal.ONE.add(rate).pow(years)
        ).setScale(2, RoundingMode.HALF_UP);

        System.out.println("Principal: $" + principal);
        System.out.println("Rate: " + rate.multiply(new BigDecimal("100")) + "%");
        System.out.println("After " + years + " years: $" + amount);

    }
}

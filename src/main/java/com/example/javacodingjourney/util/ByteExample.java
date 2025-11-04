package com.example.javacodingjourney.util;

public class ByteExample {
    public static void main(String[] args) {
        byte b = -50;  // In binary: 11001110

        // Without & 0xFF (sign extension happens)
        int directConversion = b;  // Results in -50 (negative remains negative)
        System.out.println("Direct conversion: " + directConversion);
        System.out.println("Binary: " + Integer.toBinaryString(directConversion));
        // Output: 11111111111111111111111111001110 (32 bits, sign extended)

        // With & 0xFF (treats as unsigned)
        int unsignedConversion = b & 0xFF;  // Results in 206
        System.out.println("\nWith & 0xFF: " + unsignedConversion);
        System.out.println("Binary: " + Integer.toBinaryString(unsignedConversion));
        // Output: 11001110 (8 bits, no sign extension)
    }
}
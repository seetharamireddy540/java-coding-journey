package com.example.javacodingjourney.util;

public class ControlsApp {
    public static final int READ    = 1 << 0;  // 0001 = 1
    public static final int WRITE   = 1 << 1;  // 0010 = 2
    public static final int EXECUTE = 1 << 2;  // 0100 = 4
    public static final int DELETE  = 1 << 3;  // 1000 = 8

    public static void main(String[] args) {
        bitwise();

    }

    public static void bitwise(){
        int a = 5;
        int b = -6;

        String test = Integer.toBinaryString(a);
        String aBinary32 = String.format("%32s", Integer.toBinaryString(a)).replace(' ', '0');

        System.out.println("a =  " + a + " binary->" + aBinary32);
        System.out.println("-a = " + -a + " binary->" + Integer.toBinaryString(-a));
        System.out.println("b = " + b + " binary->" + Integer.toBinaryString(b));
        System.out.println("a & b = " + (a & b));  // 0101 & 0011 = 0001 = 1
        System.out.println("a | b = " + (a | b));  // 0101 | 0011 = 0111 = 7
        System.out.println("a ^ b = " + (a ^ b));  // 0101 ^ 0011 = 0110 = 6
        System.out.println("~a = " + (~a));        // ~0101 = ...11111010 = -6
        System.out.println("a << 1 = " + (a << 1)); // 0101 << 1 = 1010 = 10
        System.out.println("a >> 1 = " + (a >> 1)); // 0101 >> 1 = 0010 = 2

    }
}

package com.example.javacodingjourney.leet;
import java.util.Arrays;
public class StringMagic {

    public static void main(String[] args) {

        String name = "Ram Mittala";
        for (char ch : name.toCharArray()) {
            int ascii = ch;
            System.out.println("ch :" + ch + "-> ascii :" + ascii);
        }
    }

}

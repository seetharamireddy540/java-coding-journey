package com.example.javacodingjourney.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneNumberValidation {

    public static void main(String[] args) {

        Pattern pattern = Pattern.compile("([0-9]{3})-([0-9]{3})-([0-9]{4})");
        String phone = "123-456-7890";
        Matcher m1 = pattern.matcher(phone);

        System.out.println("Phone: " + phone);
        System.out.println("Matches pattern 1: " + m1.matches());
    }
}

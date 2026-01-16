package com.example.javacodingjourney.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class PasswordSaltingExample {

    public static void main(String[] args) {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        String password = "XXXXXXXXXXXXXXXX";
        String saltedPassword = password + new String(salt);
        System.out.println("Salted password: " + saltedPassword);

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(saltedPassword.getBytes());
            // Convert to Base64 for storage
            String s = Base64.getEncoder().encodeToString(salt) + ":" +
                    Base64.getEncoder().encodeToString(hash);
            System.out.println("Hassed passwro->" + s);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Salt: " + new String(salt));
    }
}

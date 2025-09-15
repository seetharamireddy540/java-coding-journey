package com.example.javacodingjourney.ds;

public class Base62IdGenerator {
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int ID_LENGTH = 7;
    private static final int BASE = 62;

    public static String generateId() {
        StringBuilder id = new StringBuilder();
        long timestamp = System.currentTimeMillis();

        // Mix timestamp with random number to ensure uniqueness
        long mixed = timestamp + (long) (Math.random() * 100000);

        // Convert to base62
        while (id.length() < ID_LENGTH) {
            int remainder = (int) (mixed % BASE);
            id.insert(0, ALPHABET.charAt(remainder));
            mixed = mixed / BASE;
        }

        // Pad with random characters if necessary
        while (id.length() < ID_LENGTH) {
            id.append(ALPHABET.charAt((int) (Math.random() * BASE)));
        }

        return id.toString();
    }

    public static void main(String[] args) {
        // Example usage
        for (int i = 0; i < 5; i++) {
            System.out.println(generateId());
        }
    }
}

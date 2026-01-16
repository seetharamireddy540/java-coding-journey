package com.example.javacodingjourney.alogs.greedy;

import java.util.Arrays;

class CoinChangeProblem {
    public static int minCoins(int[] coins, int amount) {
        // Sort coins in descending order
        Arrays.sort(coins);

        int coinCount = 0;

        // Start from largest coin
        for (int i = coins.length - 1; i >= 0; i--) {
            while (amount >= coins[i]) {
                amount -= coins[i];
                coinCount++;
            }
        }

        return amount == 0 ? coinCount : -1;
    }
}
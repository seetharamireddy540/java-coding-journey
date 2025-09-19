package com.example.javacodingjourney.leet;

public class BestTimeToTravel {

    public static int[] findBestTravelDays(int[] outboundCosts, int[] returnCosts) {
        int n = outboundCosts.length;

        // For each return day j, we want to find the minimum outbound cost for days before j
        // Use prefix min to track the minimum outbound cost seen so far
        int[] minOutboundSoFar = new int[n];
        int[] minOutboundDaySoFar = new int[n];

        minOutboundSoFar[0] = outboundCosts[0];
        minOutboundDaySoFar[0] = 0;

        for (int i = 1; i < n; i++) {
            if (outboundCosts[i] < minOutboundSoFar[i-1]) {
                minOutboundSoFar[i] = outboundCosts[i];
                minOutboundDaySoFar[i] = i;
            } else {
                minOutboundSoFar[i] = minOutboundSoFar[i-1];
                minOutboundDaySoFar[i] = minOutboundDaySoFar[i-1];
            }
        }

        // Find the best combination
        int minTotalCost = Integer.MAX_VALUE;
        int bestOutboundDay = -1;
        int bestReturnDay = -1;

        for (int j = 1; j < n; j++) {  // j is the return day
            int outboundDay = minOutboundDaySoFar[j-1];
            int totalCost = minOutboundSoFar[j-1] + returnCosts[j];

            if (totalCost < minTotalCost) {
                minTotalCost = totalCost;
                bestOutboundDay = outboundDay;
                bestReturnDay = j;
            }
        }

        return new int[] {bestOutboundDay, bestReturnDay, minTotalCost};
    }

    public static void main(String[] args) {
        int[] outboundCosts = {10, 5, 7, 15, 3, 12};
        int[] returnCosts =    {8, 9, 6, 11, 5, 10};

        int[] result = findBestTravelDays(outboundCosts, returnCosts);
        System.out.println("Best outbound day: " + result[0]);
        System.out.println("Best return day: " + result[1]);
        System.out.println("Total minimum cost: " + result[2]);

        buildPrefix(outboundCosts, returnCosts);
    }

    public static int[] buildPrefix(int[] outboundCosts, int[] returnCosts) {
        int n = outboundCosts.length;
        int[] minOutboundCostsSoFar = new int[n];
        int[] minOutboundDaySoFar = new int[n];
        minOutboundCostsSoFar[0] = outboundCosts[0];
        minOutboundDaySoFar[0] = 0;

        for (int i = 1; i < n; i++) {
            if (outboundCosts[i] < minOutboundCostsSoFar[i - 1]) {
                minOutboundCostsSoFar[i] = outboundCosts[i];
                minOutboundDaySoFar[i] = i;
            } else {
                minOutboundCostsSoFar[i] = minOutboundDaySoFar[i - 1];
                minOutboundDaySoFar[i] = minOutboundDaySoFar[i - 1];
            }
        }

        int minCost = Integer.MAX_VALUE;
        int bestOutboundDay = -1;
        int besetReturnDay = -1;
        for (int j = 1; j < n; j++) {
            int outboundDay = minOutboundDaySoFar[j - 1];
            int totalCost = minOutboundCostsSoFar[j - 1] + returnCosts[j];

            if (totalCost < minCost) {
                minCost = totalCost;
                bestOutboundDay = outboundDay;
                besetReturnDay = j;
            }
        }
        return new int[]{bestOutboundDay, bestOutboundDay, besetReturnDay};
    }

}
package com.interview.notes.code.months.nov23.GoldmanSachs;

import java.util.Arrays;

/**
 * Cutting Metal Surplus
 * The owner of a construction company has a surplus of rods of arbitrary lengths. A local contractor offers to buy any
 * of the surplus, as long as all the rods have the same exact integer length, referred to as saleLength. The number of
 * sellable rods can be increased by cutting each rod zero or more times, but each cut has a cost denoted by
 * costPerCut. After all cuts have been made, ary leftover rods having a length other than saleLength must be
 * discarded for no profit. The owner’s total profit for the sale is calculated as:
 * tota/Profit = totalUniformRods * saleLength * salePrice - totalCuts * costPerCut
 * where totalUniformRods is the number of sellable rods, salePrice is the per unit length price that the contractor
 * agrees to pay, and totalCuts is the total number of times the rods needed to be cut.
 * Example
 * lengths = [30, 59, 110]
 * costPerCut = 1
 * salePrice = 10 per unit lergth
 * The following are tests based on lengths that are factors of 30, the length of the shortest bar. Factors of other
 * lengths might also be tested, but this demonstrates the methodology.
 */
public class MetalCuttingProfitCalculator {

    public static int maxProfit(int costPerCut, int salePrice, int[] lengths) {
        int maxProfit = 0;

        // Find the maximum length of the rod to check for the optimal saleLength
        int maxLength = Arrays.stream(lengths).max().getAsInt();

        // Try each possible saleLength from 1 to maxLength
        for (int saleLength = 1; saleLength <= maxLength; saleLength++) {
            int totalCuts = 0;
            int totalUniformRods = 0;

            // Check each rod to see if it can be cut into pieces of size saleLength
            for (int length : lengths) {
                int cuts = (length / saleLength) - 1;
                if (length % saleLength > 0) cuts++;

                int uniformRods = length / saleLength;

                // Update the totals if the current rod can be used to produce uniform rods
                if (uniformRods * saleLength * salePrice > cuts * costPerCut) {
                    totalCuts += cuts;
                    totalUniformRods += uniformRods;
                }
            }

            // Calculate profit for the current saleLength
            int profit = totalUniformRods * saleLength * salePrice - totalCuts * costPerCut;

            // Update maxProfit if the current profit is greater
            maxProfit = Math.max(maxProfit, profit);
        }

        return maxProfit;
    }

    public static void main(String[] args) {
        // Example usage
        int[] lengths = {26, 103, 59};
        int costPerCut = 1;
        int salePrice = 10;
        System.out.println("Maximum Profit: " + maxProfit(costPerCut, salePrice, lengths));
    }
}

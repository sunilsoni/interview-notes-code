package com.interview.notes.code.months.oct24.amz.test8;

import java.util.*;

/*
Imagine you're building a system for a small retail store that handles customer payments and returns change. Your task is to implement a flexible Cash Drawer Controller that can calculate the exact change based on available denominations. The store supports the following denominations:
Bills: $20, $10, $5, $11
Coins: $0.25, $0.10, $0.05, $0.01
The controller should support the following two use cases:
- Returns the exact change by using all available denominations.
- Returns the exact change by using only coins.
Keep in mind that these are just two uses cases and additional use cases can be added later.

 */
public class CashDrawerController1 {

    // Available denominations (in cents)
    private static final int[] BILLS = {2000, 1000, 500, 100}; // Bills: $20, $10, $5, $1
    private static final int[] COINS = {25, 10, 5, 1}; // Coins: $0.25, $0.10, $0.05, $0.01

    // Main method to run test cases
    public static void main(String[] args) {
        // Run all test cases to verify functionality
        runTestCases();
    }

    // Method to get exact change using all denominations (bills and coins)
    public static Map<String, Integer> getExactChange(int amountInCents) {
        System.out.println("Calculating exact change for amount: " + amountInCents + " cents using all denominations");
        Map<String, Integer> result = new LinkedHashMap<>(); // Store the count of each denomination used

        // Calculate the number of bills needed
        for (int bill : BILLS) {
            int count = amountInCents / bill; // Determine how many of this bill are needed
            System.out.println("Bill: " + bill + " cents, Count: " + count);
            result.put("$" + (bill / 100), count); // Add the bill to the result map
            amountInCents %= bill; // Update the remaining amount
            System.out.println("Remaining amount after bills: " + amountInCents + " cents");
        }

        // Calculate the number of coins needed
        for (int coin : COINS) {
            int count = amountInCents / coin; // Determine how many of this coin are needed
            System.out.println("Coin: " + coin + " cents, Count: " + count);
            result.put("$0." + (coin < 10 ? "0" + coin : coin), count); // Add the coin to the result map
            amountInCents %= coin; // Update the remaining amount
            System.out.println("Remaining amount after coins: " + amountInCents + " cents");
        }

        return result; // Return the map containing the breakdown of change
    }

    // Method to get exact change using only coins
    public static Map<String, Integer> getExactChangeUsingCoins(int amountInCents) {
        System.out.println("Calculating exact change for amount: " + amountInCents + " cents using only coins");
        Map<String, Integer> result = new LinkedHashMap<>(); // Store the count of each coin used

        // Calculate the number of coins needed
        for (int coin : COINS) {
            int count = amountInCents / coin; // Determine how many of this coin are needed
            System.out.println("Coin: " + coin + " cents, Count: " + count);
            result.put("$0." + (coin < 10 ? "0" + coin : coin), count); // Add the coin to the result map
            amountInCents %= coin; // Update the remaining amount
            System.out.println("Remaining amount after coins: " + amountInCents + " cents");
        }

        return result; // Return the map containing the breakdown of change
    }

    // Method to run predefined test cases
    public static void runTestCases() {
        // Array of test amounts (in cents) to verify the change calculation
        int[] testAmounts = {4676, 145, 999, 25, 876, 12345};

        // Loop through each test amount and calculate change
        for (int amount : testAmounts) {
            System.out.println("\nTesting amount: " + amount + " cents");
            Map<String, Integer> change = getExactChange(amount); // Get change using all denominations
            System.out.println("Exact Change (All Denominations): " + change);

            Map<String, Integer> coinChange = getExactChangeUsingCoins(amount); // Get change using only coins
            System.out.println("Exact Change (Only Coins): " + coinChange);
            System.out.println("======================================");
        }
    }
}

// Solution Overview
// - This implementation has two main methods to calculate change using bills and coins, or coins only.
// - It runs the test cases in the main method and prints out the results.
// - The solution is simple and efficient for the use cases presented.
//
// Time Complexity:
// - Both methods run in O(n), where n is the number of denominations. In this case, n is small (fixed), so this is efficient.
//
// Space Complexity:
// - Space complexity is O(1), since we only use a fixed amount of additional space regardless of the input size.

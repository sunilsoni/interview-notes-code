package com.interview.notes.code.months.oct24.amazon.test6;

import java.util.LinkedHashMap;
import java.util.Map;

public class CashDrawerController {

    // Available denominations
    private static final int[] BILLS = {2000, 1000, 500, 100}; // in cents
    private static final int[] COINS = {25, 10, 5, 1}; // in cents

    // Main method
    public static void main(String[] args) {
        // Test cases
        runTestCases();
    }

    // Method to get exact change using all denominations
    public static Map<String, Integer> getExactChange(int amountInCents) {
        Map<String, Integer> result = new LinkedHashMap<>();
        for (int bill : BILLS) {
            int count = amountInCents / bill;
            result.put("$" + (bill / 100), count);
            amountInCents %= bill;
        }
        for (int coin : COINS) {
            int count = amountInCents / coin;
            result.put("$0." + (coin < 10 ? "0" + coin : coin), count);
            amountInCents %= coin;
        }
        return result;
    }

    // Method to get exact change using only coins
    public static Map<String, Integer> getExactChangeUsingCoins(int amountInCents) {
        Map<String, Integer> result = new LinkedHashMap<>();
        for (int coin : COINS) {
            int count = amountInCents / coin;
            result.put("$0." + (coin < 10 ? "0" + coin : coin), count);
            amountInCents %= coin;
        }
        return result;
    }

    // Test cases
    public static void runTestCases() {
        int[] testAmounts = {4676, 145, 999, 25, 876, 12345};

        for (int amount : testAmounts) {
            System.out.println("Testing amount: " + amount + " cents");
            Map<String, Integer> change = getExactChange(amount);
            System.out.println("Exact Change (All Denominations): " + change);

            Map<String, Integer> coinChange = getExactChangeUsingCoins(amount);
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

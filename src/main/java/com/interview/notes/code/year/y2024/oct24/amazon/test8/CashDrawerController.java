package com.interview.notes.code.year.y2024.oct24.amazon.test8;

import java.util.LinkedHashMap;
import java.util.Map;

public class CashDrawerController {

    private static final int[] BILLS = {20, 10, 5, 1};
    private static final int[] COINS = {25, 10, 5, 1}; // Represented in cents

    public static void main(String[] args) {
        // Example test cases
        testCalculateChangeAllDenominations();
        testCalculateChangeCoinsOnly();
    }

    private static Map<String, Integer> calculateChangeAllDenominations(int change) {
        Map<String, Integer> changeMap = new LinkedHashMap<>();
        int remainingChange = change;

        // Calculate change with bills
        for (int bill : BILLS) {
            int count = remainingChange / bill;
            if (count > 0) {
                changeMap.put("$" + bill, count);
                remainingChange -= bill * count;
            }
        }

        // Calculate change with coins
        for (int coin : COINS) {
            int count = remainingChange / coin;
            if (count > 0) {
                changeMap.put("$" + (coin / 100.0), count);
                remainingChange -= coin * count;
            }
        }

        return changeMap;
    }

    private static Map<String, Integer> calculateChangeCoinsOnly(int change) {
        Map<String, Integer> changeMap = new LinkedHashMap<>();
        int remainingChange = change;

        // Calculate change with coins only
        for (int coin : COINS) {
            int count = remainingChange / coin;
            if (count > 0) {
                changeMap.put("$" + (coin / 100.0), count);
                remainingChange -= coin * count;
            }
        }

        return changeMap;
    }

    // Test methods
    private static void testCalculateChangeAllDenominations() {
        int changeDue = 786; // $7.86
        Map<String, Integer> expectedChange = new LinkedHashMap<>();
        expectedChange.put("$5", 1);
        expectedChange.put("$1", 2);
        expectedChange.put("$0.25", 3);
        expectedChange.put("$0.1", 1);
        expectedChange.put("$0.01", 1);

        Map<String, Integer> actualChange = calculateChangeAllDenominations(changeDue);
        assert actualChange.equals(expectedChange) : "Test case failed for all denominations";
        System.out.println("Test case passed for all denominations");
    }

    private static void testCalculateChangeCoinsOnly() {
        int changeDue = 99; // $0.99
        Map<String, Integer> expectedChange = new LinkedHashMap<>();
        expectedChange.put("$0.25", 3);
        expectedChange.put("$0.1", 2);
        expectedChange.put("$0.01", 4);

        Map<String, Integer> actualChange = calculateChangeCoinsOnly(changeDue);
        assert actualChange.equals(expectedChange) : "Test case failed for coins only";
        System.out.println("Test case passed for coins only");
    }
}

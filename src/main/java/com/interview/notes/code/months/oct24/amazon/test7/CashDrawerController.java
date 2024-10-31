package com.interview.notes.code.months.oct24.amazon.test7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CashDrawerController {
    private static final double[] DENOMINATIONS = {20.0, 11.0, 10.0, 5.0, 0.25, 0.10, 0.05, 0.01};
    private static final double[] COIN_DENOMINATIONS = {0.25, 0.10, 0.05, 0.01};

    public static void main(String[] args) {
        testCases();
    }

    public static List<Double> calculateChange(double amount, boolean coinsOnly) {
        List<Double> change = new ArrayList<>();
        double[] availableDenominations = coinsOnly ? COIN_DENOMINATIONS : DENOMINATIONS;

        for (double denomination : availableDenominations) {
            while (amount >= denomination) {
                change.add(denomination);
                amount -= denomination;
                amount = Math.round(amount * 100.0) / 100.0; // Round to avoid floating-point errors
            }
        }

        return change;
    }

    public static void testCases() {
        // Test case 1: Exact change with all denominations
        double amount1 = 47.63;
        List<Double> expectedChange1 = Arrays.asList(20.0, 20.0, 5.0, 2.0, 0.25, 0.25, 0.10, 0.03);
        runTest("Test 1", amount1, false, expectedChange1);

        // Test case 2: Exact change with coins only
        double amount2 = 0.99;
        List<Double> expectedChange2 = Arrays.asList(0.25, 0.25, 0.25, 0.10, 0.10, 0.04);
        runTest("Test 2", amount2, true, expectedChange2);

        // Test case 3: Large amount
        double amount3 = 1234.56;
        List<Double> expectedChange3 = calculateChange(amount3, false);
        runTest("Test 3", amount3, false, expectedChange3);

        // Test case 4: Zero amount
        double amount4 = 0.0;
        List<Double> expectedChange4 = new ArrayList<>();
        runTest("Test 4", amount4, false, expectedChange4);

        // Test case 5: Amount smaller than smallest denomination
        double amount5 = 0.001;
        List<Double> expectedChange5 = new ArrayList<>();
        runTest("Test 5", amount5, false, expectedChange5);
    }

    private static void runTest(String testName, double amount, boolean coinsOnly, List<Double> expectedChange) {
        List<Double> actualChange = calculateChange(amount, coinsOnly);
        boolean passed = actualChange.equals(expectedChange);
        System.out.println(testName + ": " + (passed ? "PASS" : "FAIL"));
        if (!passed) {
            System.out.println("  Expected: " + expectedChange);
            System.out.println("  Actual:   " + actualChange);
        }
    }
}

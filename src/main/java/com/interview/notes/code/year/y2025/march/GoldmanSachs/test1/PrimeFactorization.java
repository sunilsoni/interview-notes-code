package com.interview.notes.code.year.y2025.march.GoldmanSachs.test1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PrimeFactorization {

    public static List<Integer> primeFactorization(int x) {
        List<Integer> factors = new ArrayList<>();
        // Start dividing by smallest prime (2)
        for (int i = 2; i <= Math.sqrt(x); i++) {
            // While x is divisible by current prime i, add it to factors
            while (x % i == 0) {
                factors.add(i);
                x /= i;
            }
        }
        // If x is a prime greater than 2, add it directly
        if (x > 1) factors.add(x);
        return factors;
    }

    // Test cases method
    public static boolean doTestsPass() {
        // Expected factors
        List<List<Integer>> expected = Arrays.asList(
                Arrays.asList(2, 3),   // Factors of 6
                List.of(5),      // Factors of 5
                Arrays.asList(2, 2, 3) // Factors of 12
        );

        // Actual results
        List<List<Integer>> results = Arrays.asList(
                primeFactorization(6),
                primeFactorization(5),
                primeFactorization(12)
        );

        // Compare each expected with actual results
        for (int i = 0; i < expected.size(); i++) {
            if (!expected.get(i).equals(results.get(i))) {
                System.out.println("Test failed for index: " + i);
                return false;
            }
        }
        System.out.println("All tests passed");
        return true;
    }

    // Main entry point
    public static void main(String[] args) {
        doTestsPass();
    }
}

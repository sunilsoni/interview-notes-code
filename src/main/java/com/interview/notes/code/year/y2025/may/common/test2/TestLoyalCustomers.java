package com.interview.notes.code.year.y2025.may.common.test2;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class TestLoyalCustomers {
    public static void main(String[] args) {
        // Test Case 1: Basic loyal customer
        List<PageView> day1 = Arrays.asList(
            new PageView(1000L, 1, 1),
            new PageView(1001L, 2, 1)
        );
        List<PageView> day2 = Arrays.asList(
            new PageView(2000L, 3, 1)
        );
        testCase("Basic loyal customer", day1, day2, Arrays.asList(1));

        // Test Case 2: Not enough unique pages
        day1 = Arrays.asList(new PageView(1000L, 1, 2));
        day2 = Arrays.asList(new PageView(2000L, 1, 2));
        testCase("Not enough unique pages", day1, day2, Arrays.asList());

        // Test Case 3: Multiple loyal customers
        day1 = Arrays.asList(
            new PageView(1000L, 1, 1),
            new PageView(1001L, 2, 1),
            new PageView(1002L, 1, 2),
            new PageView(1003L, 2, 2)
        );
        day2 = Arrays.asList(
            new PageView(2000L, 3, 1),
            new PageView(2001L, 3, 2)
        );
        testCase("Multiple loyal customers", day1, day2, Arrays.asList(1, 2));
    }

    private static void testCase(String testName, List<PageView> day1, List<PageView> day2, List<Integer> expected) {
        Collection<Integer> result = LoyalCustomerAnalyzer.getLoyalCustomers(day1.iterator(), day2.iterator());
        Collection<Integer> resultBrute = LoyalCustomerAnalyzer.getLoyalCustomersBruteForce(day1.iterator(), day2.iterator());
        
        System.out.println("Test: " + testName);
        System.out.println("Optimized Solution: " + (new HashSet<>(expected).equals(new HashSet<>(result)) ? "PASS" : "FAIL"));
        System.out.println("Brute Force Solution: " + (new HashSet<>(expected).equals(new HashSet<>(resultBrute)) ? "PASS" : "FAIL"));
        System.out.println();
    }
}

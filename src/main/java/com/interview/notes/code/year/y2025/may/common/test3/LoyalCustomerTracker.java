package com.interview.notes.code.year.y2025.may.common.test3;

import java.util.*;
import java.util.stream.Collectors;

// Class to represent a page view entry
class PageView {
    long timestamp;
    int pageId;
    int customerId;
    
    public PageView(long timestamp, int pageId, int customerId) {
        this.timestamp = timestamp;
        this.pageId = pageId;
        this.customerId = customerId;
    }
}

public class LoyalCustomerTracker {
    /**
     * Finds loyal customers who:
     * 1. Visited on both days
     * 2. Viewed at least 2 unique pages
     * Time Complexity: O(n) where n is total number of page views
     */
    public static Collection<Integer> getLoyalCustomers(Iterator<PageView> file1, Iterator<PageView> file2) {
        // Track customer visits and their unique pages
        Map<Integer, Set<Integer>> customerPages = new HashMap<>();
        Set<Integer> day1Customers = new HashSet<>();
        Set<Integer> day2Customers = new HashSet<>();
        
        // Process day 1 views - O(n)
        processFileViews(file1, customerPages, day1Customers);
        
        // Process day 2 views - O(m)
        processFileViews(file2, customerPages, day2Customers);
        
        // Find customers meeting both criteria - O(k) where k is unique customers
        return customerPages.entrySet().stream()
            .filter(entry -> 
                day1Customers.contains(entry.getKey()) && // visited day 1
                day2Customers.contains(entry.getKey()) && // visited day 2
                entry.getValue().size() >= 2)             // viewed 2+ unique pages
            .map(Map.Entry::getKey)
            .collect(Collectors.toSet());
    }
    
    /**
     * Helper method to process views from a single day
     * Updates customer pages and day-specific customer sets
     */
    private static void processFileViews(
            Iterator<PageView> fileViews, 
            Map<Integer, Set<Integer>> customerPages, 
            Set<Integer> dayCustomers) {
        
        while (fileViews.hasNext()) {
            PageView view = fileViews.next();
            // Add customer to day's visitor set
            dayCustomers.add(view.customerId);
            // Add page to customer's unique pages set
            customerPages.computeIfAbsent(view.customerId, k -> new HashSet<>())
                        .add(view.pageId);
        }
    }

    /**
     * Main method with test cases
     */
    public static void main(String[] args) {
        // Test Case 1: Basic loyal customer
        testLoyalCustomers(
            "Basic loyal customer test",
            Arrays.asList(
                new PageView(1000L, 1, 1),
                new PageView(1001L, 2, 1)
            ),
            Arrays.asList(
                new PageView(2000L, 3, 1)
            ),
            Set.of(1)
        );

        // Test Case 2: Customer with insufficient unique pages
        testLoyalCustomers(
            "Insufficient unique pages test",
            Arrays.asList(
                new PageView(1000L, 1, 2)
            ),
            Arrays.asList(
                new PageView(2000L, 1, 2)
            ),
            Set.of()
        );

        // Test Case 3: Multiple loyal customers
        testLoyalCustomers(
            "Multiple loyal customers test",
            Arrays.asList(
                new PageView(1000L, 1, 1),
                new PageView(1001L, 2, 2),
                new PageView(1002L, 3, 3)
            ),
            Arrays.asList(
                new PageView(2000L, 4, 1),
                new PageView(2001L, 5, 2),
                new PageView(2002L, 6, 3)
            ),
            Set.of(1, 2, 3)
        );

        // Test Case 4: Empty input
        testLoyalCustomers(
            "Empty input test",
            Arrays.asList(),
            Arrays.asList(),
            Set.of()
        );

        // Test Case 5: Large dataset test
        testLargeDataset();
    }

    /**
     * Helper method to run and verify test cases
     */
    private static void testLoyalCustomers(
            String testName, 
            List<PageView> day1, 
            List<PageView> day2, 
            Set<Integer> expected) {
        
        System.out.println("Running: " + testName);
        Collection<Integer> result = getLoyalCustomers(day1.iterator(), day2.iterator());
        boolean passed = new HashSet<>(result).equals(expected);
        System.out.println("Result: " + (passed ? "PASS" : "FAIL"));
        if (!passed) {
            System.out.println("Expected: " + expected);
            System.out.println("Got: " + result);
        }
        System.out.println();
    }

    /**
     * Test with large dataset to verify performance
     */
    private static void testLargeDataset() {
        List<PageView> day1 = new ArrayList<>();
        List<PageView> day2 = new ArrayList<>();
        
        // Generate large test data
        for (int i = 0; i < 100000; i++) {
            day1.add(new PageView(i, i % 10, i % 1000));
            day2.add(new PageView(i + 100000, (i + 5) % 10, i % 1000));
        }

        long startTime = System.currentTimeMillis();
        Collection<Integer> result = getLoyalCustomers(day1.iterator(), day2.iterator());
        long endTime = System.currentTimeMillis();

        System.out.println("Large Dataset Test:");
        System.out.println("Processing Time: " + (endTime - startTime) + "ms");
        System.out.println("Loyal Customers Found: " + result.size());
        System.out.println();
    }
}

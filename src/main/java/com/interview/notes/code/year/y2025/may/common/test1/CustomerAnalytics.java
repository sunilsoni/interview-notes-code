package com.interview.notes.code.year.y2025.may.common.test1;

import java.util.*;
import java.util.stream.Collectors;

public class CustomerAnalytics {
    // Brute Force Solution - O(n1 * n2) time complexity
    public static Collection<Integer> getLoyalCustomersBruteForce(Iterator<PageView> file1, Iterator<PageView> file2) {
        // Store customer visits from day 1
        Map<Integer, Set<Integer>> day1Visits = new HashMap<>();

        // Process file 1
        while (file1.hasNext()) {
            PageView pv = file1.next();
            day1Visits.computeIfAbsent(pv.customerId, k -> new HashSet<>())
                    .add(pv.pageId);
        }

        // Store customer visits from day 2
        Map<Integer, Set<Integer>> day2Visits = new HashMap<>();

        // Process file 2
        while (file2.hasNext()) {
            PageView pv = file2.next();
            day2Visits.computeIfAbsent(pv.customerId, k -> new HashSet<>())
                    .add(pv.pageId);
        }

        // Find loyal customers
        Set<Integer> loyalCustomers = new HashSet<>();
        for (int customerId : day1Visits.keySet()) {
            if (day2Visits.containsKey(customerId)) {
                Set<Integer> allPages = new HashSet<>();
                allPages.addAll(day1Visits.get(customerId));
                allPages.addAll(day2Visits.get(customerId));
                if (allPages.size() >= 2) {
                    loyalCustomers.add(customerId);
                }
            }
        }

        return loyalCustomers;
    }

    // Optimized Solution - O(n) time complexity
    public static Collection<Integer> getLoyalCustomers(Iterator<PageView> file1, Iterator<PageView> file2) {
        Map<Integer, Set<Integer>> customerPages = new HashMap<>();
        Set<Integer> visitedBothDays = new HashSet<>();

        // Process day 1
        processDay(file1, customerPages, null, 1);

        // Process day 2 and track customers who visited both days
        processDay(file2, customerPages, visitedBothDays, 2);

        // Filter loyal customers (visited both days and at least 2 unique pages)
        return visitedBothDays.stream()
                .filter(customerId -> customerPages.get(customerId).size() >= 2)
                .collect(Collectors.toList());
    }

    private static void processDay(Iterator<PageView> file,
                                   Map<Integer, Set<Integer>> customerPages,
                                   Set<Integer> visitedBothDays,
                                   int day) {
        while (file.hasNext()) {
            PageView pv = file.next();
            Set<Integer> pages = customerPages.computeIfAbsent(pv.customerId, k -> new HashSet<>());
            pages.add(pv.pageId);

            if (day == 2 && pages.size() > 0) {
                visitedBothDays.add(pv.customerId);
            }
        }
    }

    // Test method
    public static void main(String[] args) {
        // Test Case 1: Basic scenario
        List<PageView> day1 = Arrays.asList(
                new PageView(1, 1, 1),
                new PageView(2, 2, 1),
                new PageView(3, 1, 2)
        );

        List<PageView> day2 = Arrays.asList(
                new PageView(1, 3, 1),
                new PageView(2, 1, 2),
                new PageView(3, 2, 3)
        );

        Collection<Integer> result = getLoyalCustomers(day1.iterator(), day2.iterator());
        System.out.println("Test Case 1: " +
                (result.containsAll(Arrays.asList(1, 2)) && result.size() == 2 ? "PASS" : "FAIL"));

        // Test Case 2: Empty files
        result = getLoyalCustomers(new ArrayList<PageView>().iterator(),
                new ArrayList<PageView>().iterator());
        System.out.println("Test Case 2: " + (result.isEmpty() ? "PASS" : "FAIL"));

        // Test Case 3: Large data test
        List<PageView> largeDay1 = new ArrayList<>();
        List<PageView> largeDay2 = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            largeDay1.add(new PageView(i, i % 10, i % 1000));
            largeDay2.add(new PageView(i, (i + 5) % 10, i % 1000));
        }

        long startTime = System.currentTimeMillis();
        result = getLoyalCustomers(largeDay1.iterator(), largeDay2.iterator());
        long endTime = System.currentTimeMillis();
        System.out.println("Test Case 3 (Performance): " +
                ((endTime - startTime) < 1000 ? "PASS" : "FAIL") +
                " (Time: " + (endTime - startTime) + "ms)");
    }
}

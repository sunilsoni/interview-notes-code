package com.interview.notes.code.year.y2025.may.common.test2;

import java.util.*;
import java.util.stream.Collectors;

public class LoyalCustomerAnalyzer {
    // Brute Force Solution - O(n^2) time complexity
    public static Collection<Integer> getLoyalCustomersBruteForce(Iterator<PageView> file1, Iterator<PageView> file2) {
        // Store all page views from day 1
        List<PageView> day1Views = new ArrayList<>();
        while (file1.hasNext()) {
            day1Views.add(file1.next());
        }
        
        // Store all page views from day 2
        List<PageView> day2Views = new ArrayList<>();
        while (file2.hasNext()) {
            day2Views.add(file2.next());
        }
        
        Set<Integer> loyalCustomers = new HashSet<>();
        
        // Check each customer from day 1
        for (PageView view1 : day1Views) {
            Set<Integer> customerPages = new HashSet<>();
            customerPages.add(view1.pageId);
            
            // Check if customer visited on day 2
            boolean visitedDay2 = false;
            for (PageView view2 : day2Views) {
                if (view1.customerId == view2.customerId) {
                    visitedDay2 = true;
                    customerPages.add(view2.pageId);
                }
            }
            
            // If customer visited both days and at least 2 unique pages
            if (visitedDay2 && customerPages.size() >= 2) {
                loyalCustomers.add(view1.customerId);
            }
        }
        
        return loyalCustomers;
    }

    // Optimized Solution - O(n) time complexity
    public static Collection<Integer> getLoyalCustomers(Iterator<PageView> file1, Iterator<PageView> file2) {
        // Track customer visits and pages
        Map<Integer, Set<Integer>> customerPages = new HashMap<>();
        Set<Integer> day1Customers = new HashSet<>();
        Set<Integer> day2Customers = new HashSet<>();
        
        // Process day 1
        while (file1.hasNext()) {
            PageView view = file1.next();
            day1Customers.add(view.customerId);
            customerPages.computeIfAbsent(view.customerId, k -> new HashSet<>()).add(view.pageId);
        }
        
        // Process day 2
        while (file2.hasNext()) {
            PageView view = file2.next();
            day2Customers.add(view.customerId);
            customerPages.computeIfAbsent(view.customerId, k -> new HashSet<>()).add(view.pageId);
        }
        
        // Find loyal customers
        return customerPages.entrySet().stream()
            .filter(entry -> day1Customers.contains(entry.getKey()) && 
                           day2Customers.contains(entry.getKey()) && 
                           entry.getValue().size() >= 2)
            .map(Map.Entry::getKey)
            .collect(Collectors.toSet());
    }
}

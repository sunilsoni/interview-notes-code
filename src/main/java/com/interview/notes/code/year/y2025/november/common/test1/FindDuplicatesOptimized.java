package com.interview.notes.code.year.y2025.november.common.test1;

import java.util.*;
import java.util.stream.Collectors;

public class FindDuplicatesOptimized {

    // ============ APPROACH 1: STREAM API WITH SET (RECOMMENDED) ============

    public static Set<String> findDuplicatesStreamAPI(List<String> list) {

        // Step 1: Create a HashSet to track elements we've already seen
        // We use a separate collection to avoid modifying original list
        // This set stores elements encountered during iteration
        Set<String> seen = new HashSet<>();

        // Step 2: Stream through list, filter duplicates, and collect to set
        // stream() converts list to stream
        // filter() keeps only elements that are already in 'seen' set
        // If seen.add() returns false, element already exists (duplicate)
        // collect() gathers all duplicates into a new HashSet
        // Time: O(n), Space: O(unique duplicates)
        return list.stream()
                .filter(element -> !seen.add(element))  // add() returns false if already exists
                .collect(Collectors.toSet());
    }

    // ============ APPROACH 2: SINGLE PASS (MOST EFFICIENT FOR LARGE DATA) ============

    public static Set<String> findDuplicatesSinglePass(List<String> list) {

        // Step 1: Initialize two sets for tracking
        // 'seen' tracks all elements encountered
        // 'duplicates' stores only elements that appear more than once
        Set<String> seen = new HashSet<>();
        Set<String> duplicates = new HashSet<>();

        // Step 2: Single loop through list - O(n) time
        // For each element, check if already in 'seen'
        for (String element : list) {
            // If element not in seen, add it
            if (!seen.add(element)) {
                // If add() returns false, element was already there
                // So it's a duplicate - add to duplicates set
                duplicates.add(element);
            }
        }

        // Step 3: Return all duplicates found
        // Time: O(n), Space: O(k) where k = unique duplicates
        return duplicates;
    }

    // ============ APPROACH 3: WITH FREQUENCY COUNT (GET DUPLICATE COUNT) ============

    public static Map<String, Integer> findDuplicatesWithCount(List<String> list) {

        // Step 1: Count frequency of each element using groupingBy
        // Collectors.groupingBy() creates map of element -> count
        // Collectors.summingInt() sums occurrences (each element counts as 1)
        Map<String, Integer> frequencyMap = list.stream()
                .collect(Collectors.groupingBy(
                        element -> element,  // Key: element itself
                        Collectors.summingInt(e -> 1)  // Value: count
                ));

        // Step 2: Filter to keep only duplicates (count > 1)
        // entrySet() gets all key-value pairs
        // filter() keeps only entries where count > 1
        // collect(toMap()) converts back to map
        // Time: O(n), Space: O(unique)
        return frequencyMap.entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue
                ));
    }

    // Main method with comprehensive tests
    public static void main(String[] args) {

        // ============ CREATE LIST WITH DUPLICATES ============

        // Step 1: Initialize ArrayList to store strings
        // List<String> is interface, ArrayList is implementation
        // ArrayList allows duplicates and maintains insertion order
        List<String> stringList = new ArrayList<>();

        // Step 2: Add elements (5-6 as mentioned in requirement)
        // Adding: "java", "kafka", "aws", "java", "kafka", "javascript"
        // Note: "java" appears 2 times, "kafka" appears 2 times
        stringList.add("java");        // Index 0
        stringList.add("kafka");       // Index 1
        stringList.add("aws");         // Index 2
        stringList.add("java");        // Index 3 - DUPLICATE
        stringList.add("kafka");       // Index 4 - DUPLICATE
        stringList.add("javascript");  // Index 5

        int passCount = 0;
        int failCount = 0;

        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║           FIND DUPLICATES - TEST & PERFORMANCE            ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");

        System.out.println("Original List: " + stringList);
        System.out.println("Expected Duplicates: [java, kafka]\n");

        // ============ TEST 1: STREAM API APPROACH ============

        Set<String> result1 = findDuplicatesStreamAPI(stringList);
        boolean pass1 = result1.contains("java") && result1.contains("kafka") && result1.size() == 2;
        System.out.println("Test 1 - Stream API: " + (pass1 ? "✓ PASS" : "✗ FAIL"));
        System.out.println("  Result: " + result1);
        System.out.println("  Time Complexity: O(n)");
        System.out.println("  Space Complexity: O(k) - k = unique duplicates\n");
        passCount += pass1 ? 1 : 0;
        failCount += pass1 ? 0 : 1;

        // ============ TEST 2: SINGLE PASS APPROACH ============

        Set<String> result2 = findDuplicatesSinglePass(stringList);
        boolean pass2 = result2.contains("java") && result2.contains("kafka") && result2.size() == 2;
        System.out.println("Test 2 - Single Pass: " + (pass2 ? "✓ PASS" : "✗ FAIL"));
        System.out.println("  Result: " + result2);
        System.out.println("  Time Complexity: O(n)");
        System.out.println("  Space Complexity: O(k) - Most Efficient\n");
        passCount += pass2 ? 1 : 0;
        failCount += pass2 ? 0 : 1;

        // ============ TEST 3: WITH FREQUENCY COUNT ============

        Map<String, Integer> result3 = findDuplicatesWithCount(stringList);
        boolean pass3 = result3.get("java") == 2 && result3.get("kafka") == 2 && result3.size() == 2;
        System.out.println("Test 3 - With Frequency: " + (pass3 ? "✓ PASS" : "✗ FAIL"));
        System.out.println("  Result: " + result3);
        System.out.println("  Shows how many times each duplicate appears\n");
        passCount += pass3 ? 1 : 0;
        failCount += pass3 ? 0 : 1;

        // ============ EDGE CASE TESTS ============

        // Test 4: No duplicates
        List<String> noDuplicates = Arrays.asList("a", "b", "c", "d");
        Set<String> result4 = findDuplicatesStreamAPI(noDuplicates);
        boolean pass4 = result4.isEmpty();
        System.out.println("Test 4 - No Duplicates: " + (pass4 ? "✓ PASS" : "✗ FAIL"));
        System.out.println("  Input: " + noDuplicates);
        System.out.println("  Result: " + result4 + "\n");
        passCount += pass4 ? 1 : 0;
        failCount += pass4 ? 0 : 1;

        // Test 5: All duplicates
        List<String> allDuplicates = Arrays.asList("x", "x", "x", "x");
        Set<String> result5 = findDuplicatesStreamAPI(allDuplicates);
        boolean pass5 = result5.size() == 1 && result5.contains("x");
        System.out.println("Test 5 - All Duplicates: " + (pass5 ? "✓ PASS" : "✗ FAIL"));
        System.out.println("  Input: " + allDuplicates);
        System.out.println("  Result: " + result5 + "\n");
        passCount += pass5 ? 1 : 0;
        failCount += pass5 ? 0 : 1;

        // ============ LARGE DATA PERFORMANCE TEST ============

        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║              LARGE DATA SET PERFORMANCE TEST              ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");

        // Test 6: 100K elements with many duplicates
        List<String> largeList100K = new ArrayList<>();
        for (int i = 0; i < 50000; i++) {
            largeList100K.add("element" + (i % 100));  // 100 unique, repeated 500 times
        }

        long time1 = System.nanoTime();
        Set<String> result6a = findDuplicatesStreamAPI(largeList100K);
        long duration1 = System.nanoTime() - time1;

        long time2 = System.nanoTime();
        Set<String> result6b = findDuplicatesSinglePass(largeList100K);
        long duration2 = System.nanoTime() - time2;

        boolean pass6 = result6a.size() == 99 && result6b.size() == 99;
        System.out.println("Test 6 - Large Array (50K elements): " + (pass6 ? "✓ PASS" : "✗ FAIL"));
        System.out.println("  Input: 50,000 elements (100 unique, each repeated 500x)");
        System.out.println("  Stream API Time: " + (duration1 / 1000000.0) + " ms");
        System.out.println("  Single Pass Time: " + (duration2 / 1000000.0) + " ms");
        System.out.println("  Duplicates Found: " + result6a.size() + "\n");
        passCount += pass6 ? 1 : 0;
        failCount += pass6 ? 0 : 1;

        // Test 7: 1 Million elements (heavy dataset as mentioned)
        List<String> hugeList = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            hugeList.add("item" + (i % 500));  // 500 unique, repeated 2000x
        }

        long time3 = System.nanoTime();
        Set<String> result7a = findDuplicatesStreamAPI(hugeList);
        long duration3 = System.nanoTime() - time3;

        long time4 = System.nanoTime();
        Set<String> result7b = findDuplicatesSinglePass(hugeList);
        long duration4 = System.nanoTime() - time4;

        boolean pass7 = result7a.size() == 499 && result7b.size() == 499;
        System.out.println("Test 7 - HUGE Array (1M elements): " + (pass7 ? "✓ PASS" : "✗ FAIL"));
        System.out.println("  Input: 1,000,000 elements (500 unique, each repeated 2000x)");
        System.out.println("  Stream API Time: " + (duration3 / 1000000.0) + " ms");
        System.out.println("  Single Pass Time: " + (duration4 / 1000000.0) + " ms");
        System.out.println("  Speedup: " + String.format("%.2fx", (double) duration3 / duration4));
        System.out.println("  Duplicates Found: " + result7a.size() + "\n");
        passCount += pass7 ? 1 : 0;
        failCount += pass7 ? 0 : 1;

        // Test 8: 10 Million elements (extreme case)
        List<String> extremeList = new ArrayList<>();
        for (int i = 0; i < 10000000; i++) {
            extremeList.add("data" + (i % 1000));  // 1000 unique
        }

        long time5 = System.nanoTime();
        Set<String> result8 = findDuplicatesSinglePass(extremeList);
        long duration5 = System.nanoTime() - time5;

        boolean pass8 = result8.size() == 999;
        System.out.println("Test 8 - EXTREME Array (10M elements): " + (pass8 ? "✓ PASS" : "✗ FAIL"));
        System.out.println("  Input: 10,000,000 elements");
        System.out.println("  Single Pass Time: " + (duration5 / 1000000.0) + " ms");
        System.out.println("  Memory Efficient: O(1000) space for 10M elements");
        System.out.println("  Duplicates Found: " + result8.size() + "\n");
        passCount += pass8 ? 1 : 0;
        failCount += pass8 ? 0 : 1;

        // ============ FINAL SUMMARY ============

        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║                     FINAL SUMMARY                         ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");

        System.out.println("Approach Comparison:");
        System.out.println("─".repeat(60));
        System.out.println("Stream API:        O(n) time, O(k) space | Clean & Readable");
        System.out.println("Single Pass:       O(n) time, O(k) space | Most Efficient");
        System.out.println("With Count:        O(n) time, O(k) space | Detailed Info");
        System.out.println("\nRecommendation for Heavy Data (1M+ records):");
        System.out.println("  ✓ Use: findDuplicatesSinglePass() - Lowest memory overhead");
        System.out.println("  ✓ Time: O(n) linear - scales perfectly");
        System.out.println("  ✓ Space: O(k) - only stores unique duplicates");

        System.out.println("\n" + "═".repeat(60));
        System.out.println("Total Tests: " + (passCount + failCount));
        System.out.println("Passed: " + passCount + " | Failed: " + failCount);
        if (failCount == 0) {
            System.out.println("✓ ALL TESTS PASSED - READY FOR PRODUCTION");
        }
        System.out.println("═".repeat(60));
    }
}
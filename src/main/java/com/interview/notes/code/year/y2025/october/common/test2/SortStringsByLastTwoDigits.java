package com.interview.notes.code.year.y2025.october.common.test2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SortStringsByLastTwoDigits {

    // Method to sort strings based on the last two digits
    private static List<String> sortByLastTwoDigits(List<String> list) {
        // Using Stream API to sort
        return list.stream()
                // sort by extracting last two characters and converting them to integer
                .sorted(Comparator.comparingInt(s -> Integer.parseInt(s.substring(s.length() - 2))))
                // collect back to list
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        // Test case 1: sample input
        List<String> input = Arrays.asList("ABC25", "DEF10", "GHI88", "XYZ14");
        List<String> expected = Arrays.asList("DEF10", "XYZ14", "ABC25", "GHI88");
        List<String> actual = sortByLastTwoDigits(input);

        System.out.println("Input: " + input);
        System.out.println("Expected: " + expected);
        System.out.println("Actual: " + actual);
        System.out.println("Test 1 Result: " + (expected.equals(actual) ? "PASS" : "FAIL"));

        // Test case 2: random unsorted data
        List<String> input2 = Arrays.asList("A99", "B01", "C50", "D10");
        List<String> expected2 = Arrays.asList("B01", "D10", "C50", "A99");
        List<String> actual2 = sortByLastTwoDigits(input2);
        System.out.println("Test 2 Result: " + (expected2.equals(actual2) ? "PASS" : "FAIL"));

        // Test case 3: large data test for performance
        List<String> largeList = new ArrayList<>();
        for (int i = 1000; i > 0; i--) {
            largeList.add("ITEM" + String.format("%02d", i % 100));
        }
        List<String> sortedLarge = sortByLastTwoDigits(largeList);
        boolean isSorted = true;
        for (int i = 1; i < sortedLarge.size(); i++) {
            int prev = Integer.parseInt(sortedLarge.get(i - 1).substring(sortedLarge.get(i - 1).length() - 2));
            int curr = Integer.parseInt(sortedLarge.get(i).substring(sortedLarge.get(i).length() - 2));
            if (prev > curr) {
                isSorted = false;
                break;
            }
        }
        System.out.println("Large input test passed: " + isSorted);
    }
}
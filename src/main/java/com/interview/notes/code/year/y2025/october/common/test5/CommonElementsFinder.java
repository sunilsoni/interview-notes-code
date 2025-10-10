package com.interview.notes.code.year.y2025.october.common.test5;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CommonElementsFinder {

    public static void main(String[] args) {

        // Test case 1
        Integer[] arr1 = {1, 2, 3, 4, 5};
        Integer[] arr2 = {0, 9, 8, 7, 6, 5, 1};
        Integer[] arr3 = {1, 4, 7, 9, 11, 44};

        // Expected output is [1]
        Set<Integer> expected = new HashSet<>(List.of(1));

        Set<Integer> result = findCommonElements(arr1, arr2, arr3);
        System.out.println("Common Elements: " + result);

        if (result.equals(expected)) {
            System.out.println("✅ Test Case 1 PASSED");
        } else {
            System.out.println("❌ Test Case 1 FAILED");
        }

        // Test case 2 - large input
        Integer[] big1 = IntStream.range(1, 1000000).boxed().toArray(Integer[]::new);
        Integer[] big2 = IntStream.range(500000, 1500000).boxed().toArray(Integer[]::new);
        Integer[] big3 = IntStream.range(750000, 1250000).boxed().toArray(Integer[]::new);

        long start = System.currentTimeMillis();
        Set<Integer> bigResult = findCommonElements(big1, big2, big3);
        long end = System.currentTimeMillis();

        System.out.println("Large Data Common Count: " + bigResult.size());
        System.out.println("Execution Time (ms): " + (end - start));
    }

    // Function to find common elements between 3 arrays
    private static Set<Integer> findCommonElements(Integer[] arr1, Integer[] arr2, Integer[] arr3) {

        // Step 1: Convert arrays to sets for faster lookup
        Set<Integer> set1 = Arrays.stream(arr1).collect(Collectors.toSet());
        Set<Integer> set2 = Arrays.stream(arr2).collect(Collectors.toSet());
        Set<Integer> set3 = Arrays.stream(arr3).collect(Collectors.toSet());

        // Step 2: Find intersection - only elements present in all three
        // First intersect set1 and set2, then intersect with set3
        return set1.stream()
                .filter(set2::contains)
                .filter(set3::contains)
                .collect(Collectors.toSet());
    }
}
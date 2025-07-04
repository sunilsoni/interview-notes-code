package com.interview.notes.code.year.y2025.June.ibm.test1;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solution {

    /**
     * Returns the k most recent distinct requests,
     * in order from most recent → least recent.
     */
    public static List<String> getLatestKRequests(List<String> requests, int k) {
        Set<String> seen = new HashSet<>();
        // iterate backwards, keep first occurrence of each distinct ID, limit to k
        return IntStream.range(0, requests.size())
                .mapToObj(i -> requests.get(requests.size() - 1 - i))
                .filter(id -> seen.add(id))
                .limit(k)
                .collect(Collectors.toList());
    }

    /**
     * Simple main method to run tests and print PASS/FAIL.
     */
    public static void main(String[] args) {
        int passed = 0, total = 0;

        // Sample Case 0
        {
            total++;
            List<String> req = Arrays.asList("item3", "item2", "item1", "item2", "item3");
            List<String> expected = Arrays.asList("item3", "item2", "item1");
            List<String> actual = getLatestKRequests(req, 3);
            if (actual.equals(expected)) {
                System.out.println("Test 0: PASS");
                passed++;
            } else {
                System.out.println("Test 0: FAIL");
                System.out.println("  expected=" + expected);
                System.out.println("  actual  =" + actual);
            }
        }

        // Sample Case 1
        {
            total++;
            List<String> req = Collections.singletonList("item1");
            List<String> expected = Collections.singletonList("item1");
            List<String> actual = getLatestKRequests(req, 1);
            if (actual.equals(expected)) {
                System.out.println("Test 1: PASS");
                passed++;
            } else {
                System.out.println("Test 1: FAIL");
                System.out.println("  expected=" + expected);
                System.out.println("  actual  =" + actual);
            }
        }

        // Edge: fewer distinct than k
        {
            total++;
            List<String> req = Arrays.asList("a", "b", "a", "b", "a");
            List<String> expected = Arrays.asList("a", "b");
            List<String> actual = getLatestKRequests(req, 5);
            if (actual.equals(expected)) {
                System.out.println("Test 2: PASS");
                passed++;
            } else {
                System.out.println("Test 2: FAIL");
                System.out.println("  expected=" + expected);
                System.out.println("  actual  =" + actual);
            }
        }

        // Large-data sanity check
        {
            total++;
            int n = 100_000;
            int uniq = 10_000, k = 5_000;
            List<String> req = new ArrayList<>(n);
            // cycle through "0", "1", ..., "9999" ten times
            for (int t = 0; t < n; t++) {
                req.add(String.valueOf(t % uniq));
            }
            List<String> actual = getLatestKRequests(req, k);
            if (actual.size() == k) {
                System.out.println("Test 3 (large): PASS");
                passed++;
            } else {
                System.out.println("Test 3 (large): FAIL");
                System.out.println("  expected size=" + k);
                System.out.println("  actual size  =" + actual.size());
            }
        }

        // summary
        System.out.printf("Passed %d of %d tests.%n", passed, total);
    }
}
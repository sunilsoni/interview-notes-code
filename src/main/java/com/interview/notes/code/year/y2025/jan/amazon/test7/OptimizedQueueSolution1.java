package com.interview.notes.code.year.y2025.jan.amazon.test7;

import java.util.*;

public class OptimizedQueueSolution1 {

    // --------------------------------------------
    // 1) The function to solve the problem
    // --------------------------------------------
    public static int getMaximumEvents(List<Integer> payload) {
        // Count frequencies of each distinct value
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int value : payload) {
            freqMap.put(value, freqMap.getOrDefault(value, 0) + 1);
        }

        // Extract distinct values and sort them
        List<Integer> distinctValues = new ArrayList<>(freqMap.keySet());
        Collections.sort(distinctValues);

        // We will build three lists:
        //   Inc1 in ascending order
        //   Dec in ascending order (then we will reverse it)
        //   Inc2 in ascending order
        // This is just a "tentative" placement.
        List<Integer> inc1 = new ArrayList<>();
        List<Integer> dec = new ArrayList<>();
        List<Integer> inc2 = new ArrayList<>();

        // For each distinct value x in ascending order,
        // we can place up to 3 copies: one in inc1, one in dec, one in inc2
        for (int x : distinctValues) {
            int count = freqMap.get(x);

            // Place up to 1 copy in inc1
            if (count > 0) {
                inc1.add(x);
                count--;
            }
            // Place up to 1 copy in dec
            if (count > 0) {
                dec.add(x);
                count--;
            }
            // Place up to 1 copy in inc2
            if (count > 0) {
                inc2.add(x);
                count--;
            }
            // If there are even more copies left, we simply cannot use them
            // in a strictly inc/dec/inc sequence without repeating adjacent values.
        }

        // Now dec is in ascending order, but we want the final segment to be strictly decreasing.
        // So we will reverse dec at the end.

        // Next, fix any boundary conflicts:
        //  - If last(inc1) == first(dec), we cannot pivot strictly downward.
        //    So remove the first element from dec.
        if (!inc1.isEmpty() && !dec.isEmpty()) {
            if (inc1.get(inc1.size() - 1).equals(dec.get(0))) {
                // Remove that conflict from dec
                dec.remove(0);
            }
        }

        // Now, after we eventually reverse dec, the last element of dec in the final arrangement
        // will be dec.get(0) in the reversed version (the "smallest" in the dec list).
        // We have to check the pivot with inc2:
        //   if last element of dec == first element of inc2, that breaks the "strictly <" boundary.
        // But note: after reversing, dec.get(dec.size()-1) will be the largest (front pivot),
        // and dec.get(0) will be the smallest (back pivot). So effectively, the "last element"
        // in the final dec segment is dec.get(0) before reversing.

        if (!dec.isEmpty() && !inc2.isEmpty()) {
            // The last element of dec in final arrangement is dec.get(0) now,
            // because we haven't reversed it yet.
            if (dec.get(0).equals(inc2.get(0))) {
                inc2.remove(0);
            }
        }

        // Reverse the 'dec' part to make it strictly decreasing in final arrangement
        Collections.reverse(dec);

        // The result length is simply total elements across the three segments
        return inc1.size() + dec.size() + inc2.size();
    }

    // --------------------------------------------
    // 2) Testing in a simple main (No JUnit)
    // --------------------------------------------
    public static void main(String[] args) {
        // We'll create multiple test cases and print pass/fail
        // You can add or modify as many tests as you want.

        // Format: we create a method testPayload(...) to quickly do the check
        // and compare with an expected result.

        boolean allPassed = true;

        // Test #1: Provided sample with 2 events
        {
            List<Integer> input = Arrays.asList(1, 100);
            int expected = 2;
            allPassed &= testPayload("Sample Case n=2", input, expected);
        }

        // Test #2: Provided sample with 7 events
        {
            // 7
            // 5
            // 5
            // 2
            // 1
            // 3
            // 4
            // 5
            List<Integer> input = Arrays.asList(5, 5, 2, 1, 3, 4, 5);
            int expected = 6;
            allPassed &= testPayload("Sample Case n=7", input, expected);
        }

        // Test #3: Provided example with 9 events
        {
            // 9
            // 1 3 5 4 2 6 8 7 9
            List<Integer> input = Arrays.asList(1, 3, 5, 4, 2, 6, 8, 7, 9);
            int expected = 9;
            allPassed &= testPayload("Sample Case n=9", input, expected);
        }

        // Additional Test #4: Minimal n=2 distinct with different arrangement
        {
            List<Integer> input = Arrays.asList(10, 1);
            // We can do inc->dec, or dec->empty, etc. Max is 2
            int expected = 2;
            allPassed &= testPayload("Extra 2-distinct test", input, expected);
        }

        // Additional Test #5: All elements the same (but the problem states
        //   "payload has at least 2 distinct elements" so in reality we should
        //    never get a case with all identical values. We skip it or just show
        //    how it behaves in principle).

        // Additional Test #6: Multiple duplicates
        {
            List<Integer> input = Arrays.asList(2, 2, 2, 2, 2);
            // Although the problem says at least 2 distinct, let's see the approach:
            // Distinct = {2}, freq=5 => inc=[2], dec=[2], inc2=[2].
            // Then pivot check inc.last=2, dec.first=2 => remove dec.first => dec=[]
            // dec->inc2 pivot => dec empty => no removal => final: inc=[2], inc2=[2] => 2 used.
            // That’s just to demonstrate how the code handles duplicates. 
            // But because only one distinct value is present, it can’t form real inc->dec->inc.
            // The code returns 2. This is consistent with the approach.
            int expected = 2;
            allPassed &= testPayload("All same test (only 1 distinct) - not strictly valid per problem constraints", input, expected);
        }

        // Additional Large Test #7 (just to check performance)
        {
            List<Integer> largeInput = new ArrayList<>();
            // Let's generate 1..50000 twice, so total 100000 numbers
            // This ensures we have many distinct values and duplicates
            int limit = 50000;
            for (int i = 1; i <= limit; i++) {
                largeInput.add(i);
            }
            for (int i = 1; i <= limit; i++) {
                largeInput.add(i);
            }
            // We won't strictly know the exact maximum offhand, but we just want to see it runs fast
            int result = getMaximumEvents(largeInput);
            System.out.println("Large Test with 100000 elements => Output size: " + result
                    + " (just checking performance, no exact pass/fail here)");
        }

        System.out.println("--------------------------------");
        System.out.println(allPassed
                ? "ALL SMALL TESTS PASSED"
                : "SOME TEST(S) FAILED");
    }

    // Helper to run a single test and print pass/fail
    private static boolean testPayload(String testName, List<Integer> input, int expected) {
        int result = getMaximumEvents(input);
        boolean pass = (result == expected);
        System.out.println(testName + " -> Expected: " + expected
                + ", Got: " + result + " => " + (pass ? "PASS" : "FAIL"));
        return pass;
    }
}

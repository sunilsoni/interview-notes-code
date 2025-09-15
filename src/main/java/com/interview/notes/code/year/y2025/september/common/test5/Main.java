package com.interview.notes.code.year.y2025.september.common.test5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

class LongestConsecutiveSequence {
    // Map will store number -> length of consecutive sequence that includes this number
    private final Map<Integer, Integer> map = new HashMap<>();

    // Track the maximum length found so far
    private int maxLength = 0;

    // Add a number into our data structure
    public void AddNumber(int num) {
        // If already present, skip to avoid duplicate processing
        if (map.containsKey(num)) return;

        // Length of left sequence (num-1) if exists
        int left = map.getOrDefault(num - 1, 0);

        // Length of right sequence (num+1) if exists
        int right = map.getOrDefault(num + 1, 0);

        // Current length is left + right + 1 (including num)
        int currLength = left + right + 1;

        // Store current number
        map.put(num, currLength);

        // Update boundaries: very important to update sequence edges
        map.put(num - left, currLength);   // update start boundary
        map.put(num + right, currLength);  // update end boundary

        // Update global maximum
        maxLength = Math.max(maxLength, currLength);
    }

    // Return longest consecutive sequence length
    public int LongestConsecutive() {
        return maxLength;
    }
}

public class Main {
    public static void main(String[] args) {
        // Define test cases as a list of operations and expected results
        List<Runnable> tests = new ArrayList<>();

        // Test 1: Example from the image
        tests.add(() -> {
            LongestConsecutiveSequence seq = new LongestConsecutiveSequence();
            boolean pass = seq.LongestConsecutive() == 0;

            // Initial should be 0

            seq.AddNumber(1);
            seq.AddNumber(2);
            if (seq.LongestConsecutive() != 2) pass = false;

            seq.AddNumber(4);
            if (seq.LongestConsecutive() != 2) pass = false;

            seq.AddNumber(3);
            if (seq.LongestConsecutive() != 4) pass = false;

            System.out.println("Test 1: " + (pass ? "PASS" : "FAIL"));
        });

        // Test 2: Single element
        tests.add(() -> {
            LongestConsecutiveSequence seq = new LongestConsecutiveSequence();
            seq.AddNumber(10);
            System.out.println("Test 2: " + (seq.LongestConsecutive() == 1 ? "PASS" : "FAIL"));
        });

        // Test 3: Duplicate numbers should not break
        tests.add(() -> {
            LongestConsecutiveSequence seq = new LongestConsecutiveSequence();
            seq.AddNumber(5);
            seq.AddNumber(5);
            System.out.println("Test 3: " + (seq.LongestConsecutive() == 1 ? "PASS" : "FAIL"));
        });

        // Test 4: Large data performance
        tests.add(() -> {
            LongestConsecutiveSequence seq = new LongestConsecutiveSequence();

            // Insert 1 million consecutive numbers using Stream API
            IntStream.rangeClosed(1, 1_000_000).forEach(seq::AddNumber);

            System.out.println("Test 4: " + (seq.LongestConsecutive() == 1_000_000 ? "PASS" : "FAIL"));
        });

        // Run all tests
        tests.forEach(Runnable::run);
    }
}
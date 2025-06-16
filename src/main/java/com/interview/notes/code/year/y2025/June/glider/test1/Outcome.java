package com.interview.notes.code.year.y2025.June.glider.test1;

import java.util.*;
import java.util.stream.*;

class Outcome {

    public static int solve(List<Integer> dolls) {
        // Count the frequency of each doll size
        Map<Integer, Integer> freq = new HashMap<>();
        dolls.forEach(d -> freq.put(d, freq.getOrDefault(d, 0) + 1));
        
        // The answer is the maximum frequency (most duplicates)
        return Collections.max(freq.values());
    }

    // Main method for testing
    public static void main(String[] args) {

        // Provided Test Cases
        List<List<Integer>> testCases = Arrays.asList(
            Arrays.asList(2, 2, 3, 3), // Expected output: 2
            Arrays.asList(1, 2, 2, 3, 4, 5) // Expected output: 2
        );
        List<Integer> expectedResults = Arrays.asList(2, 2);

        // Additional Edge Test Case
        List<Integer> largeCase = IntStream.generate(() -> 1).limit(100000).boxed().collect(Collectors.toList()); // Expected output: 100000
        testCases = new ArrayList<>(testCases);
        testCases.add(largeCase);
        expectedResults = new ArrayList<>(expectedResults);
        expectedResults.add(100000);

        // Execute test cases
        for (int i = 0; i < testCases.size(); i++) {
            int result = solve(testCases.get(i));
            System.out.printf("Test Case #%d: %s\n", i + 1, (result == expectedResults.get(i)) ? "PASS" : "FAIL");
            System.out.println("Output: " + result + ", Expected: " + expectedResults.get(i));
        }
    }
}

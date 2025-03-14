package com.interview.notes.code.year.y2025.march.common.test17;

import java.util.*;
import java.util.stream.*;

// Class implementing the competitive gaming ranking logic
public class CompetitiveGaming {

    // Method to calculate how many players level up
    public static int numPlayers(int k, List<Integer> scores) {
        // Sort scores in descending order to rank players
        List<Integer> sortedScores = scores.stream()
                .filter(score -> score > 0) // Exclude zero scores
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());

        // Edge case: No player has a valid score
        if (sortedScores.isEmpty()) return 0;

        int rank = 1, playersWhoLevelUp = 0;

        // Assign ranks, keeping track of ties
        for (int i = 0; i < sortedScores.size(); i++) {
            // Increment rank only when score changes (tie handling)
            if (i > 0 && !sortedScores.get(i).equals(sortedScores.get(i - 1))) {
                rank = i + 1;
            }

            // Players within cutoff rank can level up
            if (rank <= k) {
                playersWhoLevelUp++;
            } else {
                break; // No need to check further as ranks are increasing
            }
        }

        return playersWhoLevelUp;
    }

    // Main method for minimal reproducible example
    public static void main(String[] args) {
        List<Integer> scoresExample = Arrays.asList(100, 50, 50, 25);
        int kExample = 3;

        int result = numPlayers(kExample, scoresExample);
        System.out.println("Players who can level up: " + result);

        // Test with provided test cases
        runTests();
    }

    // Test method to verify correctness and handle large input cases
    public static void runTests() {
        Map<List<Integer>, Integer> testCases = new LinkedHashMap<>();
        testCases.put(Arrays.asList(20, 40, 60, 80, 100), 4); // Expected: 4
        testCases.put(Arrays.asList(2, 2, 3, 4, 5), 4);       // Expected: 5
        testCases.put(Arrays.asList(0, 0, 0, 0), 3);          // Edge Case: Expected: 0

        int testNumber = 1;
        boolean allPass = true;

        for (Map.Entry<List<Integer>, Integer> entry : testCases.entrySet()) {
            int expected = entry.getValue();
            List<Integer> scores = entry.getKey();
            int actual = numPlayers(expected, scores);

            if (actual == expected) {
                System.out.println("Test " + testNumber + " PASS");
            } else {
                System.out.println("Test " + testNumber + " FAIL. Expected: " + expected + ", Actual: " + actual);
                allPass = false;
            }
            testNumber++;
        }

        // Large input case testing
        List<Integer> largeInput = IntStream.range(0, 100000).map(i -> 100).boxed().collect(Collectors.toList());
        int largeInputResult = numPlayers(99999, largeInput);
        System.out.println("Large input test result (should be 100000): " + largeInputResult);

        if (largeInputResult == 100000 && allPass) {
            System.out.println("All test cases PASS including large data input.");
        } else {
            System.out.println("Some test cases FAILED.");
        }
    }
}
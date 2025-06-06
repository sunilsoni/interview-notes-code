package com.interview.notes.code.year.y2025.may.google;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FiveWordPuzzleSolver {

    // Main method to demonstrate and test the solution
    public static void main(String[] args) {
        // Sample dictionary with test words
        List<String> dictionary = Arrays.asList(
                "chunk", "fjord", "gymps", "vibex", "waltz",
                "quick", "brown", "jumps", "over", "lazy",
                "fjord", "gucks", "nymph", "vibex", "waltz"
        );

        // Find and print solutions
        List<List<String>> solutions = findFiveWordCombinations(dictionary);

        // Test case 1: Verify we can find valid combinations
        System.out.println("Test Case 1: Finding valid combinations");
        if (!solutions.isEmpty()) {
            System.out.println("PASS - Found " + solutions.size() + " combinations");
            solutions.forEach(System.out::println);
        } else {
            System.out.println("FAIL - No combinations found");
        }

        // Test case 2: Verify each solution uses 25 distinct letters
        System.out.println("\nTest Case 2: Verifying distinct letters");
        boolean allValid = solutions.stream()
                .allMatch(solution -> hasDistinct25Letters(String.join("", solution)));
        System.out.println(allValid ? "PASS - All solutions use 25 distinct letters"
                : "FAIL - Invalid solutions found");
    }

    // Main logic to find valid five-word combinations
    public static List<List<String>> findFiveWordCombinations(List<String> dictionary) {
        // Filter only 5-letter words and convert to uppercase for consistency
        List<String> fiveLetterWords = dictionary.stream()
                .filter(word -> word.length() == 5)
                .map(String::toUpperCase)
                .distinct()
                .collect(Collectors.toList());

        List<List<String>> results = new ArrayList<>();
        int n = fiveLetterWords.size();

        // Use nested loops to try all possible combinations of 5 words
        for (int i = 0; i < n - 4; i++) {
            for (int j = i + 1; j < n - 3; j++) {
                for (int k = j + 1; k < n - 2; k++) {
                    for (int l = k + 1; l < n - 1; l++) {
                        for (int m = l + 1; m < n; m++) {
                            List<String> combination = Arrays.asList(
                                    fiveLetterWords.get(i),
                                    fiveLetterWords.get(j),
                                    fiveLetterWords.get(k),
                                    fiveLetterWords.get(l),
                                    fiveLetterWords.get(m)
                            );

                            // Check if this combination uses 25 distinct letters
                            if (hasDistinct25Letters(String.join("", combination))) {
                                results.add(combination);
                            }
                        }
                    }
                }
            }
        }
        return results;
    }

    // Helper method to check if a string contains 25 distinct letters
    private static boolean hasDistinct25Letters(String combined) {
        // Convert string to set of characters and check if size is 25
        return combined.chars()
                .mapToObj(ch -> (char) ch)
                .collect(Collectors.toSet())
                .size() == 25;
    }
}

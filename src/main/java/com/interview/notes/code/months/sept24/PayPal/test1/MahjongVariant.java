package com.interview.notes.code.months.sept24.PayPal.test1;

public class MahjongVariant {
    public static void main(String[] args) {
        // Test cases
        String[] testCases = {
                "88844", "99", "55555", "22333333", "73797439949499477339977777997394947947477993",
                "111333555", "42", "888", "100100000", "346664366", "8999998999898", "17610177",
                "600061166", "6996999", "03799449", "64444333355556", "7", "776655"
        };
        boolean[] expectedResults = {
                true, true, true, true, true, false, false, false, false, false,
                false, false, false, false, false, false, false, false
        };

        for (int i = 0; i < testCases.length; i++) {
            boolean result = complete(testCases[i]);
            System.out.println("Test Case " + (i + 1) + ": " +
                    (result == expectedResults[i] ? "Passed" : "Failed"));
        }

        // Additional test cases
        System.out.println("Test Case 19: " + (complete("") ? "Failed" : "Passed")); // Empty string
        System.out.println("Test Case 20: " + (complete("1234567890") ? "Failed" : "Passed")); // All different
        System.out.println("Test Case 21: " + (complete("999999999") ? "Passed" : "Failed")); // All same

        // Large input test case
        StringBuilder largeInput = new StringBuilder();
        for (int i = 0; i < 1000000; i++) {
            largeInput.append(i % 10);
        }
        long startTime = System.currentTimeMillis();
        boolean largeResult = complete(largeInput.toString());
        long endTime = System.currentTimeMillis();
        System.out.println("Large Input Test: " + (largeResult ? "Passed" : "Failed") +
                " (Time: " + (endTime - startTime) + "ms)");
    }

    public static boolean complete(String tiles) {
        // Check for null or empty input
        if (tiles == null || tiles.isEmpty()) {
            return false; // Invalid input, can't form a complete hand
        }
        // Initialize an array to count occurrences of each tile (0-9)
        int[] counts = new int[10];
        // Iterate through each character in the input string
        for (char tile : tiles.toCharArray()) {
            // Validate that the character is a digit between 0 and 9
            if (tile < '0' || tile > '9') {
                return false; // Invalid input: non-digit character found
            }
            // Increment the count for this tile
            // Subtract '0' to convert char to int (e.g., '3' - '0' = 3)
            counts[tile - '0']++;
        }
        // Flag to track if we've found a pair
        boolean hasPair = false;
        // Iterate through the counts of each tile
        for (int count : counts) {
            // If count % 3 == 1, we have a single tile left over
            // which can't form a triple or be part of a pair
            if (count % 3 == 1) {
                return false; // Can't form a valid hand
            }
            // If count % 3 == 2, we have a potential pair
            if (count % 3 == 2) {
                // Check if we already found a pair
                if (hasPair) {
                    return false; // More than one pair, invalid hand
                }
                hasPair = true; // Mark that we've found a pair
            }
            // Note: If count % 3 == 0, it's a valid triple (or multiples of triples)
        }
        // A valid hand must have exactly one pair
        return hasPair;
    }

}

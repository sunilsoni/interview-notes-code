package com.interview.notes.code.year.y2025.jan.paypal.test3;

public class MahjongTiles {

    public static boolean isCompleteHand(String tiles) {
        // Count frequency of each tile
        int[] counts = new int[10];
        for (char c : tiles.toCharArray()) {
            counts[c - '0']++;
        }

        // Try each number as the pair
        for (int i = 0; i < 10; i++) {
            if (counts[i] >= 2) {
                // Make a copy of counts array
                int[] tempCounts = counts.clone();
                tempCounts[i] -= 2;  // Remove the pair

                // Try forming triples greedily
                if (tryFormTriples(tempCounts)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean tryFormTriples(int[] counts) {
        // Check each digit
        for (int i = 0; i < counts.length; i++) {
            // Must use all tiles of each number
            while (counts[i] > 0) {
                if (counts[i] >= 3) {
                    counts[i] -= 3;
                } else {
                    return false;  // Can't form a triple
                }
            }
        }
        return true;  // All tiles used successfully
    }

    public static void main(String[] args) {
        // Test cases
        String[] testCases = {
                "88884",        // true
                "99",          // true
                "55555",       // true
                "2233333",     // true
                "737974399494974379777979739497477993", // true
                "11133355",    // false
                "42",          // false
                "888",         // false
                "10010000",    // false
                "346664366",   // false
                "899999989999898", // false
                "17610177",    // false
                "54616616",    // false
                "6969699",     // false
                "0379949",     // false
                "6444433355556", // false
                "7",           // false
                "776655"       // false
        };

        // Run tests and print results
        for (int i = 0; i < testCases.length; i++) {
            boolean result = isCompleteHand(testCases[i]);
            System.out.printf("Test case %2d: %s => %b (Expected: %b) %s%n",
                    i + 1,
                    testCases[i],
                    result,
                    i < 5, // First 5 cases should be true
                    result == (i < 5) ? "✓" : "✗"
            );
        }

        // Additional verification for the large test case
        String largeCase = "737974399494974379777979739497477993";
        boolean largeResult = isCompleteHand(largeCase);
        System.out.println("\nLarge case verification: " + largeResult);
    }
}

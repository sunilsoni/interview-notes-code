package com.interview.notes.code.year.y2025.jan.paypal.test2;

public class MahjongChecker {

    /**
     * Checks if a string of digit-characters represents a 'complete hand'
     * under the rules:
     * - Exactly one pair
     * - Zero or more triples
     * - No leftover tiles
     */
    public static boolean isCompleteHand(String tiles) {
        // Edge cases: if < 2 tiles, can't form even a single pair
        if (tiles == null || tiles.length() < 2) {
            return false;
        }

        // Count frequencies of each digit
        int[] freq = new int[10];
        for (char c : tiles.toCharArray()) {
            // Ensure valid digit range, though problem states 0-9 only
            if (c < '0' || c > '9') {
                return false; // or handle as invalid input
            }
            freq[c - '0']++;
        }

        // Try each digit as the pair
        for (int d = 0; d < 10; d++) {
            if (freq[d] >= 2) {
                // Use two of digit d as the pair
                freq[d] -= 2;

                // Check if remaining can form only triples
                if (canAllFormTriples(freq)) {
                    // Restore frequency for cleanliness (optional here)
                    freq[d] += 2;
                    return true;
                }

                // Restore frequency before trying the next possibility
                freq[d] += 2;
            }
        }

        // If no digit could serve as a valid pair -> not complete
        return false;
    }

    /**
     * Helper method: checks if each digit's frequency is a multiple of 3.
     * i.e., freq[d] % 3 == 0 for all d
     */
    private static boolean canAllFormTriples(int[] freq) {
        for (int count : freq) {
            if (count % 3 != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Main method to demonstrate and test the solution.
     * We show PASS/FAIL for each known case.
     */
    public static void main(String[] args) {
        // Provided test inputs and expected results
        // Format: { "tiles", expectedBoolean, testDescription }
        Object[][] testCases = {
                {"88884", true, "Base case - a pair and a triple"},
                {"99", true, "Just a pair is enough"},
                {"55555", true, "Triple and pair of the same tile"},
                {"2233333", true, "A pair and two triples"},
                {"737974399494974379777979739497477993", true,
                        "4 digits: two triples + a pair for '4', others triple out"},
                {"11133355", false, "3 triples but no pair"},
                {"42", false, "Two single tiles not forming a pair"},
                {"888", false, "One triple, no pair"},
                {"10010000", false, "A leftover digit remains after pairing/tripling"},
                {"346664366", false, "Three pairs + one triple (must be exactly one pair)"},
                {"899999989999898", false, "Leftover digit 8"},
                {"17610177", false, "Does not properly form one pair + triples"},
                {"54616616", false, "Leaves a leftover 6"},
                {"6969699", false, "Two different pairs plus a triple"},
                {"0379949", false, "Leftover tiles remain"},
                {"6444433355556", false, "Multiple pairs instead of exactly one"},
                {"7", false, "Single tile leftover"},
                {"776655", false, "Three pairs"}
        };

        // Run each test case
        int passCount = 0;
        for (Object[] test : testCases) {
            String tiles = (String) test[0];
            boolean expected = (Boolean) test[1];
            String description = (String) test[2];

            boolean result = isCompleteHand(tiles);
            boolean pass = (result == expected);

            // Print out a PASS/FAIL message
            System.out.println(
                    String.format(
                            "Test: %-40s | Expected: %5s | Got: %5s | %s",
                            "\"" + tiles + "\"",
                            expected,
                            result,
                            pass ? "PASS" : "FAIL"
                    )
            );
            if (pass) {
                passCount++;
            }
        }

        System.out.println();
        System.out.println("Summary: " + passCount + "/" + testCases.length + " tests passed.");

        // Optional: Add a couple of extra edge-case or large-data tests
        extraTests();
    }

    /**
     * Demonstrates additional tests, including edge and large data cases.
     */
    private static void extraTests() {
        // Edge cases
        String[] edgeCases = {
                "",       // empty
                "0",      // single digit
                "999999", // all same digit, forms 2 triples, but no pair
                "3333",   // two pairs (3,3) but no triple => should be false
                "000",    // one triple => no pair => false
                "0000"    // triple(0) + 1 leftover => false
        };

        System.out.println("\nExtra Edge Cases:");
        for (String ec : edgeCases) {
            System.out.println(
                    "\"" + ec + "\"" + " -> " + isCompleteHand(ec)
            );
        }

        // Large data test (repetitive pattern)
        StringBuilder large = new StringBuilder();
        // Example: 1000 '1's, 3 leftover '2's => not complete
        // Because you can't form a valid single pair and triple out everything
        for (int i = 0; i < 1000; i++) {
            large.append('1');
        }
        large.append("222"); // leftover '2's
        System.out.println("\nLarge Data Test (1000 '1's + 3 '2's):");
        System.out.println("Result -> " + isCompleteHand(large.toString()));
    }
}

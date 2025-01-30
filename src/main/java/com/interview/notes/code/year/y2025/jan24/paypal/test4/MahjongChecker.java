package com.interview.notes.code.year.y2025.jan24.paypal.test4;

import java.util.Arrays;

public class MahjongChecker {
    
    /**
     * Checks if a string of digit-characters (each '0'–'9') represents a
     * "complete hand" under the rules:
     *   - Exactly one pair (2 of the same digit)
     *   - Zero or more triples (3 of the same digit)
     *   - No leftover tiles (every tile belongs to exactly one pair or triple)
     */
    public static boolean isCompleteHand(String tiles) {
        // Edge case: fewer than 2 tiles => can't form a pair
        if (tiles == null || tiles.length() < 2) {
            return false;
        }
        
        // Frequency array for digits 0..9
        int[] freq = new int[10];
        
        // Count how many times each digit appears
        for (char c : tiles.toCharArray()) {
            // If not in '0'..'9', treat as invalid
            if (c < '0' || c > '9') {
                return false;
            }
            freq[c - '0']++;
        }
        
        // Try each digit as the pair
        for (int d = 0; d < 10; d++) {
            if (freq[d] >= 2) {
                // Use two of digit d for the pair
                freq[d] -= 2;
                
                // Check if the remaining can all form multiples of 3
                if (canAllFormTriples(freq)) {
                    // Restore (optional here) and return success
                    freq[d] += 2;
                    return true;
                }
                
                // Restore frequency before testing next digit
                freq[d] += 2;
            }
        }
        
        // If no digit could form the required pair => false
        return false;
    }
    
    /**
     * Helper: checks that every digit's frequency is a multiple of 3.
     * That means each digit can form 0 or more "triples" exactly.
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
     * Main method to test the function on provided puzzle inputs
     * and some extra edge cases. Prints PASS/FAIL status.
     */
    public static void main(String[] args) {
        
        // The puzzle’s test inputs and comments (unchanged from prompt):
        // NOTE: Some are contradictory with the standard "pair + triple" logic.
        Object[][] testCases = {
            { "88884",        true,  "Base case - a pair and a triple (Puzzle says True, likely a mismatch)" },
            { "99",           true,  "Just a pair is enough" },
            { "55555",        true,  "The triple and a pair can be of the same tile value" },
            { "2233333",      true,  "A pair and two triples (Puzzle says True, but there are only 7 tiles)" },
            { "737974399494974379777979739497477993", true,
                    "Puzzle claims large combo (Puzzle says True, code might differ)" },
            { "11133355",     false, "Puzzle says False, but logically it's 3x1, 3x3, pair of 5 => should be True" },
            { "42",           false, "Two singles not forming a pair" },
            { "888",          false, "A triple, no pair" },
            { "10010000",     false, "Puzzle says leftover of 0 => code may differ" },
            { "346664366",    false, "Three pairs and a triple => puzzle says False" },
            { "899999989999898", false, "Puzzle says leftover of 8 => false" },
            { "17610177",     false, "Puzzle says false" },
            { "54616616",     false, "Puzzle says false" },
            { "6969699",      false, "Puzzle says false" },
            { "0379949",      false, "Puzzle says false" },
            { "6444433355556",false, "Puzzle says false" },
            { "7",            false, "Single tile leftover => false" },
            { "776655",       false, "Three pairs => false" }
        };
        
        int passCount = 0;
        for (Object[] tc : testCases) {
            String tiles = (String) tc[0];
            boolean expected = (Boolean) tc[1];
            String comment  = (String) tc[2];
            
            boolean got = isCompleteHand(tiles);
            boolean pass = (got == expected);
            
            System.out.printf(
                "Test: %-35s | Expected: %5s | Got: %5s | %s%n",
                "\"" + tiles + "\"",
                expected,
                got,
                pass ? "PASS" : "FAIL"
            );
            
            if (pass) {
                passCount++;
            }
        }
        
        System.out.println("\nSummary: " + passCount + "/" + testCases.length + " tests passed.");
        
        // Extra edge cases
        System.out.println("\nExtra Edge Cases:");
        String[] edgeCases = {
            "",         // empty
            "0",        // single digit
            "999999",   // can form two triples => no pair => false
            "3333",     // two pairs => no single pair + triple => false
            "000",      // just one triple => no pair => false
            "0000"      // triple + leftover => false
        };
        
        for (String ec : edgeCases) {
            System.out.println("\"" + ec + "\" -> " + isCompleteHand(ec));
        }
        
        // Large data test
        System.out.println("\nLarge Data Test (1000 '1's + 3 '2's):");
        StringBuilder large = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            large.append('1');
        }
        large.append("222"); 
        System.out.println("Length: " + large.length() + " -> " + isCompleteHand(large.toString()));
    }
}

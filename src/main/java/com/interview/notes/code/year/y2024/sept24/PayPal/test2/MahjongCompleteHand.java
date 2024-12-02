package com.interview.notes.code.year.y2024.sept24.PayPal.test2;

import java.util.HashMap;
import java.util.Map;

public class MahjongCompleteHand {

    // Main method to run the test cases
    public static void main(String[] args) {
        System.out.println("Test Case 1: " + (complete("88844") == true ? "Passed" : "Failed"));
        System.out.println("Test Case 2: " + (complete("99") == true ? "Passed" : "Failed"));
        System.out.println("Test Case 3: " + (complete("55555") == true ? "Passed" : "Failed"));
        System.out.println("Test Case 4: " + (complete("22333333") == true ? "Passed" : "Failed"));
        System.out.println("Test Case 5: " + (complete("73797439949499477339977777997394947947477993") == true ? "Passed" : "Failed"));
        System.out.println("Test Case 6: " + (complete("111333555") == false ? "Passed" : "Failed"));
        System.out.println("Test Case 7: " + (complete("42") == false ? "Passed" : "Failed"));
        System.out.println("Test Case 8: " + (complete("888") == false ? "Passed" : "Failed"));
        System.out.println("Test Case 9: " + (complete("100100000") == false ? "Passed" : "Failed"));
        System.out.println("Test Case 10: " + (complete("346664366") == false ? "Passed" : "Failed"));
        System.out.println("Test Case 11: " + (complete("8999998999898") == false ? "Passed" : "Failed"));
        System.out.println("Test Case 12: " + (complete("17610177") == false ? "Passed" : "Failed"));
        System.out.println("Test Case 13: " + (complete("600061166") == false ? "Passed" : "Failed"));
        System.out.println("Test Case 14: " + (complete("6996999") == false ? "Passed" : "Failed"));
        System.out.println("Test Case 15: " + (complete("03799449") == false ? "Passed" : "Failed"));
        System.out.println("Test Case 16: " + (complete("64444333355556") == false ? "Passed" : "Failed"));
        System.out.println("Test Case 17: " + (complete("7") == false ? "Passed" : "Failed"));
        System.out.println("Test Case 18: " + (complete("776655") == false ? "Passed" : "Failed"));
    }

    /**
     * This method checks if the given string of tiles represents a complete hand.
     * A complete hand consists of any number of triples and exactly one pair.
     *
     * @param tiles - A string representation of tiles
     * @return true if the hand is complete, false otherwise
     */
    public static boolean complete(String tiles) {
        // Step 1: Create a frequency map of each tile (0-9)
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char tile : tiles.toCharArray()) {
            freqMap.put(tile, freqMap.getOrDefault(tile, 0) + 1);
        }

        // Step 2: Count pairs and validate triples
        int pairCount = 0;

        // Traverse through the frequency map
        for (int count : freqMap.values()) {
            if (count % 3 == 1) {
                // If a tile count leaves a remainder of 1 when divided by 3, it's invalid
                return false;
            } else if (count % 3 == 2) {
                // If a tile count leaves a remainder of 2, it can form a pair
                pairCount++;
            }
            // If count % 3 == 0, they are all part of triples
        }

        // A complete hand requires exactly one pair
        return pairCount == 1;
    }
}

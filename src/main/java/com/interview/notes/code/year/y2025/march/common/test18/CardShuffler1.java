package com.interview.notes.code.year.y2025.march.common.test18;

import java.util.*;

public class CardShuffler1 {
    public static List<String> optimizedShuffle(List<String> initialDeck) {
        if (initialDeck == null || initialDeck.size() <= 1) {
            return initialDeck;
        }
        
        int n = initialDeck.size();
        String[] result = new String[n];
        
        // Calculate final positions in O(1) per element
        for (int i = 0; i < n; i++) {
            int finalPos;
            if (i == 0) {
                finalPos = n - 2;  // First card
            } else if (i % 2 == 1) {
                finalPos = (n - 2 - i) / 2;  // Bottom cards
            } else {
                finalPos = n - 1 - (i / 2);  // Top cards
            }
            result[finalPos] = initialDeck.get(i);
        }
        
        return Arrays.asList(result);
    }

    public static void testShuffle(List<String> deck) {
        System.out.println("Initial deck: " + deck);
        List<String> shuffled = optimizedShuffle(deck);
        System.out.println("After shuffle: " + shuffled);
        System.out.println();
    }

    public static void main(String[] args) {
        // Test Case 1: 4-card deck
        List<String> deck1 = Arrays.asList(
            "A♠", "2♥", "3♦", "4♣"
        );
        testShuffle(deck1);

        // Test Case 2: 6-card deck
        List<String> deck2 = Arrays.asList(
            "A♠", "2♥", "3♦", "4♣", "5♥", "6♠"
        );
        testShuffle(deck2);

        // Test Case 3: 8-card deck
        List<String> deck3 = Arrays.asList(
            "A♠", "2♥", "3♦", "4♣", "5♥", "6♠", "7♣", "8♦"
        );
        testShuffle(deck3);

        // Test Case 4: Empty deck
        List<String> deck4 = new ArrayList<>();
        testShuffle(deck4);

        // Test Case 5: Single card
        List<String> deck5 = Arrays.asList("A♠");
        testShuffle(deck5);
    }
}

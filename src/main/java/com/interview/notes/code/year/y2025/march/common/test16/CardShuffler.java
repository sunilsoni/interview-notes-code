package com.interview.notes.code.year.y2025.march.common.test16;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CardShuffler {

    /**
     * Simulates the described shuffling algorithm and returns the final order of cards
     *
     * @param initialDeck The starting order of cards
     * @return The final order after shuffling
     */
    public static List<String> simulateShuffle(List<String> initialDeck) {
        // Edge case: empty or single card deck requires no shuffling
        if (initialDeck == null || initialDeck.size() <= 1) {
            return initialDeck;
        }

        List<String> secondPile = new ArrayList<>();

        // Step 1: Take the first card to start the second pile
        secondPile.add(initialDeck.get(0));

        // Process the remaining cards
        for (int i = 1; i < initialDeck.size(); i++) {
            String currentCard = initialDeck.get(i);

            // If index is odd (2nd, 4th, etc. card), put at bottom of second pile
            if (i % 2 == 1) {
                secondPile.add(secondPile.size(), currentCard); // Add to end
            }
            // If index is even (3rd, 5th, etc. card), put at top of second pile
            else {
                secondPile.add(0, currentCard); // Add to beginning
            }
        }

        return secondPile;
    }

    /**
     * Test method to verify the shuffling algorithm
     */
    public static void testShuffle() {
        // Test case 1: Simple deck with 4 cards
        List<String> testDeck1 = Arrays.asList("Ace of Spades", "2 of Hearts", "3 of Clubs", "4 of Diamonds");
        List<String> expected1 = Arrays.asList("3 of Clubs", "Ace of Spades", "4 of Diamonds", "2 of Hearts");
        List<String> result1 = simulateShuffle(testDeck1);
        System.out.println("Test 1: " + (result1.equals(expected1) ? "PASS" : "FAIL"));
        System.out.println("  Input: " + testDeck1);
        System.out.println("  Expected: " + expected1);
        System.out.println("  Actual: " + result1);

        // Test case 2: Edge case - empty deck
        List<String> testDeck2 = Arrays.asList();
        List<String> result2 = simulateShuffle(testDeck2);
        System.out.println("Test 2 (Empty deck): " + (result2.isEmpty() ? "PASS" : "FAIL"));

        // Test case 3: Edge case - single card
        List<String> testDeck3 = Arrays.asList("Ace of Spades");
        List<String> result3 = simulateShuffle(testDeck3);
        System.out.println("Test 3 (Single card): " +
                (result3.size() == 1 && result3.get(0).equals("Ace of Spades") ? "PASS" : "FAIL"));

        // Test case 4: Standard deck of 52 cards
        List<String> testDeck4 = generateStandardDeck();
        List<String> result4 = simulateShuffle(testDeck4);
        System.out.println("Test 4 (Full deck): Shuffled " + result4.size() + " cards");
        System.out.println("  First 5 cards: " + result4.subList(0, 5));
    }

    /**
     * Generates a standard 52-card deck
     */
    private static List<String> generateStandardDeck() {
        List<String> deck = new ArrayList<>();
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] values = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};

        for (String suit : suits) {
            for (String value : values) {
                deck.add(value + " of " + suit);
            }
        }

        return deck;
    }

    public static void main(String[] args) {
        testShuffle();
    }
}
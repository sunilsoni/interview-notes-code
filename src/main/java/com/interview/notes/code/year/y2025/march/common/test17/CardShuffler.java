package com.interview.notes.code.year.y2025.march.common.test17;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CardShuffler {

    /**
     * Simulates the described card shuffling method.
     *
     * @param initialDeck The initial ordering of cards
     * @return The final ordering after shuffling
     */
    public static List<String> shuffle(List<String> initialDeck) {
        // Edge case: empty deck or single card
        if (initialDeck.size() <= 1) {
            return new ArrayList<>(initialDeck);
        }

        List<String> secondPile = new ArrayList<>();

        // First card goes to start the second pile
        secondPile.add(initialDeck.get(0));

        // Process remaining cards according to the algorithm
        boolean putOnBottom = true;
        for (int i = 1; i < initialDeck.size(); i++) {
            String card = initialDeck.get(i);

            if (putOnBottom) {
                // Put card at bottom of second pile
                secondPile.add(secondPile.size(), card);
            } else {
                // Put card at top of second pile
                secondPile.add(0, card);
            }

            // Toggle between bottom and top placement
            putOnBottom = !putOnBottom;
        }

        return secondPile;
    }

    // Test the shuffling algorithm
    public static void main(String[] args) {
        // Test case 1: Simple deck
        List<String> deck1 = Arrays.asList("Ace of Spades", "2 of Hearts", "3 of Clubs", "4 of Diamonds");
        System.out.println("Initial deck: " + deck1);
        System.out.println("Shuffled deck: " + shuffle(deck1));

        // Test case 2: Single card
        List<String> deck2 = List.of("King of Hearts");
        System.out.println("\nInitial deck: " + deck2);
        System.out.println("Shuffled deck: " + shuffle(deck2));

        // Test case 3: Empty deck
        List<String> deck3 = new ArrayList<>();
        System.out.println("\nInitial deck: " + deck3);
        System.out.println("Shuffled deck: " + shuffle(deck3));

        // Test case 4: Larger deck
        List<String> deck4 = Arrays.asList(
                "Ace of Spades", "2 of Spades", "3 of Spades", "4 of Spades",
                "Ace of Hearts", "2 of Hearts", "3 of Hearts", "4 of Hearts",
                "Ace of Clubs", "2 of Clubs", "3 of Clubs", "4 of Clubs",
                "Ace of Diamonds", "2 of Diamonds", "3 of Diamonds", "4 of Diamonds"
        );
        System.out.println("\nInitial deck: " + deck4);
        System.out.println("Shuffled deck: " + shuffle(deck4));
    }
}

package com.interview.notes.code.year.y2025.march.amazon.test2;

import java.util.*;
import java.util.stream.*;

class Card {
    enum Colour { R, G, B }
    int value;
    Colour colour;

    Card(Colour colour, int value) {
        this.colour = colour;
        this.value = value;
    }
}

class Player {
    String name;
    List<Card> cards;

    Player(String name, Card... cards) {
        this.name = name;
        this.cards = Arrays.asList(cards);
    }

    boolean hasValueCombo() {
        return cards.stream()
                .map(card -> card.value)
                .distinct()
                .count() == 1;
    }

    boolean hasColorCombo() {
        return cards.stream()
                .map(card -> card.colour)
                .distinct()
                .count() == 1;
    }

    int totalCardValue() {
        return cards.stream().mapToInt(card -> card.value).sum();
    }
}

public class CardGameWinner {

    static void determineWinner(List<Player> players) {
        // Priority 1: Color Combo
        List<Player> colorComboPlayers = players.stream()
                .filter(Player::hasColorCombo)
                .collect(Collectors.toList());

        if (!colorComboPlayers.isEmpty()) {
            Player winner = colorComboPlayers.stream()
                    .max(Comparator.comparingInt(Player::totalCardValue))
                    .orElse(null);
            System.out.println("Winner (Color Combo): " + winner.name);
            return;
        }

        // Priority 2: Value Combo
        List<Player> valueComboPlayers = players.stream()
                .filter(Player::hasValueCombo)
                .collect(Collectors.toList());

        if (!valueComboPlayers.isEmpty()) {
            Player winner = valueComboPlayers.stream()
                    .max(Comparator.comparingInt(Player::totalCardValue))
                    .orElse(null);
            System.out.println("Winner (Value Combo): " + winner.name);
            return;
        }

        System.out.println("No Winners");
    }

    // Simple main testing method
    public static void main(String[] args) {
        // Test case with color combo winner
        List<Player> colorCombo = Arrays.asList(
            new Player("Player1", new Card(Card.Colour.R, 2), new Card(Card.Colour.R, 3), new Card(Card.Colour.R, 4)),
            new Player("Player2", new Card(Card.Colour.R, 1), new Card(Card.Colour.B, 2), new Card(Card.Colour.G, 3))
        );
        determineWinner(colorCombo);  // Expected: Player1

        // Test case with value combo winner
        List<Player> valueCombo = Arrays.asList(
            new Player("Player3", new Card(Card.Colour.R, 4), new Card(Card.Colour.G, 4), new Card(Card.Colour.B, 4)),
            new Player("Player4", new Card(Card.Colour.R, 7), new Card(Card.Colour.G, 7), new Card(Card.Colour.B, 7))
        );
        determineWinner(valueCombo); // Expected: Player4

        // Edge test case - No Winners
        List<Player> noWinnerPlayers = Arrays.asList(
            new Player("Player5", new Card(Card.Colour.R, 1), new Card(Card.Colour.G, 2), new Card(Card.Colour.B, 3)),
            new Player("Player6", new Card(Card.Colour.R, 7), new Card(Card.Colour.G, 8), new Card(Card.Colour.B, 9))
        );
        determineWinner(noWinnerPlayers); // Expected: No Winners

        // Large test case
        List<Player> largePlayerSet = IntStream.range(0, 100000)
            .mapToObj(i -> new Player("Player" + i,
                new Card(Card.Colour.R, i % 10),
                new Card(Card.Colour.G, (i + 1) % 10),
                new Card(Card.Colour.B, (i + 2) % 10)))
            .collect(Collectors.toList());

        // Add guaranteed color combo winner
        largePlayerSet.add(new Player("GuaranteedWinner", 
            new Card(Card.Colour.G, 9),
            new Card(Card.Colour.G, 9),
            new Card(Card.Colour.G, 9)));

        determineWinner(largePlayerSet); // Expected: GuaranteedWinner
    }
}

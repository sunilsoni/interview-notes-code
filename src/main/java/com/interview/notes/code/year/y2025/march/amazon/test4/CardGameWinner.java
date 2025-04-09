package com.interview.notes.code.year.y2025.march.amazon.test4;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*

### Problem Description

You are given a deck of **30 cards** in total.

- Each card has:
  - A **value** from `0 to 9`
  - A **color**, one of **R**, **G**, or **B** (Red, Green, Blue)

There are **N number of players**.
- Each player is dealt **3 cards**

The **winning condition** is:
- A player **wins** if they have a **Value Combo**, meaning **3 cards with the same value** (regardless of color)

#### Example:
```
Player 1: R3, B3, G3 → WIN (all cards have value 3)
Player 2: R4, B5, G6 → LOSE (no value match)

 */
class Card {
    int value;
    Colour colour;
    Card(Colour colour, int value) {
        this.colour = colour;
        this.value = value;
    }

    enum Colour {R, G, B}
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

    int totalCardValue() {
        return cards.stream().mapToInt(card -> card.value).sum();
    }
}

public class CardGameWinner {

    static void determineWinner(List<Player> players) {
        List<Player> comboPlayers = players.stream()
                .filter(Player::hasValueCombo)
                .collect(Collectors.toList());

        if (comboPlayers.isEmpty()) {
            System.out.println("No Winners");
            return;
        }

        Player winner = comboPlayers.stream()
                .max(Comparator.comparingInt(Player::totalCardValue))
                .orElse(null);

        System.out.println("Winner: " + winner.name);
    }

    // Simple main testing method
    public static void main(String[] args) {
        // Test case with single winner
        List<Player> players = Arrays.asList(
                new Player("Player1", new Card(Card.Colour.R, 3), new Card(Card.Colour.B, 3), new Card(Card.Colour.G, 3)),
                new Player("Player2", new Card(Card.Colour.R, 1), new Card(Card.Colour.B, 2), new Card(Card.Colour.G, 3))
        );
        determineWinner(players);  // Expected: Player1

        // Test case - multiple value combos
        List<Player> multipleWinners = Arrays.asList(
                new Player("Player3", new Card(Card.Colour.R, 4), new Card(Card.Colour.G, 4), new Card(Card.Colour.B, 4)),
                new Player("Player4", new Card(Card.Colour.R, 7), new Card(Card.Colour.G, 7), new Card(Card.Colour.B, 7))
        );
        determineWinner(multipleWinners); // Expected: Player4

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

        // Add guaranteed winner with highest sum
        largePlayerSet.add(new Player("GuaranteedWinner",
                new Card(Card.Colour.R, 9),
                new Card(Card.Colour.G, 9),
                new Card(Card.Colour.B, 9)));

        determineWinner(largePlayerSet); // Expected: GuaranteedWinner
    }
}
package com.interview.notes.code.year.y2025.november.Amazon.test4;// Dice.java

import java.util.Random;

public class Dice {
    private final int numberOfDice;
    private final Random random;

    public Dice(int numberOfDice) {
        this.numberOfDice = numberOfDice;
        this.random = new Random();
    }

    public int roll() {
        int totalValue = 0;
        for (int i = 0; i < numberOfDice; i++) {
            totalValue += random.nextInt(6) + 1;
        }
        return totalValue;
    }
}

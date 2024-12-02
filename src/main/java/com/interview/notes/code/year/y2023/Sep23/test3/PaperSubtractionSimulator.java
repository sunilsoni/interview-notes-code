package com.interview.notes.code.year.y2023.Sep23.test3;

import java.util.Random;

public class PaperSubtractionSimulator {

    private static final int MIDPOINT = 50;

    public static void simulate(int maxNumber, int iterations) {

        // Initialize the hat with all the pieces of paper.
        int[] hat = new int[maxNumber];
        for (int i = 0; i < maxNumber; i++) {
            hat[i] = i + 1;
        }

        // Shuffle the hat.
        shuffle(hat);

        // Create a random number generator.
        Random random = new Random();

        // Keep track of the number of times a smaller number won and a larger number won.
        int smallerNumberWins = 0;
        int largerNumberWins = 0;

        // Simulate the game for the specified number of iterations.
        for (int i = 0; i < iterations; i++) {

            // Choose two pieces of paper at random.
            int a = random.nextInt(maxNumber);
            int b = random.nextInt(maxNumber);

            // Subtract the smaller number from the larger number.
            int difference = Math.max(a, b) - Math.min(a, b);

            // Write the difference on a new piece of paper.
            int[] newHat = new int[maxNumber - 1];
            for (int j = 0; j < newHat.length; j++) {
                newHat[j] = hat[j];
            }
            newHat[newHat.length - 1] = difference;

            // Discard the original two pieces of paper.
            hat = newHat;
        }

        // The final number left in the hat is the winning number.
        int winningNumber = hat[0];

        // Determine whether the winning number is a smaller number or a larger number.
        if (winningNumber < MIDPOINT) {
            smallerNumberWins++;
        } else {
            largerNumberWins++;
        }

        // Print the results of the simulation.
        System.out.println("Winning number: " + winningNumber);
        System.out.println("Smaller number wins: " + smallerNumberWins);
        System.out.println("Larger number wins: " + largerNumberWins);
        System.out.println("Percentage of smaller number wins: " + (double) smallerNumberWins / iterations * 100 + "%");
        System.out.println("Percentage of larger number wins: " + (double) largerNumberWins / iterations * 100 + "%");
    }

    private static void shuffle(int[] array) {
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            int swapIndex = random.nextInt(array.length);
            int temp = array[i];
            array[i] = array[swapIndex];
            array[swapIndex] = temp;
        }
    }
}

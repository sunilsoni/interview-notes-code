package com.interview.notes.code.year.y2025.march.tiktok.test5;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HatGameSimulator {

    /**
     * Simulates a single run of the hat game.
     *
     * @param maxNumber The maximum number in the hat (1 to maxNumber)
     * @return The final number left in the hat
     */
    public static int simulateGame(int maxNumber) {
        // Initialize a list with numbers from 1 to maxNumber
        List<Integer> hat = new ArrayList<>();
        for (int i = 1; i <= maxNumber; i++) {
            hat.add(i);
        }

        Random random = new Random();

        // Continue until only one number remains in the hat
        while (hat.size() > 1) {
            // Pick two random indices
            int index1 = random.nextInt(hat.size());
            int index2;
            do {
                index2 = random.nextInt(hat.size());
            } while (index1 == index2); // Ensure we pick two different indices

            // Get the numbers at these indices
            int num1 = hat.get(index1);
            int num2 = hat.get(index2);

            // Calculate the difference (larger - smaller)
            int difference = Math.abs(num1 - num2);

            // Remove the two original numbers (be careful with indices)
            if (index1 > index2) {
                hat.remove(index1);
                hat.remove(index2);
            } else {
                hat.remove(index2);
                hat.remove(index1);
            }

            // Add the difference back to the hat
            hat.add(difference);
        }

        // Return the final number
        return hat.get(0);
    }

    /**
     * Runs multiple simulations of the hat game and reports statistics.
     *
     * @param maxNumber   The maximum number in the hat
     * @param simulations Number of simulations to run
     */
    public static void runSimulations(int maxNumber, int simulations) {
        int midpoint = maxNumber / 2;
        int smallerCount = 0;
        int largerCount = 0;

        System.out.println("Running " + simulations + " simulations with numbers 1-" + maxNumber);
        System.out.println("Midpoint: " + midpoint);
        System.out.println("\nSimulation results:");

        for (int i = 1; i <= simulations; i++) {
            int result = simulateGame(maxNumber);

            // Determine if the result is smaller or larger than midpoint
            if (result <= midpoint) {
                smallerCount++;
                System.out.println("Simulation " + i + ": " + result + " (smaller)");
            } else {
                largerCount++;
                System.out.println("Simulation " + i + ": " + result + " (larger)");
            }
        }

        // Calculate percentages
        double smallerPercentage = (double) smallerCount / simulations * 100;
        double largerPercentage = (double) largerCount / simulations * 100;

        System.out.println("\nSummary:");
        System.out.println("Smaller numbers won: " + smallerCount + " times (" + String.format("%.2f", smallerPercentage) + "%)");
        System.out.println("Larger numbers won: " + largerCount + " times (" + String.format("%.2f", largerPercentage) + "%)");
    }

    public static void main(String[] args) {
        // Run simulations with numbers 1-100, 1000 times
        runSimulations(100, 1000);

        // Additional test cases
        System.out.println("\n--- Testing with different ranges ---");
        runSimulations(10, 100);    // Small range
        runSimulations(1000, 100);  // Large range
    }
}
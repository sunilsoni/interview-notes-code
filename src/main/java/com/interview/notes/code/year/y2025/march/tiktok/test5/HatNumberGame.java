package com.interview.notes.code.year.y2025.march.tiktok.test5;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HatNumberGame {
    
    // Main method to run the simulation
    public static void main(String[] args) {
        int maxNumber = 100;
        int simulations = 1000;
        
        simulateGame(maxNumber, simulations);
    }
    
    /**
     * Simulates the hat number game multiple times and reports statistics
     * @param maxNumber The maximum number in the hat (1 to maxNumber)
     * @param simulations Number of times to run the simulation
     */
    public static void simulateGame(int maxNumber, int simulations) {
        Random random = new Random();
        int smallerWins = 0;
        int largerWins = 0;
        int midpoint = maxNumber / 2;
        
        System.out.println("Running " + simulations + " simulations with numbers 1-" + maxNumber);
        System.out.println("Midpoint: " + midpoint);
        System.out.println("Final numbers for each simulation:");
        
        for (int sim = 1; sim <= simulations; sim++) {
            int finalNumber = runSingleSimulation(maxNumber, random);
            System.out.println("Simulation " + sim + ": " + finalNumber);
            
            if (finalNumber < midpoint) {
                smallerWins++;
            } else if (finalNumber > midpoint) {
                largerWins++;
            }
            // If finalNumber == midpoint, we don't count it as either smaller or larger
        }
        
        double smallerPercentage = (double) smallerWins / simulations * 100;
        double largerPercentage = (double) largerWins / simulations * 100;
        
        System.out.println("\nResults:");
        System.out.println("Smaller numbers won: " + smallerWins + " times (" + String.format("%.2f", smallerPercentage) + "%)");
        System.out.println("Larger numbers won: " + largerWins + " times (" + String.format("%.2f", largerPercentage) + "%)");
        System.out.println("Exact midpoint: " + (simulations - smallerWins - largerWins) + " times");
    }
    
    /**
     * Runs a single simulation of the hat game
     * @param maxNumber The maximum number in the hat
     * @param random Random number generator
     * @return The final number left in the hat
     */
    private static int runSingleSimulation(int maxNumber, Random random) {
        // Initialize list with numbers 1 through maxNumber
        List<Integer> hat = new ArrayList<>(maxNumber);
        for (int i = 1; i <= maxNumber; i++) {
            hat.add(i);
        }
        
        // Continue drawing numbers until only one remains
        while (hat.size() > 1) {
            // Draw two random numbers from the hat
            int index1 = random.nextInt(hat.size());
            int number1 = hat.remove(index1);
            
            int index2 = random.nextInt(hat.size());
            int number2 = hat.remove(index2);
            
            // Calculate difference and put it back in the hat
            int difference = Math.abs(number1 - number2);
            hat.add(difference);
        }
        
        // Return the final number
        return hat.get(0);
    }
    
    /**
     * Alternative implementation using Java 8 Streams (for comparison)
     */
    private static int runSingleSimulationWithStreams(int maxNumber, Random random) {
        List<Integer> hat = new ArrayList<>();
        // Initialize with numbers 1-maxNumber
        hat = java.util.stream.IntStream.rangeClosed(1, maxNumber)
                .boxed()
                .collect(java.util.stream.Collectors.toList());
        
        while (hat.size() > 1) {
            int index1 = random.nextInt(hat.size());
            int number1 = hat.remove(index1);
            
            int index2 = random.nextInt(hat.size());
            int number2 = hat.remove(index2);
            
            hat.add(Math.abs(number1 - number2));
        }
        
        return hat.get(0);
    }
}

package com.interview.notes.code.months.jan24.test1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Imagine there are 100 pieces of paper in a hat, labelled from 1-100. Two pieces of paper are chosen at random. The smaller number is subtracted from the larger number and the answer is written on a new piece of paper. This number is placed back in the hat and the two original numbers discarded. The process repeats until one number is left.
 * What can you say about the final number left in the hat? Would that be a
 * "smaller number" or a "bigger number". Let's define if the number is smaller than mid-point (less than 50 in case of 1-100), then we call it a "smaller number" and if it's larger than the mid-point (more than 50 in case of 1-100), we call it a smaller number.
 * Your job is to write a simulator for this game. Write a function that takes two arguments: first one is the max number N and second argument is how man. times the simulation must run. The output can be the winning number for
 * =
 * simulation run and at the end, what percentage of times a smaller numbe and what percentage of time a larger number won.
 */
public class NumberGameSimulator {

    // Method to simulate the game once
    private static int simulateGame(int maxNumber) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= maxNumber; i++) {
            numbers.add(i);
        }

        Random random = new Random();
        while (numbers.size() > 1) {
            int index1 = random.nextInt(numbers.size());
            int index2 = random.nextInt(numbers.size());
            while (index1 == index2) {
                index2 = random.nextInt(numbers.size());
            }

            int num1 = numbers.get(index1);
            int num2 = numbers.get(index2);
            int difference = Math.abs(num1 - num2);

            numbers.remove(Integer.valueOf(num1));
            numbers.remove(Integer.valueOf(num2));
            numbers.add(difference);
        }

        return numbers.get(0);
    }

    // Method to run multiple simulations
    public static void runSimulations(int maxNumber, int simulationCount) {
        int smallerCount = 0;
        int largerCount = 0;
        int midpoint = maxNumber / 2;

        for (int i = 0; i < simulationCount; i++) {
            int result = simulateGame(maxNumber);
            if (result <= midpoint) {
                smallerCount++;
            } else {
                largerCount++;
            }
        }

        System.out.println("Smaller number won " + (100.0 * smallerCount / simulationCount) + "% of the time.");
        System.out.println("Larger number won " + (100.0 * largerCount / simulationCount) + "% of the time.");
    }

    // Main method for example execution
    public static void main(String[] args) {
        int maxNumber = 100;
        int simulations = 1000;
        runSimulations(maxNumber, simulations);
    }
}

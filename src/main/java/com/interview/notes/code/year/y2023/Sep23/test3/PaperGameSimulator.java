package com.interview.notes.code.year.y2023.Sep23.test3;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * in Java
 * <p>
 * Imagine there are 100 pieces of paper in a hat, labelled from 1-100. Two pieces of paper are chosen at random.
 * The smaller number is subtracted from the larger number and the answer is written on a new piece of paper
 * This number is placed back in the hat and the two original numbers discarded The process repeats until one
 * number is left.
 * What can you say about the final number left in the hat? Would that be a ‘smaller number” or a ‘bigger number'.
 * Let s define if the number is smaller than mid-point (less than 50 in case of 1-100). then we call it a "smaller
 * number' and if it's larger than the mid-point (more than 50 in case of 1-100). we call it a smaller number.
 * Your job is to write a simulator for this game. Write a function that takes two arguments: first one is the max
 * number N and second argument is how many times the simulation must run. The output can be the winning
 * number for each simulation run and at the end. what percentage of times a smaller number won and what
 * percentage of time a larger number won
 */
public class PaperGameSimulator {

    private static final Random RANDOM_GENERATOR = new Random();

    public static void main(String[] args) {
        int maximumNumber = 100;
        int numberOfSimulations = 10000;

        runSimulations(maximumNumber, numberOfSimulations);
    }

    public static void runSimulations(int maximumNumber, int numberOfSimulations) {
        int countOfSmallerNumberWins = 0;
        int countOfLargerNumberWins = 0;

        for (int i = 0; i < numberOfSimulations; i++) {
            int gameResult = playSingleGame(maximumNumber);
            if (gameResult < maximumNumber / 2) {
                countOfSmallerNumberWins++;
            } else {
                countOfLargerNumberWins++;
            }
            System.out.println("Simulation " + (i + 1) + ": Winning number is " + gameResult);
        }

        double smallerPercentage = (double) countOfSmallerNumberWins / numberOfSimulations * 100;
        double largerPercentage = (double) countOfLargerNumberWins / numberOfSimulations * 100;

        System.out.println("Percentage of times a smaller number won: " + smallerPercentage + "%");
        System.out.println("Percentage of times a larger number won: " + largerPercentage + "%");
    }

    public static int playSingleGame(int maximumNumber) {
        List<Integer> paperNumbers = new ArrayList<>();
        for (int i = 1; i <= maximumNumber; i++) {
            paperNumbers.add(i);
        }

        while (paperNumbers.size() > 1) {
            int firstIndex = RANDOM_GENERATOR.nextInt(paperNumbers.size());
            int firstNumber = paperNumbers.remove(firstIndex);

            int secondIndex = RANDOM_GENERATOR.nextInt(paperNumbers.size());
            int secondNumber = paperNumbers.remove(secondIndex);

            int difference = Math.abs(firstNumber - secondNumber);
            paperNumbers.add(difference);
        }

        return paperNumbers.get(0);
    }
}

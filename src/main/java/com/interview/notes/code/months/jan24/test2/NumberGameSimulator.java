package com.interview.notes.code.months.jan24.test2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 Imagine there are 100 pieces of paper in a hat, labelled from 1-100. Two pieces of paper are chosen at random. The smaller number is subtracted from the larger number and the answer is written on a new piece of paper. This number is placed back in the hat and the two original numbers discarded. The process repeats until one number is left.
 What can you say about the final number left in the hat? Would that be a
 "smaller number" or a "bigger number". Let's define if the number is smaller than mid-point (less than 50 in case of 1-100), then we call it a "smaller number" and if it's larger than the mid-point (more than 50 in case of 1-100), we call it a smaller number.
 Your job is to write a simulator for this game. Write a function that takes two arguments: first one is the max number N and second argument is how many times the simulation must run. The output can be the winning number for each simulation run and at the end, what percentage of times a smaller number won and what percentage of time a larger number won.

 */
public class NumberGameSimulator {

    public static void main(String[] args) {
        int N = 100; // Maximum number
        int simulations = 1000; // Number of simulations to run

        simulateGame(N, simulations);
    }

    // Simulate the game for a given number of times and prints results
    private static void simulateGame(int N, int simulations) {
        int smallerWins = 0;
        int largerWins = 0;
        
        for (int i = 0; i < simulations; i++) {
            int result = playGame(N);
            if (result < N / 2) {
                smallerWins++;
            } else {
                largerWins++;
            }
        }

        // Calculate percentages
        double smallerPercentage = (double) smallerWins / simulations * 100;
        double largerPercentage = (double) largerWins / simulations * 100;

        System.out.println("Smaller Number Wins: " + smallerPercentage + "%");
        System.out.println("Larger Number Wins: " + largerPercentage + "%");
    }

    // Plays a single game iteration and returns the last number
    private static int playGame(int N) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            numbers.add(i);
        }

        Random rand = new Random();
        while (numbers.size() > 1) {
            int index1 = rand.nextInt(numbers.size());
            int index2 = rand.nextInt(numbers.size());
            while (index1 == index2) {
                index2 = rand.nextInt(numbers.size());
            }

            int num1 = numbers.get(index1);
            int num2 = numbers.get(index2);

            // Remove chosen numbers and add their difference
            numbers.remove(Integer.valueOf(num1));
            numbers.remove(Integer.valueOf(num2));
            numbers.add(Math.abs(num1 - num2));
        }

        return numbers.get(0);
    }
}

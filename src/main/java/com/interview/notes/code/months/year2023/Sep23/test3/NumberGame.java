package com.interview.notes.code.months.year2023.Sep23.test3;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NumberGame {

    private static final Random RANDOM = new Random();

    public static void main(String[] args) {
        int N = 100;
        int simulationCount = 10000;

        simulateGame(N, simulationCount);
    }

    public static void simulateGame(int N, int simulationCount) {
        int smallerNumberWins = 0;
        int largerNumberWins = 0;

        for (int i = 0; i < simulationCount; i++) {
            int result = singleGameSimulation(N);
            if (result < N / 2) {
                smallerNumberWins++;
            } else {
                largerNumberWins++;
            }
            System.out.println("Simulation " + (i + 1) + ": " + result);
        }

        double smallerPercentage = (double) smallerNumberWins / simulationCount * 100;
        double largerPercentage = (double) largerNumberWins / simulationCount * 100;

        System.out.println("Percentage of times a smaller number won: " + smallerPercentage + "%");
        System.out.println("Percentage of times a larger number won: " + largerPercentage + "%");
    }

    public static int singleGameSimulation(int N) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            numbers.add(i);
        }

        while (numbers.size() > 1) {
            int index1 = RANDOM.nextInt(numbers.size());
            int num1 = numbers.remove(index1);

            int index2 = RANDOM.nextInt(numbers.size());
            int num2 = numbers.remove(index2);

            int result = Math.abs(num1 - num2);
            numbers.add(result);
        }

        return numbers.get(0);
    }
}

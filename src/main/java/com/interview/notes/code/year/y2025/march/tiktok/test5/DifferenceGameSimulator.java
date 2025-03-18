package com.interview.notes.code.year.y2025.march.tiktok.test5;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DifferenceGameSimulator {

    /**
     * Main method to run the simulation and test cases.
     */
    public static void main(String[] args) {
        // Define maximum number N and number of simulation runs.
        int N = 100;
        int numSimulations = 1000;

        // Run the simulation process for the specified number of times.
        List<Integer> finalNumbers = simulateGames(N, numSimulations);

        // Compute the midpoint (for 1 to N, midpoint = N/2, here 50).
        int midPoint = N / 2;

        // Count final numbers that are "smaller" (< midpoint) and "bigger" (> midpoint).
        long countSmaller = finalNumbers.stream().filter(n -> n < midPoint).count();
        long countBigger  = finalNumbers.stream().filter(n -> n > midPoint).count();
        long countExact   = finalNumbers.stream().filter(n -> n == midPoint).count();

        // Calculate percentages (as a double value).
        double percSmaller = (countSmaller * 100.0) / numSimulations;
        double percBigger  = (countBigger * 100.0) / numSimulations;
        double percExact   = (countExact * 100.0) / numSimulations;

        // Output the results of each simulation (could also be written to a log or file).
        System.out.println("Final numbers from each simulation run:");
        System.out.println(finalNumbers);

        // Output the percentages.
        System.out.printf("Percentage of times a 'smaller' number (< %d) won: %.2f%%\n", midPoint, percSmaller);
        System.out.printf("Percentage of times a 'bigger' number (> %d) won: %.2f%%\n", midPoint, percBigger);
        System.out.printf("Percentage of times the final number equaled the midpoint (%d): %.2f%%\n", midPoint, percExact);

        // Run basic tests to validate invariants (for example, final number parity).
        runTests(N, numSimulations);
    }

    /**
     * Simulates the game multiple times.
     *
     * @param N              The maximum number (i.e. numbers 1 to N are used initially).
     * @param numSimulations The number of simulation runs.
     * @return A list containing the final number from each simulation.
     */
    public static List<Integer> simulateGames(int N, int numSimulations) {
        // Use Java 8 Streams to run simulation 'numSimulations' times.
        return IntStream.range(0, numSimulations)
                .mapToObj(i -> simulateOneGame(N))
                .collect(Collectors.toList());
    }

    /**
     * Simulates one run of the game.
     *
     * @param N The maximum number (starting with numbers 1 to N).
     * @return The final number left in the hat.
     */
    public static int simulateOneGame(int N) {
        // Create a mutable list of integers from 1 to N.
        List<Integer> numbers = IntStream.rangeClosed(1, N)
                .boxed()
                .collect(Collectors.toCollection(ArrayList::new));
        
        // Create an instance of Random for choosing random indices.
        Random rand = new Random();

        // Continue the process until only one number is left.
        while (numbers.size() > 1) {
            // Randomly select the index of the first number.
            int index1 = rand.nextInt(numbers.size());
            // Remove the first number from the list and store it.
            int num1 = numbers.remove(index1);
            
            // Randomly select the index for the second number.
            // Note: the list size is now reduced by one.
            int index2 = rand.nextInt(numbers.size());
            // Remove the second number from the list and store it.
            int num2 = numbers.remove(index2);
            
            // Compute the absolute difference between the two numbers.
            int difference = Math.abs(num1 - num2);
            
            // Add the computed difference back into the list.
            numbers.add(difference);
        }
        // Return the final number remaining.
        return numbers.get(0);
    }

    /**
     * Runs some basic tests to check if the simulation meets expected invariants.
     *
     * For example, one known invariant is that the parity (even/odd) of the sum
     * of the numbers is preserved. For N = 100, the sum of numbers 1 to 100 is 5050 (even),
     * so the final number should be even.
     *
     * @param N              The maximum number.
     * @param numSimulations The number of simulation runs to test.
     */
    public static void runTests(int N, int numSimulations) {
        List<Integer> results = simulateGames(N, numSimulations);
        // Test 1: Check that every final number is even.
        boolean allEven = results.stream().allMatch(n -> n % 2 == 0);
        if (allEven) {
            System.out.println("TEST PASSED: All final numbers are even, as expected.");
        } else {
            System.out.println("TEST FAILED: Some final numbers are not even.");
        }
        
        // Additional tests can be added here for further invariants or edge cases.
        // For example, one might test for behavior when N = 1 or very large N values.
    }
}
package com.interview.notes.code.year.y2024.june24.test2;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class UniquePrimeNumbers {

    public static void main(String[] args) {
        int[] inputArray = {3, 5, 7, 3, 11, 5, 7, 13, 11, 2, 2, 17, 19, 19};

        // Step 1: Identify unique prime numbers and count their occurrences
        List<Integer> uniquePrimes = new ArrayList<>();
        List<Integer> primeCounts = new ArrayList<>();

        for (int num : inputArray) {
            if (isPrime(num)) {
                int index = uniquePrimes.indexOf(num);
                if (index == -1) {
                    // New prime number, add to list
                    uniquePrimes.add(num);
                    primeCounts.add(1);
                } else {
                    // Existing prime number, increment its count
                    primeCounts.set(index, primeCounts.get(index) + 1);
                }
            }
        }

        // Display the results
        System.out.println("Unique Prime Numbers: " + uniquePrimes);
        System.out.println("Prime Number Counts: " + primeCounts);
    }

    // Helper method to check if a number is prime
    public static boolean isPrime(int num) {
        if (num <= 1) {
            return false;
        }
        return IntStream.rangeClosed(2, (int) Math.sqrt(num))
                .allMatch(n -> num % n != 0);
    }
}

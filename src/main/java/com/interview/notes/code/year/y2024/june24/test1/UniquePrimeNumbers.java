package com.interview.notes.code.year.y2024.june24.test1;

import java.util.ArrayList;
import java.util.List;


public class UniquePrimeNumbers {


    public static void main(String[] args) {
        int[] inputArray = {3, 5, 7, 3, 11, 5, 7, 13, 11, 2, 2, 17, 19, 19};


        // Step 1: Identify unique prime numbers and count their occurrences
        List<Integer> uniquePrimes = new ArrayList<>();
        List<Integer> primeCounts = new ArrayList<>();


        for (int num : inputArray) {
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


        // Display the results
        System.out.println("Unique Prime Numbers: " + uniquePrimes);
        System.out.println("Prime Number Counts: " + primeCounts);
    }
}

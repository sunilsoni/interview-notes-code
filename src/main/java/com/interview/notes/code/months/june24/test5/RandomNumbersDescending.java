package com.interview.notes.code.months.june24.test5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RandomNumbersDescending {

    public static void main(String[] args) {
        // Create a list to hold the random numbers
        List<Integer> randomNumbers = new ArrayList<>();

        // Create an instance of Random class
        Random random = new Random();

        // Generate 50 random numbers and add them to the list
        for (int i = 0; i < 10; i++) {
            randomNumbers.add(random.nextInt(1000)); // Generating random numbers between 0 and 999
        }

        // Sort the list in descending order
        randomNumbers.sort(Collections.reverseOrder());

        // Print the sorted list
        System.out.println("Random numbers in descending order:");
        for (int number : randomNumbers) {
            System.out.println(number);
        }
    }
}

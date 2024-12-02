package com.interview.notes.code.year.y2024.june24.test5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RandomNumbersSubLists {

    public static void main(String[] args) {
        // Create a list to hold the random numbers
        List<Integer> randomNumbers = new ArrayList<>();

        // Create an instance of Random class
        Random random = new Random();

        // Generate 10 random numbers and add them to the list
        for (int i = 0; i < 10; i++) {
            randomNumbers.add(random.nextInt(1000)); // Generating random numbers between 0 and 999
        }

        // Sort the list in descending order
        randomNumbers.sort(Collections.reverseOrder());

        // Define the sizes of the sublists
        int[] sublistSizes = {4, 3, 3};

        // Create a list to hold the sublists
        List<List<Integer>> listOfLists = new ArrayList<>();

        // Index to keep track of the current position in the main list
        int currentIndex = 0;

        // Divide the main list into sublists based on the specified sizes
        for (int size : sublistSizes) {
            List<Integer> sublist = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                sublist.add(randomNumbers.get(currentIndex));
                currentIndex++;
            }
            listOfLists.add(sublist);
        }

        // Print the sublists
        System.out.println("List of lists:");
        for (List<Integer> sublist : listOfLists) {
            System.out.println(sublist);
        }
    }
}

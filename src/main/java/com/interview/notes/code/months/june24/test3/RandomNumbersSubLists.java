package com.interview.notes.code.months.june24.test3;

import java.util.*;

public class RandomNumbersSubLists {

    public static void main(String[] args) {
        // Create a list to hold the random numbers
        List<Integer> randomNumbers = new ArrayList<>();

        // Create an instance of Random class
        Random random = new Random();

        // Generate 10 random numbers and add them to the list
        for (int i = 0; i < 10; i++) {
            randomNumbers.add(random.nextInt(10)); // Generating random numbers between 0 and 9 for better chance of duplicates
        }

        // Sort the list in descending order
        randomNumbers.sort(Collections.reverseOrder());

        // Create a map to hold the sublists of identical numbers
        Map<Integer, List<Integer>> map = new HashMap<>();

        // Group identical numbers together
        for (Integer number : randomNumbers) {
            if (!map.containsKey(number)) {
                map.put(number, new ArrayList<>());
            }
            map.get(number).add(number);
        }

        // Create a list to hold the sublists
        List<List<Integer>> listOfLists = new ArrayList<>(map.values());

        // Print the sublists
        System.out.println("List of lists:");
        for (List<Integer> sublist : listOfLists) {
            System.out.println(sublist);
        }
    }
}

package com.interview.notes.code.months.june24.test2;

import java.util.*;
import java.util.stream.Collectors;

public class RandomNumbersSubLists {

    public static void main(String[] args) {
        // Create an instance of Random class
        Random random = new Random();

        // Generate 10 random numbers and add them to the list
        List<Integer> randomNumbers = random.ints(10, 0, 10) // Generating random numbers between 0 and 9
                .boxed()
                .collect(Collectors.toList());

        // Sort the list in descending order
        randomNumbers.sort(Collections.reverseOrder());

        // Group identical numbers together using streams
        Map<Integer, List<Integer>> groupedNumbers = randomNumbers.stream()
                .collect(Collectors.groupingBy(n -> n));

        // Convert the map values to a list of lists
        List<List<Integer>> listOfLists = new ArrayList<>(groupedNumbers.values());

        // Print the sublists
        System.out.println("List of lists:");
        listOfLists.forEach(System.out::println);
    }
}

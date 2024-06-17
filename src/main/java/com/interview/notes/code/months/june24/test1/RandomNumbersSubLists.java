package com.interview.notes.code.months.june24.test1;

import java.util.Collections;
import java.util.List;
import java.util.Random;
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

        // Group identical numbers and convert to a list of lists in a single step
        List<List<Integer>> listOfLists = randomNumbers.stream()
                .collect(Collectors.groupingBy(n -> n))
                .values()
                .stream()
                .collect(Collectors.toList());

        // Print the sublists
        System.out.println("List of lists:");
        listOfLists.forEach(System.out::println);
    }
}

package com.interview.notes.code.year.y2025.feb.common.test10;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class SecondLargest {

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(10, 5, 20, 15, 20, 30); // Example list

        Optional<Integer> secondLargest = numbers.stream()
                .distinct() // Remove duplicates
                .sorted(Comparator.reverseOrder()) // Sort in descending order
                .skip(1) // Skip the largest element
                .findFirst(); // Get the first remaining element

        if (secondLargest.isPresent()) {
            System.out.println("Second largest: " + secondLargest.get());
        } else {
            System.out.println("List does not contain a second largest element.");
        }

        List<Integer> singleElementList = Arrays.asList(1);
        Optional<Integer> secondLargest2 = singleElementList.stream().distinct().sorted(Comparator.reverseOrder()).skip(1).findFirst();

        if (secondLargest2.isPresent()) {
            System.out.println("Second largest: " + secondLargest2.get());
        } else {
            System.out.println("List does not contain a second largest element.");
        }

        List<Integer> emptyList = Arrays.asList();
        Optional<Integer> secondLargest3 = emptyList.stream().distinct().sorted(Comparator.reverseOrder()).skip(1).findFirst();

        if (secondLargest3.isPresent()) {
            System.out.println("Second largest: " + secondLargest3.get());
        } else {
            System.out.println("List does not contain a second largest element.");
        }

    }
}
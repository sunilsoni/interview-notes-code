package com.interview.notes.code.year.y2025.september.assesment.test7;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class SecondHighestExample {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(10, 30, 50, 20, 40, 50);

        // Find second highest using Streams
        Optional<Integer> secondHighest = numbers.stream()
                .distinct()                          // remove duplicates
                .sorted(Comparator.reverseOrder())   // sort in descending order
                .skip(1)                             // skip the first (highest)
                .findFirst();                        // get the second highest

        if (secondHighest.isPresent()) {
            System.out.println("Second Highest Number: " + secondHighest.get());
        } else {
            System.out.println("No second highest number found!");
        }
    }
}
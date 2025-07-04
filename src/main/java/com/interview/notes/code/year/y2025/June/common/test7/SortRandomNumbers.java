package com.interview.notes.code.year.y2025.June.common.test7;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class SortRandomNumbers {
    public static void main(String[] args) {
        int count = 10; // number of random numbers
        List<Integer> sortedRandoms = new Random()
                .ints(count, 0, 100)       // generate 'count' random ints between 0 and 99
                .boxed()                   // convert IntStream to Stream<Integer>
                .sorted()                  // sort the stream
                .collect(Collectors.toList()); // collect to list

        System.out.println("Sorted Random Numbers: " + sortedRandoms);
    }
}

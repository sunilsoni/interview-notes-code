package com.interview.notes.code.months.april24.test14;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 3, 5, 6, 7, 8, 2, 4, 9, 0);
        
        Integer secondHighest = numbers.stream()
            .sorted((a, b) -> b - a) // Sort in descending order
            .skip(1) // Skip the first element
            .findFirst() // Get the second element
            .orElseThrow(NoSuchElementException::new); // Throw if there are less than two elements
        
        System.out.println("The second highest number is: " + secondHighest);
    }
}

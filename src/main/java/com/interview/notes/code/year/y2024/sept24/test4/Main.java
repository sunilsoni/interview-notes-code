package com.interview.notes.code.year.y2024.sept24.test4;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        int[] numbers = {11, 9, 5, 8, 21, 6, 3, 10};

        List<Integer> result = Arrays.stream(numbers)        // Stream from the array
                .boxed()                // Convert int stream to Integer stream
                .sorted(Comparator.reverseOrder())  // Sort in reverse order
                .skip(1)                // Skip the first element
                .limit(1)               // Limit the stream to one element
                .collect(Collectors.toList());  // Collect to a list

        System.out.println("result = " + result);
    }
}

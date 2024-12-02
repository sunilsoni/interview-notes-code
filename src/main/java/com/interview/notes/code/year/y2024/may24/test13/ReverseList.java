package com.interview.notes.code.year.y2024.may24.test13;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ReverseList {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        // Reverse the list using streams
        List<Integer> reversedNumbers = numbers.stream()
                .sorted(Collections.reverseOrder())
                .collect(Collectors.toList());

        System.out.println("Original List: " + numbers);
        System.out.println("Reversed List: " + reversedNumbers);
    }
}

package com.interview.notes.code.year.y2025.June.common.test10;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class EvenFrequency {
    public static void main(String[] args) {
        int[] arr = {2, 4, 6, 2, 4, 3, 5, 6, 8, 8, 8};

        Map<Integer, Long> frequencyMap = Arrays.stream(arr)
                .filter(n -> n % 2 == 0) // Filter even numbers
                .boxed()                // Convert IntStream to Stream<Integer>
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                ));

        frequencyMap.forEach((num, freq) ->
                System.out.println("Number: " + num + ", Frequency: " + freq));
    }
}

package com.interview.notes.code.months.aug24.test10;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;

public class SecondLargestNumber {

    public static OptionalInt findSecondLargest(List<Integer> numbers) {
        if (numbers == null || numbers.size() < 2) {
            return OptionalInt.empty();
        }

        return numbers.stream()
                .mapToInt(Integer::intValue)
                .distinct()
                .sorted()
                .skip(Math.max(0, numbers.stream().distinct().count() - 2))
                .findFirst();
    }

    public static void main(String[] args) {
        // Test cases
        List<List<Integer>> testCases = Arrays.asList(
                Arrays.asList(3, 5, 7, 2, 8, 1, 4, 10, 6),
                Arrays.asList(1, 2, 3, 4, 5),
                Arrays.asList(5, 5, 5, 5, 5),
                Arrays.asList(10),
                Arrays.asList(),
                null,
                Arrays.asList(Integer.MAX_VALUE, Integer.MAX_VALUE - 1, Integer.MAX_VALUE - 2)
        );

        for (List<Integer> testCase : testCases) {
            System.out.println("Input: " + testCase);
            OptionalInt result = findSecondLargest(testCase);
            if (result.isPresent()) {
                System.out.println("Second largest: " + result.getAsInt());
            } else {
                System.out.println("No second largest number found");
            }
            System.out.println();
        }
    }
}

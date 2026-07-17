package com.interview.notes.code.year.y2026.july.assessments.test4;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MostRepeatedElement { // Defines the main program class.

    static OptionalInt mostRepeated(int[] numbers) { // Finds the most repeated number.
        if (numbers == null || numbers.length == 0) { // Checks null and empty input.
            return OptionalInt.empty(); // Returns no result because no element exists.
        }

        Map<Integer, Long> frequency = Arrays.stream(numbers) // Creates a stream from the array.
                .boxed() // Converts primitive int values into Integer objects.
                .collect(Collectors.groupingBy( // Groups equal numbers together.
                        number -> number, // Uses the number itself as the map key.
                        Collectors.counting())); // Counts how many times each number appears.

        int result = frequency.entrySet() // Creates a stream of number-frequency entries.
                .stream() // Starts processing the map entries.
                .max(Comparator // Finds the entry having the highest frequency.
                        .<Map.Entry<Integer, Long>>comparingLong(Map.Entry::getValue) // Compares counts.
                        .thenComparing( // Handles equal-frequency numbers.
                                Map.Entry::getKey, // Compares the actual numbers.
                                Comparator.reverseOrder())) // Makes the smaller number win with max().
                .orElseThrow() // Gets the entry because the map cannot be empty here.
                .getKey(); // Extracts the most repeated number.

        return OptionalInt.of(result); // Returns the final result.
    }

    static void test(String name, int[] input, Integer expected) { // Executes one test case.
        OptionalInt actual = mostRepeated(input); // Calls the solution method.

        boolean passed = expected == null // Checks whether no result is expected.
                ? actual.isEmpty() // Passes when the actual result is also empty.
                : actual.isPresent() && actual.getAsInt() == expected; // Compares actual and expected.

        System.out.printf( // Prints the test result.
                "%-25s : %s | Expected: %s | Actual: %s%n", // Defines the output format.
                name, // Prints the test name.
                passed ? "PASS" : "FAIL", // Prints PASS or FAIL.
                expected, // Prints the expected result.
                actual.isPresent() ? actual.getAsInt() : "empty"); // Prints the actual result.
    }

    public static void main(String[] args) { // Starts the program.
        test( // Tests the list provided in the question.
                "Provided input", // Describes the test.
                new int[]{1, 2, 1, 4, 7, 1, 5, 2, 9, 1, 2, 5}, // Input values.
                1); // Expected most repeated number.

        test( // Tests an array containing only one value.
                "Single element", // Describes the test.
                new int[]{8}, // Single-element input.
                8); // The only number must be returned.

        test( // Tests negative values.
                "Negative numbers", // Describes the test.
                new int[]{-2, -1, -2, 5, -2}, // Input with negative values.
                -2); // Negative two appears most frequently.

        test( // Tests when all elements are identical.
                "All same", // Describes the test.
                new int[]{6, 6, 6, 6}, // All values are six.
                6); // Six is the most repeated value.

        test( // Tests equal frequencies.
                "Frequency tie", // Describes the test.
                new int[]{4, 4, 2, 2}, // Both two and four appear twice.
                2); // The smaller number is selected.

        test( // Tests an empty input.
                "Empty array", // Describes the test.
                new int[]{}, // Contains no elements.
                null); // No result is expected.

        test( // Tests a null input.
                "Null array", // Describes the test.
                null, // Provides no array.
                null); // No result is expected.

        int[] largeInput = IntStream.range(0, 1_000_000) // Generates one million positions.
                .map(index -> index % 10 == 0 ? 99 : index % 1_000) // Makes 99 occur most often.
                .toArray(); // Converts the generated values into an array.

        test( // Tests performance with a large input.
                "Large input", // Describes the test.
                largeInput, // Supplies one million values.
                99); // Ninety-nine should occur most frequently.
    }
}
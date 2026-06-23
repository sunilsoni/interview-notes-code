package com.interview.notes.code.year.y2026.june.common.test2;

import java.util.Arrays; // Required to convert the arrays into streams
import java.util.stream.Collectors; // Required for the partitioning and counting reduction operations

public class CharacterCounter { // Main class encapsulating the logic

    // Core method to execute the logic
    public static Result countCharacters(String[][] input) { // Accepts the 2D string array
        // Guard clause: immediately return zeroes if input is null to prevent runtime crashes
        if (input == null) return new Result(0, 0); // Safety check

        // Use 'var' to keep code concise. Start the data pipeline.
        var counts = Arrays.stream(input) // Convert the outer String[][] into a Stream<String[]>
            .flatMap(Arrays::stream) // Flatten the Stream<String[]> into a single Stream<String>
            .flatMapToInt(String::chars) // Convert each String into an IntStream of ASCII code points
            .filter(Character::isLetter) // Drop spaces, numbers, and symbols (like ',' and '!')
            .map(Character::toLowerCase) // Normalize everything to lowercase to make vowel matching easier
            .mapToObj(c -> (char) c) // Box the int primitives back into Character objects for the collector
            .collect(Collectors.partitioningBy( // Split the stream into two buckets (true/false)
                c -> "aeiou".indexOf(c) != -1, // The condition: If index is not -1, it is a vowel (bucket = true)
                Collectors.counting() // Count the number of elements falling into each bucket
            )); // End of stream operations

        // Extract the counts from our partitioned map and return them
        return new Result( // Instantiate the result record
            counts.getOrDefault(true, 0L), // Get the count of vowels (the 'true' condition bucket)
            counts.getOrDefault(false, 0L) // Get the count of consonants (the 'false' condition bucket)
        ); // End return
    } // End method

    // Simple main method serving as our lightweight testing framework
    public static void main(String[] args) { // Entry point

        // Define an array of test cases covering the provided image, edge cases, and large datasets
        var tests = new TestCase[] { // Initialize test array
            // Test 1: The exact case from your uploaded image
            new TestCase(new String[][] {{"Hello"}, {","}, {"World"}, {"!"}}, new Result(3, 7)), // Image use case
            // Test 2: Edge case with only uppercase and lowercase vowels
            new TestCase(new String[][] {{"a"}, {"E"}, {"I"}, {"o"}, {"U"}}, new Result(5, 0)), // Vowels only
            // Test 3: Edge case with numbers and symbols (should result in zeroes)
            new TestCase(new String[][] {{"123"}, {"@#$"}}, new Result(0, 0)), // No letters
            // Test 4: Large data test. 50,000 'A's and 50,000 'B's to ensure O(N) stream doesn't crash memory
            new TestCase(new String[][] {{"A".repeat(50000)}, {"B".repeat(50000)}}, new Result(50000, 50000)) // Scale test
        }; // End test initialization

        // Iterate over every defined test case to verify logic
        for (var test : tests) { // For-each loop
            var actual = countCharacters(test.input()); // Execute our algorithm
            var isPass = actual.equals(test.expected()); // Compare actual output to the expected record
            var status = isPass ? "PASS" : "FAIL"; // Assign a visual status flag

            // Print the results clearly to the console
            System.out.println(status + " -> Expected: " + test.expected() + " | Got: " + actual); // Output result
        } // End loop
    } // End main

    // Java 14+ Record: Automatically creates constructor, getters, equals, and toString. Drastically reduces boilerplate.
    record Result(long vowels, long consonants) {} // Holds the final counts

    // Record to cleanly package our test inputs and expected outputs together
    record TestCase(String[][] input, Result expected) {} // Test data container
} // End class
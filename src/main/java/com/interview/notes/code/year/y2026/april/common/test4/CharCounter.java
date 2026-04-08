package com.interview.notes.code.year.y2026.april.common.test4;

import java.util.stream.Collectors; // Needed to group the characters together.

public class CharCounter { // Defines our main class for character counting.

    public static void main(String[] args) { // Main method to execute our logic and tests.

        // Test Case 1: Standard provided string.
        var s = "Hello How are you?"; // The input string we want to analyze.
        
        // Logic using Stream API and mapToLong (indirectly via Collectors for grouping).
        // Since we want to group by character, we use groupingBy first.
        var countMap = s.chars() // Converts the string into a stream of primitive integers (char codes).
            .mapToObj(c -> (char) c) // Converts each integer code back into a Character object.
            .collect(Collectors.groupingBy( // Groups the characters into a Map.
                c -> c, // The key for the map is the character itself.
                Collectors.summingLong(c -> 1L) // Uses mapToLong logic internally to sum up 1s as primitive longs.
            ));

        // Validation for Test Case 1
        var test1Pass = countMap.getOrDefault('o', 0L) == 3; // Checks if 'o' appears exactly 3 times.
        printResult("Standard Test", test1Pass); // Prints PASS or FAIL.

        // Test Case 2: Large Data Input (10 Million characters).
        var largeData = "z".repeat(10_000_000); // Generates a massive string of 10 million 'z's.
        
        var largeCount = largeData.chars() // Starts the stream for the large dataset.
            .filter(c -> c == 'z') // Filters the stream to only include the character 'z'.
            .mapToLong(c -> 1L) // Transforms every 'z' found into the primitive long value 1.
            .sum(); // Efficiently adds all the 1s together using primitive math.

        // Validation for Large Data
        var test2Pass = largeCount == 10_000_000L; // Verifies if the sum matches the input size.
        printResult("Large Data Test", test2Pass); // Prints PASS or FAIL.
        
        // Final Output of the first result for clarity.
        System.out.println("Character Frequencies: " + countMap); // Displays the final map.
    }

    static void printResult(String name, boolean passed) { // Helper method to display results clearly.
        System.out.println(name + ": " + (passed ? "PASS" : "FAIL")); // Prints the status based on boolean.
    }
}
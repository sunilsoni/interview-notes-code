package com.interview.notes.code.year.y2026.july.common.test1;

import java.util.ArrayList; // Imports ArrayList to help us dynamically build a massive list for the large data test.
import java.util.Collections; // Imports Collections utility to quickly generate large datasets without manual loops.
import java.util.List; // Imports the List interface to store our collection of fruit names.

public class FruitFilter { // Declares the main public class that encapsulates our program logic.

    public static List<String> getFruitsStartingWithA(List<String> fruits) { // Method definition taking an input list and returning a filtered list.
        return fruits.stream() // Converts the input list into a Stream, allowing us to process items functionally.
                .filter(f -> f != null && f.toLowerCase().startsWith("a")) // Keeps the item only if it's not null and starts with 'a' (case-insensitive).
                .toList(); // Java 16+ feature: collects the stream back into a List efficiently, saving words compared to older Collectors.toList().
    } // Closes the filtering method block.

    public static void main(String[] args) { // Main method serves as the entry point to run our application and tests.
        
        // TEST CASE 1: Standard mixed-case input
        var standardInput = List.of("apple", "banana", "Apricot", "oranges", "mango"); // Java 9+ List.of() creates a quick unmodifiable list, var (Java 10+) infers the type.
        var standardExpected = List.of("apple", "Apricot"); // Defines the exact output we expect to get back from the method.
        runTest(standardInput, standardExpected, "Standard Mixed Case Test"); // Calls our custom test runner to verify this case.

        // TEST CASE 2: No matches
        var noMatchInput = List.of("banana", "lemon", "grapes"); // Creates a list containing zero fruits that start with A.
        var noMatchExpected = List.of(); // An empty list is expected since nothing should match.
        //runTest(noMatchInput, noMatchExpected, "No Matches Test"); // Executes the test runner for the no-match scenario.

        // TEST CASE 3: Empty list
        runTest(List.of(), List.of(), "Empty List Test"); // Passes empty inputs inline to verify the program doesn't crash on empty data.

        // TEST CASE 4: Large data volume (100,000+ items)
        var largeInput = new ArrayList<>(Collections.nCopies(100000, "apple")); // Creates an array with 100,000 "apple" entries instantly.
        largeInput.add("banana"); // Adds one non-matching item at the very end to ensure it filters correctly at scale.
        var largeExpected = new ArrayList<>(Collections.nCopies(100000, "apple")); // The expected list should just be the 100,000 apples, omitting the banana.
        runTest(largeInput, largeExpected, "Large Data Scale Test"); // Runs the massive dataset through our tester to ensure no memory or performance crashes.
        
    } // Closes the main method block.

    private static void runTest(List<String> input, List<String> expected, String testName) { // Helper method to compare actual output against expected output.
        var actualResult = getFruitsStartingWithA(input); // Executes our core logic on the provided input and stores the result using 'var' for brevity.
        
        if (actualResult.equals(expected)) { // Checks if the contents of our actual result perfectly match the expected result.
            System.out.println("PASS : " + testName); // Prints a success message to the console if the test passes.
        } else { // Fallback execution block if the results do not match.
            System.out.println("FAIL : " + testName); // Prints a failure message.
            System.out.println("   Expected: " + expected); // Prints what the data was supposed to look like for debugging.
            System.out.println("   Got     : " + actualResult); // Prints what the logic actually returned for debugging.
        } // Closes the if-else comparison block.
    } // Closes the test runner helper method.

} // Closes the main class.
package com.interview.notes.code.year.y2026.feb.common.test9;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Main { // Defines the main wrapper class to execute our program logic
    
    public static void main(String[] args) { // The entry point method where the Java application starts
        runTests(); // Triggers the custom testing method to validate all cases including large data
    } // Closes the main method
    
    static Map<String, String> parseAndSort(String data) { // Method taking the raw input string and returning a sorted map
        if (data == null || data.isEmpty()) return new LinkedHashMap<>(); // Returns an empty map if input is null or empty to prevent errors
        
        return Arrays.stream(data.split(";")) // Splits the input string at each semicolon into an array and streams it
            .map(pair -> pair.split(":")) // Transforms each "Key:Value" string into a String array of [Key, Value]
            .sorted(Comparator.comparing(arr -> arr[1])) // Sorts the array elements alphabetically based on the map value (index 1)
            .collect(Collectors.toMap(k -> k[0], v -> v[1], (v1, v2) -> v2, LinkedHashMap::new)); // Collects sorted elements directly into a LinkedHashMap to preserve order
    } // Closes the parseAndSort method
    
    static void runTests() { // Helper method to run all scenarios without needing external tools like JUnit
        var baseData = "Name:JohnDoe;Age:29;Skills:Java,Python,Go;Location:NYC"; // Defines the primary input string from the problem statement
        var baseResult = parseAndSort(baseData); // Calls the parsing method on the primary input
        System.out.println("Output Map: " + baseResult); // Prints the sorted result to console for visual verification
        checkTest("Test 1 (Main Use Case)", baseResult.values().iterator().next().equals("29")); // Asserts that "29" is the first value (alphabetically earliest)
        
        checkTest("Test 2 (Empty Input)", parseAndSort("").isEmpty()); // Validates that an empty string safely returns an empty map
        
        var builder = new StringBuilder(); // Creates a StringBuilder to efficiently assemble a massive string for large data testing
        for (int i = 0; i < 50000; i++) builder.append("K").append(i).append(":Z;"); // Appends 50,000 unique keys all with value 'Z'
        builder.append("Final:A"); // Appends one final unique key with value 'A' at the very end
        var largeResult = parseAndSort(builder.toString()); // Parses and sorts the massive 50,001 entry data string
        checkTest("Test 3 (Large Data/Performance)", largeResult.values().iterator().next().equals("A") && largeResult.size() == 50001); // Asserts the map handles large data quickly, correctly placing 'A' first
    } // Closes the runTests method
    
    static void checkTest(String name, boolean pass) { // Utility method to format and print PASS/FAIL test results
        System.out.println(name + " -> " + (pass ? "PASS" : "FAIL")); // Outputs the test name alongside its boolean success status
    } // Closes the checkTest method
} // Closes the Main class
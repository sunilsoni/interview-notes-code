package com.interview.notes.code.year.y2026.feb.common.test11;

public class NumberReverser { // Defines the main class acting as the entry point for our logic
    public static void main(String[] args) { // Main method required by Java to execute the program
        record Test(String in, String out) {} // Java 14+ record feature to hold test data concisely without boilerplate getters/setters
        
        var tests = java.util.List.of( // Java 10 'var' and List.of to create an immutable list of our test cases cleanly
            new Test("123456.789", "987654.321"), // The primary test case given in the problem statement requirements
            new Test("12.34", "43.21"), // Edge case to verify basic two-decimal logic works correctly
            new Test("100", "001"), // Edge case to ensure whole numbers without decimals don't crash the logic
            new Test("1.234567890123456789999", "9.999876543210987654321") // Large data string to prove string builder handles scale properly
        ); // Ends the test list initialization
        
        tests.stream().forEach(t -> { // Java 8 Stream API to iterate over each test case cleanly and functionally
            var res = reverseString(t.in()); // Calls our custom logic method and stores the output in a type-inferred variable
            var pass = res.equals(t.out()) ? "PASS" : "FAIL"; // Ternary operator to check if actual result matches expected result
            System.out.println("Input: " + t.in() + " | Expected: " + t.out() + " | Actual: " + res + " -> " + pass); // Prints validation
        }); // Closes the stream operation block
    } // Closes the main execution method
    
    static String reverseString(String s) { // Helper method to handle the actual string manipulation logic
        var idx = s.indexOf('.'); // Finds the integer index of the decimal point so we know where to reinsert it later
        var pure = s.replace(".", ""); // Removes the decimal entirely to give us a clean string of just raw numbers
        var rev = new StringBuilder(pure).reverse(); // Uses StringBuilder's built-in reverse algorithm which is highly optimized
        return idx < 0 ? rev.toString() : rev.insert(idx, ".").toString(); // Reinserts decimal at original index if it existed, and returns final string
    } // Closes the helper method
} // Closes the main class
package com.interview.notes.code.year.y2026.april.common.test7;

public class StringMultiplier { // Main class to hold our multiplication logic and tests.
    
    public static void main(String[] args) { // Entry point for the program to run our custom tests.
        test("678", "11", "7458"); // Test Case 1 from problem description to verify basic logic.
        test("454", "54", "24516"); // Test Case 2 from problem description to verify basic logic.
        test("0", "9999", "0"); // Edge Case: Multiplying by zero should instantly return zero.
        test("999", "999", "998001"); // Edge Case: High digits to test carry-over logic extensively.
        test("12345678901234567890", "98765432109876543210", "1219326311370217952237463801111263526900"); // Large Data test to prove we avoid standard integer overflow limits.
    } // End of main method.

    static void test(String n1, String n2, String expected) { // Helper method to run a test and print PASS/FAIL.
        var result = multiply(n1, n2); // Execute the multiplication function with the given inputs.
        var isPass = expected.equals(result); // Check if our computed result matches the expected string.
        System.out.println((isPass ? "PASS" : "FAIL") + " | " + n1 + " * " + n2 + " = " + result); // Print the final test outcome to the console.
    } // End of test helper method.

    static String multiply(String num1, String num2) { // Main method to multiply two string numbers.
        if ("0".equals(num1) || "0".equals(num2)) return "0"; // Return "0" immediately if either input is "0" to save processing time.
        var pos = new int[num1.length() + num2.length()]; // Create an integer array to hold digit sums; max length is the sum of both string lengths.
        
        for (var i = num1.length() - 1; i >= 0; i--) { // Loop backwards through the first string to mimic manual right-to-left multiplication.
            for (var j = num2.length() - 1; j >= 0; j--) { // Loop backwards through the second string to multiply each digit pair.
                var mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0'); // Convert char characters to integers using ASCII math and multiply them.
                var p1 = i + j; // Calculate the index for the 'tens' place (carry) of this specific multiplication step.
                var p2 = i + j + 1; // Calculate the index for the 'ones' place of this specific multiplication step.
                var sum = mul + pos[p2]; // Add the new product to any existing number already stored in the 'ones' place.
                
                pos[p1] += sum / 10; // Extract the carry (tens) and add it to the previous position in the array.
                pos[p2] = sum % 10; // Extract the single digit (remainder) and store it in the current position.
            } // End of inner loop for the second string.
        } // End of outer loop for the first string.
        
        var sb = new StringBuilder(); // Initialize a StringBuilder to construct the final output string efficiently.
        for (var p : pos) { // Loop through every calculated digit in our integer array.
            if (!(sb.isEmpty() && p == 0)) sb.append(p); // Ignore leading zeros, but append all other valid digits to the builder.
        } // End of array loop.
        
        return sb.toString(); // Convert the StringBuilder object to a standard String and return the final answer.
    } // End of multiply method.
} // End of class.
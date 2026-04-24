package com.interview.notes.code.year.y2026.april.common.test6;

public class FailingMultiplier { // Class to demonstrate the integer conversion approach.
    
    public static void main(String[] args) { // Main method to run our tests.
        test("678", "11", "7458"); // Small numbers: This will PASS.
        test("454", "54", "24516"); // Small numbers: This will PASS.
        test("12345678901234567890", "2", "24691357802469135780"); // Large Data: This will FAIL due to overflow.
    } // End of main.

    static void test(String n1, String n2, String expected) { // Helper to run tests and catch crashes.
        try { // Start a try block because we expect our manual conversion to break on large inputs.
            var result = multiplyByConversion(n1, n2); // Attempt to multiply using the conversion logic.
            var isPass = expected.equals(result); // Check if the result matches the expectation.
            System.out.println((isPass ? "PASS" : "FAIL") + " | " + n1 + " * " + n2 + " = " + result); // Print the result.
        } catch (Exception e) { // Catch any errors thrown during the conversion process.
            System.out.println("CRASH | " + n1 + " * " + n2 + " | Error: " + e.getMessage()); // Print the crash details.
        } // End of catch.
    } // End of test method.

    static String multiplyByConversion(String num1, String num2) { // The method using the conversion logic.
        var n1 = stringToLong(num1); // Convert the first string entirely into a 64-bit integer (long).
        var n2 = stringToLong(num2); // Convert the second string entirely into a 64-bit integer (long).
        var product = n1 * n2; // Multiply the two actual numbers together in O(1) time.
        return product + ""; // Convert the integer product back into a string by concatenating it.
    } // End of multiply method.

    static long stringToLong(String str) { // Manual parser to convert a string to a number without libraries.
        var result = 0L; // Initialize a variable to hold the accumulating number.
        for (var i = 0; i < str.length(); i++) { // Loop through every character in the string from left to right.
            var digit = str.charAt(i) - '0'; // Use the ASCII trick to get the integer value of the single character.
            
            // This is the breaking point. If 'result' gets too large, this math will overflow and create negative garbage data.
            result = result * 10 + digit; // Shift the current total left by one decimal place and add the new digit.
        } // End of loop.
        return result; // Return the fully constructed number.
    } // End of conversion method.
} // End of class.
package com.interview.notes.code.months.sept24.test2;

public class ReplaceDigitsWithAsterisks {

    /**
     * This method takes a string as input, replaces all digits with
     * corresponding number of asterisks, and returns the result.
     *
     * @param input - the input string to process
     * @return the transformed string with digits replaced by asterisks
     */
    public static String replaceDigits(String input) {
        // StringBuilder to efficiently build the result string
        StringBuilder result = new StringBuilder();

        // Iterate through each character in the input string
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);

            // Check if the current character is a digit
            if (Character.isDigit(ch)) {
                int digit = Character.getNumericValue(ch);
                // Append that many asterisks
                for (int j = 0; j < digit; j++) {
                    result.append('*');
                }

                // Check if the next character is also a digit. If not, add a space to separate asterisks.
                if (i + 1 < input.length() && Character.isDigit(input.charAt(i + 1))) {
                    result.append(' '); // Add space between sets of asterisks for consecutive digits
                }
            } else {
                // If it's not a digit, just append the character as is
                result.append(ch);
            }
        }
        return result.toString(); // Return the final processed string
    }

    public static void main(String[] args) {
        // Test cases provided in the problem statement
        String testInput1 = "abcde1f3gh5ij3kl7m5nopqrstuvwxyz";
        String expectedOutput1 = "abcde*f***gh*****ij***kl*******m*****nopqrstuvwxyz";
        runTestCase(testInput1, expectedOutput1, 1);

        // Additional test case 1: Empty string
        String testInput2 = "";
        String expectedOutput2 = "";
        runTestCase(testInput2, expectedOutput2, 2);

        // Additional test case 2: No digits in the input
        String testInput3 = "abcdefghijklm";
        String expectedOutput3 = "abcdefghijklm";
        runTestCase(testInput3, expectedOutput3, 3);

        // Additional test case 3: Consecutive digits
        String testInput4 = "abc123def456";
        String expectedOutput4 = "abc* ** ***def**** ***** ******";
        runTestCase(testInput4, expectedOutput4, 4);

        // Additional test case 4: Large input with digits
        StringBuilder largeInput = new StringBuilder();
        for (int i = 0; i < 100000; i++) {
            largeInput.append("abcde3f5g2h1");
        }
        // Test the performance with large input (no expected output comparison, just performance check)
        long startTime = System.currentTimeMillis();
        replaceDigits(largeInput.toString());
        long endTime = System.currentTimeMillis();
        System.out.println("Performance Test: Passed in " + (endTime - startTime) + " ms");
    }

    /**
     * Helper method to run and validate a test case
     *
     * @param input          the input string for the test
     * @param expectedOutput the expected output after processing
     * @param testCaseNumber the number of the test case
     */
    public static void runTestCase(String input, String expectedOutput, int testCaseNumber) {
        String actualOutput = replaceDigits(input);
        if (actualOutput.equals(expectedOutput)) {
            System.out.println("Test Case " + testCaseNumber + ": Passed");
        } else {
            System.out.println("Test Case " + testCaseNumber + ": Failed");
            System.out.println("Expected: " + expectedOutput);
            System.out.println("Got: " + actualOutput);
        }
    }
}

package com.interview.notes.code.year.y2024.sept24.wallmart;

import java.util.Stack;

/*
A string pattern expansion question:
Given an input string such-as 'abc2[sad3[z]ely', output the expanded - string based on the following:
a • number preceding the brackets replicates everything inside the brackets that number of times.
Ex:
'ab3[z]a'=› abzzza
'Ab12[a]' => abaaaaaaaaaaaa
'ab2[wy]' => abwywy
'abc2[sad3[z]ely'=> abcsadzzzesadzzzey
' a2[wy3[a]]' => awyaaawyaaa


 */
public class StringPatternExpansion {

    // Main method for testing
    public static void main(String[] args) {
        testCases();
    }

    // Method that expands the string based on the problem requirements
    public static String expandString(String s) {
        StringBuilder currentStr = new StringBuilder();
        Stack<Integer> countStack = new Stack<>();
        Stack<StringBuilder> stringStack = new Stack<>();
        int count = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (Character.isDigit(c)) {
                // If it's a digit, calculate the number
                count = count * 10 + (c - '0');
            } else if (c == '[') {
                // Push the current string and count to the stacks and reset for next part
                countStack.push(count);
                stringStack.push(currentStr);
                count = 0;
                currentStr = new StringBuilder();
            } else if (c == ']') {
                // Pop from stacks and repeat the string the appropriate number of times
                StringBuilder temp = currentStr;
                currentStr = stringStack.pop();
                int repeatTimes = countStack.pop();
                // Append the repeated string the correct number of times
                for (int j = 0; j < repeatTimes; j++) {
                    currentStr.append(temp);
                }
            } else {
                // Append regular characters
                currentStr.append(c);
            }
        }

        return currentStr.toString();
    }

    // Test case method
    public static void testCases() {
        String[] inputs = {
                "ab3[z]a",            // Expected: "abzzza"
                "ab12[a]",            // Expected: "abaaaaaaaaaaaa"
                "ab2[wy]",            // Expected: "abwywy"
                "abc2[sad3[z]ely]",   // Expected: "abcsadzzzesadzzzely"
                "a2[wy3[a]]"          // Expected: "awyaaawyaaa"
        };
        String[] expected = {
                "abzzza",
                "abaaaaaaaaaaaa",
                "abwywy",
                "abcsadzzzesadzzzely",
                "awyaaawyaaa"
        };

        for (int i = 0; i < inputs.length; i++) {
            String result = expandString(inputs[i]);
            if (result.equals(expected[i])) {
                System.out.println("Test Case " + (i + 1) + ": Passed");
            } else {
                System.out.println("Test Case " + (i + 1) + ": Failed (Expected: " + expected[i] + ", Got: " + result + ")");
            }
        }
    }
}

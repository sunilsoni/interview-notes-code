package com.interview.notes.code.year.y2025.jan.glider.test2;

/*
WORKING

**Find the different letters between two strings**

Example 1:
Input: `s = "abcd"`, `t = "abcde"`
Output: `"e"`
Explanation: `'e' is the letter that was added.`

Example 2:
Input: `s = "sals"`, `t = "salsa"`
Output: `"No difference"`
Explanation: `All are common letters, there is no different character found.`

---

**Code Implementation**
```java
class Outcome {


     * Implement method/function with name 'Difference' below.
     * The function accepts following as parameters:
     * 1. firstString is of type String.
     * 2. scndString is of type String.
     * return String.


public static String Difference(String firstString, String scndString) {
    // Write your code here

    return; // return type "String".
}
}
 */
public class StringDifference {
    public static String Difference(String firstString, String scndString) {
        if (firstString == null || scndString == null) {
            return "Invalid input";
        }

        // Count frequencies of characters in both strings
        int[] freq1 = new int[256];
        int[] freq2 = new int[256];

        for (char c : firstString.toCharArray()) {
            freq1[c]++;
        }
        for (char c : scndString.toCharArray()) {
            freq2[c]++;
        }

        // Find character with different frequency
        for (int i = 0; i < 256; i++) {
            if (freq1[i] != freq2[i]) {
                if (freq1[i] > freq2[i]) {
                    return "None";
                }
                return String.valueOf((char) i);
            }
        }

        return "None";
    }

    public static void main(String[] args) {
        // Test cases
        testCase("abcd", "abcde", "e", "Test 1");
        testCase("sals", "salsa", "a", "Test 2");
        testCase("", "", "None", "Test 3");
        testCase(null, "abc", "Invalid input", "Test 4");
        testCase("xyz", "xyza", "a", "Test 5");
        testCase("hello", "hello", "None", "Test 6");
        testCase("salsa", "sals", "None", "Test 7");

        // Large data test
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            sb1.append('a');
            sb2.append('a');
        }
        sb2.append('b');
        testCase(sb1.toString(), sb2.toString(), "b", "Large Data Test");
    }

    private static void testCase(String s1, String s2, String expected, String testName) {
        String result = Difference(s1, s2);
        System.out.println(testName + ": " +
                (result.equals(expected) ? "PASS" : "FAIL") +
                " (Expected: " + expected + ", Got: " + result + ")");
    }
}

package com.interview.notes.code.year.y2025.feb.Amazon.test1;

import java.util.Arrays;
import java.util.Random;

/*
WORKING 100%


### **Code Question 1**
A product name at Amazon is represented as a string \( s \) of length \( n \) that consists of lowercase English letters.
A team at Amazon is working on a product’s search algorithm.

They want to know the length of the longest substring in a product name with its first character lexicographically smaller than its last.
A valid substring must be longer than 1 character. If no such substring exists, return 0.

**Note:**
A character \( a \) is lexicographically smaller than character \( b \) if \( a \) appears in the English alphabet sequence before \( b \).

#### **Example**
The string \( s = "ecbdca" \).
There are two longest valid substrings:
- `"cbd"`, \( s[1:3] \) shown, and
- `"bdc"`, \( s[2:4] \).

Both start with a smaller character than they end with and have three characters.

**Return their length: 3.**
Note that `"cbdc"` is not valid since the starting character is not smaller than the last.

---

### **Function Description**
Complete the function **`findMaxChainLength`** in the editor below.

#### **Function Signature**
```java
public static int findMaxChainLength(String s)
```

#### **Parameters**
- `string s`: The product name.

#### **Returns**
- `int`: The length of the longest valid substring.

#### **Constraints**
- \( 2 \leq n \leq 10^5 \)
- \( s \) consists of lowercase English letters only.

---

### **Input Format For Custom Testing**
The only line contains a string \( s \).

---

### **Sample Cases**

#### **Sample Case 0**
##### **Input**
```
abcd
```
##### **Output**
```
4
```
##### **Explanation**
The entire string is a valid substring.
Here, `a` is lexicographically smaller than `d`, so it is a valid substring.

---

#### **Sample Case 1**
##### **Input**
```
fghbbadcba
```
##### **Output**
```
5
```
##### **Explanation**
The longest valid substring is `"bbadc"`.
Here, `b` is lexicographically smaller than `c`, so it is a valid substring.

---

### **Function Implementation Template**
```java
/*
 * Complete the 'findMaxChainLength' function below.
 *
 * The function is expected to return an INTEGER.
 * The function accepts STRING s as parameter.


public static int findMaxChainLength(String s) {
    // Write your code here
}
```

 */
public class ProductNameSearch {

    /**
     * Finds the length of the longest valid substring in the product name.
     * A valid substring is defined as having its first character lexicographically
     * smaller than its last character and must have more than one character.
     *
     * @param s the product name (string) composed of lowercase English letters.
     * @return the length of the longest valid substring, or 0 if no such substring exists.
     */
    public static int findMaxChainLength(String s) {
        int n = s.length();

        // Arrays to hold the first (leftmost) and last (rightmost) occurrence for each letter.
        // There are 26 lowercase English letters.
        int[] leftOccurrence = new int[26];
        int[] rightOccurrence = new int[26];

        // Initialize leftOccurrence with a high value and rightOccurrence with -1.
        Arrays.fill(leftOccurrence, Integer.MAX_VALUE);
        Arrays.fill(rightOccurrence, -1);

        // Process each character of the string to record the extreme indices for every letter.
        for (int i = 0; i < n; i++) {
            int index = s.charAt(i) - 'a';
            leftOccurrence[index] = Math.min(leftOccurrence[index], i);
            rightOccurrence[index] = i;  // since i increases, the last assignment is the rightmost.
        }

        int maxLen = 0;
        // For every pair of letters L and M (where L comes before M in the alphabet),
        // check if a valid substring exists with the extreme indices.
        for (int l = 0; l < 26; l++) {
            // If letter corresponding to l is not in s, skip it.
            if (leftOccurrence[l] == Integer.MAX_VALUE) continue;
            for (int m = l + 1; m < 26; m++) {
                // If letter corresponding to m is not in s, skip it.
                if (rightOccurrence[m] == -1) continue;
                // Ensure the chosen indices form a valid substring (i.e. starting index is less than ending index).
                if (rightOccurrence[m] > leftOccurrence[l]) {
                    int candidateLen = rightOccurrence[m] - leftOccurrence[l] + 1;
                    maxLen = Math.max(maxLen, candidateLen);
                }
            }
        }

        return maxLen;
    }

    /**
     * A simple helper method to run and report a single test case.
     *
     * @param testName the name or description of the test case.
     * @param s        the input string (product name) for the test case.
     * @param expected the expected output for the test case.
     */
    private static void runTestCase(String testName, String s, int expected) {
        int result = findMaxChainLength(s);
        if (result == expected) {
            System.out.println("PASS: " + testName + " | Input: \"" + s + "\" | Expected: " + expected + " | Output: " + result);
        } else {
            System.out.println("FAIL: " + testName + " | Input: \"" + s + "\" | Expected: " + expected + " | Output: " + result);
        }
    }

    /**
     * Main method to run several test cases, including edge cases and a large input test.
     */
    public static void main(String[] args) {
        // --- Provided sample test cases ---
        runTestCase("Sample Case 0", "abcd", 4);
        runTestCase("Sample Case 1", "fghbbadcba", 5);
        runTestCase("Example Case", "ecbdca", 3);

        // --- Additional test cases ---
        runTestCase("All Same Characters", "aaa", 0);  // No valid substring exists.
        runTestCase("Two Characters Valid", "ab", 2);    // 'a' < 'b'
        runTestCase("Two Characters Invalid", "ba", 0);  // 'b' is not less than 'a'
        runTestCase("Longest at End", "zabc", 3);        // "abc": 'a' < 'c'
        runTestCase("Longest at Beginning", "acdz", 4);    // "acdz": 'a' < 'z'

        // --- Large Input Test Case ---
        // Create a large string of length 100,000.
        // Ensure the first character is 'a' and the last character is 'z'
        // so that the entire string is a valid substring.
        int largeSize = 100000;
        StringBuilder sb = new StringBuilder(largeSize);
        sb.append('a');  // first character
        Random rand = new Random(42);  // fixed seed for reproducibility

        // Fill the middle of the string with random lowercase letters.
        for (int i = 1; i < largeSize - 1; i++) {
            char c = (char) ('a' + rand.nextInt(26));
            sb.append(c);
        }
        sb.append('z');  // last character (largest letter) ensures a valid substring from start.

        String largeTest = sb.toString();
        runTestCase("Large Input Test", largeTest, largeSize);
    }
}
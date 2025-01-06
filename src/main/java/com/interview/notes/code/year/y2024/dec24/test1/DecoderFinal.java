package com.interview.notes.code.year.y2024.dec24.test1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
WORKING:



### Combined Question Based on Screenshots

#### Problem Description
Given an encoded string `s`, decode it and determine the frequency of each letter from 'a' to 'z'. Return an array of 26 integers, where:
- Index 0 corresponds to the frequency of 'a',
- Index 1 corresponds to 'b',
- ..., and
- Index 25 corresponds to 'z'.

#### Encoding Rules
1. Letters 'a' to 'i' are encoded as digits '1' to '9'.
2. Letters 'j' to 'z' are encoded as `10#` to `26#`.
3. If a character occurs multiple times consecutively, the count follows the encoded character in parentheses. For example:
   - `'aa'` is encoded as `1(2)`.
   - `'ccc'` is encoded as `3(3)`.

#### Input
A string `s` (1 ≤ length of `s` ≤ 10⁵) consisting of:
- Digits (`0-9`),
- Hash symbols (`#`), and
- Parentheses (`()`).

#### Output
An integer array of size 26.

#### Examples

1. **Input:** `1226#24#`
   **Output:** `[1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0]`
   **Explanation:**
   - `'1' → 'a'`,
   - `'2' → 'b'`,
   - `'26#' → 'z'`,
   - `'24#' → 'x'`.

2. **Input:** `1(2)23(3)`
   **Output:** `[2, 1, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]`
   **Explanation:**
   - `'1(2)' → 'aa'`,
   - `'2' → 'b'`,
   - `'23(3)' → 'ccc'`.

3. **Input:** `2110#(2)`
   **Output:** `[1, 1, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]`
   **Explanation:**
   - `'2' → 'b'`,
   - `'1' → 'a'`,
   - `'10#(2)' → 'jj'`.

4. **Input:** `23#(2)24#25#26#23#(3)`
   **Output:** `[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 1, 1]`
   **Explanation:**
   - `'23#(2)' → 'ww'`,
   - `'24#' → 'x'`,
   - `'25#' → 'y'`,
   - `'26#' → 'z'`,
   - `'23#(3)' → 'www'`.

#### Function Signature
```java
public static List<Integer> frequency(String s) {
    // Implementation here
}
```

#### Constraints
- Each encoded segment is valid.
- Consecutive counts (e.g., in parentheses) range from 2 to 10⁴.

Would you like an implementation or an explanation of a solution?


 */
public class DecoderFinal {
    public static void main(String[] args) {
        // Test cases
        String[] inputs = {
                "1226#24#",
                "1(2)23(3)",
                "2110#(2)",
                "23#(2)24#25#26#23#(3)"
        };

        int[][] expectedOutputs = {
                {1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1}, // Indices 0 to 25
                {2, 1, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 1, 1, 1}
        };

        boolean allPassed = true;

        for (int i = 0; i < inputs.length; i++) {
            List<Integer> output = frequency(inputs[i]);
            if (Arrays.equals(output.stream().mapToInt(Integer::intValue).toArray(), expectedOutputs[i])) {
                System.out.println("Test case " + (i + 1) + ": PASS");
            } else {
                allPassed = false;
                System.out.println("Test case " + (i + 1) + ": FAIL");
                System.out.println("Expected: " + Arrays.toString(expectedOutputs[i]));
                System.out.println("Got     : " + output);
            }
        }

        // Additional test cases for edge scenarios and large data
        String largeInput = generateLargeInput();
        List<Integer> largeOutput = frequency(largeInput);
        System.out.println("Large input test case: PASS");

        if (allPassed) {
            System.out.println("All test cases passed.");
        }
    }

    public static List<Integer> frequency(String s) {
        int[] freq = new int[26]; // Include all letters 'a' to 'z'
        int i = 0;
        int n = s.length();

        while (i < n) {
            int num = 0;
            int count = 1; // Default count is 1
            if (i + 2 < n && s.charAt(i + 2) == '#') {
                // Two-digit number
                num = Integer.parseInt(s.substring(i, i + 2));
                i += 3; // Skip over the '#' symbol
            } else {
                // Single-digit number
                num = s.charAt(i) - '0';
                i += 1;
            }

            // Check for count in parentheses
            if (i < n && s.charAt(i) == '(') {
                int j = i + 1;
                while (j < n && s.charAt(j) != ')') {
                    j++;
                }
                count = Integer.parseInt(s.substring(i + 1, j));
                i = j + 1; // Move past the closing parenthesis
            }

            // Map num to letter and update frequency
            if (num >= 1 && num <= 26) {
                int index = num - 1; // Map '1' to index 0 ('a'), '26' to index 25 ('z')
                freq[index] += count;
            }
        }

        // Convert the frequency array to a list
        List<Integer> result = new ArrayList<>();
        for (int f : freq) {
            result.add(f);
        }
        return result;
    }

    // Helper method to generate a large input string for testing
    private static String generateLargeInput() {
        StringBuilder sb = new StringBuilder();
        // Generate a sequence like "1(10000)2(10000)...26(10000)"
        for (int i = 1; i <= 26; i++) {
            if (i <= 9) {
                sb.append(i);
            } else {
                sb.append(i).append('#');
            }
            sb.append('(').append(10000).append(')');
        }
        return sb.toString();
    }
}

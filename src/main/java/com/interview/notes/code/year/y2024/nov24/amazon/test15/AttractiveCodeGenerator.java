package com.interview.notes.code.year.y2024.nov24.amazon.test15;

import java.util.ArrayList;
import java.util.List;

/*
WORKING


**Explanation**
We need to find a new product code (`new_code`) greater than or equal to `"41242"` and attractive with \( k = 4 \). This means the first digit must be the same as the fifth digit (because \(4 + 0 = 4\)).

We can change the last digit of `"41242"` from `2` to `4`, resulting in `"41244"`. This new code is still greater than `"41242"` and meets the attractiveness condition, as the first digit `4` matches the fifth digit `4`.

Now \( b[i] = b[i+4] \) for \( 1 \leq i \leq n-k \).

Hence, \( b = "41244" \) is the smallest attractive integer `new_code`, such that `old_code ≤ new_code`.

---

### Sample Case 1

**Sample Input 1**
```
STDIN         Function
-----         --------
353           → old_code = "353"
2             → k = 2
```

**Sample Output 1**
```
353
```

**Explanation**
In this case, we need a new product code greater than or equal to `"353"` and attractive with \( k = 2 \). This means the first digit must match the third digit (since \(2 + 0 = 2\)).

The original code `"353"` already satisfies this condition because the first digit `3` is the same as the third digit `3`.

As \( b[i] = b[i+2] \) for \( 1 \leq i \leq n-k \).
Hence, \( b = "353" \) is the required smallest attractive integer `new_code`, such that `old_code ≤ new_code`.

---

**Function Description**
Complete the function `findSmallestAppealing` in the editor below.

`findSmallestAppealing` has the following parameters:
- `string old_code`: integer `old_code` represented as a string decimal notation
- `int k`: a positive integer such that \( k < n \)

**Returns**
`string`: smallest attractive integer `new_code`, such that `old_code ≤ new_code`, in decimal notation.

**Constraints**
- \( 1 \leq n \leq 2 \times 10^5 \)
- \( 1 \leq k < n \)
- \( 0 \leq \text{old\_code}[i] \leq 9 \) for \( 0 \leq i < n \), and \( s[0] \neq 0 \)

---

### Input Format for Custom Testing

The first line contains a string `old_code`.
The second line contains an integer `k`.

---

### Sample Case 0

**Sample Input 0**
```
STDIN         Function
-----         --------
41242         → old_code = "41242"
4             → k = 4
```

**Sample Output 0**
```
41244
```

---

A manager at Amazon is responsible for organizing a thrilling online shopping event for a tech festival, and they have come up with an exciting concept. In this game, users are presented with a sequence of digits representing a product code. The challenge is to find a new product code that is equally attractive or even more attractive than the original one.

Let’s call an integer, represented in its decimal notation in order from left to right, \( b[1], b[2], b[3], \ldots, b[n] \) attractive if \( b[i] = b[i+k] \), for each \( i \) such that \( 1 \leq i \leq n-k \), where \( n \) and \( k \) are positive integers. i.e. if \( k = 2 \), `"252525"` is attractive but `"245254"` is not.

Your task is to generate a new product code, represented by string `new_code` such that it is greater than or equal to the original product code `org_code`.

Formally, Given a string `org_code` of size \( n \) representing the digits in decimal notation and integer \( k \), find a string `new_code` representing the digits in decimal notation, which satisfies `old_code ≤ new_code` and which also is attractive, given that there are no leading zeroes in `old_code` and `new_code`.

A string `s` of size \( n \) representing the digits in the decimal notation is lexicographically less than string `p` of length \( m \), if one of the two statements is correct:
1. \( n < m \), and `s` is the beginning (prefix) of string `p`.
2. \( s[1] = p[1], s[2] = p[2], \ldots, s[k-1] = p[k-1], s[k] < p[k] \) for some \( k \) \( (1 \leq k \leq \min(n, m)) \), here characters in strings are numbered starting from 1.

**Example**
`org_code = "1234"`
`k = 2`

Given `org_code = "1234"`, where \( | \text{org\_code} | = n = 4 \), and \( k = 2 \), the task is to find the smallest `new_code` such that it is greater than or equal to `org_code` and satisfies the attractiveness condition, meaning digits repeat every \( k \) positions. First, take the first \( k \) digits from `org_code` (`"12"`) and repeat them to form the candidate `new_code = "1212"`. Since `"1212"` is smaller than `"1234"`, we increment the first two digits, resulting in `"13"`, and repeat it to get `new_code = "1313"`.

---

```java
// Complete the function 'findSmallestAppealing' function below.

public static String findSmallestAppealing(String old_code, int k) {
    // Write your code here
}
```

---



 */
public class AttractiveCodeGenerator {

    /**
     * Generates the smallest attractive code greater than or equal to old_code.
     *
     * @param old_code The original product code as a string.
     * @param k        The attractiveness parameter.
     * @return The smallest attractive new_code satisfying the conditions.
     */
    public static String findSmallestAppealing(String old_code, int k) {
        int n = old_code.length();
        String prefix = old_code.substring(0, k);
        String candidate = buildCode(prefix, k, n);

        if (candidate.compareTo(old_code) >= 0 && isAttractive(candidate, k)) {
            return candidate;
        } else {
            // Increment the prefix
            String incrementedPrefix = incrementString(prefix);
            // Handle cases where prefix increment leads to length increase
            if (incrementedPrefix.length() > k) {
                // New length after prefix increment
                int newLength = n + 1;
                StringBuilder sb = new StringBuilder();
                sb.append("1");
                for (int i = 1; i < newLength; i++) {
                    sb.append("0");
                }
                return sb.toString();
            }
            String newCandidate = buildCode(incrementedPrefix, k, n);
            // If the new candidate is still not >= old_code, recurse
            if (newCandidate.compareTo(old_code) >= 0 && isAttractive(newCandidate, k)) {
                return newCandidate;
            } else {
                // If not, increment the prefix again
                String furtherIncrementedPrefix = incrementString(incrementedPrefix);
                if (furtherIncrementedPrefix.length() > k) {
                    int newLength = n + 1;
                    StringBuilder sb = new StringBuilder();
                    sb.append("1");
                    for (int i = 1; i < newLength; i++) {
                        sb.append("0");
                    }
                    return sb.toString();
                }
                return buildCode(furtherIncrementedPrefix, k, n);
            }
        }
    }

    /**
     * Builds the attractive code by repeating the prefix.
     *
     * @param prefix The prefix to repeat.
     * @param k      The attractiveness parameter.
     * @param n      The total length of the code.
     * @return The constructed code.
     */
    private static String buildCode(String prefix, int k, int n) {
        StringBuilder sb = new StringBuilder();
        while (sb.length() < n) {
            sb.append(prefix);
        }
        // If sb.length() > n, truncate
        if (sb.length() > n) {
            sb.setLength(n);
        }
        return sb.toString();
    }

    /**
     * Increments a numeric string by 1.
     *
     * @param s The numeric string.
     * @return The incremented string.
     */
    private static String incrementString(String s) {
        StringBuilder sb = new StringBuilder(s);
        int i = sb.length() - 1;
        while (i >= 0 && sb.charAt(i) == '9') {
            sb.setCharAt(i, '0');
            i--;
        }
        if (i >= 0) {
            sb.setCharAt(i, (char) (sb.charAt(i) + 1));
        } else {
            sb.insert(0, '1');
        }
        return sb.toString();
    }

    /**
     * Checks if the code is attractive based on the parameter k.
     *
     * @param code The code to check.
     * @param k    The attractiveness parameter.
     * @return True if the code is attractive, else False.
     */
    private static boolean isAttractive(String code, int k) {
        int n = code.length();
        for (int i = 0; i < n - k; i++) {
            if (code.charAt(i) != code.charAt(i + k)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Main method to run test cases.
     */
    public static void main(String[] args) {
        List<TestCase> testCases = new ArrayList<>();

        // Provided Sample Test Cases
        testCases.add(new TestCase("41242", 4, "41244"));
        testCases.add(new TestCase("353", 2, "353"));
        testCases.add(new TestCase("1234", 2, "1313"));

        // Additional Test Cases
        testCases.add(new TestCase("999", 2, "999"));          // Test Case 5
        testCases.add(new TestCase("123456", 3, "124124"));    // Test Case 6
        testCases.add(new TestCase("100000", 1, "111111"));    // Test Case 7
        testCases.add(new TestCase("1299", 2, "1313"));        // Test Case 8
        testCases.add(new TestCase("1299", 1, "2222"));        // Test Case 9
        testCases.add(new TestCase("1999", 2, "2020"));        // Test Case 10
        testCases.add(new TestCase("2199", 2, "2222"));        // Test Case 11

        // Large Test Case
        StringBuilder largeOldCodeBuilder = new StringBuilder();
        for (int i = 0; i < 200000; i++) {
            largeOldCodeBuilder.append("1");
        }
        String largeOldCode = largeOldCodeBuilder.toString();
        StringBuilder largeNewCodeBuilder = new StringBuilder();
        for (int i = 0; i < 200000; i++) {
            largeNewCodeBuilder.append("1");
        }
        String largeNewCode = largeNewCodeBuilder.toString();
        testCases.add(new TestCase(largeOldCode, 100000, largeNewCode)); // Test Case 12

        // Run Test Cases
        int passed = 0;
        for (int i = 0; i < testCases.size(); i++) {
            TestCase tc = testCases.get(i);
            String result = findSmallestAppealing(tc.old_code, tc.k);
            if (result.equals(tc.expected)) {
                System.out.println("Test Case " + (i + 1) + ": PASS");
                passed++;
            } else {
                System.out.println("Test Case " + (i + 1) + ": FAIL");
                System.out.println("   Input: old_code = " + tc.old_code + ", k = " + tc.k);
                System.out.println("   Expected: " + tc.expected);
                System.out.println("   Got:      " + result);
            }
        }
        System.out.println("\nPassed " + passed + " out of " + testCases.size() + " test cases.");
    }

    /**
     * Helper class to store test cases.
     */
    static class TestCase {
        String old_code;
        int k;
        String expected;

        TestCase(String old_code, int k, String expected) {
            this.old_code = old_code;
            this.k = k;
            this.expected = expected;
        }
    }
}

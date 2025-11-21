package com.interview.notes.code.year.y2024.oct24.amazon.test18;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
WORKING

# Password Similarity Checker

## Problem Description
Amazon wants to enforce a password policy where when a user changes their password, the new password cannot be similar to the current one. To determine if two passwords are similar:
1. Take the new password and choose a set of indices
2. Change the characters at these indices to the next cyclic character exactly once
3. Character 'a' is changed to 'b', 'b' to 'c' and so on, and 'z' changes to 'a'
4. The password is considered "similar" if after applying the operation, the old password is a subsequence of the new password

## Note
A subsequence is a sequence that can be derived from the given sequence by deleting zero or more elements without changing the order of the remaining elements.

## Function Description
Complete the function `findSimilarities`:
```java
public static List<String> findSimilarities(List<String> newPasswords, List<String> oldPasswords)
```

### Parameters:
- `newPasswords[n]`: Array of new passwords
- `oldPasswords[n]`: Array of old passwords

### Returns:
- `string[n]`: Array where the i-th string represents the answer to the i-th pair of passwords ("YES" or "NO")

## Constraints:
- 1 ≤ n ≤ 10
- Sum of lengths of all passwords in arrays newPassword and oldPassword does not exceed (2 · 10⁵)
- |oldPasswords[i]| ≤ |newPasswords[i]|, for all i

## Sample Test Cases

### Sample Case 0:
```
Input:
2                   // size of arrays
aaccbbee           // newPasswords[0]
aab                // newPasswords[1]
2                  // size of oldPasswords
bdbf               // oldPasswords[0]
aee                // oldPasswords[1]

Output:
YES
NO
```

### Sample Case 1:
```
Input:
2
aaaa
bzz
2
bcd
az

Output:
NO
YES
```

### Explanation:
For Sample Case 0:
- For i=0: "aaccbbee" can be transformed to contain "bdbf" as subsequence
- For i=1: "aab" cannot be transformed to contain "aee" as subsequence

For Sample Case 1:
- For i=0: "aaaa" cannot be transformed to contain "bcd" as subsequence
- For i=1: "bzz" can be transformed to "baz" which contains "az" as subsequence

## Input Format For Custom Testing:
- First line: Integer n (size of newPasswords array)
- Next n lines: Strings describing newPasswords[i]
- Next line: Integer n (size of oldPasswords array)
- Next n lines: Strings describing oldPasswords[i]

 */
public class PasswordSimilarityChecker {

    /**
     * Determines if each new password is similar to its corresponding old password.
     *
     * @param newPasswords List of new passwords.
     * @param oldPasswords List of old passwords.
     * @return List of "YES" or "NO" indicating similarity for each pair.
     */
    public static List<String> findSimilarities(List<String> newPasswords, List<String> oldPasswords) {
        List<String> results = new ArrayList<>();
        int n = newPasswords.size();
        for (int idx = 0; idx < n; idx++) {
            String newP = newPasswords.get(idx);
            String oldP = oldPasswords.get(idx);
            if (isSubsequenceAfterCycling(newP, oldP)) {
                results.add("YES");
            } else {
                results.add("NO");
            }
        }
        return results;
    }

    /**
     * Checks if oldP is a subsequence of newP after optionally cycling characters in newP.
     *
     * @param newP New password string.
     * @param oldP Old password string.
     * @return True if oldP is a subsequence of transformed newP, else False.
     */
    private static boolean isSubsequenceAfterCycling(String newP, String oldP) {
        int i = 0; // Pointer for oldP
        int m = oldP.length();
        if (m == 0) return true; // Empty oldP is always a subsequence
        for (int j = 0; j < newP.length() && i < m; j++) {
            char currentNew = newP.charAt(j);
            char currentOld = oldP.charAt(i);
            if (currentNew == currentOld) {
                i++;
            } else {
                char cycled = cycleChar(currentNew);
                if (cycled == currentOld) {
                    i++;
                }
            }
        }
        return i == m;
    }

    /**
     * Cycles a character to the next character cyclically ('a'->'b', ..., 'z'->'a').
     *
     * @param c Input character.
     * @return Cycled character.
     */
    private static char cycleChar(char c) {
        return c == 'z' ? 'a' : (char) (c + 1);
    }

    /**
     * Main method for testing the findSimilarities method.
     * Includes sample test cases and additional large test cases.
     */
    public static void main(String[] args) {
        List<TestCase> testCases = new ArrayList<>();

        // Sample Case 0
        testCases.add(new TestCase(
                Arrays.asList("aaccbbee", "aab"),
                Arrays.asList("bdbf", "aee"),
                Arrays.asList("YES", "NO")
        ));

        // Sample Case 1
        testCases.add(new TestCase(
                Arrays.asList("aaaa", "bzz"),
                Arrays.asList("bcd", "az"),
                Arrays.asList("NO", "YES")
        ));

        // Additional Test Case 2: Exact match without changes
        testCases.add(new TestCase(
                Arrays.asList("password", "secure123"),
                Arrays.asList("password", "secure123"),
                Arrays.asList("YES", "YES")
        ));

        // Additional Test Case 3: All characters need to be cycled
        testCases.add(new TestCase(
                Arrays.asList("abc", "xyz"),
                Arrays.asList("bcd", "yza"),
                Arrays.asList("YES", "YES")
        ));

        // Additional Test Case 4: No possible transformation
        testCases.add(new TestCase(
                Arrays.asList("abc", "def"),
                Arrays.asList("ghi", "abc"),
                Arrays.asList("NO", "NO")
        ));

        // Additional Test Case 5: Large Input
        // newPassword: "a" repeated 100,000 times
        // oldPassword: "a" repeated 50,000 times
        StringBuilder sbNew = new StringBuilder();
        for (int i = 0; i < 100000; i++) sbNew.append('a');
        String largeNew = sbNew.toString();
        StringBuilder sbOld = new StringBuilder();
        for (int i = 0; i < 50000; i++) sbOld.append('a');
        String largeOld = sbOld.toString();
        testCases.add(new TestCase(
                List.of(largeNew),
                List.of(largeOld),
                List.of("YES")
        ));

        // Additional Test Case 6: Large Input with cycling
        // newPassword: "azazaz...az" (100,000 characters)
        // oldPassword: "ba" repeated 50,000 times
        sbNew = new StringBuilder();
        for (int i = 0; i < 50000; i++) {
            sbNew.append("az");
        }
        largeNew = sbNew.toString();
        sbOld = new StringBuilder();
        for (int i = 0; i < 50000; i++) {
            sbOld.append("ba");
        }
        largeOld = sbOld.toString();
        testCases.add(new TestCase(
                List.of(largeNew),
                List.of(largeOld),
                List.of("YES")
        ));

        // Execute Test Cases
        int testCaseNumber = 1;
        for (TestCase tc : testCases) {
            List<String> actual = findSimilarities(tc.newPasswords, tc.oldPasswords);
            boolean pass = actual.equals(tc.expected);
            System.out.println("Test Case " + testCaseNumber + ": " + (pass ? "PASS" : "FAIL"));
            if (!pass) {
                System.out.println("Expected: " + tc.expected);
                System.out.println("Actual  : " + actual);
            }
            testCaseNumber++;
        }
    }

    /**
     * Helper class to represent a test case.
     */
    static class TestCase {
        List<String> newPasswords;
        List<String> oldPasswords;
        List<String> expected;

        TestCase(List<String> newPasswords, List<String> oldPasswords, List<String> expected) {
            this.newPasswords = newPasswords;
            this.oldPasswords = oldPasswords;
            this.expected = expected;
        }
    }
}

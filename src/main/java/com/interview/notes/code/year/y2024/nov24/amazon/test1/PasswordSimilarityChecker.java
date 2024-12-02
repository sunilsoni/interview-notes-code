package com.interview.notes.code.year.y2024.nov24.amazon.test1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
WORKING:



**Question Instructions**

Complete the `findSimilarities` function below.

The function is expected to return a `STRING_ARRAY`.
The function accepts the following parameters:
1. `STRING_ARRAY newPasswords`
2. `STRING_ARRAY oldPasswords`

```java
public static List<String> findSimilarities(List<String> newPasswords, List<String> oldPasswords) {
    // Write your code here
}
```

---

**Sample Case 1**

**Sample Input For Custom Testing:**

| STDIN | FUNCTION                    |
|-------|------------------------------|
| 2     | newPasswords[] size n = 2    |
| aaaa  | newPasswords = ["aaaa", "bzz"] |
| bzz   |                              |
| 2     | oldPasswords[] size n = 2    |
| bcd   | oldPasswords = ["bcd", "az"] |
| az    |                              |

**Sample Output:**

```
NO
YES
```

**Explanation:**

For `i = 0`:
- `newPasswords[0] = "aaaa"`
- `oldPasswords[0] = "bcd"`

It is impossible to get the string `oldPasswords[0]` as a subsequence of `newPasswords[0]` by performing the operation.

For `i = 1`:
- `newPasswords[1] = "bzz"`
- `oldPasswords[1] = "az"`

Here, character `z` is changed to the next cyclic character, which is `a`. This makes `oldPasswords[1]` a subsequence of `newPasswords[1]`.

---

**Sample Case 0**

**Sample Input For Custom Testing:**

| STDIN | FUNCTION                    |
|-------|------------------------------|
| 2     | newPasswords[] size n = 2    |
| aaccbbee | newPasswords = ["aaccbbee", "aab"] |
| aab   |                              |
| 2     | oldPasswords[] size n = 2    |
| bdbf  | oldPasswords = ["bdbf", "aee"] |
| aee   |                              |

**Sample Output:**

```
YES
NO
```

**Explanation:**

For `i = 0`:
- `newPasswords[0] = "aaccbbee"`
- `oldPasswords[0] = "bdbf"`

Changing specific characters in `newPasswords[0]` to the next cyclic character allows `oldPasswords[0]` to form a subsequence of it.

For `i = 1`:
- `newPasswords[1] = "aab"`
- `oldPasswords[1] = "aee"`

It is impossible to get the string `oldPasswords[1]` as a subsequence of `newPasswords[1]` by performing the operation.

---

**Function Description**

Complete the function `findSimilarities` in the editor below.

`findSimilarities` has the following parameters:
- `string newPasswords[n]`: `newPasswords[i]` represents the new password of the *i*th pair
- `string oldPasswords[n]`: `oldPasswords[i]` represents the old password of the *i*th pair

**Returns**

- `string[n]`: The *i*th string represents the answer to the *i*th pair of passwords.

**Constraints**

- \(1 \leq n \leq 10\)
- Sum of lengths of all passwords in arrays `newPassword` and `oldPassword` does not exceed \(2 \times 10^5\).
- \( | oldPasswords[i] | \leq | newPasswords[i] | \), for all *i*.

---

**Input Format For Custom Testing**

The first line contains an integer *n*, the size of the array `newPasswords`.
Each line *i* of the *n* subsequent lines (where \(0 \leq i < n\)) contains a string that describes `newPasswords[i]`.
The first line contains an integer *n*, the size of the array `oldPasswords`.
Each line *i* of the *n* subsequent lines (where \(0 \leq i < n\)) contains a string that describes `oldPasswords[i]`.

---

**Code Question 1**

Amazon would like to enforce a password policy such that when a user changes their password, the new password cannot be similar to the current one. To determine whether two passwords are similar, they take the new password, choose a set of indices, and change the characters at these indices to the next cyclic character exactly once. Character 'a' is changed to 'b', 'b' to 'c', and so on, with 'z' changing to 'a'. A password is said to be *similar* if, after applying this operation, the old password is a subsequence of the new password.

Developers come up with a set of *n* password change requests, where `newPasswords` denotes the array of new passwords and `oldPasswords` denotes the array of old passwords. For each pair `newPasswords[i]` and `oldPasswords[i]`, return `"YES"` if the passwords are similar (i.e., `oldPasswords[i]` becomes a subsequence of `newPasswords[i]` after performing the operations) and `"NO"` otherwise.

**Note:** A subsequence is a sequence derived from another sequence by deleting zero or more elements without changing the order of the remaining elements.

**Example**

The two lists of passwords are given as `newPasswords = ["baacbab", "accdb","baacba"]` and `oldPasswords = ["abdbc","ach","abb"]`.

Consider the first pair:
- `newPasswords[0] = "baacbab"`
- `oldPasswords[0] = "abdbc"`

Change "ac" to "bd" at the 3rd and 4th positions and "b" to "c" at the last position.
The answer for this pair is `YES`.

For the second pair,
- `newPasswords[1] = "accdb"`
- `oldPasswords[1] = "ach"`

It is impossible to change the character of the new password to "h," which occurs in the old password, so there is no subsequence that matches. The answer for this pair is `NO`.

 */
public class PasswordSimilarityChecker {

    /**
     * Determines if old passwords are similar to new passwords based on the transformation rules.
     *
     * @param newPasswords List of new passwords.
     * @param oldPasswords List of old passwords.
     * @return List of "YES" or "NO" indicating similarity for each pair.
     */
    public static List<String> findSimilarities(List<String> newPasswords, List<String> oldPasswords) {
        List<String> results = new ArrayList<>();
        int n = newPasswords.size();

        for (int i = 0; i < n; i++) {
            String newPass = newPasswords.get(i);
            String oldPass = oldPasswords.get(i);
            if (isSubsequenceWithTransformation(newPass, oldPass)) {
                results.add("YES");
            } else {
                results.add("NO");
            }
        }

        return results;
    }

    /**
     * Checks if oldPass is a subsequence of newPass after optionally transforming characters.
     *
     * @param newPass New password string.
     * @param oldPass Old password string.
     * @return True if oldPass is a subsequence after transformation, else False.
     */
    private static boolean isSubsequenceWithTransformation(String newPass, String oldPass) {
        if (oldPass.length() == 0) {
            return true;
        }
        if (oldPass.length() > newPass.length()) {
            return false;
        }

        int j = 0; // Pointer for oldPass
        for (int i = 0; i < newPass.length() && j < oldPass.length(); i++) {
            char currentNew = newPass.charAt(i);
            char transformedNew = incrementChar(currentNew);
            char currentOld = oldPass.charAt(j);

            if (currentNew == currentOld || transformedNew == currentOld) {
                j++;
            }
        }

        return j == oldPass.length();
    }

    /**
     * Increments a character to its next cyclic character ('a' -> 'b', ..., 'z' -> 'a').
     *
     * @param c Character to increment.
     * @return Incremented character.
     */
    private static char incrementChar(char c) {
        if (c == 'z') {
            return 'a';
        }
        return (char) (c + 1);
    }

    /**
     * Main method to run test cases and verify the implementation.
     */
    public static void main(String[] args) {
        // Define test cases
        List<TestCase> testCases = new ArrayList<>();

        // Sample Case 1
        testCases.add(new TestCase(
                Arrays.asList("aaaa", "bzz"),
                Arrays.asList("bcd", "az"),
                Arrays.asList("NO", "YES")
        ));

        // Sample Case 0
        testCases.add(new TestCase(
                Arrays.asList("aaccbbee", "aab"),
                Arrays.asList("bdbf", "aee"),
                Arrays.asList("YES", "NO")
        ));

        // Additional Test Case 1: Identical passwords
        testCases.add(new TestCase(
                Arrays.asList("password"),
                Arrays.asList("password"),
                Arrays.asList("YES")
        ));

        // Additional Test Case 2: Empty old password
        testCases.add(new TestCase(
                Arrays.asList("anynewpassword"),
                Arrays.asList(""),
                Arrays.asList("YES")
        ));

        // Additional Test Case 3: All characters need to be incremented
        testCases.add(new TestCase(
                Arrays.asList("abc"),
                Arrays.asList("bcd"),
                Arrays.asList("YES")
        ));

        // Additional Test Case 4: Large Input
        StringBuilder largeNew = new StringBuilder();
        StringBuilder largeOld = new StringBuilder();
        for (int i = 0; i < 100000; i++) {
            largeNew.append('a');
            largeOld.append('a');
        }
        testCases.add(new TestCase(
                Arrays.asList(largeNew.toString()),
                Arrays.asList(largeOld.toString()),
                Arrays.asList("YES")
        ));

        // Run all test cases
        int passed = 0;
        for (int i = 0; i < testCases.size(); i++) {
            TestCase tc = testCases.get(i);
            List<String> output = findSimilarities(tc.newPasswords, tc.oldPasswords);
            boolean isPass = output.equals(tc.expectedOutput);
            if (isPass) {
                System.out.println("Test Case " + (i + 1) + ": PASS");
                passed++;
            } else {
                System.out.println("Test Case " + (i + 1) + ": FAIL");
                System.out.println("Expected: " + tc.expectedOutput);
                System.out.println("Got: " + output);
            }
        }
        System.out.println("Passed " + passed + " out of " + testCases.size() + " test cases.");
    }

    /**
     * Helper class to define a test case.
     */
    static class TestCase {
        List<String> newPasswords;
        List<String> oldPasswords;
        List<String> expectedOutput;

        TestCase(List<String> newPasswords, List<String> oldPasswords, List<String> expectedOutput) {
            this.newPasswords = newPasswords;
            this.oldPasswords = oldPasswords;
            this.expectedOutput = expectedOutput;
        }
    }
}

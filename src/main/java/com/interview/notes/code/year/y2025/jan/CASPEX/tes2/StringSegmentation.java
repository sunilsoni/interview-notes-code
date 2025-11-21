package com.interview.notes.code.year.y2025.jan.CASPEX.tes2;

import java.util.Arrays;
import java.util.List;

/*
WORKING:


### Example #2

**Input**
```
catsandog
5
cats dog sand and cat
```

**Output**
```
false
```

**Explanation:**
Here "catsandog" can be segmented as `cat-sand-og`, `cats-and-og`, `cat-san-dog`, etc., but none of these combinations are completely present in the dictionary `[cats, dog, sand, and, cat]`. So, return `false`.

---

The third line of input contains **N** space-separated strings, representing the words in the dictionary.

**Output**
Print `true` if `S` can be segmented into a space-separated sequence, otherwise print `false`.

**Constraints**
`1 <= N <= 25`

---

### Example #1

**Input**
```
applepenapple
2
apple pen
```

**Output**
```
true
```

**Explanation:**
Here "applepenapple" can be segmented as `apple-pen-apple`, so return `true`.

---

### String Segmentation

You are given a string `S` and a dictionary of strings `wordDict`. Write a program that returns `true` if `S` can be segmented into a space-separated sequence of one or more dictionary words, else return `false`.

**Note:** The same word in the dictionary may be reused multiple times in the segmentation.

#### For Example:
**Input**
```
S = "applepenapple"
wordDict = ["apple", "pen"]
```

**Output**
```
true
```

This should return `true` because "applepenapple" can be segmented as `apple pen apple`. Since you are allowed to reuse a dictionary word.

---

### Function Template

```java
class Outcome {
    /**
     * Implement method/function with the name 'solve' below.
     * The function accepts the following parameters:
     * 1. S is of type String.
     * 2. wordDict is of type List<String>.
     * return type: String.

public static String solve(String S, List<String> wordDict) {
    // Write your code here

    return ""; // return type "String".
}
}
        ```
 */
public class StringSegmentation {
    // Main solve method that returns "true" or "false" as String
    public static String solve(String S, List<String> wordDict) {
        return canSegment(S, wordDict) ? "true" : "false";
    }

    // Helper method that does the actual segmentation check
    private static boolean canSegment(String s, List<String> wordDict) {
        if (s == null || s.length() == 0) return false;

        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;  // Empty string is always valid

        // Try all possible prefixes
        for (int i = 1; i <= s.length(); i++) {
            // Check all possible words that could end at position i
            for (String word : wordDict) {
                int len = word.length();
                if (len <= i) {
                    // Check if we can form a valid segmentation using this word
                    if (dp[i - len] && s.substring(i - len, i).equals(word)) {
                        dp[i] = true;
                        break;
                    }
                }
            }
        }

        return dp[s.length()];
    }

    // Main method with test cases
    public static void main(String[] args) {
        // Test Case 1: Basic valid case
        test("applepenapple",
                Arrays.asList("apple", "pen"),
                "true");

        // Test Case 2: Invalid case
        test("catsandog",
                Arrays.asList("cats", "dog", "sand", "and", "cat"),
                "false");

        // Test Case 3: Empty string
        test("",
                Arrays.asList("cat", "dog"),
                "false");

        // Test Case 4: Single character
        test("a",
                List.of("a"),
                "true");

        // Test Case 5: Large repeated words
        test("aaaaaaa",
                Arrays.asList("a", "aa", "aaa"),
                "true");
    }

    // Test helper method
    private static void test(String input, List<String> dict, String expected) {
        String result = solve(input, dict);
        System.out.println("Input: " + input);
        System.out.println("Dict: " + dict);
        System.out.println("Expected: " + expected);
        System.out.println("Got: " + result);
        System.out.println("Test " + (result.equals(expected) ? "PASSED" : "FAILED"));
        System.out.println();
    }
}

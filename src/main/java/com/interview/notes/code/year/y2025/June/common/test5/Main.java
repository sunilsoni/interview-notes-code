package com.interview.notes.code.year.y2025.June.common.test5;

import java.util.*;
import java.util.stream.IntStream;

//100% WORKING
/*

### **String Segmentation Problem**

You are given:

* a string `S`
* and a dictionary of strings `wordDict` (a list of valid words).

Write a function `solve(S, wordDict)` that returns `"true"` if `S` can be segmented into a space-separated sequence of one or more dictionary words from `wordDict`. Otherwise, return `"false"`.

*Note*: Words in the dictionary may be reused multiple times.

---

### **Function Signature**

```java
public static String solve(String S, List<String> wordDict)
```

---

### **Input Format**

1. First line: a string `S`.
2. Second line: integer `N`, the number of words in the dictionary.
3. Third line: `N` space-separated words (the dictionary entries).

---

### **Output Format**

* Print `"true"` if the string `S` can be segmented using words from the dictionary.
* Otherwise, print `"false"`.

---

### **Constraints**

* 1 ≤ N ≤ 25

---

### **Examples**

#### Example 1:

**Input:**

```
applepenapple
2
apple pen
```

**Output:**

```
true
```

#### Example 2:

**Input:**

```
catsandog
5
cats dog sand and cat
```

**Output:**

```
false
```



 */
public class Main {

    /**
     * Returns "true" if S can be segmented into one or more dictionary words,
     * otherwise "false".
     */
    public static String solve(String s, List<String> wordDict) {
        int n = s.length();
        // put words into a set for O(1) lookups
        Set<String> dict = new HashSet<>(wordDict);
        // compute the maximum word length to limit our checks
        int maxLen = wordDict.stream().mapToInt(String::length).max().orElse(0);

        boolean[] dp = new boolean[n + 1];
        dp[0] = true;  // empty string can always be segmented

        for (int i = 1; i <= n; i++) {
            int start = Math.max(0, i - maxLen);
            final int end = i;
            // check any j in [start, i) where dp[j] is true and s[j:i] is a dict word
            dp[i] = IntStream.rangeClosed(start, end - 1)
                    .anyMatch(j -> dp[j] && dict.contains(s.substring(j, end)));
        }

        return dp[n] ? "true" : "false";
    }

    // Simple test harness — no JUnit, just main and PASS/FAIL output
    public static void main(String[] args) {
        class Test {
            final String s;
            final List<String> dict;
            final String expected;

            Test(String s, List<String> dict, String expected) {
                this.s = s;
                this.dict = dict;
                this.expected = expected;
            }
        }

        List<Test> tests = Arrays.asList(
                new Test("applepenapple", Arrays.asList("apple", "pen"), "true"),
                new Test("catsandog", Arrays.asList("cats", "dog", "sand", "and", "cat"), "false"),
                new Test("", Collections.emptyList(), "true"),  // empty string
                new Test("a", List.of("b"), "false")
                // you can add more edge / large cases here
        );

        for (Test t : tests) {
            String result = solve(t.s, t.dict);
            String status = result.equals(t.expected) ? "PASS" : "FAIL";
            System.out.printf(
                    "Input: \"%s\", Dict: %s => got %s, expected %s [%s]%n",
                    t.s, t.dict, result, t.expected, status
            );
        }
    }
}
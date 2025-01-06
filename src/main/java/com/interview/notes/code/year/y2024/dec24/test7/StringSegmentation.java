package com.interview.notes.code.year.y2024.dec24.test7;

import java.util.*;

/*
WORKING
### **String Segmentation Problem**

---

### **Description**
You are given a string \( S \) and a dictionary of strings \( wordDict \). Write a program that returns **true** if \( S \) can be segmented into a space-separated sequence of one or more dictionary words, else return **false**.

**Note:** The same word in the dictionary may be reused multiple times in the segmentation.

---

### **Input**
1. The first line contains a string \( S \).
2. The second line contains an integer \( N \), representing the size of \( wordDict \).
3. The third line contains \( N \) space-separated strings, representing the words in the dictionary.

---

### **Output**
Print **true** if \( S \) can be segmented into a space-separated sequence, otherwise print **false**.

---

### **Constraints**
- \( 1 \leq N \leq 25 \)

---

### **Example #1**
**Input**:
```
applepenapple
2
apple pen
```

**Output**:
```
true
```

**Explanation**:
"applepenapple" can be segmented as "apple-pen-apple", so return **true**.

---

### **Example #2**
**Input**:
```
catsandog
5
cats dog sand and cat
```

**Output**:
```
false
```

**Explanation**:
"catsandog" can be segmented as cat-sand-og, cats-and-og, etc., but none of these combinations are completely present in the dictionary.

---

### **Function Definition**
```java
/*
* Implement method/function with name 'solve' below.
* The function accepts following as parameters:
* 1. S is of type String.
* 2. wordDict is of type List<String>.
* return String.


 */
public class StringSegmentation {
    public static String solve(String s, List<String> wordDict) {
        if (s == null || s.isEmpty() || wordDict == null || wordDict.isEmpty()) {
            return "false";
        }

        // Convert wordDict to HashSet for O(1) lookups
        Set<String> dictionary = new HashSet<>(wordDict);

        // dp[i] represents whether s[0...i] can be segmented
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;  // empty string is always valid

        // For each position in string
        for (int i = 1; i <= s.length(); i++) {
            // Try all possible previous positions
            for (int j = 0; j < i; j++) {
                if (dp[j] && dictionary.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return String.valueOf(dp[s.length()]).toLowerCase();
    }

    public static void main(String[] args) {
        // Test cases
        List<TestCase> testCases = new ArrayList<>();

        // Test case 1: Basic case
        testCases.add(new TestCase(
                "applepenapple",
                Arrays.asList("apple", "pen"),
                "true"
        ));

        // Test case 2: Cannot be segmented
        testCases.add(new TestCase(
                "catsandog",
                Arrays.asList("cats", "dog", "sand", "and", "cat"),
                "false"
        ));

        // Test case 3: Single word
        testCases.add(new TestCase(
                "leetcode",
                Arrays.asList("leet", "code"),
                "true"
        ));

        // Test case 4: Empty string
        testCases.add(new TestCase(
                "",
                Arrays.asList("test"),
                "false"
        ));

        // Test case 5: Long repeated pattern
        StringBuilder sb = new StringBuilder();
        List<String> longDict = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            sb.append("ab");
            longDict.add("ab");
        }
        testCases.add(new TestCase(
                sb.toString(),
                longDict,
                "true"
        ));

        // Run tests
        for (int i = 0; i < testCases.size(); i++) {
            TestCase tc = testCases.get(i);
            String result = solve(tc.input, tc.dict);
            boolean passed = result.equals(tc.expected);

            System.out.printf("Test Case %d: %s\n", i + 1, passed ? "PASS" : "FAIL");

            if (!passed) {
                System.out.println("Input String: " + tc.input);
                System.out.println("Dictionary: " + tc.dict);
                System.out.println("Expected: " + tc.expected);
                System.out.println("Got: " + result);
            }
        }
    }

    static class TestCase {
        String input;
        List<String> dict;
        String expected;

        TestCase(String input, List<String> dict, String expected) {
            this.input = input;
            this.dict = dict;
            this.expected = expected;
        }
    }
}

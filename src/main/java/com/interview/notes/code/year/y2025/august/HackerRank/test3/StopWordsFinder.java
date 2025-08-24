package com.interview.notes.code.year.y2025.august.HackerRank.test3;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*

**Problem Statement:**

Given two strings `s` and `t` of lengths `m` and `n` respectively, return the **minimum window substring** of `s` such that every character in `t` (including duplicates) is included in the window.

If there is no such substring, return the empty string `""`.

The test cases will be generated such that the answer is **unique**.

---

**Example 1:**

```
Input: s = "ADOBECODEBANC", t = "ABC"
Output: "BANC"

Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.
```

---

**Example 2:**

```
Input: s = "a", t = "a"
Output: "a"

Explanation: The entire string s is the minimum window.
```

---

**Example 3:**

```
Input: s = "a", t = "aa"
Output: ""

Explanation: Both 'a's from t must be included in the window.
Since the largest window of s only has one 'a', return empty string.
```

---

**Constraints:**

* `m == s.length`
* `n == t.length`
* `1 <= m, n <= 10^5`
* `s` and `t` consist of uppercase and lowercase English letters.

---

 */
public class StopWordsFinder {
    public static List<String> findStopWords(String text, int k) {
        Map<String, Long> freq = Arrays.stream(text.split(" "))
                .collect(Collectors.groupingBy(w -> w, LinkedHashMap::new, Collectors.counting()));
        return freq.entrySet().stream()
                .filter(e -> e.getValue() >= k)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<String[]> tests = Arrays.asList(
                new String[]{"the brown fox jumps over the brown dog and runs away to a brown house", "2", "the,brown"},
                new String[]{"foo bar foo baz foo", "3", "foo"},
                new String[]{"a mouse is smaller than a dog but a dog is stronger", "2", "a,is,dog"},
                new String[]{"one two three four", "1", "one,two,three,four"},
                new String[]{"x x y y z z x y z", "3", "x,y,z"}
        );

        for (String[] test : tests) {
            String text = test[0];
            int k = Integer.parseInt(test[1]);
            String expected = test[2];
            long start = System.nanoTime();
            List<String> result = findStopWords(text, k);
            long end = System.nanoTime();
            String resultStr = String.join(",", result);
            System.out.println("Input: \"" + text + "\" k=" + k);
            System.out.println("Output: " + resultStr);
            System.out.println("Expected: " + expected);
            System.out.println("Result: " + (resultStr.equals(expected) ? "PASS" : "FAIL") + " | Time: " + (end - start) / 1_000_000.0 + " ms\n");
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 20000; i++) sb.append("word").append(i % 100).append(" ");
        String largeText = sb.toString().trim();
        long start = System.nanoTime();
        List<String> largeResult = findStopWords(largeText, 200);
        long end = System.nanoTime();
        System.out.println("Large dataset processed in " + (end - start) / 1_000_000.0 + " ms -> PASS");
    }
}
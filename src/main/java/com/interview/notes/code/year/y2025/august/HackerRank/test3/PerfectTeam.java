package com.interview.notes.code.year.y2025.august.HackerRank.test3;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*


You are working on a lightweight search engine indexing module, and one of the early tasks is to filter out non-informative words that appear too frequently, often called stop words. These words are so common across documents that they dilute search relevance.

Implement a function that finds all the words that occur in the string `text` at least `k` times.

The function `findStopWords` will take two inputs:

* `string text`: a string containing words of letters in the English alphabet separated by space `" "`.
* `int k`: the minimum number of occurrences of a word required to be considered as a stop word.

The function should return an array of strings, the stop words in order of their first occurrence in the string.

**Example**

```
text = "a mouse is smaller than a dog but a dog is stronger"
k = 2
```

The list of stop words that occur at least `k = 2` times is `["a", "is", "dog"]`.
`"a"` occurs 3 times, `"is"` and `"dog"` both occur 2 times. No other word occurs at least 2 times. The answer is in order of their first appearance in `text`.

**Constraints**

* `text` has at most 50000 characters.
* Every character in `text` is either an English lowercase letter or a space.
* `text` starts and ends with a letter. No two consecutive characters are spaces, i.e., text is a valid sentence.
* There will be at least one stop word in the text.
* `1 ≤ k ≤ 18`.

**Input Format for Custom Testing**

* The first line contains the string `text`.
* The second line contains the integer `k`.

---

**Sample Case 0**
**Sample Input 0**

```
the brown fox jumps over the brown dog and runs away to a brown house
2
```

**Sample Output 0**

```
the
brown
```

**Explanation**
`"the"` occurs 2 times and `"brown"` occurs 3 times. These words are returned in the order of their first occurrence in the text.

---

**Sample Case 1**
**Sample Input 1**

```
foo bar foo baz foo
3
```

**Sample Output 1**

```
foo
```

**Explanation**
`"foo"` occurs 3 times.

---

**Function Signature**

```java
public static List<String> findStopWords(String text, int k) {
    // Write your code here
}
```
 */
public class PerfectTeam {
    public static int perfectTeam(String skills) {
        Map<Character, Long> freq = skills.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(c -> c, Collectors.counting()));
        return (int) Arrays.asList('p', 'c', 'm', 'b', 'z')
                .stream()
                .mapToLong(ch -> freq.getOrDefault(ch, 0L))
                .min()
                .orElse(0);
    }

    public static void main(String[] args) {
        List<String[]> tests = Arrays.asList(
                new String[]{"pcmbz", "1"},
                new String[]{"pcmp", "0"},
                new String[]{"pcmpcmbbbzz", "2"},
                new String[]{"pppppcccccmmmmmbbbbzzzz", "5"},
                new String[]{"ppppccccmmmmbbbbzzzz", "4"}
        );

        for (String[] test : tests) {
            String skills = test[0];
            int expected = Integer.parseInt(test[1]);
            long start = System.nanoTime();
            int result = perfectTeam(skills);
            long end = System.nanoTime();
            System.out.println("Input: \"" + skills + "\"");
            System.out.println("Output: " + result);
            System.out.println("Expected: " + expected);
            System.out.println("Result: " + (result == expected ? "PASS" : "FAIL") + " | Time: " + (end - start) / 1_000_000.0 + " ms\n");
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 500000; i++) sb.append("pcmbz".charAt(i % 5));
        String largeSkills = sb.toString();
        long start = System.nanoTime();
        int largeResult = perfectTeam(largeSkills);
        long end = System.nanoTime();
        System.out.println("Large dataset processed in " + (end - start) / 1_000_000.0 + " ms -> PASS");
    }
}
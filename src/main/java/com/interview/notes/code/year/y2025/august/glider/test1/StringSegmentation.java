package com.interview.notes.code.year.y2025.august.glider.test1;


import java.util.*;
import java.util.stream.*;
/*
Here’s the properly combined **final question** from the screenshots and discussion:

---

### Final Question

**String Segmentation**

You are given a string **S** and a dictionary of strings **wordDict**. Write a program that returns **true** if **S** can be segmented into a space-separated sequence of one or more dictionary words, else return **false**.

**Note:** The same word in the dictionary may be reused multiple times in the segmentation.

---

#### Input

* The first line of input contains a string **S**.
* The second line of input contains an integer **N**, representing the size of the **wordDict**.
* The third line of input contains **N** space-separated strings, representing the words in the dictionary.

#### Output

* Print **true** if **S** can be segmented into a space-separated sequence, otherwise print **false**.

#### Constraints

* `1 <= N <= 25`

---

#### Example #1

Input

```
applepenapple
2
apple pen
```

Output

```
true
```

Explanation: Here *"applepenapple"* can be segmented as *"apple-pen-apple"*. So return **true**.

---

#### Example #2

Input

```
catsandog
5
cats dog sand and cat
```

Output

```
false
```

Explanation: Here *"catsandog"* can be segmented as *cat-sand-og*, *cats-and-og*, *cat-san-dog*, etc., but none of these combinations are completely present in the dictionary \[cats, dog, sand, and, cat]. So return **false**.

---

#### Function to Implement




 */
public class StringSegmentation {

    public static String solve(String S, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict);
        boolean[] dp = new boolean[S.length() + 1];
        dp[0] = true;

        for (int i = 1; i <= S.length(); i++) {
            int idx = i;
            dp[i] = IntStream.range(0, i)
                    .anyMatch(j -> dp[j] && dict.contains(S.substring(j, idx)));
        }
        return dp[S.length()] ? "true" : "false";
    }

    public static void main(String[] args) {
        List<Object[]> tests = Arrays.asList(
                new Object[]{"applepenapple", Arrays.asList("apple", "pen"), "true"},
                new Object[]{"catsandog", Arrays.asList("cats", "dog", "sand", "and", "cat"), "false"},
                new Object[]{"leetcode", Arrays.asList("leet", "code"), "true"},
                new Object[]{"aaaaaaa", Arrays.asList("aaaa", "aaa"), "true"},
                new Object[]{"cars", Arrays.asList("car", "ca", "rs"), "true"}
        );

        for (Object[] test : tests) {
            String input = (String) test[0];
            List<String> dict = (List<String>) test[1];
            String expected = (String) test[2];
            String output = solve(input, dict);
            System.out.println("Input: " + input + " Dict: " + dict +
                    " | Expected: " + expected + " | Got: " + output +
                    " | Result: " + (output.equals(expected) ? "PASS" : "FAIL"));
        }

        String largeInput = String.join("", Collections.nCopies(25, "apple"));
        List<String> largeDict = Arrays.asList("apple", "pen");
        long start = System.currentTimeMillis();
        String result = solve(largeInput, largeDict);
        long end = System.currentTimeMillis();
        System.out.println("Large Input Test | Result: " + result + " | Time(ms): " + (end - start));
    }
}
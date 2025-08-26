package com.interview.notes.code.year.y2025.august.glider.test1;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
public class BrothersGame {

    public static int solve(List<Integer> ar) {
        int ones = (int) ar.stream().filter(x -> x == 1).count();
        int maxDiff = Integer.MIN_VALUE, cur = 0;
        for (int val : ar) {
            int diff = (val == 0 ? 1 : -1);
            cur = Math.max(diff, cur + diff);
            maxDiff = Math.max(maxDiff, cur);
        }
        return ones + maxDiff;
    }

    public static void main(String[] args) {
        List<Object[]> tests = Arrays.asList(
                new Object[]{Arrays.asList(0, 1, 0, 0, 1), 4},
                new Object[]{Arrays.asList(1, 0, 0, 1, 0, 0), 5},
                new Object[]{Arrays.asList(1, 1, 1, 1), 3},
                new Object[]{Arrays.asList(0, 0, 0, 0), 4},
                new Object[]{Arrays.asList(1, 0, 1, 0, 1, 0, 1, 0, 1, 0), 7}
        );

        for (Object[] test : tests) {
            List<Integer> input = (List<Integer>) test[0];
            int expected = (int) test[1];
            int output = solve(input);
            System.out.println("Input: " + input +
                    " | Expected: " + expected +
                    " | Got: " + output +
                    " | Result: " + (output == expected ? "PASS" : "FAIL"));
        }

        List<Integer> largeInput = IntStream.range(0, 100)
                .map(i -> i % 2)
                .boxed()
                .collect(Collectors.toList());
        long start = System.currentTimeMillis();
        int result = solve(largeInput);
        long end = System.currentTimeMillis();
        System.out.println("Large Input Test | Result: " + result + " | Time(ms): " + (end - start));
    }
}
package com.interview.notes.code.year.y2024.oct24.test23;

/*


Java8+ provide main method with exmpales to run this and also ahndle large inputs within 3 seconds of time

### 7. TikTok String Challenge

At the TikTok Academy, students are learning how to improve video captions.

You are given a string `caption` that only has small English letters ('a' to 'z'). You can make two kinds of changes to the `caption` at any letter as many times as needed:

1. Change the letter to the one right before it in the alphabet. You cannot change 'a' because it's already the first letter.
2. Change the letter to the one right after it in the alphabet. You cannot change 'z' because it's already the last letter.

For example, you can change 'f' to 'e' (the letter before) or 'g' (the letter after).

Your task is to make sure that every letter in the `caption` has at least one same letter next to it. For example, "aaabb" and "aaccdd" are good captions, but "abaaa" and "abcdef" are not.

Return the minimum number of changes needed to make the `caption` meet this rule.

#### Example

Given `caption = "aca"`

You can change 'c' to 'b' (the letter before), then to 'a' (the letter before). Now the `caption` is "aaa", which meets the rule.
In total, you need 2 changes to meet the rule, which is the minimum possible.

Thus, the answer is 2.

---

### Function Description

Complete the function `getMinTransformations` in the editor below.

`getMinTransformations` has the following parameter(s):

- `string caption`: the input string representing the caption.

---

#### Returns

- `int`: the minimum transformations required.

#### Constraints

- \( 2 \leq |caption| \leq 10^5 \)

---

### Input Format for Custom Testing

The first and only line contains a string `caption`.

---

### Sample Case 0

#### Sample Input 0

```
STDIN         FUNCTION
-----         --------
abab          -> caption = "abab"
```

#### Sample Output 0

```
2
```

#### Explanation

You can change `caption[0]` from 'a' to 'b' (the letter after), and `caption[3]` from 'b' to 'a' (the letter before). Now the `caption` is "bbaa", which meets the rule.
In total, you need 2 changes to meet the rule, which is the minimum possible.

Thus, the answer is 2.

---

### Sample Case 1

#### Sample Input 1

```
STDIN         FUNCTION
-----         --------
abcdef        -> caption = "abcdef"
```

#### Sample Output 1

```
3
```

#### Explanation

You can change `caption[0]` from 'a' to 'b' (the letter after), `caption[3]` from 'd' to 'c' (the letter before), and `caption[4]` from 'e' to 'f' (the letter after). Now the `caption` is "bbccff", which meets the rule.
In total, you need 3 changes to meet the rule, which is the minimum possible.

Thus, the answer is 3.
 */
public class TikTokStringChallengePartialFiveOf15 {
    public static int getMinTransformations(String caption) {
        int n = caption.length();
        char[] s = caption.toCharArray();
        int totalCost = 0;
        int i = 0;

        while (i + 3 <= n) {
            // Process positions i and i+1
            int minCost = Integer.MAX_VALUE;
            for (char c = 'a'; c <= 'z'; c++) {
                int cost = Math.abs(s[i] - c) + Math.abs(s[i + 1] - c);
                if (cost < minCost) {
                    minCost = cost;
                }
            }
            totalCost += minCost;
            i += 2;
        }

        if (n - i == 2) {
            // Process the last two characters
            int minCost = Integer.MAX_VALUE;
            for (char c = 'a'; c <= 'z'; c++) {
                int cost = Math.abs(s[i] - c) + Math.abs(s[i + 1] - c);
                if (cost < minCost) {
                    minCost = cost;
                }
            }
            totalCost += minCost;
        } else if (n - i == 3) {
            // Process the last three characters
            int minCost = Integer.MAX_VALUE;
            for (char c = 'a'; c <= 'z'; c++) {
                int cost = Math.abs(s[i] - c) + Math.abs(s[i + 1] - c) + Math.abs(s[i + 2] - c);
                if (cost < minCost) {
                    minCost = cost;
                }
            }
            totalCost += minCost;
        }

        return totalCost;
    }

    public static void main(String[] args) {
        // Sample test cases
        String[] testCaptions = {
                "aca",
                "abab",
                "abcdef"
        };
        int[] expectedResults = {
                2,
                2,
                3
        };

        boolean allPassed = true;
        for (int i = 0; i < testCaptions.length; i++) {
            int result = getMinTransformations(testCaptions[i]);
            if (result == expectedResults[i]) {
                System.out.println("Test case " + (i + 1) + " passed.");
            } else {
                System.out.println("Test case " + (i + 1) + " failed. Expected " + expectedResults[i] + ", got " + result);
                allPassed = false;
            }
        }

        // Handling large input
        StringBuilder largeCaptionBuilder = new StringBuilder();
        for (int i = 0; i < 100000; i++) {
            largeCaptionBuilder.append('a' + (i % 26));
        }
        String largeCaption = largeCaptionBuilder.toString();
        long startTime = System.currentTimeMillis();
        getMinTransformations(largeCaption);
        long endTime = System.currentTimeMillis();
        System.out.println("Large input processed in " + (endTime - startTime) + " ms");

        if (allPassed) {
            System.out.println("All test cases passed.");
        }
    }
}

package com.interview.notes.code.year.y2025.july.amazon.test1;

import java.util.Random;

/**
 * WORKING SOLUTION

 ---

 ## 💼 Problem: Amazon Prime Day Sale Partitioning

 **Context:**

 Amazon Prime Day is a sale event where a list of items is categorized using lowercase English letters. The company wants to split this list into **two contiguous, non-empty parts** (prefix and suffix), for two sale days, such that both parts share a **sufficient number of similar categories**.

 To ensure enough overlap in product types, we define a valid partition where the **number of distinct characters shared by both the prefix and suffix is greater than a given integer `k`**.

 ---

 ## ✅ Objective:

 Write a function:

 ```java
 public static int countValidPartitions(String itemCategories, int k)
 ```

 **Where:**

 * `itemCategories`: a string of lowercase English letters representing item categories.
 * `k`: an integer threshold (0 ≤ k ≤ 26).

 **Return:**
 The number of ways to partition the string into prefix and suffix such that the number of **shared distinct characters** between them is **greater than `k`**.

 ---

 ## 🧩 Input Format:

 * First line: a string `itemCategories`
 * Second line: an integer `k`

 ---

 ## 🔁 Constraints:

 * 1 ≤ length(`itemCategories`) ≤ 10⁵
 * 0 ≤ `k` ≤ 26
 * The string only contains lowercase English characters.

 ---

 ## 🧪 Example:

 ### Sample Case 1

 **Input:**

 ```
 itemCategories = "wxyzzxyw"
 k = 1
 ```

 **Output:**

 ```
 5
 ```

 **Explanation:**

 Only 5 valid partitions exist where the number of shared characters between prefix and suffix is > 1.

 ---

 ### Sample Case 0

 **Input:**

 ```
 itemCategories = "adbccdbada"
 k = 2
 ```

 **Output:**

 ```
 4
 ```

 **Explanation:**

 Valid partitions exist at indices 2, 3, 4, and 5 where shared distinct character count > 2.

 ---

 ### Additional Example:

 **Input:**

 ```
 itemCategories = "abbcac"
 k = 1
 ```

 **Output:**

 ```
 2
 ```

 ---


 */
public class CountValidPartitions {

    /**
     * Count the number of ways to split itemCategories into
     * two non-empty parts so that the number of distinct
     * common characters is > k.
     */
    public static int countValidPartitions(String s, int k) {
        int n = s.length();
        if (n < 2) return 0;

        // suffixMask[i] = bitmask of letters in s[i..n-1]
        int[] suffixMask = new int[n];
        int mask = 0;
        for (int i = n - 1; i >= 0; i--) {
            mask |= 1 << (s.charAt(i) - 'a');
            suffixMask[i] = mask;
        }

        int result = 0;
        int prefixMask = 0;
        // split between i and i+1, so i goes 0..n-2
        for (int i = 0; i < n - 1; i++) {
            prefixMask |= 1 << (s.charAt(i) - 'a');
            int common = Integer.bitCount(prefixMask & suffixMask[i + 1]);
            if (common > k) {
                result++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        // --- all prompt samples + tiny strings ---
        String[] tests = {
            "abbcac",       // sample in prompt description
            "wxyzzyxw",     // sample case 1
            "adbccdbada",   // sample case 0
            "a",            // too short
            "aa",           // minimal, same letters
            "ab"            // minimal, different letters
        };
        int[] ks        = { 1, 1, 2, 0, 0, 0 };
        int[] expected  = { 2, 5, 4, 0, 1, 0 };  // <<< fixed last expected from 1 to 0

        System.out.println("=== Basic Tests ===");
        for (int i = 0; i < tests.length; i++) {
            int got = countValidPartitions(tests[i], ks[i]);
            String pass = (got == expected[i]) ? "PASS" : "FAIL";
            System.out.printf(
                "Case %d: s=\"%s\", k=%d → got=%d, exp=%d [%s]\n",
                i+1, tests[i], ks[i], got, expected[i], pass
            );
        }

        // --- edge / all-same-letter tests ---
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) sb.append('x');
        String allX = sb.toString();

        System.out.println("\n=== Edge Cases ===");
        // k=0 → every split shares 'x' ⇒ 9 splits
        System.out.printf("allX, k=0 → %d (exp=9)\n",
            countValidPartitions(allX, 0));
        // k=1 → no split has more than 1 distinct common ⇒ 0
        System.out.printf("allX, k=1 → %d (exp=0)\n",
            countValidPartitions(allX, 1));

        // --- large random test for performance ---
        int N = 100_000;
        Random rnd = new Random(123);
        sb.setLength(0);
        for (int i = 0; i < N; i++) {
            sb.append((char)('a' + rnd.nextInt(26)));
        }
        String large = sb.toString();

        long start = System.nanoTime();
        int largeResult = countValidPartitions(large, 0);
        long ms = (System.nanoTime() - start) / 1_000_000;
        System.out.printf(
            "\nLarge random (n=%d), k=0 → %d splits in %d ms\n",
            N, largeResult, ms
        );
    }
}
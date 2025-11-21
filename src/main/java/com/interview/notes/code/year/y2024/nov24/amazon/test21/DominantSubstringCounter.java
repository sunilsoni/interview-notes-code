package com.interview.notes.code.year.y2024.nov24.amazon.test21;

import java.util.HashMap;
import java.util.Map;

/**
 * FINAL WORKING 100% IGNORE OLD SOLUTIONS
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * ```
 * Complete the 'getDominantStringCount' function below.
 * <p>
 * The function is expected to return a LONG_INTEGER.
 * The function accepts STRING s as parameter.
 * <p>
 * public static long getDominantStringCount(String s) {
 * // Write your code here
 * }
 * ```
 * <p>
 * I'll now extract text from the second image.
 * <p>
 * Here’s the extracted content from the second new image:
 * <p>
 * ```
 * Code Question 2:
 * <p>
 * A team of data analysts at Amazon is working to identify patterns in data. During their analysis, they discovered a category of strings they call a dominant string:
 * <p>
 * • The string has an even length.
 * • The string contains at least one character with a frequency equal to exactly half the length of the string.
 * <p>
 * Given a string `s` of length `n`, determine how many of its substrings are dominant strings.
 * <p>
 * Example:
 * s = "idafddfii".
 * <p>
 * All even-length substrings of `s` with a frequency of any character that is exactly half their length are:
 * ```
 * <p>
 * I will now extract text from the third image.
 * <p>
 * Here’s the extracted content from the third new image:
 * <p>
 * ```
 * Substring     Length of substring     Frequent character     Frequency of character
 * "id"          2                      'd'/'i'               1
 * "da"          2                      'd'/'a'               1
 * "af"          2                      'a'/'f'               1
 * "fd"          2                      'f'/'d'               1
 * "df"          2                      'd'/'f'               1
 * "fi"          2                      'f'/'i'               1
 * "dafd"        4                      'd'                   2
 * "afdd"        4                      'd'                   2
 * "fddf"        4                      'f'/'d'               2
 * "ddfi"        4                      'd'                   2
 * "dfii"        4                      'i'                   2
 * "idafdd"      6                      'd'                   3
 * "dafddf"      6                      'd'                   3
 * <p>
 * There are 13 dominant substrings in `s`.
 * <p>
 * *Function Description**:
 * Complete the function `getDominantStringCount` in the editor below.
 * <p>
 * `getDominantStringCount` has the following parameter:
 * - string `s`: the string to analyze
 * ```
 * <p>
 * I'll proceed with extracting text from the fourth image.
 * <p>
 * Here’s the extracted content from the fourth new image:
 * <p>
 * ```
 * *Returns**:
 * int: the number of dominant substrings in `s`
 * <p>
 * *Constraints**:
 * - \(1 ≤ n ≤ 10^5\)
 * - `s` consists of lowercase English letters in the range 'a' to 'j' (inclusive) only.
 * <p>
 * *Input Format For Custom Testing**:
 * The first and only line of input contains a string `s`.
 * <p>
 * *Sample Case 0**:
 * Sample Input For Custom Testing:
 * STDIN                FUNCTION
 * aaaaid               s = "aaaaid"
 * Sample Output: 3
 * <p>
 * *Explanation**:
 * 'ai', 'id', and 'aaid' are dominant substrings.
 * <p>
 * *Sample Case 1**:
 * Sample Input For Custom Testing:
 * STDIN                FUNCTION
 * abab                 s = "abab"
 * ```
 * <p>
 * I will now extract text from the fifth and final image.
 * <p>
 * Here’s the extracted content from the fifth new image:
 * <p>
 * ```
 * *Sample Case 1**:
 * <p>
 * Sample Input For Custom Testing:
 * STDIN                FUNCTION
 * abab                 s = "abab"
 * Sample Output: 4
 * <p>
 * *Explanation**:
 * Here are the dominant substrings in `s = "abab"`:
 * <p>
 * 1. "ab" (from position 0 to 1):
 * - Length = 2, 'a' and 'b' both appear once, which is exactly half of the length (2 / 2 = 1).
 * 2. "ba" (from position 1 to 2):
 * - Length = 2, 'b' and 'a' both appear once, which is exactly half of the length (2 / 2 = 1).
 * 3. "ab" (from position 2 to 3):
 * - Length = 2, 'a' and 'b' both appear once, which is exactly half of the length (2 / 2 = 1).
 * - This substring is counted again because it appears in a different part of the string.
 * 4. "abab" (from position 0 to 3):
 * - Length = 4, 'a' appears twice, which is exactly half of the length (4 / 2 = 2).
 * <p>
 * The dominant substrings are "ab", "ba", another occurrence of "ab", and "abab", making a total of 4.
 * ```
 * <p>
 * ### **Combined and Structured Text for the Entire Problem**
 * <p>
 * 1. **Problem Statement**:
 * - A team of data analysts at Amazon discovered a category of strings called **dominant strings**:
 * - The string has an even length.
 * - The string contains at least one character with a frequency equal to exactly half the length of the string.
 * - Given a string `s` of length `n`, determine how many of its substrings are dominant strings.
 * <p>
 * 2. **Function Signature**:
 * ```java
 * public static long getDominantStringCount(String s)
 * ```
 * <p>
 * 3. **Constraints**:
 * - \(1 ≤ n ≤ 10^5\)
 * - The string `s` consists of lowercase English letters in the range 'a' to 'j' (inclusive).
 * <p>
 * 4. **Input Format**:
 * - The first and only line of input contains a string `s`.
 * <p>
 * 5. **Output**:
 * - Returns an integer representing the number of dominant substrings in `s`.
 * <p>
 * 6. **Sample Cases**:
 * - **Sample Case 0**:
 * - **Input**: `"aaaaid"`
 * - **Output**: `3`
 * - **Explanation**: The dominant substrings are "ai", "id", and "aaid".
 * <p>
 * - **Sample Case 1**:
 * - **Input**: `"abab"`
 * - **Output**: `4`
 * - **Explanation**:
 * - Dominant substrings are:
 * - "ab" (positions 0-1)
 * - "ba" (positions 1-2)
 * - "ab" (positions 2-3)
 * - "abab" (positions 0-3)
 * <p>
 * 7. **Example for Explanation**:
 * - Given `s = "idafddfii"`:
 * - The dominant substrings are derived based on even-length substrings and character frequency meeting the dominant condition.
 */
public class DominantSubstringCounter {

    /**
     * Counts the number of dominant substrings in the given string.
     * <p>
     * A dominant substring is defined as an even-length substring that contains
     * at least one character with a frequency exactly equal to half the substring's length.
     *
     * @param s The input string consisting of lowercase letters from 'a' to 'j'.
     * @return The number of dominant substrings as a long integer.
     */
    public static long getDominantStringCount(String s) {
        long total = 0;
        // Initialize counts for each character 'a' to 'j'
        Map<Character, Long> perCharCounts = new HashMap<>();
        for (char c = 'a'; c <= 'j'; c++) {
            perCharCounts.put(c, 0L);
        }

        // Compute per-character counts
        for (char c = 'a'; c <= 'j'; c++) {
            Map<Long, Integer> map = new HashMap<>();
            map.put(0L, 1); // Initialize with transformed value 0
            long count_c = 0;
            for (int j = 0; j < s.length(); j++) {
                if (s.charAt(j) == c) {
                    count_c++;
                }
                long transformed = 2 * count_c - (j + 1);
                total += map.getOrDefault(transformed, 0);
                map.put(transformed, map.getOrDefault(transformed, 0) + 1);
            }
            // perCharCounts.put(c, map.get(0L)); // Not needed for final calculation
        }

        // Now, compute per-pair counts using inclusion-exclusion
        long totalPairCounts = 0;
        char[] chars = new char[10];
        for (int i = 0; i < 10; i++) {
            chars[i] = (char) ('a' + i);
        }

        for (int i = 0; i < 10; i++) {
            for (int j = i + 1; j < 10; j++) {
                char c1 = chars[i];
                char c2 = chars[j];
                Map<Long, Integer> map = new HashMap<>();
                // Unique encoding for pair (trans_c1, trans_c2)
                // To avoid negative values, we can offset transformed counts
                // But since Java's HashMap can handle negative keys, it's not necessary
                // Key = trans_c1 * 1_000_000 + trans_c2
                // Initialize with (0,0)
                long keyInitial = 0L;
                map.put(keyInitial, 1);
                long count_c1 = 0;
                long count_c2 = 0;
                long count_c1c2 = 0;
                for (int k = 0; k < s.length(); k++) {
                    if (s.charAt(k) == c1) {
                        count_c1++;
                    }
                    if (s.charAt(k) == c2) {
                        count_c2++;
                    }
                    long transformed_c1 = 2 * count_c1 - (k + 1);
                    long transformed_c2 = 2 * count_c2 - (k + 1);
                    long key = transformed_c1 * 1_000_000L + transformed_c2;
                    count_c1c2 += map.getOrDefault(key, 0);
                    map.put(key, map.getOrDefault(key, 0) + 1);
                }
                totalPairCounts += count_c1c2;
            }
        }

        // Total dominant substrings is sum of per-character counts minus sum of per-pair counts
        long result = total - totalPairCounts;

        return result;
    }

    /**
     * Main method to run test cases.
     */
    public static void main(String[] args) {
        // Test Case 0
        String test0 = "aaaaid";
        long expected0 = 3;
        long result0 = getDominantStringCount(test0);
        System.out.println("Test Case 0: " + (result0 == expected0 ? "PASS" : "FAIL") + " | Expected: " + expected0 + ", Got: " + result0);

        // Test Case 1
        String test1 = "abab";
        long expected1 = 4;
        long result1 = getDominantStringCount(test1);
        System.out.println("Test Case 1: " + (result1 == expected1 ? "PASS" : "FAIL") + " | Expected: " + expected1 + ", Got: " + result1);

        // Test Case 2
        String test2 = "ab";
        long expected2 = 1; // Only "ab" satisfies the condition
        long result2 = getDominantStringCount(test2);
        System.out.println("Test Case 2: " + (result2 == expected2 ? "PASS" : "FAIL") + " | Expected: " + expected2 + ", Got: " + result2);

        // Test Case 3
        String test3 = "aaaaid";
        long expected3 = 3; // "ai", "id", "aaid"
        long result3 = getDominantStringCount(test3);
        System.out.println("Test Case 3: " + (result3 == expected3 ? "PASS" : "FAIL") + " | Expected: " + expected3 + ", Got: " + result3);

        // Test Case 4
        String test4 = "idafddfii";
        long expected4 = 13;
        long result4 = getDominantStringCount(test4);
        System.out.println("Test Case 4: " + (result4 == expected4 ? "PASS" : "FAIL") + " | Expected: " + expected4 + ", Got: " + result4);

        // Test Case 5
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 100000; i++) {
            sb.append('a');
        }
        sb.append('b');
        String test5 = sb.toString();
        long expected5 = 0; // No dominant substrings
        long result5 = getDominantStringCount(test5);
        System.out.println("Test Case 5: " + (result5 == expected5 ? "PASS" : "FAIL") + " | Expected: " + expected5 + ", Got: " + result5);
    }
}

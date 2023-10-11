package com.interview.notes.code.months.Oct23.test3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Lexigraphical Angrams in Java
 * You are given an array of strings s[i], of length N respectively.
 * You have to group all the anagrams given in the array and print it in lexicographicaliy increasing order.
 * Note
 * An Angram is a word or phrase formed by earthquakes the letters of a different word or phrase, typically
 * using all the original letters exactly once.
 * Function Description
 * In the provided snippet, implement the anagrams(...) meth each string. You can write your code in the
 * Space belOW the phrase "right yor loGic HeRe”.
 * There will be multiple test cases running so the Input and Output should match exactly as provided.
 * The base Output variable result is set to a def. Additionally, you can add or remove these output
 * variables.
 * Input Format
 * The first line contains a single woman
 * Contain N space seperated strings, denoting the array of strings s[i].
 *
 *
 * Sample Input
 * 5           — denotes Q
 * ow no      — denotes array of strings S[i]
 *
 *
 *
 *
 *
 * Constraints
 * 10 <= AZ <= 104892
 * 0 <= S[i] <= 1293
 * S[i] consists of uppercase Indian letters.
 * Output Format
 * The output contains array of string denoting anagrams that are grouped in seperate lines
 * in lexicographically increasing order.
 * Sample Output
 * ate eat tea
 * bat
 * nat tan
 * Explanation
 * Given, s[i] = eat tea tan ate nat bat
 * 1 st to be grouped is ate eat tea
 * 2 nd to be grouped is bat (no other anagram found)
 * 3 rd to be grouped is nat tan
 */
public class AnagramGrouping {
    public static void anagrams(String[] arr) {
        // Initialize a HashMap to store the sorted string as key and its anagrams as a list of values
        Map<String, List<String>> map = new HashMap<>();

        for (String s : arr) {
            // Convert the string to a char array and sort it
            char[] charArray = s.toCharArray();
            Arrays.sort(charArray);

            // Convert back to string
            String sorted = new String(charArray);

            // If this sorted form is already in map, append the original string to the list
            if (map.containsKey(sorted)) {
                map.get(sorted).add(s);
            } else {
                // If this is a new form, add it to map and initialize the list with this string
                List<String> list = new ArrayList<>();
                list.add(s);
                map.put(sorted, list);
            }
        }

        // Print the grouped anagrams
        for (List<String> anagramGroup : map.values()) {
            // Sort each anagram group in lexicographically increasing order
            anagramGroup.sort(String::compareTo);

            // Print the sorted anagram group as space-separated string
            System.out.println(String.join(" ", anagramGroup));
        }
    }

    public static void main(String[] args) {
        String[] arr = {"ate", "eat", "tea", "nat", "tan", "bat"};
        anagrams(arr);
    }
}

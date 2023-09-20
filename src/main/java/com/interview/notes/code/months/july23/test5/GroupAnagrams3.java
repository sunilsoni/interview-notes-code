package com.interview.notes.code.months.july23.test5;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class GroupAnagrams3 {
    public static void main(String[] args) {
        String[] words = {"tea", "eat", "apple", "ate", "vaja", "cut", "java", "utc"};
        System.out.println(solution(words));
    }

    public static int solution(String[] words) {
        Map<String, Integer> count = new HashMap<>();
        for (String word : words) {
            char[] chars = word.toCharArray();
            Arrays.sort(chars);
            String sorted = new String(chars);
            count.put(sorted, count.getOrDefault(sorted, 0) + 1);
        }
        return count.size();
    }
}

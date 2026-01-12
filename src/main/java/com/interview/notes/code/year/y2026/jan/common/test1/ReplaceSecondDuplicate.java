package com.interview.notes.code.year.y2026.jan.common.test1;

public class ReplaceSecondDuplicate {
    public static void main(String[] args) {
        String s = "hello";
        StringBuilder result = new StringBuilder();
        java.util.Map<Character, Integer> countMap = new java.util.HashMap<>();

        for (char c : s.toCharArray()) {
            countMap.put(c, countMap.getOrDefault(c, 0) + 1);
            if (countMap.get(c) == 2) {
                result.append('x');   // replace second duplicate with X
            } else {
                result.append(c);
            }
        }

        System.out.println(result);  // Output: helxo
    }
}

package com.interview.notes.code.months.jan24.test14;

import java.util.*;

public class CommonSubstringChecker2 {
    
    public static void commonSubstring(List<String> a, List<String> b) {
        for (int i = 0; i < a.size(); i++) {
            if (hasCommonSubstring(a.get(i), b.get(i))) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }

    private static boolean hasCommonSubstring(String s1, String s2) {
        Set<Character> set = new HashSet<>();
        for (char c : s1.toCharArray()) {
            set.add(c);
        }
        for (char c : s2.toCharArray()) {
            if (set.contains(c)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        List<String> a = Arrays.asList("ab", "cd", "ef");
        List<String> b = Arrays.asList("af", "ee", "ef");
        commonSubstring(a, b);

        // Add more test cases if necessary
        List<String> a2 = Arrays.asList("hello", "hi");
        List<String> b2 = Arrays.asList("world", "bye");
        commonSubstring(a2, b2);
    }
}

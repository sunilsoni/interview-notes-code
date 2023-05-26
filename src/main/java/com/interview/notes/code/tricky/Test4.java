package com.interview.notes.code.tricky;

import java.util.HashSet;
import java.util.Set;

public class Test4 {
    public static void main(String[] args) {
        System.out.println(solution3("abcabba"));
        System.out.println(solution3("abcabba"));

    }


    public static int solution1(String s) {
        int n = s.length();
        int maxLen = 0;
        int i = 0;
        int j = 0;
        HashSet<Character> set = new HashSet<>();

        while (i < n && j < n) {
            if (!set.contains(s.charAt(j))) {
                set.add(s.charAt(j++));
                maxLen = Math.max(maxLen, j - i);
            } else {
                set.remove(s.charAt(i++));
            }
        }

        return maxLen;
    }

    public static int solution2(String s) {
        int n = s.length();
        int ans = 0;
        Set<Character> set = new HashSet<>();
        int i = 0, j = 0;
        while (i < n && j < n) {
            if (!set.contains(s.charAt(j))) {
                set.add(s.charAt(j++));
                ans = Math.max(ans, j - i);
            } else {
                set.remove(s.charAt(i++));
            }
        }
        return ans;
    }

    public static int solution3(String s) {
        int n = s.length();
        int ans = 0;
        Set<Character> set = new HashSet<>();
        int i = 0, j = 0;
        while (i < n && j < n) {
            if (!set.contains(s.charAt(j))) {
                set.add(s.charAt(j++));
                ans = Math.max(ans, j - i);
            } else {
                set.remove(s.charAt(i++));
            }
        }
        return ans;
    }

}

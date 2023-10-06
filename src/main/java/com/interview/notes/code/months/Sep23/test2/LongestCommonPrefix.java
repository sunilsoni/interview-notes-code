package com.interview.notes.code.months.Sep23.test2;

import java.util.Arrays;
import java.util.List;

public class LongestCommonPrefix {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("fly", "flow", "flight");
        System.out.println(longestCommonPrefix(list));  // Output: fl
    }

    public static String longestCommonPrefix(List<String> list) {
        if (list == null || list.size() == 0) return "";

        // Sort the list
        list.sort(String::compareTo);

        String first = list.get(0);
        String last = list.get(list.size() - 1);

        int i = 0;
        while (i < first.length() && i < last.length() && first.charAt(i) == last.charAt(i)) {
            i++;
        }
        return first.substring(0, i);
    }
}

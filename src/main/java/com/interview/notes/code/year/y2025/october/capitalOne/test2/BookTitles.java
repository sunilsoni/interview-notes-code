package com.interview.notes.code.year.y2025.october.capitalOne.test2;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class BookTitles {

    static long solution(String[] titles) {
        Arrays.sort(titles);
        long count = 0L;
        int n = titles.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                // since sorted, if titles[j] doesn't start with titles[i], 
                // no later strings will match either
                if (titles[j].startsWith(titles[i]) || titles[i].startsWith(titles[j])) count++;
                else break;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        List<String[]> tests = Arrays.asList(
            new String[]{"wall", "wallpaper", "science", "wallet", "philosophy", "phil", "book"},
            new String[]{"abc", "a", "a", "b", "ab", "ac"},
            new String[]{"a", "a"},
            new String[]{"a", "ab", "abc", "b"},
            new String[]{"z", "y", "x"},
            new String[]{"aa", "aa", "aa", "a", "aaa"}
        );

        List<Long> expected = Arrays.asList(3L, 8L, 1L, 3L, 0L, 9L);

        IntStream.range(0, tests.size()).forEach(i -> {
            long result = solution(tests.get(i));
            System.out.println("Test " + (i + 1) + ": " + (result == expected.get(i) ? "PASS" : "FAIL")
                    + " | Output=" + result + " | Expected=" + expected.get(i));
        });

        int n = 100000;
        String[] large = new String[n];
        for (int i = 0; i < n; i++) large[i] = "a".repeat(1 + (i % 10));
        long start = System.currentTimeMillis();
        long result = solution(large);
        long end = System.currentTimeMillis();
        System.out.println("Large data test executed in " + (end - start) + " ms | Result=" + result);
    }
}
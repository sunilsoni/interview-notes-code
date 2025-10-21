package com.interview.notes.code.year.y2025.october.capitalOne.test1;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class BookTitles {

    static long solution(String[] titles) {
        Arrays.sort(titles);
        long count = 0;
        for (int i = 0; i < titles.length; i++) {
            for (int j = i + 1; j < titles.length; j++) {
                if (titles[j].startsWith(titles[i])) count++;
                else break;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        List<String[]> tests = Arrays.asList(
            new String[]{"wall", "wallpaper", "science", "wallet", "philosophy", "phil", "book"},
            new String[]{"abc", "a", "a", "b", "ab", "ac"}
        );

        List<Long> expected = Arrays.asList(3L, 8L);

        IntStream.range(0, tests.size()).forEach(i -> {
            long result = solution(tests.get(i));
            System.out.println("Test " + (i + 1) + ": " + (result == expected.get(i) ? "PASS" : "FAIL")
                    + " | Output=" + result + " | Expected=" + expected.get(i));
        });

        String[] large = IntStream.range(0, 100000).mapToObj(i -> "book" + i).toArray(String[]::new);
        long start = System.currentTimeMillis();
        long result = solution(large);
        long end = System.currentTimeMillis();
        System.out.println("Large data test executed in " + (end - start) + " ms | Result=" + result);
    }
}
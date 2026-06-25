package com.interview.notes.code.year.y2026.june.common.test2;

import java.util.Arrays;

public class RemoveArrayDuplicates {

    static String[] removeDuplicates(String[] values) {
        return Arrays.stream(values).distinct().toArray(String[]::new);
    }

    static void test(String[] input, String[] expected) {
        String[] actual = removeDuplicates(input);

        System.out.println(
                Arrays.equals(actual, expected)
                        ? "PASS"
                        : "FAIL"
        );

        System.out.println(Arrays.toString(actual));
    }

    public static void main(String[] args) {
        test(
                new String[]{"a", "b", "c", "b", "a", "d"},
                new String[]{"a", "b", "c", "d"}
        );

        test(
                new String[]{"a", "a", "a"},
                new String[]{"a"}
        );

        test(
                new String[]{},
                new String[]{}
        );
    }
}
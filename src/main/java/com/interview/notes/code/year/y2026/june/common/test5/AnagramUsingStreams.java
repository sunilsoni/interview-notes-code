package com.interview.notes.code.year.y2026.june.common.test5;

public class AnagramUsingStreams {

    static boolean isAnagram(String first, String second) {

        return first != null
                && second != null
                && first.length() == second.length()
                && first.chars()
                        .sorted()
                        .boxed()
                        .toList()
                        .equals(
                                second.chars()
                                        .sorted()
                                        .boxed()
                                        .toList()
                        );
    }

    static void test(String first, String second, boolean expected) {

        boolean actual = isAnagram(first, second);

        System.out.println(
                actual == expected ? "PASS" : "FAIL"
        );
    }

    public static void main(String[] args) {

        test("manoj", "anjom", true);

        test("java", "avaj", true);

        test("java", "python", false);

        test("", "", true);

        test(null, "abc", false);

        test(
                "a".repeat(100_000) + "b",
                "b" + "a".repeat(100_000),
                true
        );
    }
}
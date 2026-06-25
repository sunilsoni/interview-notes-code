package com.interview.notes.code.year.y2026.june.common.test3;

public class CharacterRepeater {

    static String expand(String input) {
        StringBuilder result = new StringBuilder();
        int count = 0;

        for (char ch : input.toCharArray()) {
            if (Character.isDigit(ch)) {
                count = count * 10 + ch - '0';
            } else {
                while (count-- > 0) {
                    result.append(ch);
                }
                count = 0;
            }
        }

        return result.toString();
    }

    static void test(String input, String expected) {
        String actual = expand(input);

        System.out.println(
                expected.equals(actual)
                        ? "PASS: " + actual
                        : "FAIL: " + actual
        );
    }

    public static void main(String[] args) {
        test("3A2C0D", "AAACC");
        test("1A4B", "ABBBB");
        test("0A2B", "BB");
        test("10X", "XXXXXXXXXX");
        test("", "");
    }
}
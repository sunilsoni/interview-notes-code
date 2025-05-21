package com.interview.notes.code.year.y2025.may.common.test10;

public class RemoveAdjacentDuplicatesSimple {

    // Remove all adjacent duplicates using a StringBuilder as a stack
    public static String removeAdjDuplicates(String input) {
        StringBuilder sb = new StringBuilder();               // mutable buffer for result
        for (char c : input.toCharArray()) {                  // loop over each character
            if (sb.length() > 0 && sb.charAt(sb.length() - 1) == c) {
                sb.deleteCharAt(sb.length() - 1);             // pop last if it matches current
            } else {
                sb.append(c);                                 // else push current
            }
        }
        return sb.toString();                                 // build final string
    }

    // Simple tester that prints PASS or FAIL
    private static void test(String input, String expected) {
        String result = removeAdjDuplicates(input);           // run the method
        boolean pass = result.equals(expected);               // compare to expected
        System.out.printf("\"%s\" -> %s (expected=\"%s\", got=\"%s\")%n",
                          input, pass ? "PASS" : "FAIL", expected, result);
    }

    public static void main(String[] args) {
        // provided examples
        test("abbccdd", "a");
        test("bccb", "");
        test("xwxx", "xw");
        test("xwxw", "xwxw");

        // edge cases
        test("", "");
        test("a", "a");
        test("aa", "");

        // large input: repeat "ab" 50k times (no duplicates)
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 50000; i++) {
            builder.append("ab");                            // build large test string
        }
        String large = builder.toString();
        test(large, large);
    }
}
package com.interview.notes.code.year.y2025.march.amazon.test12;

import java.util.*;
import java.util.stream.Collectors;

public class BoxIdMinimizer {

    public static String getMinimumString(String s_id) {
        List<Character> modified = new ArrayList<>();
        List<Character> untouched = new ArrayList<>();

        for (char c : s_id.toCharArray()) {
            while (!untouched.isEmpty() && untouched.get(untouched.size() - 1) > c) {
                char removed = untouched.remove(untouched.size() - 1);
                modified.add((char) Math.min(removed + 1, '9'));
            }
            untouched.add(c);
        }

        List<Character> result = new ArrayList<>(untouched);
        modified.sort(Comparator.naturalOrder());
        result.addAll(modified);
        return result.stream().map(String::valueOf).collect(Collectors.joining());
    }

    public static void main(String[] args) {
        // Provided test cases
        runTest("04829", "02599");
        runTest("34892", "24599");
        runTest("26547", "24677");

        // Additional test cases
        runTest("99999", "99999");
        runTest("00000", "00000");
        runTest("1234567890", "1234567891");
        runTest("9876543210", "1234567899");

        // Large input test
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 200000; i++) sb.append("5");
        runTest(sb.toString(), sb.toString());

        // Edge case
        runTest("5", "5");
    }

    private static void runTest(String input, String expected) {
        String output = getMinimumString(input);
        System.out.println("Input: " + input);
        System.out.println("Output: " + output);
        System.out.println(output.equals(expected) ? "PASS" : "FAIL");
        System.out.println("----------------------------");
    }
}

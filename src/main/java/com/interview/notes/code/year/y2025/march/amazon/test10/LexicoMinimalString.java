package com.interview.notes.code.year.y2025.march.amazon.test10;

import java.util.*;

public class LexicoMinimalString {
    public static String getMinimumString(String s_id) {
        char[] digits = s_id.toCharArray();
        int n = digits.length;

        Deque<Character> stack = new ArrayDeque<>();
        char[] minRight = new char[n];
        minRight[n - 1] = digits[n - 1];

        // Calculate minimum digit from the right at each position
        for (int i = n - 2; i >= 0; i--) {
            minRight[i] = (char) Math.min(digits[i], minRight[i + 1]);
        }

        StringBuilder result = new StringBuilder();
        PriorityQueue<Character> pq = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            pq.offer(digits[i]);
            while (!pq.isEmpty() && (i == n - 1 || pq.peek() <= minRight[i + 1])) {
                result.append(pq.poll());
            }
        }

        while (!pq.isEmpty()) {
            result.append(pq.poll());
        }

        return result.toString();
    }

    // Simple main method to test without JUnit
    public static void main(String[] args) {
        test("04829", "02599");
        test("34892", "24599");
        test("26547", "24677");
        test("11111", "11111"); // Edge: identical digits
        test("01234", "01234"); // Edge: already minimal

        // Large test case
        StringBuilder largeInput = new StringBuilder();
        for (int i = 0; i < 200000; i++) {
            largeInput.append((char)('9' - (i % 10)));
        }
        String largeResult = getMinimumString(largeInput.toString());
        System.out.println("Large test case result length: " + largeResult.length());
    }

    static void test(String input, String expected) {
        String result = getMinimumString(input);
        System.out.println("Input: " + input 
            + " | Expected: " + expected 
            + " | Result: " + result 
            + " | " + (result.equals(expected) ? "PASS" : "FAIL"));
    }
}

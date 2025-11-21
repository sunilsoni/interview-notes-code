package com.interview.notes.code.year.y2025.march.caspex.test11;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Main {
    public static int maxArea(List<Integer> B) {
        Stack<Integer> stack = new Stack<>();
        int max = 0, n = B.size();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && B.get(stack.peek()) > B.get(i)) {
                int h = B.get(stack.pop());
                int r = i - 1;
                int l = stack.isEmpty() ? 0 : stack.peek() + 1;
                max = Math.max(max, h * (r - l + 1));
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int h = B.get(stack.pop());
            int r = n - 1;
            int l = stack.isEmpty() ? 0 : stack.peek() + 1;
            max = Math.max(max, h * (r - l + 1));
        }
        return max;
    }

    public static void main(String[] args) {
        Object[][] tests = {
                new Object[]{Arrays.asList(7, 3, 5, 6, 0, 7), 15},
                new Object[]{Arrays.asList(9, 1, 1, 9), 9},
                new Object[]{Arrays.asList(2, 2, 2, 2), 8},
                new Object[]{Arrays.asList(0, 0, 0), 0},
                new Object[]{List.of(5), 5}
        };
        for (Object[] t : tests) {
            @SuppressWarnings("unchecked")
            List<Integer> input = (List<Integer>) t[0];
            int expected = (int) t[1];
            int result = maxArea(input);
            System.out.println(
                    "Input: " + input +
                            ", Output: " + result +
                            ", " + (result == expected ? "PASS" : "FAIL (Expected " + expected + ")")
            );
        }
    }
}

package com.interview.notes.code.year.y2025.march.caspex.test9;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static int solve(String S) {
        List<Integer> nums = Arrays.stream(S.split("\\D+"))
                .filter(s -> !s.isEmpty())
                .map(Integer::parseInt)
                .filter(x -> (x & 1) == 1)
                .collect(Collectors.toList());
        return nums.isEmpty() ? -1 : Collections.max(nums);
    }

    public static void main(String[] args) {
        String[][] tests = {
                {"gt12cty65mt1", "65"},
                {"mkf43kd1cmk32k1mv123", "123"},
                {"abc", "-1"},
                {"1abc9", "9"},
                {"2468", "-1"}
        };
        for (String[] t : tests) {
            int res = solve(t[0]);
            int exp = Integer.parseInt(t[1]);
            System.out.println(
                    "Input: " + t[0] +
                            ", Output: " + res +
                            ", " + (res == exp ? "PASS" : "FAIL (Expected " + exp + ")")
            );
        }
    }
}

package com.interview.notes.code.year.y2025.June.common.test9;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class RobotMovement {

    public static String solution(String commands) {
        // Count net position changes
        long up = commands.chars().filter(c -> c == 'U').count();
        long down = commands.length() - up;
        long net = up - down;

        if (net == 0) return "";
        return net > 0 ? "U" : "D";
    }

    public static void main(String[] args) {
        Map<String, String> testCases = new LinkedHashMap<>();

        // Sample tests
        testCases.put("UDUDDD", "D");
        testCases.put("DDUUUDDUUUU", "");

        // Edge case: 1 character
        testCases.put("U", "U");
        testCases.put("D", "D");

        // Large input: equal U and D
        String largeBalanced = String.join("", Collections.nCopies(500, "U")) +
                String.join("", Collections.nCopies(500, "D"));
        testCases.put(largeBalanced, "");

        // Large input: 999 D, 1 U
        String largeUnbalanced = String.join("", Collections.nCopies(999, "D")) + "U";
        testCases.put(largeUnbalanced, "D");

        int pass = 0;
        int total = testCases.size();
        for (Map.Entry<String, String> entry : testCases.entrySet()) {
            String input = entry.getKey();
            String expected = entry.getValue();
            String actual = solution(input);
            if (expected.equals(actual)) {
                System.out.println("PASS");
                pass++;
            } else {
                System.out.println("FAIL");
                System.out.println("Input   : " + input);
                System.out.println("Expected: " + expected);
                System.out.println("Actual  : " + actual);
            }
        }
        System.out.println("Result: " + pass + "/" + total + " test cases passed.");
    }
}
package com.interview.notes.code.year.y2025.June.common.test8;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class RobotMovement {

    public static String solution(String commands) {
        long up = commands.chars().filter(c -> c == 'U').count();
        long down = commands.length() - up;
        long net = down - up;

        if (net == 0) return "";
        return net > 0 ? "D" : "U";
    }

    public static void main(String[] args) {
        Map<String, String> testCases = new LinkedHashMap<>();

        testCases.put("UDUDDD", "D");
        testCases.put("DDUUUDDUUUU", "");
        testCases.put("U", "U");
        testCases.put("D", "D");

        String largeBalanced = String.join("", Collections.nCopies(500, "U")) +
                String.join("", Collections.nCopies(500, "D"));
        testCases.put(largeBalanced, "");

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
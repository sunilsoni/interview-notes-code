package com.interview.notes.code.year.y2025.June.common.test6;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class RobotDirection {

    /**
     * Returns "U", "D", or "" according to the final height.
     */
    static String solution(String commands) {
        // count 'U' with a stream; the rest are 'D'
        long ups = commands.chars().filter(c -> c == 'U').count();
        long downs = commands.length() - ups;

        if (ups > downs) return "U";
        if (downs > ups) return "D";
        return "";
    }

    /**
     * Simple main method that runs built-in test cases and prints PASS / FAIL.
     */
    public static void main(String[] args) {
        List<String> inputs = Arrays.asList(
                "UDUDDD",          // sample 1 → D
                "DDUUUDDUUUU",     // sample 2 → ""
                "",                // empty string edge case → ""
                "UUU",             // only up → U
                "DDD",             // only down → D
                generateLarge(1000) // 500 U + 500 D → ""
        );

        List<String> expected = Arrays.asList("D", "", "", "U", "D", "");

        for (int i = 0; i < inputs.size(); i++) {
            String out = solution(inputs.get(i));
            boolean ok = out.equals(expected.get(i));
            System.out.printf("Test %02d : %s  (expected \"%s\", got \"%s\")%n",
                    i + 1, ok ? "PASS" : "FAIL", expected.get(i), out);
        }
    }

    /**
     * Builds a long test string: first half 'U', second half 'D'.
     */
    private static String generateLarge(int n) {
        StringBuilder sb = new StringBuilder(n);
        IntStream.range(0, n / 2).forEach(i -> sb.append('U'));
        IntStream.range(0, n - n / 2).forEach(i -> sb.append('D'));
        return sb.toString();
    }
}

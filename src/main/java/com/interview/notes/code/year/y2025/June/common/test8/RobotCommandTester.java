package com.interview.notes.code.year.y2025.June.common.test8;

import java.util.*;
import java.util.stream.*;

public class RobotCommandTester {

    // Returns "U", "D", or "" depending on final position
    public static String solution(String commands) {
        int net = commands.chars()
                          .map(c -> c == 'U' ? 1 : -1)
                          .sum();
        if (net > 0) return "U";
        if (net < 0) return "D";
        return "";
    }

    public static void main(String[] args) {
        // list of (input, expected) pairs
        List<Map.Entry<String,String>> tests = Arrays.asList(
            new AbstractMap.SimpleEntry<>("UDUDDD", "D"),
            new AbstractMap.SimpleEntry<>("DDUUUDDUUUU", ""),
            new AbstractMap.SimpleEntry<>("", ""),               // edge: empty
            new AbstractMap.SimpleEntry<>("U", "U"),
            new AbstractMap.SimpleEntry<>("D", "D"),
            new AbstractMap.SimpleEntry<>("UUUU", "U"),
            new AbstractMap.SimpleEntry<>("DDDD", "D")
        );

        // run small tests
        int passCount = 0;
        for (int i = 0; i < tests.size(); i++) {
            String input = tests.get(i).getKey();
            String expected = tests.get(i).getValue();
            String actual = solution(input);
            boolean ok = Objects.equals(actual, expected);
            System.out.printf("Test %02d: %-10s  Input=\"%s\"\n",
                              i+1,
                              ok ? "PASS" : "FAIL",
                              input);
            if (ok) passCount++;
        }
        System.out.printf("Small tests passed: %d/%d%n",
                          passCount, tests.size());

        // large-input stress test
        int N = 1_000_000;  // 1 million commands
        String large = Stream.generate(() -> "UD")
                             .limit(N/2)
                             .collect(Collectors.joining());
        // this will net to zero → expect ""
        long start = System.currentTimeMillis();
        String result = solution(large);
        long time = System.currentTimeMillis() - start;
        boolean largeOk = result.equals("");
        System.out.printf("Large test (%d chars): %s in %dms%n",
                          large.length(),
                          largeOk ? "PASS" : "FAIL",
                          time);
    }
}
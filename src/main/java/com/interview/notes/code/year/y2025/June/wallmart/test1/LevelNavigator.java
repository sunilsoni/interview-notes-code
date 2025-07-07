package com.interview.notes.code.year.y2025.June.wallmart.test1;

import java.util.*;
import java.util.stream.*;

public class LevelNavigator {

    /**
     * Returns true if following `instr` navigates from 0 to 10
     * without hitting obstacles or falling off [0..10].
     */
    public static boolean level(List<Integer> obstacles, String instr) {
        // Convert obstacle list to HashSet for O(1) checks
        Set<Integer> obs = obstacles.stream()
                                    .collect(Collectors.toSet());
        int pos = 0;                // current position
        char lastMove = 'R';        // the first move is always R by rule

        for (char c : instr.toCharArray()) {
            int delta;              // how much to move this step

            if (c == 'L') {
                delta = -1;         // move left
                lastMove = 'L';     // update direction for future J
            } else if (c == 'R') {
                delta = +1;         // move right
                lastMove = 'R';     // update direction for future J
            } else if (c == 'J') {
                // jump two in direction of lastMove
                delta = (lastMove == 'L' ? -2 : +2);
            } else {
                // invalid instruction—fail fast
                throw new IllegalArgumentException("Invalid instruction: " + c);
            }

            pos += delta;           // apply the move

            // check falling off the level
            if (pos < 0 || pos > 10) {
                return false;
            }
            // check obstacle collision
            if (obs.contains(pos)) {
                return false;
            }
            // early success if we hit the exit
            if (pos == 10) {
                return true;
            }
        }

        // final check: must end exactly at 10
        return pos == 10;
    }

    public static void main(String[] args) {
        // define test cases: {obstacles, instructions, expectedResult}
        Object[][] tests = {
            {Arrays.asList(4, 6), "RRRJJRRR", true},
            {Arrays.asList(4, 6), "RRRLJ",     false},
            {Arrays.asList(4, 6), "RRRJJRRL", true},
            {Arrays.asList(4, 6), "RRRLRJJRRR", true},
            {Arrays.asList(4, 6), "RRRRRRRRRR", false},
            {Arrays.asList(4, 6), "RRJJJ",     false},
            {Arrays.asList(4, 6), "RLRRRLRJLRJLLJJJLRRR", true},
            {Arrays.asList(4, 6), "RRLRRLJJJRRR", false},
            {Arrays.asList(4, 6), "R",          false},
            {Arrays.asList(4, 6), "RJJJR",      true},
            {Arrays.asList(9, 4, 2), "RJJRRRRR", false},
            {Arrays.asList(9, 4, 2), "RJJRRRJ",  true},
            {Arrays.asList(9, 4, 2), "RJJJLJRJR",false},
            {Collections.emptyList(),  "R",     false},
        };

        // run and report
        for (int i = 0; i < tests.length; i++) {
            List<Integer> obs = (List<Integer>) tests[i][0];
            String instr       = (String)      tests[i][1];
            boolean expected   = (Boolean)     tests[i][2];
            boolean result     = level(obs, instr);
            String status      = (result == expected) ? "PASS" : "FAIL";
            System.out.printf("Test %2d: %s (got=%b, expected=%b)%n",
                              i+1, status, result, expected);
        }

        // large random test for performance
        Random rnd = new Random(0);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1_000_000; i++) {
            sb.append("LRJ".charAt(rnd.nextInt(3)));
        }
        boolean perfResult = level(Collections.emptyList(), sb.toString());
        System.out.println("Large test completed, result=" + perfResult);
    }
}
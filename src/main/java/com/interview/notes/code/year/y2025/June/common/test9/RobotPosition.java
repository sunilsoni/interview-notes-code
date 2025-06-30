package com.interview.notes.code.year.y2025.June.common.test9;

import java.util.stream.IntStream;

public class RobotPosition {

    public static String solution(String commands) {
        // Calculate the final position
        int position = commands.chars()
                .map(c -> c == 'U' ? 1 : -1)
                .sum();

        // Determine result based on final position
        return position > 0 ? "U" : position < 0 ? "D" : "";
    }

    // Simple main method for testing
    public static void main(String[] args) {

        // Provided test cases
        test("UDUDDD", "D");
        test("DDUUUDDUUUU", "");

        // Additional edge cases
        test("UUUUU", "U");
        test("DDDDD", "D");
        test("UDUDUDUDUD", "");

        // Large input test case
        String largeCommands = IntStream.range(0, 500)
                .mapToObj(i -> "UD")
                .reduce("", String::concat);

        test(largeCommands, "");

        largeCommands = IntStream.range(0, 1000)
                .mapToObj(i -> "U")
                .reduce("", String::concat);

        test(largeCommands, "U");

        largeCommands = IntStream.range(0, 1000)
                .mapToObj(i -> "D")
                .reduce("", String::concat);

        test(largeCommands, "D");
    }

    // Helper method to perform tests
    private static void test(String commands, String expected) {
        String result = solution(commands);
        if (result.equals(expected)) {
            System.out.println("PASS");
        } else {
            System.out.println("FAIL\nInput   : " + commands + "\nExpected: " + expected + "\nActual  : " + result);
        }
    }
}

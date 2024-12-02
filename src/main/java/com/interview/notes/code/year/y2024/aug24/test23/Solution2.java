package com.interview.notes.code.year.y2024.aug24.test23;

import java.util.Arrays;

public class Solution2 {
    public static int[] solution(String[] commands) {
        int[] counts = new int[3]; // [cp, ls, mv]

        for (int i = 0; i < commands.length; i++) {
            executeCommand(commands, i, counts);
        }

        return counts;
    }

    private static void executeCommand(String[] commands, int index, int[] counts) {
        String command = commands[index];

        if (command.equals("cp")) {
            counts[0]++;
        } else if (command.equals("ls")) {
            counts[1]++;
        } else if (command.equals("mv")) {
            counts[2]++;
        } else if (command.startsWith("!")) {
            int referenceIndex = Integer.parseInt(command.substring(1)) - 1;
            executeCommand(commands, referenceIndex, counts);
        }
    }

    public static void main(String[] args) {
        // Test case 1
        String[] commands1 = {"ls", "cp", "mv", "mv", "mv", "!1", "!3", "!6"};
        System.out.println("Test case 1 result: " + Arrays.toString(solution(commands1))); // Expected: [1, 3, 4]

        // Test case 2
        String[] commands2 = {"ls", "cp", "mv", "!3", "mv", "!1", "!6"};
        System.out.println("Test case 2 result: " + Arrays.toString(solution(commands2))); // Expected: [1, 3, 3]

        // Additional test cases
        String[] commands3 = {"cp", "cp", "ls", "mv", "!2", "!5"};
        System.out.println("Test case 3 result: " + Arrays.toString(solution(commands3))); // Expected: [3, 1, 1]

        String[] commands4 = {"ls", "!1", "!2", "!3", "!4"};
        System.out.println("Test case 4 result: " + Arrays.toString(solution(commands4))); // Expected: [0, 5, 0]

        String[] commands5 = {"mv", "cp", "ls", "!1", "!2", "!3"};
        System.out.println("Test case 5 result: " + Arrays.toString(solution(commands5))); // Expected: [2, 2, 2]
    }
}

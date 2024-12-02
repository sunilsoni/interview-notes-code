package com.interview.notes.code.year.y2024.aug24.test23;

public class Solution1 {
    public static int solution(char[] recording) {
        if (recording.length <= 1) {
            return 0;
        }

        int keyChanges = 0;
        char previousKey = Character.toLowerCase(recording[0]);

        for (int i = 1; i < recording.length; i++) {
            char currentKey = Character.toLowerCase(recording[i]);
            if (currentKey != previousKey) {
                keyChanges++;
                previousKey = currentKey;
            }
        }

        return keyChanges;
    }

    public static void main(String[] args) {
        // Test case 1
        char[] recording1 = {'w', 'w', 'a', 'A', 'a', 'b', 'B'};
        System.out.println("Test case 1 result: " + solution(recording1)); // Expected: 2

        // Test case 2
        char[] recording2 = {'w', 'w', 'a', 'w', 'a'};
        System.out.println("Test case 2 result: " + solution(recording2)); // Expected: 3

        // Additional test cases
        char[] recording3 = {'A', 'a', 'a', 'B', 'b', 'C'};
        System.out.println("Test case 3 result: " + solution(recording3)); // Expected: 2

        char[] recording4 = {'Z'};
        System.out.println("Test case 4 result: " + solution(recording4)); // Expected: 0

        char[] recording5 = {'a', 'B', 'c', 'D', 'e'};
        System.out.println("Test case 5 result: " + solution(recording5)); // Expected: 4
    }
}

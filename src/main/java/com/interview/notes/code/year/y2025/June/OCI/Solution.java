package com.interview.notes.code.year.y2025.June.OCI;

public class Solution {
    public static boolean canReachExit(int[] obstacles, String instructions) {
        int position = 0;
        char previousMove = 'R'; // Default direction is right
        final int MAX_POSITION = 10;
        final int MIN_POSITION = 0;

        for (char instruction : instructions.toCharArray()) {
            switch (instruction) {
                case 'R':
                    if (position < MAX_POSITION) {
                        position++;
                    }
                    previousMove = 'R';
                    break;
                    
                case 'L':
                    if (position > MIN_POSITION) {
                        position--;
                    }
                    previousMove = 'L';
                    break;
                    
                case 'J':
                    // Jump 2 positions based on previous direction
                    if (previousMove == 'R') {
                        position = Math.min(position + 2, MAX_POSITION);
                    } else { // previousMove == 'L'
                        position = Math.max(position - 2, MIN_POSITION);
                    }
                    break;
            }

            // Check for obstacles after each move
            for (int obstacle : obstacles) {
                if (position == obstacle) {
                    return false;
                }
            }

            // Success condition
            if (position == MAX_POSITION) {
                return true;
            }
        }

        // Check final position
        return position == MAX_POSITION;
    }

    public static void main(String[] args) {
        int[] obstacles_1 = {4, 6};
        int[] obstacles_2 = {9, 4, 2};
        int[] obstacles_3 = {};

        String instructions_1 = "RRRJJRRR";
        String instructions_2 = "RRRLJ";
        String instructions_3 = "RRRJJRRRL";
        String instructions_4 = "RRRLRJJRRR";
        String instructions_5 = "RRRRRRRRRR";
        String instructions_6 = "RRJJJJ";
        String instructions_7 = "RLRRRJJRRLLJJJLRRRJJRRR";
        String instructions_8 = "RRRJJRLJJJRRR";
        String instructions_9 = "R";
        String instructions_10 = "RJJJJR";
        String instructions_11 = "RJJRRRRR";
        String instructions_12 = "RJJRRRJ";
        String instructions_13 = "RJJJLJRJRJ";

        // Test with obstacles_1
        System.out.println("Testing with obstacles_1:");
        System.out.println("Test 1: " + canReachExit(obstacles_1, instructions_1));
        System.out.println("Test 2: " + canReachExit(obstacles_1, instructions_2));
        System.out.println("Test 3: " + canReachExit(obstacles_1, instructions_3));
        System.out.println("Test 4: " + canReachExit(obstacles_1, instructions_4));
        System.out.println("Test 5: " + canReachExit(obstacles_1, instructions_5));
        System.out.println("Test 6: " + canReachExit(obstacles_1, instructions_6));
        System.out.println("Test 7: " + canReachExit(obstacles_1, instructions_7));
        System.out.println("Test 8: " + canReachExit(obstacles_1, instructions_8));
        System.out.println("Test 9: " + canReachExit(obstacles_1, instructions_9));
        System.out.println("Test 10: " + canReachExit(obstacles_1, instructions_10));
        System.out.println("Test 11: " + canReachExit(obstacles_1, instructions_11));
        System.out.println("Test 12: " + canReachExit(obstacles_1, instructions_12));
        System.out.println("Test 13: " + canReachExit(obstacles_1, instructions_13));

        // Test with obstacles_2
        System.out.println("\nTesting with obstacles_2:");
        System.out.println("Test 1: " + canReachExit(obstacles_2, instructions_1));
        // ... (repeat for all instructions)

        // Test with obstacles_3
        System.out.println("\nTesting with obstacles_3:");
        System.out.println("Test 1: " + canReachExit(obstacles_3, instructions_1));
        // ... (repeat for all instructions)
    }
}

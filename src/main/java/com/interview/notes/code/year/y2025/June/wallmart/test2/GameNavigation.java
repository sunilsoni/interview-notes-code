package com.interview.notes.code.year.y2025.June.wallmart.test2;

public class GameNavigation {
    private static final int MIN_POSITION = 0;
    private static final int MAX_POSITION = 10;
    private static final int[] OBSTACLES = {4, 6};

    public static boolean canReachExit(String instructions) {
        int position = 0;
        char previousMove = 'R'; // Default direction is right

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
            if (position == 4 || position == 6) {
                return false;
            }

            // Success condition
            if (position == MAX_POSITION) {
                return true;
            }
        }

        // Check final position
        return position == MAX_POSITION;
    }

    public static void main1(String[] args) {
        // Test cases
        System.out.println("Test 5: " + canReachExit("RRRRRRRRRR")); // False
        System.out.println("Test 7: " + canReachExit("RLRRRJJRRLLJJJLRRRJJRRR")); // True
    }
}

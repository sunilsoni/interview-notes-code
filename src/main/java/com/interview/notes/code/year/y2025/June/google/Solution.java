package com.interview.notes.code.year.y2025.June.google;

/*

### 🚀 Problem: Navigate Through the Level

You are working on a video game where the player has to go through a level without falling into any obstacles.

* The player starts at position `0` and the exit is **always at position `10`**.
* The player can move using the following instructions:

  * `L` (left) → move 1 position to the left
  * `R` (right) → move 1 position to the right
  * `J` (jump) → jump 2 positions **in the direction of the previous move**

The rules:

* The first move is always `R` (right).
* Instructions never move the player outside the level (i.e., positions 0–10).
* If the player lands on an obstacle, they lose.
* Return `True` if the player reaches position `10` safely, else `False`.

---

### 🧱 Example Obstacles and Instructions

#### Obstacles: `[4, 6]`

```
Positions:  0 1 2 3 4 5 6 7 8 9 10
            - - - - X - X - - - E
```

#### Instructions 1: `"RRRJJRRR"` → ✅ True

Explanation:

* Moves → R R R J J R R R → lands at position 10 avoiding obstacles at 4 and 6.

---

### ✅ All Test Cases:

```
obstacles_1 = [4, 6]
obstacles_2 = [9, 4, 2]
obstacles_3 = []

assert level(obstacles_1, "RRRJJRRR") == True     # Test 1
assert level(obstacles_1, "RRRLJ") == False       # Test 2
assert level(obstacles_1, "RRRJJRRL") == True     # Test 3
assert level(obstacles_1, "RRRLRJJRRR") == True   # Test 4
assert level(obstacles_1, "RRRRRRRRRR") == False  # Test 5
assert level(obstacles_1, "RRJJJ") == False       # Test 6
assert level(obstacles_1, "RLRRRLRJLRJLLJJJLRRR") == True # Test 7
assert level(obstacles_1, "RRLRRLJJJRRR") == False        # Test 8
assert level(obstacles_1, "R") == False                   # Test 9
assert level(obstacles_1, "RJJJR") == True                # Test 10
assert level(obstacles_2, "RJJRRRRR") == False            # Test 11
assert level(obstacles_2, "RJJRRRJ") == True              # Test 12
assert level(obstacles_2, "RJJJLJRJR") == False           # Test 13
assert level(obstacles_3, "R") == False                   # Test 14
```

---

### 📌 Notes:

* Complexity variable: `N` = number of instructions
* Make sure to follow jump direction rules (`J` depends on last move).
* Jump skips 2 positions in the direction of last valid `L` or `R`.






 */
public class Solution {
    public static boolean level(int[] obstacles, String instructions) {
        int position = 0;
        char previousMove = 'R';
        final int MAX_POSITION = 10;

        for (char instruction : instructions.toCharArray()) {
            switch (instruction) {
                case 'R':
                    position++;
                    previousMove = 'R';
                    break;
                case 'L':
                    if (position > 0) {
                        position--;
                        previousMove = 'L';
                    }
                    break;
                case 'J':
                    if (previousMove == 'R') {
                        position += 2;
                    } else {
                        position = Math.max(0, position - 2);
                    }
                    break;
            }

            position = Math.min(position, MAX_POSITION);

            // check for obstacles
            for (int obstacle : obstacles) {
                if (position == obstacle) {
                    return false;
                }
            }

            // immediately succeed once exit is reached
            if (position == MAX_POSITION) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int[] obstacles_1 = {4, 6};
        int[] obstacles_2 = {9, 4, 2};
        int[] obstacles_3 = {};

        testCase("Test 1", obstacles_1, "RRRJJRRR", true);
        testCase("Test 2", obstacles_1, "RRRLJ", false);
        testCase("Test 3", obstacles_1, "RRRJJRRRL", true);
        testCase("Test 4", obstacles_1, "RRRLRJJRRR", true);
        testCase("Test 5", obstacles_1, "RRRRRRRRRR", false);
        testCase("Test 6", obstacles_1, "RRJJJJ", false);
        testCase("Test 7", obstacles_1, "RLRRRJJRRLLJJJLRRRJJRRR", true);
        testCase("Test 8", obstacles_1, "RRRJJRLJJJRRR", false);
        testCase("Test 9", obstacles_1, "R", false);
        testCase("Test 10", obstacles_1, "RJJJJR", true);
        testCase("Test 11", obstacles_2, "RJJRRRRR", false);
        testCase("Test 12", obstacles_2, "RJJRRRJ", true);
        testCase("Test 13", obstacles_2, "RJJJLJRJRJ", false);
        testCase("Test 14", obstacles_3, "R", false);
    }

    private static void testCase(String testName, int[] obstacles, String instructions, boolean expected) {
        boolean result = level(obstacles, instructions);
        boolean passed = result == expected;
        System.out.printf("%-8s | Instructions: %-20s | Expected: %-5b | Got: %-5b | %s%n",
                testName, instructions, expected, result, passed ? "PASS ✅" : "FAIL ❌");
    }
}
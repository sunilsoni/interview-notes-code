package com.interview.notes.code.months.nov24.CodeSignal;

/*
WORKING


**Function Signature**
```java
String solution(String commands) {
    // Implementation here
}
```

**Output**
The function should return a `string`:
- `"D"`, `"U"`, or `""` (an empty string) depending on where the robot stops relative to its starting position.

---

**Problem Description**
A robot responds to two commands:
- `"U"`: Move one step up.
- `"D"`: Move one step down on a vertical line.

You are given a string `commands`, representing a series of `"U"` and `"D"` commands. After responding to all commands in sequence, the robot will stop at a certain position. Your task is to determine the final position relative to the starting point and return:
- `"U"` if the robot stops above its starting position.
- `""` (an empty string) if the robot returns to its starting position.
- `"D"` if the robot stops below its starting position.

**Constraints**
- The solution should have a time complexity of no worse than \(O(\text{commands.length}^2)\), which fits within the execution time limit.

---

**Examples**
1. **Input**: `commands = "UDUDDD"`
   **Output**: `"D"`
   - Explanation:
     - The robot returns to its starting position after executing the first two commands.
     - It returns again after the third and fourth commands.
     - After the final two commands, the robot is two steps below its starting position, so the output is `"D"`.

2. **Input**: `commands = "DDDDDUUUUU"`
   **Output**: `""`
   - Explanation:
     - After all commands, the robot returns to its starting position, so the output is an empty string.

---

**Input/Output**
- **Execution time limit**: 3 seconds (Java)
- **Memory limit**: 1 GB
- **Input**: `commands` — a string of characters `D` and/or `U`.
  - Constraints: \(1 \leq \text{commands.length} \leq 1000\)




 */
public class RobotPositionSolver {

    public static String solution(String commands) {
        if (commands == null || commands.isEmpty()) {
            return "";
        }

        int position = 0;
        for (char command : commands.toCharArray()) {
            if (command == 'U') {
                position++;
            } else if (command == 'D') {
                position--;
            }
        }

        return position > 0 ? "U" : position < 0 ? "D" : "";
    }

    public static void main(String[] args) {
        // Test case class to structure our tests
        class TestCase {
            String commands;
            String expected;
            String description;

            TestCase(String commands, String expected, String description) {
                this.commands = commands;
                this.expected = expected;
                this.description = description;
            }
        }

        // Array of test cases
        TestCase[] testCases = {
                new TestCase("UDUDDD", "D", "Basic test with mixed commands ending below"),
                new TestCase("DDDDDUUUUU", "", "Equal ups and downs"),
                new TestCase("U", "U", "Single up command"),
                new TestCase("D", "D", "Single down command"),
                new TestCase("", "", "Empty string"),
                new TestCase("UUUU", "U", "Multiple ups"),
                new TestCase("DDDD", "D", "Multiple downs"),
                new TestCase("UDUDUDUD", "", "Alternating commands"),
                // Large input test cases
                new TestCase("U".repeat(500) + "D".repeat(500), "", "Large balanced input"),
                new TestCase("U".repeat(1000), "U", "Maximum ups"),
                new TestCase("D".repeat(1000), "D", "Maximum downs"),
                new TestCase("UDUDUDUD".repeat(125), "", "Large alternating sequence")
        };

        // Run all test cases
        int passedTests = 0;
        int totalTests = testCases.length;

        for (int i = 0; i < totalTests; i++) {
            TestCase tc = testCases[i];
            String result = solution(tc.commands);
            boolean passed = result.equals(tc.expected);

            System.out.printf("Test Case %d: %s\n", i + 1, tc.description);
            System.out.printf("Input: %s\n", tc.commands.length() > 50 ?
                    tc.commands.substring(0, 47) + "..." : tc.commands);
            System.out.printf("Expected: %s, Got: %s -> %s\n",
                    tc.expected, result, passed ? "PASS" : "FAIL");
            System.out.println();

            if (passed) passedTests++;
        }

        // Print summary
        System.out.println("Test Summary:");
        System.out.printf("Passed: %d/%d tests\n", passedTests, totalTests);
        System.out.printf("Success Rate: %.2f%%\n", (passedTests * 100.0) / totalTests);
    }
}
package com.interview.notes.code.year.y2024.oct24.test26;

/*
FINAL WORKING : improved solton
Today our world is approaching an ecological crisis.
Due to global warming, the sea level is rising. At the same time, the amount of drinkable water is decreasing. One idea about preventing the loss of drinkable water is the propagation of rainwater storage, in other words, equipping houses with a water tank for rainwater.
You are given a string S describing a street, in which 'H' denotes a house and '-' denotes an empty plot. You may place water tanks in empty plots to collect rainwater from nearby houses. A house can collect its own rainwater if there is a tank next to it (on either the left or the right side).
Your task is to find the minimum number of water tanks needed to collect rainwater from all of the houses.

For example, given S = "-H-HH--", you can collect
rainwater from all three houses by using two water tanks. You can position one water tank between the first and second houses and the other after the third house. This placement of water tanks can be represented as "-HTHHT-", where 'T' denotes a water tank.
Write a function:
class Solution { public int solution (String S); }
that, given a string S of length N, returns the minimum number of water tanks needed.
If there is no solution, return -1.

Examples:
1. Given S = "-H-HH--", the function should return 2, as explained above.
2. Given S = "H", the function should return -1. There is no available plot on which to place a
water tank.
3. Given S = "HH-HH", the function should return -1. There is only one plot to put a water tank,
and it is impossible to collect rainwater from the first and last houses.
4. Given S = "-H-H-H-H-H", the function should return 3. One possible way of placing water
tanks is "-HTH-HTHTH".
Assume that:
• N is an integer within the range [1.20];
• string S is made only of the characters '-' and/or 'H'.
In your solution, focus on correctness. The performance of your solution will not be the focus of the assessment.

public int solution (String S){

}

Examples:
 "" // return 0
 "-" // return 0
 "H" // return -1
 "HHH-" // return -1
 "-HHH" // return -1
 "---HHH---" // return -1
 "-H-H-H-H" // return 2

 */
public class RainwaterTankFinal {
    public static void main(String[] args) {
        RainwaterTankFinal sol = new RainwaterTankFinal();

        // Define test cases
        String[] testCases = {
                "-H-HH--",
                "H",
                "HH-HH",
                "-H-H-H-H-H",
                "",
                "-",
                "H",
                "HHH-",
                "-HHH",
                "---HHH---",
                "-H-H-H-H",
                "H-H-H-H-H"
        };

        // Expected outputs for each test case
        int[] expectedOutputs = {
                2,
                -1,
                -1,
                3,
                0,
                0,
                -1,
                -1,
                -1,
                -1,
                2,
                5
        };

        boolean allPassed = true;

        // Run each test case and compare the result with the expected output
        for (int i = 0; i < testCases.length; i++) {
            int result = sol.solution(testCases[i]);
            if (result == expectedOutputs[i]) {
                System.out.println("Test case " + (i + 1) + " passed.");
            } else {
                System.out.println("Test case " + (i + 1) + " failed. Expected " + expectedOutputs[i] + ", got " + result);
                allPassed = false;
            }
        }

        // Test with large input (maximum size of N=20)
        String largeInput = "H-H-H-H-H-H-H-H-H-H-H-H-H-H-H-H-H-H-H-H";
        int largeResult = sol.solution(largeInput);
        int expectedLargeOutput = 10; // Every 'H' requires a tank, and we can place tanks optimally
        if (largeResult == expectedLargeOutput) {
            System.out.println("Large input test case passed.");
        } else {
            System.out.println("Large input test case failed. Expected " + expectedLargeOutput + ", got " + largeResult);
            allPassed = false;
        }

        // Print final result based on all test cases
        if (allPassed) {
            System.out.println("All test cases passed successfully.");
        } else {
            System.out.println("Some test cases failed.");
        }
    }

    public int solution(String S) {
        int n = S.length();
        int tankCount = 0;
        int lastTankPosition = -2; // Track the position of the last placed tank (initialized to an invalid position)

        // Iterate through each character in the string
        for (int i = 0; i < n; i++) {
            if (S.charAt(i) == 'H') {
                // Check if the house is already served by a tank on the left
                if (i - 1 == lastTankPosition) {
                    // If there is already a tank to the left, skip to the next house
                    continue;
                }
                // Try to place a tank to the right of the house if there is an empty plot
                if (i + 1 < n && S.charAt(i + 1) == '-') {
                    lastTankPosition = i + 1; // Place a tank to the right of the house
                    tankCount++; // Increment the tank count
                    i++; // Skip the next plot since we placed a tank there
                }
                // If no empty plot is available to the right, try to place a tank to the left
                else if (i > 0 && S.charAt(i - 1) == '-') {
                    lastTankPosition = i - 1; // Place a tank to the left of the house
                    tankCount++; // Increment the tank count
                }
                // If no adjacent empty plot is available, it is impossible to serve this house
                else {
                    return -1; // Return -1 indicating that it is not possible to place tanks for all houses
                }
            }
        }
        return tankCount; // Return the total number of tanks placed
    }
}

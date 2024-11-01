package com.interview.notes.code.months.oct24.test30;

/*


Today our world is approaching an ecological crisis.
Due to global warming, the sea level is rising. At the same time, the amount of drinkable water is decreasing. One idea about preventing the loss of drinkable water is the propagation of rainwater storage, in other words, equipping houses with a water tank for rainwater.
You are given a string S describing a street, in which 'H denotes a house and'- denotes an empty plot. You may place water tanks in empty plots to collect rainwater from nearby houses. A house can collect its own rainwater if there is a tank next to it (on either the left or the right side).
Your task is to find the minimum number of water tanks needed to collect rainwater from all of the houses.

For example, given S = "-H-HH--", you can collect
rainwater from all three houses by using two water tanks. You can position one water tank between the first and second houses and the other after the third house. This placement of water tanks can be represented as "-HTHHT-", where 'T' denotes a water tank.
Write a function:
class Solution { public int solution (String S); }
that, given a stringS of length N, returns the minimum number of water tanks needed.
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
• string S is made only of the characters'-' and/or 'H'.
In your solution, focus on correctness. The performance of your solution will not be the focus of the assessment.

public int solution (String S){

}


 Exmaples;
 "" // return 0
"_" // return 0
"H" // return -1
"HHH-" // return -1
"-HHH" // return -1
"---HHH---" // return -1
"-H-H-H-H" // return 2

 */
public class Solution {
    public static void main(String[] args) {
        Solution sol = new Solution();

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
                "-H-H-H-H"
        };

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
                2
        };

        boolean allPassed = true;

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

        if (allPassed) {
            System.out.println("All test cases passed successfully.");
        } else {
            System.out.println("Some test cases failed.");
        }
    }

    public int solution(String S) {
        int n = S.length();
        char[] arr = S.toCharArray();
        int tankCount = 0;

        for (int i = 0; i < n; i++) {
            if (arr[i] == 'H') {
                // Check if the house is already served by a tank on the left
                if (i > 0 && arr[i - 1] == 'T') {
                    continue;
                }
                // Try to place a tank to the right
                if (i + 1 < n && arr[i + 1] == '-') {
                    arr[i + 1] = 'T';
                    tankCount++;
                }
                // Try to place a tank to the left
                else if (i > 0 && arr[i - 1] == '-') {
                    arr[i - 1] = 'T';
                    tankCount++;
                }
                // No place to put a tank adjacent to the house
                else {
                    return -1;
                }
            }
        }
        return tankCount;
    }
}

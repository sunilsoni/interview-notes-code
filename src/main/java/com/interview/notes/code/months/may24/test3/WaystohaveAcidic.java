package com.interview.notes.code.months.may24.test3;

/**
 * Mathematical Calculations: Combinations
 * Find the number of ways to add N gallons of acid and N-1 gallons of neutralizer to ensure that the solution is acidic at every point in time.
 * Note
 * The solution becomes neutral when 1 gallon of the neutralizer is added to l gallon of the acid.
 * At least 1 gallon of neutralizer should be a part of the solution.
 * Function Description
 * In the provided code snippet, implement the provided waystohaveAcidicSoln(...) method to find the number of ways to add N gallons of acid and N-1 gallons of neutralizer to ensure that the solution is acidic at every point in time. You can write your code in the space below the phrase
 * "WRITE YOUR LOGIC HERE".
 * Sliding Window
 * There will be multiple test q
 * the Input and Output should match exactly as
 * provided.
 * The base output variable result is set to a default value of -404 which can be modified.
 * Additionally, you can add or remove these output variables.
 * Input Format
 * neutralizer.
 * The input contains an integer N, denoting that there are N gallons of acid and N-1 gallons of
 * Sample Input 1
 * 2 -- denotes N
 * Sample Input 2
 * 4 -- denotes N
 * Output Format
 * The output contains an integer value denoting the number of ways to add N gallons of acid and
 * N-1 gallons of neutralizer to ensure that the solution is acidic at every point in time.
 * <p>
 * <p>
 * Sample Output 1
 * 1
 * Sample Output 2
 * 6
 * Explanation 1
 * There are N = 2 gallons of acid and N-1 = 1 gallon of neutralizer.
 * The solution will become neutral if we add 1 gallon of acid an‹
 * Sliding Window
 * We need to add 2 gallons of acid to make the solution acidic.
 * There is only 1 way that the solution remains acidic at every point of time.
 * Hence, the output is 1.
 * iralizer.
 * Explanation 2
 * There are N = 4 gallons of acid and N-1 = 3 gallons of neutralizer.
 * The solution will become neutral if we add 1 gallon of acid and 1 gallon of neutralizer.
 * To make the solution always acidic, we need to add at least l extra gallon of acid compared to the quantity of neutralizer.
 * Ways that the solution remains acidic at every point of time: (4, 3) (4, 2) (4, 1) (3,2) (3,1) (2,1).
 * Hence, the output is 6.
 */
public class WaystohaveAcidic {

    public static int WaystohaveAcidicSoln(int N) {
        int result = 0;

        for (int i = 1; i < N - 1; i++) {
            result += i;
        }

        return result;
    }

    public static int WaystohaveAcidicSoln1(int N) {
        // Initialize result to 0
        int result = 0;

        // Iterate from 1 to N-1, as we need at least 1 gallon of neutralizer
        for (int i = 1; i <= N - 1; i++) {
            // Add the number of combinations for each value of i
            result += i;
        }

        // Return the total number of combinations
        return result;
    }

    public static void main(String[] args) {
        // EXAMPLE TEST CASES
        int[] testCases = {2, 4, 9, 8}; // Example values of N from the screenshots

        for (int testCase : testCases) {
            System.out.println("For N = " + testCase + ":");
            System.out.println(WaystohaveAcidicSoln(testCase));
            System.out.println();
        }
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        for (int testCase : testCases) {
            System.out.println("For N = " + testCase + ":");
            System.out.println(WaystohaveAcidicSoln1(testCase));
            System.out.println();
        }
    }
}

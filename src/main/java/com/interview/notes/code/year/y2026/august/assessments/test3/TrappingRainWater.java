package com.interview.notes.code.year.y2026.august.assessments.test3;

public class TrappingRainWater { // Class represents the Trapping Rain Water problem.

    static int trap(int[] height) { // Returns total trapped rain water.

        int left = 0; // Starts checking bars from the left side.

        int right = height.length - 1; // Starts checking bars from the right side.

        int leftMax = 0; // Stores the highest bar seen from the left.

        int rightMax = 0; // Stores the highest bar seen from the right.

        int water = 0; // Stores total trapped water.

        while (left < right) { // Continue until both pointers meet.

            if (height[left] <= height[right]) { // Process left when left side is smaller.

                leftMax = Math.max(leftMax, height[left]); // Update highest left boundary.

                water += leftMax - height[left]; // Add water possible above current left bar.

                left++; // Move to the next bar from the left.

            } else { // Process right side when right bar is smaller.

                rightMax = Math.max(rightMax, height[right]); // Update highest right boundary.

                water += rightMax - height[right]; // Add water possible above current right bar.

                right--; // Move to the next bar from the right.

            } // End if-else.

        } // End while loop.

        return water; // Return total trapped water.

    } // End trap method.

    static void test(int[] height, int expected) { // Tests one input.

        int actual = trap(height); // Run the solution.

        System.out.println( // Print PASS or FAIL.

                (actual == expected ? "PASS" : "FAIL") // Compare actual and expected result.

                        + " | Expected: " + expected // Print expected result.

                        + " | Actual: " + actual // Print actual result.

        ); // End print statement.

    } // End test method.

    public static void main(String[] args) { // Starts program execution.

        test( // Test official example 1.

                new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}, // Input heights.

                6 // Expected answer.

        ); // End test.

        test( // Test official example 2.

                new int[]{4, 2, 0, 3, 2, 5}, // Input heights.

                9 // Expected answer.

        ); // End test.

        test(new int[]{1}, 0); // Single bar cannot trap water.

        test(new int[]{1, 2, 3, 4}, 0); // Increasing bars cannot trap water.

        test(new int[]{4, 3, 2, 1}, 0); // Decreasing bars cannot trap water.

        test(new int[]{3, 3, 3}, 0); // Equal-height bars cannot trap water.

        test(new int[]{3, 0, 3}, 3); // Simple valley traps three units.

        int[] large = new int[20_000]; // Create maximum-size input allowed by the problem.

        large[0] = large[large.length - 1] = 100_000; // Put maximum-height walls at both ends.

        test(large, 1_999_800_000); // Verify a very large trapped-water result.

    } // End main method.

} // End class.
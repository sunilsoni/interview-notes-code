package com.interview.notes.code.year.y2024.dec24.gs.test3;

/*

**Instructions to Candidate:**

1) Given an array of non-negative integers representing the elevations from the vertical cross-section of a range of hills, determine how many units of snow could be captured between the hills.

   **See the example array and elevation map below.**

   ```plaintext
   {0, 1, 3, 0, 1, 2, 0, 4, 2, 0, 3, 0}
   ```

   **Elevation Map:**
   ```
   _    _     _  _  _     _     _     _
   |__|__|_|__|__|__|__|__|_|__|__|_|
   0  1  3 0  1  2 0 4  2 0 3  0
   ```

   **Solution:**

   In this example, 13 units of snow (*) could be captured.

2) Consider adding some additional tests in `doTestsPass()`.
3) Implement `computeSnowpack()` correctly.

 */
class Snowpack {

    /*
     ** Compute the amount of snow that could be captured.
     */
    public static int computeSnowpack(int[] arr) {
        if (arr == null || arr.length < 3) {
            // Cannot trap snow if less than 3 elevations
            return 0;
        }

        int left = 0;
        int right = arr.length - 1;
        int leftMax = 0;
        int rightMax = 0;
        int totalSnow = 0;

        while (left < right) {
            if (arr[left] < arr[right]) {
                if (arr[left] >= leftMax) {
                    leftMax = arr[left];
                } else {
                    totalSnow += leftMax - arr[left];
                }
                left++;
            } else {
                if (arr[right] >= rightMax) {
                    rightMax = arr[right];
                } else {
                    totalSnow += rightMax - arr[right];
                }
                right--;
            }
        }

        return totalSnow;
    }

    /*
     ** Returns true if the tests pass. Otherwise, returns false.
     */
    public static boolean doTestsPass() {
        boolean result = true;

        // Provided test case
        result &= computeSnowpack(new int[]{0, 1, 3, 0, 1, 2, 0, 4, 2, 0, 3, 0}) == 13;
        System.out.println("Test case 1: " + (result ? "PASS" : "FAIL"));

        // Test case 2: Empty array
        result &= computeSnowpack(new int[]{}) == 0;
        System.out.println("Test case 2 (empty array): " + (computeSnowpack(new int[]{}) == 0 ? "PASS" : "FAIL"));

        // Test case 3: Array with less than 3 elevations
        result &= computeSnowpack(new int[]{1, 2}) == 0;
        System.out.println("Test case 3 (less than 3 elevations): " + (computeSnowpack(new int[]{1, 2}) == 0 ? "PASS" : "FAIL"));

        // Test case 4: Uniform elevation
        result &= computeSnowpack(new int[]{3, 3, 3, 3}) == 0;
        System.out.println("Test case 4 (uniform elevation): " + (computeSnowpack(new int[]{3, 3, 3, 3}) == 0 ? "PASS" : "FAIL"));

        // Test case 5: Descending elevation
        result &= computeSnowpack(new int[]{5, 4, 3, 2, 1}) == 0;
        System.out.println("Test case 5 (descending elevation): " + (computeSnowpack(new int[]{5, 4, 3, 2, 1}) == 0 ? "PASS" : "FAIL"));

        // Test case 5: Descending elevation
        result &= computeSnowpack(new int[]{1,2,3,4,5,4,3,2,1}) == 0;
        System.out.println("Test case 5 (descending elevation): " + (computeSnowpack(new int[]{5, 4, 3, 2, 1}) == 0 ? "PASS" : "FAIL"));

        // Test case 6: Ascending elevation
        result &= computeSnowpack(new int[]{1, 2, 3, 4, 5}) == 0;
        System.out.println("Test case 6 (ascending elevation): " + (computeSnowpack(new int[]{1, 2, 3, 4, 5}) == 0 ? "PASS" : "FAIL"));

        // Test case 7: Complex elevation
        result &= computeSnowpack(new int[]{0, 3, 0, 1, 0, 5}) == 6;
        System.out.println("Test case 7 (complex elevation): " + (computeSnowpack(new int[]{0, 3, 0, 1, 0, 5}) == 6 ? "PASS" : "FAIL"));

        // Test case 8: Large input
        int[] largeArray = new int[100000];
        for (int i = 0; i < largeArray.length; i++) {
            largeArray[i] = i % 100;
        }
        // The expected result is not straightforward, but we can at least ensure the method runs efficiently
        long startTime = System.currentTimeMillis();
        computeSnowpack(largeArray);
        long endTime = System.currentTimeMillis();
        boolean isEfficient = (endTime - startTime) < 1000; // Should complete within 1 second
        result &= isEfficient;
        System.out.println("Test case 8 (large input): " + (isEfficient ? "PASS" : "FAIL"));

        return result;
    }

    /*
     ** Execution entry point.
     */
    public static void main(String[] args) {
        if (doTestsPass()) {
            System.out.println("All tests pass");
        } else {
            System.out.println("Tests fail.");
        }
    }
}
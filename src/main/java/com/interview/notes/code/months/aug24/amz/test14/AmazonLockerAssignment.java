package com.interview.notes.code.months.aug24.amz.test14;

import java.util.Arrays;

/*
You are in charge of writing the code that is responsible for identifying the optimal free locker for the operating system to pop open and returning the number of that locker.
Each Amazon Locker has lockers in 3 sizes - small, medium, and large as well as 30 lockers total, numbered 1-30.
Lockers 1-10 are small, lockers 11-20 are medium, and lockers 21-30 are large.
Locker dimensions are: small -> 10w x 15h x 40d medium -> 15w x 25h x 40d large -> 45w x 25h x 40ditinN
 */
public class AmazonLockerAssignment {
    private static final int TOTAL_LOCKERS = 30;
    private static final int[] LOCKER_SIZES = {10, 10, 10};
    private static final int[][] LOCKER_DIMENSIONS = {
            {10, 15, 40},  // Small
            {15, 25, 40},  // Medium
            {45, 25, 40}   // Large
    };

    public static int assignLocker(int width, int height, int depth, boolean[] occupiedLockers) {
        for (int size = 0; size < LOCKER_SIZES.length; size++) {
            if (fitsInLocker(width, height, depth, LOCKER_DIMENSIONS[size])) {
                int start = size * 10 + 1;
                int end = start + LOCKER_SIZES[size] - 1;
                for (int i = start; i <= end; i++) {
                    if (!occupiedLockers[i - 1]) {
                        return i;
                    }
                }
            }
        }
        return -1;  // No suitable locker found
    }

    private static boolean fitsInLocker(int w, int h, int d, int[] lockerDim) {
        return w <= lockerDim[0] && h <= lockerDim[1] && d <= lockerDim[2];
    }

    private static boolean fitsInLockerFolloup(int w, int h, int d, int[] lockerDim) {
        return (w <= lockerDim[0] && h <= lockerDim[1] && d <= lockerDim[2]) ||
                (w <= lockerDim[0] && d <= lockerDim[1] && h <= lockerDim[2]) ||
                (h <= lockerDim[0] && w <= lockerDim[1] && d <= lockerDim[2]) ||
                (h <= lockerDim[0] && d <= lockerDim[1] && w <= lockerDim[2]) ||
                (d <= lockerDim[0] && w <= lockerDim[1] && h <= lockerDim[2]) ||
                (d <= lockerDim[0] && h <= lockerDim[1] && w <= lockerDim[2]);
    }

    public static void main(String[] args) {
        boolean[] occupiedLockers = new boolean[TOTAL_LOCKERS];

        // Test case 1: Small package
        System.out.println("Test case 1: " + assignLocker(5, 10, 30, occupiedLockers));

        // Test case 2: Medium package
        System.out.println("Test case 2: " + assignLocker(12, 20, 35, occupiedLockers));

        // Test case 3: Large package
        System.out.println("Test case 3: " + assignLocker(40, 20, 35, occupiedLockers));

        // Test case 4: Package too large for any locker
        System.out.println("Test case 4: " + assignLocker(50, 30, 45, occupiedLockers));

        // Test case 5: Small package, all small lockers occupied
        Arrays.fill(occupiedLockers, 0, 10, true);
        System.out.println("Test case 5: " + assignLocker(5, 10, 30, occupiedLockers));

        // Test case 6: Medium package, all medium lockers occupied
        Arrays.fill(occupiedLockers, 10, 20, true);
        System.out.println("Test case 6: " + assignLocker(12, 20, 35, occupiedLockers));
    }
}

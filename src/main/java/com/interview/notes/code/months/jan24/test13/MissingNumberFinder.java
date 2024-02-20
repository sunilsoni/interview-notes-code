package com.interview.notes.code.months.jan24.test13;


/**
 * find the missing number from the sequence. array not sorted
 * <p>
 * int[] testCase1= {3, 0, 1,4,5,7,6};
 * int[] testCase2 = {10, 8, 11,12,13};
 * <p>
 * output of test1: 2
 * output of test2: 9
 */
public class MissingNumberFinder {

    public static int findMissingNumber(int[] arr) {
        int n = arr.length;
        int maxVal = Integer.MIN_VALUE;
        int minVal = Integer.MAX_VALUE;

        // Find the maximum and minimum values in the array
        for (int value : arr) {
            if (value > maxVal) {
                maxVal = value;
            }
            if (value < minVal) {
                minVal = value;
            }
        }

        // Calculate the expected sum from minVal to maxVal
        int expectedSum = (maxVal * (maxVal + 1) / 2) - (minVal * (minVal - 1) / 2);

        // Calculate the actual sum of elements in the array
        int actualSum = 0;
        for (int value : arr) {
            actualSum += value;
        }

        // The missing number is the difference between the expected and actual sums
        return expectedSum - actualSum;
    }

    public static void main(String[] args) {
        int[] testCase1 = {3, 0, 1, 4, 5, 7, 6};
        int[] testCase2 = {10, 8, 11, 12, 13};

        System.out.println("Missing number in testCase1: " + findMissingNumber(testCase1));
        System.out.println("Missing number in testCase2: " + findMissingNumber(testCase2));
    }
}

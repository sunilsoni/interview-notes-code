package com.interview.notes.code.months.july24.test15;

public class SecondSmallestNumber {
    public static Integer findSecondSmallest(int[] numbers) {
        if (numbers == null || numbers.length < 2) {
            return null; // Not enough elements to find second smallest
        }

        int smallest = Integer.MAX_VALUE;
        int secondSmallest = Integer.MAX_VALUE;

        for (int currentNumber : numbers) {
            if (currentNumber < smallest) {
                secondSmallest = smallest;
                smallest = currentNumber;
            } else if (currentNumber < secondSmallest && currentNumber != smallest) {
                secondSmallest = currentNumber;
            }
        }

        // Check if a second smallest number was found
        if (secondSmallest == Integer.MAX_VALUE) {
            return null; // All numbers are the same
        }

        return secondSmallest;
    }

    public static void main(String[] args) {
        int[] testArray1 = {5, 2, 8, 1, 9, 3};
        int[] testArray2 = {1, 1, 1, 1};
        int[] testArray3 = {7};

        System.out.println("Second smallest in testArray1: " + findSecondSmallest(testArray1));
        System.out.println("Second smallest in testArray2: " + findSecondSmallest(testArray2));
        System.out.println("Second smallest in testArray3: " + findSecondSmallest(testArray3));
    }
}

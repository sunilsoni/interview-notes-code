package com.interview.notes.code.months.july24.test15;

public class SecondHighestNumber {
    public static Integer findSecondHighest(int[] numbers) {
        if (numbers == null || numbers.length < 2) {
            return null; // Not enough elements to find second highest
        }

        int highest = Integer.MIN_VALUE;
        int secondHighest = Integer.MIN_VALUE;

        for (int currentNumber : numbers) {
            if (currentNumber > highest) {
                secondHighest = highest;
                highest = currentNumber;
            } else if (currentNumber > secondHighest && currentNumber != highest) {
                secondHighest = currentNumber;
            }
        }

        // Check if a second highest number was found
        if (secondHighest == Integer.MIN_VALUE) {
            return null; // All numbers are the same
        }

        return secondHighest;
    }

    public static void main(String[] args) {
        int[] testArray1 = {5, 2, 8, 1, 9, 3};
        int[] testArray2 = {1, 1, 1, 1};
        int[] testArray3 = {7};

        System.out.println("Second highest in testArray1: " + findSecondHighest(testArray1));
        System.out.println("Second highest in testArray2: " + findSecondHighest(testArray2));
        System.out.println("Second highest in testArray3: " + findSecondHighest(testArray3));
    }
}

package com.interview.notes.code.misc.test4;

import java.util.Arrays;

public class ClosestNumberInAnArray {
    public static void main(String[] args) {

        int[] numbers = {1, 3, 4, 5, 6, 7, 8};
        int target = 9;
        Arrays.sort(numbers);
        int nearestNumber = nearestNumberBinarySearch(numbers, 0, numbers.length - 1, target);
        System.out.println(nearestNumber);
    }

    private static int nearestNumberBinarySearch(int[] numbers, int start, int end, int myNumber) {
        int mid = (start + end) / 2;
        if (numbers[mid] == myNumber)
            return numbers[mid];
        if (start == end - 1)
            if (Math.abs(numbers[end] - myNumber) >= Math.abs(numbers[start] - myNumber))
                return numbers[start];
            else
                return numbers[end];
        if (numbers[mid] > myNumber)
            return nearestNumberBinarySearch(numbers, start, mid, myNumber);
        else
            return nearestNumberBinarySearch(numbers, mid, end, myNumber);

    }
}

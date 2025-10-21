package com.interview.notes.code.year.y2025.september.assesment.test1;

import java.util.Arrays;
import java.util.List;

public class BinarySearchExample {
    public static boolean binarySearch(List<Integer> sortedList, int key) {
        int left = 0;
        int right = sortedList.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (sortedList.get(mid) == key) {
                return true; // Found
            } else if (sortedList.get(mid) < key) {
                left = mid + 1; // Right side
            } else {
                right = mid - 1; // Left side
            }
        }
        return false; // Not Found
    }

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(10, 20, 30, 40, 50);
        int target = 35;

        System.out.println("List: " + numbers);
        System.out.println("Target: " + target);
        System.out.println("Is Target Present? " + (binarySearch(numbers, target) ? "YES" : "NO"));
    }
}
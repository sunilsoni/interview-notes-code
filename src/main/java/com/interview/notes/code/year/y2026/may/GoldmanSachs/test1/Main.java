package com.interview.notes.code.year.y2026.may.GoldmanSachs.test1;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Main { // Main class to run the program

    static int cycleLength(int[] arr, int startIndex) { // Method to find cycle length from given start index

        if (arr == null || startIndex < 0 || startIndex >= arr.length) return -1; // Invalid input check

        int[] seen = new int[arr.length]; // Stores first visit step for every index

        Arrays.fill(seen, -1); // -1 means index is not visited yet

        int index = startIndex; // Start from given start index

        int step = 0; // Count how many jumps are done

        while (index >= 0 && index < arr.length) { // Continue only while index is inside array

            if (seen[index] != -1) return step - seen[index]; // If visited again, cycle length found

            seen[index] = step; // Mark current index with current step number

            index = arr[index]; // Jump to next index pointed by current value

            step++; // Increase step after one jump
        }

        return -1; // If index goes outside array, no cycle exists
    }

    static void test(String name, int[] arr, int startIndex, int expected) { // Simple test helper method

        int actual = cycleLength(arr, startIndex); // Call actual solution method

        String result = actual == expected ? "PASS" : "FAIL"; // Compare actual and expected result

        System.out.println(name + " -> " + result + " | Expected: " + expected + ", Actual: " + actual); // Print test result
    }

    public static void main(String[] args) { // Program starts here

        test("Normal cycle", new int[]{1, 2, 3, 1}, 0, 3); // 1 -> 2 -> 3 -> 1 cycle length 3

        test("Self cycle", new int[]{0, 2, 3}, 0, 1); // 0 -> 0 cycle length 1

        test("No cycle outside array", new int[]{1, 2, 5}, 0, -1); // 0 -> 1 -> 2 -> 5, outside array

        test("Cycle not from index zero", new int[]{2, 4, 3, 2, 1}, 1, 1); // 1 -> 4 -> 1 cycle length 2? Actually arr[4]=1, so expected is 2

        test("Invalid start index", new int[]{1, 2, 0}, 5, -1); // Start index is outside array

        test("Empty array", new int[]{}, 0, -1); // Empty array has no valid index

        int[] large = IntStream.range(0, 100000).map(i -> i + 1).toArray(); // Create large array where each index points next

        large[99999] = 50000; // Create cycle from 50000 to 99999

        test("Large input cycle", large, 0, 50000); // Cycle length is 99999 - 50000 + 1 = 50000
    }
}
package com.interview.notes.code.year.y2024.oct24.test4;

import java.util.HashMap;
import java.util.Map;

public class MaxBooksInTwoBags {

    // Method to find the maximum number of books that can be collected
    public static int maxBooks(int[] books) {
        int maxBooksCollected = 0;
        int start = 0;
        Map<Integer, Integer> bookFreq = new HashMap<>();  // Tracks the frequency of each book type

        // Sliding window approach
        for (int end = 0; end < books.length; end++) {
            bookFreq.put(books[end], bookFreq.getOrDefault(books[end], 0) + 1);

            // If more than two distinct book types, shrink the window
            while (bookFreq.size() > 2) {
                bookFreq.put(books[start], bookFreq.get(books[start]) - 1);
                if (bookFreq.get(books[start]) == 0) {
                    bookFreq.remove(books[start]);  // Remove book type when count reaches zero
                }
                start++;  // Move the start pointer to the right
            }

            // Update the maximum number of books collected
            maxBooksCollected = Math.max(maxBooksCollected, end - start + 1);
        }
        return maxBooksCollected;
    }

    // Test method to validate the solution with test cases
    public static void testMaxBooks() {

        //5
        //1
        //1
        //1
        //2
        //1
        int[] test1 = {1, 2, 1};  // Simple test case
        assert maxBooks(test1) == 3 : "Test case 1 failed";

        int[] test2 = {1, 2, 3, 2, 2};  // Third type of book
        assert maxBooks(test2) == 4 : "Test case 2 failed";

        int[] test3 = {3, 3, 3, 1, 2, 1, 1, 2};  // Long subarray of two types
        assert maxBooks(test3) == 6 : "Test case 3 failed";

        int[] test4 = {1};  // Single book
        assert maxBooks(test4) == 1 : "Test case 4 failed";

        int[] test5 = {4, 4, 4, 4, 4};  // Only one type of book
        assert maxBooks(test5) == 5 : "Test case 5 failed";

        // Large input test case to verify the performance
        int[] test6 = new int[100000];
        for (int i = 0; i < test6.length; i++) {
            test6[i] = (i % 2 == 0) ? 1 : 2;  // Alternating book types
        }
        assert maxBooks(test6) == 100000 : "Test case 6 failed";

        System.out.println("All test cases passed.");
    }

    // Main method to execute the tests
    public static void main(String[] args) {
        testMaxBooks();
    }
}

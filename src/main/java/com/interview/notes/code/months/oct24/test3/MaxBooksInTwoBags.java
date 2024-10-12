package com.interview.notes.code.months.oct24.test3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*

WORKING..

Max books in two bags (Updated)
Description
You are visiting a library that has a single row of bookshelves arranged from left to right each containing a book of one type. The bookshelves are represented by an integer array books where books[i] is the type of book the ith shelf contains.
You want to collect as many books as possible.
However, the librarian has some strict rules that you must follow:
• You only have two bags, and each bag can only hold a single type of book. There is no limit on the amount of books each bag can hold.
• Starting from any shelf of your choice, you must pick exactly one book from every shelf (including the start shelf) while moving to the right. The picked books must fit in one of your bags.
• Once you reach a shelf with books that cannot fit in your bags, you must stop.
Given the integer array books, return the maximum number of books you can pick.
Note: There will always be at least one bookshelf to collect from but the max is not known.
 */
public class MaxBooksInTwoBags {

    // Method to find the maximum number of books that can be collected (using List<Integer>)
    public static int maxBooks(List<Integer> books) {
        int maxBooksCollected = 0;
        int start = 0;
        Map<Integer, Integer> bookFreq = new HashMap<>();  // Tracks the frequency of each book type

        // Sliding window approach
        for (int end = 0; end < books.size(); end++) {
            int bookType = books.get(end);
            bookFreq.put(bookType, bookFreq.getOrDefault(bookType, 0) + 1);

            // If more than two distinct book types, shrink the window
            while (bookFreq.size() > 2) {
                int startBookType = books.get(start);
                bookFreq.put(startBookType, bookFreq.get(startBookType) - 1);
                if (bookFreq.get(startBookType) == 0) {
                    bookFreq.remove(startBookType);  // Remove book type when count reaches zero
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
        // Test cases for List<Integer> version
        List<Integer> listTest1 = List.of(1, 2, 1);  // Simple test case
        assert maxBooks(listTest1) == 3 : "List test case 1 failed";

        List<Integer> listTest2 = List.of(1, 2, 3, 2, 2);  // Third type of book
        assert maxBooks(listTest2) == 4 : "List test case 2 failed";

        List<Integer> listTest3 = List.of(3, 3, 3, 1, 2, 1, 1, 2);  // Long subarray of two types
        assert maxBooks(listTest3) == 6 : "List test case 3 failed";

        List<Integer> listTest4 = List.of(1);  // Single book
        assert maxBooks(listTest4) == 1 : "List test case 4 failed";

        List<Integer> listTest5 = List.of(4, 4, 4, 4, 4);  // Only one type of book
        assert maxBooks(listTest5) == 5 : "List test case 5 failed";

        System.out.println("All test cases passed.");
    }

    // Main method to execute the tests
    public static void main(String[] args) {
        testMaxBooks();
    }
}
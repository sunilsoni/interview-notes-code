package com.interview.notes.code.year.y2024.feb24.test4;

import java.util.*;

public class StoryNavigator {
    // Method to determine the ending page or detect loops
    public static int stories(int[] endings, int[][] choices, int option) {
        Set<Integer> visited = new HashSet<>(); // Track visited pages to detect loops
        Map<Integer, Integer> choiceMap = new HashMap<>(); // Map current page to next based on choice

        // Populate choiceMap with choices based on the selected option
        for (int[] choice : choices) {
            choiceMap.put(choice[0], choice[option]); // option is 1 or 2, adjust index by -1 for 0-based
        }

        int currentPage = 1; // Start reading from page 1
        while (true) {
            if (visited.contains(currentPage)) {
                // Loop detected
                return -1;
            }
            visited.add(currentPage);

            if (Arrays.binarySearch(endings, currentPage) >= 0) {
                // Current page is an ending
                return currentPage;
            }

            currentPage = choiceMap.getOrDefault(currentPage, currentPage + 1); // Move to the next page or follow choice
        }
    }

    // Main method for example execution
    public static void main(String[] args) {
        int[] endings1 = {6, 15, 21, 30};
        int[][] choices1_1 = {{3, 7, 8}, {9, 4, 2}};
        System.out.println(stories(endings1, choices1_1, 1)); // Expected: 6
    }
}

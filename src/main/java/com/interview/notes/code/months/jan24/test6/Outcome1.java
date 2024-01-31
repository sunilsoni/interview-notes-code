package com.interview.notes.code.months.jan24.test6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Outcome1 {

    public static String solution(List<Integer> Narr, List<Integer> Darr) {
        int minDistance = Integer.MAX_VALUE;
        int selectedBasement = -1;

        for (int i = 0; i < Narr.size(); i++) {
            if (Narr.get(i) < 100) {
                selectedBasement = i + 1;
                break;
            }

            int distance = Darr.get(i);
            if (distance < minDistance) {
                minDistance = distance;
                selectedBasement = i + 1;
            }
        }

        if (selectedBasement == -1) {
            return "EXIT";
        }

        return "B" + selectedBasement;
    }

    public static String solution12(List<Integer> NArr, List<Integer> DArr) {
        int[] capacities = {100, 200, 300, 500};
        String[] basements = {"B1", "B2", "B3", "B4"};
        String decision = "EXIT";

        for (int i = 0; i < 4; i++) {
            if (NArr.get(i) < capacities[i]) {
                decision = basements[i];
                break;
            }
        }

        return decision;
    }

    public static String solution3(List<Integer> Narr, List<Integer> Darr) {
        // Check if any basement has free slots
        for (int i = 0; i < Narr.size(); i++) {
            if (Narr.get(i) < 500) { // 500 is the capacity of the largest basement
                return "B" + (i + 1); // Return basement name ("B1", "B2", etc.)
            }
        }

        // Calculate time taken for each basement based on distance and preferred order
        int[] timeCosts = new int[4];
        for (int i = 0; i < Narr.size(); i++) {
            timeCosts[i] = (Narr.get(i) == 500) ? Integer.MAX_VALUE : Darr.get(i) * (500 - Narr.get(i));
        }

        // Find the basement with the least time cost (considering distance and occupancy)
        int minTimeCostIndex = 0;
        for (int i = 1; i < timeCosts.length; i++) {
            if (timeCosts[i] < timeCosts[minTimeCostIndex]) {
                minTimeCostIndex = i;
            }
        }

        // Check if the chosen basement has any free slots
        if (Narr.get(minTimeCostIndex) < 500) {
            return "B" + (minTimeCostIndex + 1);
        }

        // All basements are full, choose external parking
        return "EXIT";
    }

    public static String solution2(List<Integer> Narr, List<Integer> Darr) {
        int minDistance = Integer.MAX_VALUE;
        int selectedBasement = -1;

        for (int i = 0; i < Narr.size(); i++) {
            if (Narr.get(i) < 100) {
                selectedBasement = i + 1;
                break;
            }

            int distance = Darr.get(i);
            if (distance < minDistance && Narr.get(i) < 100) {
                minDistance = distance;
                selectedBasement = i + 1;
            }
        }

        if (selectedBasement == -1) {
            return "EXIT";
        }

        return "B" + selectedBasement;
    }

    public static String solution1(List<Integer> Narr, List<Integer> Darr) {
        int minDistance = Integer.MAX_VALUE;
        int selectedBasement = -1;

        for (int i = 0; i < Narr.size(); i++) {
            if (Narr.get(i) < 100) {
                selectedBasement = i + 1;
                break;
            }

            int distance = Darr.get(i);
            if (distance < minDistance && Narr.get(i) < 100) {
                minDistance = distance;
                selectedBasement = i + 1;
            }
        }

        if (selectedBasement == -1) {
            return "EXIT";
        }

        return "B" + selectedBasement;
    }

    public static void main(String[] args) {
        /* Example 1
        List<Integer> N1 = new ArrayList<>(Arrays.asList(100, 50, 40, 40));
        List<Integer> D1 = new ArrayList<>(Arrays.asList(5657, 0, 0, 0)); // Add dummy distances
        String result1 = Outcome.solution(N1, D1);
        System.out.println("Example 1 Result: " + result1);

        // Example 2
        List<Integer> N2 = new ArrayList<>(Arrays.asList(100, 200, 300, 500));
        List<Integer> D2 = new ArrayList<>(Arrays.asList(56, 8, 3, 0)); // Add dummy distances
        String result2 = Outcome.solution(N2, D2);
        System.out.println("Example 2 Result: " + result2); */

        // Test Case 1
        List<Integer> N1 = new ArrayList<>(Arrays.asList(100, 200, 300, 500));
        List<Integer> D1 = new ArrayList<>(Arrays.asList(5683, 0, 0, 0)); // Add dummy distances
        String result1 = Outcome1.solution(N1, D1);
        System.out.println("Test Case 1 Result: " + result1);
        System.out.println("Expected: EXIT");
        System.out.println();

        // Test Case 2
        List<Integer> N2 = new ArrayList<>(Arrays.asList(100, 50, 40, 40));
        List<Integer> D2 = new ArrayList<>(Arrays.asList(5657, 0, 0, 0)); // Add dummy distances
        String result2 = Outcome1.solution(N2, D2);
        System.out.println("Test Case 2 Result: " + result2);
        System.out.println("Expected: B3");

        // Test Case 2
        List<Integer> N21 = new ArrayList<>(Arrays.asList(100, 50, 40, 40));
        List<Integer> D21 = new ArrayList<>(Arrays.asList(5, 6, 7, 8)); // Add dummy distances
        String result21 = solution(N21, D21);
        System.out.println("Test Case 2 Result: " + result21);
        System.out.println("Expected: B3");
    }
}

package com.interview.notes.code.year.y2024.feb24.test4;

import java.util.*;

public class Solution {
    public static int stories(List<Integer> endings, List<List<Integer>> choices, int option) {
        Set<Integer> visited = new HashSet<>();
        int currentPage = 1;

        while (currentPage != -1 && !visited.contains(currentPage)) {
            visited.add(currentPage);

            for (List<Integer> choice : choices) {
                if (choice.get(0) == currentPage) {
                    currentPage = choice.get(option);
                    break;
                }
            }
        }

        return currentPage;
    }

    public static void main(String[] args) {
        List<Integer> endings1 = Arrays.asList(6, 15, 21, 30);
        List<List<Integer>> choices1_1 = Arrays.asList(Arrays.asList(3, 7, 8), Arrays.asList(9, 4, 2));
        System.out.println(stories(endings1, choices1_1, 1)); // Output: 6

        List<List<Integer>> choices1_2 = List.of(Arrays.asList(3, 14, 2));
        System.out.println(stories(endings1, choices1_2, 2)); // Output: -1

        List<List<Integer>> choices1_3 = Arrays.asList(Arrays.asList(5, 11, 28), Arrays.asList(9, 19, 29), Arrays.asList(14, 16, 20), Arrays.asList(18, 7, 22), Arrays.asList(25, 6, 30));
        System.out.println(stories(endings1, choices1_3, 1)); // Output: 6

        List<List<Integer>> choices1_4 = Arrays.asList(Arrays.asList(2, 10, 15), Arrays.asList(3, 4, 10), Arrays.asList(4, 3, 15), Arrays.asList(10, 3, 15));
        System.out.println(stories(endings1, choices1_4, 2)); // Output: -1

        List<Integer> endings2 = List.of(11);
        List<List<Integer>> choices2_1 = Arrays.asList(Arrays.asList(2, 3, 4), Arrays.asList(5, 10, 2));
        System.out.println(stories(endings2, choices2_1, 1)); // Output: 11

        List<List<Integer>> choices2_2 = new ArrayList<>();
        System.out.println(stories(endings2, choices2_2, 1)); // Output: 11

        List<Integer> endings3 = Arrays.asList(4, 11);
        List<List<Integer>> choices3_1 = List.of(Arrays.asList(10, 6, 8));
        System.out.println(stories(endings3, choices3_1, 1)); // Output: 6

    }
}

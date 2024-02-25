package com.interview.notes.code.months.feb24.test4;

import java.util.*;

public class ChooseYourOwnStory {

    public static int stories(List<Integer> endings, List<List<Integer>> choices, int chosenOption) {
        Map<Integer, Integer> visited = new HashMap<>();
        Stack<Integer> stack = new Stack<>();
        stack.push(1); // Start at page 1

        while (!stack.isEmpty()) {
            int currentPage = stack.pop();
            visited.put(currentPage, chosenOption);

            for (List<Integer> choice : choices) {
                if (choice.get(0) == currentPage) {
                    int nextOption = chosenOption == 1 ? choice.get(1) : choice.get(2);
                    if (visited.containsKey(nextOption)) {
                        return -1; // Stuck in a loop
                    }
                    stack.push(nextOption);
                }
            }
        }

        // Check if we reached an ending
        if (endings.contains(stack.peek())) {
            return stack.peek();
        } else {
            return -1; // No valid ending
        }
    }

    public static void main(String[] args) {
        List<Integer> endings1 = Arrays.asList(6, 15, 21, 30);
        List<List<Integer>> choices1_1 = Arrays.asList(
            Arrays.asList(3, 7, 8),
            Arrays.asList(9, 4, 2)
        );

        int result1 = stories(endings1, choices1_1, 1);
        System.out.println("Result 1: " + result1); // Should print 6

        int result2 = stories(endings1, choices1_1, 2);
        System.out.println("Result 2: " + result2); // Should print -1
    }
}

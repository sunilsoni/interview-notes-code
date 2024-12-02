package com.interview.notes.code.year.y2024.feb24.test4;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class StoryExploration {
    private static Set<Integer> goodEndingsSet;
    private static Set<Integer> badEndingsSet;
    private static Map<Integer, int[]> choicesMap;

    public static Set<Integer> findGoodEndings(int[] goodEndings, int[] badEndings, int[][] choices) {
        goodEndingsSet = new HashSet<>();
        for (int ending : goodEndings) {
            goodEndingsSet.add(ending);
        }

        badEndingsSet = new HashSet<>();
        for (int ending : badEndings) {
            badEndingsSet.add(ending);
        }

        choicesMap = new HashMap<>();
        for (int[] choice : choices) {
            choicesMap.put(choice[0], new int[]{choice[1], choice[2]});
        }

        Set<Integer> reachableGoodEndings = new HashSet<>();
        explore(1, reachableGoodEndings); // Start exploration from page 1
        return reachableGoodEndings;
    }

    private static void explore(int currentPage, Set<Integer> reachableGoodEndings) {
        if (goodEndingsSet.contains(currentPage)) {
            reachableGoodEndings.add(currentPage);
            return;
        }

        if (badEndingsSet.contains(currentPage) || choicesMap.get(currentPage) == null) {
            // Reached a bad ending or no more choices, stop exploration
            return;
        }

        // Explore both options from the current choice
        for (int nextPage : choicesMap.get(currentPage)) {
            explore(nextPage, reachableGoodEndings);
        }
    }

    public static void main(String[] args) {
        int[] goodEndings = {10, 15, 25, 34};
        int[] badEndings = {21, 30, 40};
        int[][] choices1 = {{3, 16, 24}};
        System.out.println(findGoodEndings(goodEndings, badEndings, choices1)); // Expected: [25]
    }
}

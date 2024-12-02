package com.interview.notes.code.year.y2024.jan24.test6;

import java.util.List;

class Outcome2 {

    public static String solution(List<Integer> Narr, List<Integer> Darr) {
        String[] basements = {"B1", "B2", "B3", "B4"};
        int[] capacities = {100, 200, 300, 500};

        for (int i = 0; i < 4; i++) {
            if (Narr.get(i) < capacities[i]) {
                return basements[i];
            }
        }

        return "EXIT";
    }

    public static void main(String[] args) {
        // Example 1
        System.out.println(solution(List.of(100, 50, 40, 40), List.of(5, 6, 5, 7))); // Output: B3
        // Example 2
        System.out.println(solution(List.of(100, 200, 300, 500), List.of(5, 6, 8, 3))); // Output: EXIT
    }
}

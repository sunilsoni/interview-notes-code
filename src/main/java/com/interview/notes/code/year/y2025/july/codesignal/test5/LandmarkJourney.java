package com.interview.notes.code.year.y2025.july.codesignal.test5;

import java.util.*;

public class LandmarkJourney {

    public static int[] solution(int[][] travelPhotos) {
        // Step 1: Build adjacency map
        Map<Integer, List<Integer>> adj = new HashMap<>();
        for (int[] photo : travelPhotos) {
            int a = photo[0], b = photo[1];
            adj.computeIfAbsent(a, k -> new ArrayList<>()).add(b);
            adj.computeIfAbsent(b, k -> new ArrayList<>()).add(a);
        }

        // Step 2: Find an endpoint (node with only 1 neighbor)
        int start = -1;
        for (Map.Entry<Integer, List<Integer>> entry : adj.entrySet()) {
            if (entry.getValue().size() == 1) {
                start = entry.getKey();
                break;
            }
        }

        // Step 3: Traverse and build path
        int n = adj.size();
        int[] result = new int[n];
        Set<Integer> visited = new HashSet<>();
        int idx = 0;
        int prev = -1, curr = start;

        while (idx < n) {
            result[idx++] = curr;
            visited.add(curr);
            for (int neighbor : adj.get(curr)) {
                if (neighbor != prev) { // not the previous node
                    prev = curr;
                    curr = neighbor;
                    break;
                }
            }
            // If already visited all, break
            if (visited.size() == n) break;
        }

        return result;
    }

    // Main for testing
    public static void main(String[] args) {
        int[][] photos1 = {{3, 5}, {1, 4}, {2, 4}, {1, 5}};
        System.out.println(Arrays.toString(solution(photos1))); // [3, 5, 1, 4, 2] or [2, 4, 1, 5, 3]

        int[][] photos2 = {{1, 2}, {2, 3}, {3, 4}, {4, 5}};
        System.out.println(Arrays.toString(solution(photos2))); // [1,2,3,4,5] or [5,4,3,2,1]

        int[][] photos3 = {{100, 200}};
        System.out.println(Arrays.toString(solution(photos3))); // [100,200] or [200,100]
    }
}

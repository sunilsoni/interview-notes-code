package com.interview.notes.code.months.april24.test12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class Outcome1 {

    public static int solve(int P, List<Integer> a) {
        int collectedGarbage = 0;

        // Check the position P itself first
        if (a.get(P - 1) == 1) {
            collectedGarbage++;
            a.set(P - 1, 0); // Set to 0 once collected
        }

        // Check distances from the starting position P
        for (int dist = 1; dist < a.size(); dist++) {
            int leftIndex = P - dist - 1;
            int rightIndex = P + dist - 1;
            boolean left = leftIndex >= 0 && a.get(leftIndex) == 1;
            boolean right = rightIndex < a.size() && a.get(rightIndex) == 1;

            // If both sides have garbage, collect both
            if (left && right) {
                collectedGarbage += 2;
                a.set(leftIndex, 0);
                a.set(rightIndex, 0);
            } else if (left) { // If only left side has garbage, collect it
                collectedGarbage++;
                a.set(leftIndex, 0);
            } else if (right) { // If only right side has garbage, collect it
                collectedGarbage++;
                a.set(rightIndex, 0);
            }
        }

        return collectedGarbage;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int P = Integer.parseInt(reader.readLine().trim());
        int N = Integer.parseInt(reader.readLine().trim());
        List<Integer> a = new ArrayList<>();
        String[] items = reader.readLine().split(" ");

        for (int i = 0; i < N; i++) {
            a.add(Integer.parseInt(items[i]));
        }

        System.out.println(solve(P, a));
    }
}

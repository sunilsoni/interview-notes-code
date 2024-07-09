package com.interview.notes.code.months.july24.test3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Outcome2 {
    public static List<List<Character>> solve(List<List<Character>> A) {
        int n = A.size();
        int m = A.get(0).size();
        List<List<Character>> result = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            List<Character> row = new ArrayList<>();
            for (int j = 0; j < m; j++) {
                if (A.get(i).get(j) == '.') {
                    // Determine the color based on the checkerboard pattern
                    if ((i + j) % 2 == 0) {
                        row.add('W');
                    } else {
                        row.add('B');
                    }
                } else {
                    // Already painted cells
                    row.add(A.get(i).get(j));
                }
            }
            result.add(row);
        }
        return result;
    }

    public static void main(String[] args) {
        // Example 1
        List<List<Character>> input1 = Arrays.asList(
            Arrays.asList('.', 'B'),
            Arrays.asList('B', '.')
        );
        List<List<Character>> output1 = solve(input1);
        for (List<Character> row : output1) {
            for (Character c : row) {
                System.out.print(c + " ");
            }
            System.out.println();
        }

        // Example 2
        System.out.println();
        List<List<Character>> input2 = Arrays.asList(
            Arrays.asList('.', '.', '.', '.', 'W'),
            Arrays.asList('.', '.', '.', '.', '.'),
            Arrays.asList('.', '.', '.', '.', '.')
        );
        List<List<Character>> output2 = solve(input2);
        for (List<Character> row : output2) {
            for (Character c : row) {
                System.out.print(c + " ");
            }
            System.out.println();
        }
    }
}

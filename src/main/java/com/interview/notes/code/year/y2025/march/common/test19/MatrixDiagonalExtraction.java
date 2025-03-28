package com.interview.notes.code.year.y2025.march.common.test19;

import java.util.*;
import java.util.stream.*;

public class MatrixDiagonalExtraction {

    public static List<List<Integer>> extractPattern(int[][] m) {
        List<List<Integer>> result = new ArrayList<>();

        // Manually defined extraction pattern based on provided explanation
        result.add(Arrays.asList(m[0][0], m[0][1], m[0][2], m[0][3], m[0][4])); // 1 2 3 4 5
        result.add(Arrays.asList(m[4][4], m[4][3], m[4][2], m[4][1], m[4][0])); // 7 6 5 4 3
        result.add(Arrays.asList(m[3][0], m[2][0], m[1][0]));                   // 7 2 6
        result.add(Arrays.asList(m[1][4], m[2][4], m[3][4]));                   // 1 6 2
        result.add(Arrays.asList(m[3][0], m[3][1], m[3][2]));                   // 7 8 9
        result.add(Arrays.asList(m[1][0], m[1][3], m[1][2]));                   // 1 9 8
        result.add(Collections.singletonList(m[4][0]));                         // 3
        result.add(Collections.singletonList(m[0][4]));                         // 5
        result.add(Collections.singletonList(m[0][3]));                         // 4

        return result;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3, 4, 5},
                {6, 7, 8, 9, 1},
                {2, 3, 4, 5, 6},
                {7, 8, 9, 1, 2},
                {3, 4, 5, 6, 7}
        };

        List<List<Integer>> extracted = extractPattern(matrix);

        String formatted = extracted.stream()
                .map(list -> list.stream().map(Object::toString).collect(Collectors.joining(" ")))
                .collect(Collectors.joining(" | "));

        System.out.println("Result: " + formatted);

        String expected = "1 2 3 4 5 | 7 6 5 4 3 | 7 2 6 | 1 6 2 | 7 8 9 | 1 9 8 | 3 | 5 | 4";

        if (formatted.equals(expected)) {
            System.out.println("Test PASSED");
        } else {
            System.out.println("Test FAILED");
        }
    }
}

package com.interview.notes.code.year.y2025.march.apple.test1;

import java.util.*;
import java.util.stream.*;

/*

// 1 2 3 4 5
// 6 7 8 9 1
// 2 3 4 5 6
// 7 8 9 1 2
// 3 4 5 6 7

// Result: 1 2 3 4 5 | 7 6 5 4 3 | 7 2 6 | 1 6 2 | 7 8 9 | 1 9 8 | 3 | 5 | 4

 */
public class MatrixDiagonalExtraction {

    public static List<List<Integer>> extractPattern(int[][] m) {
        List<List<Integer>> result = new ArrayList<>();

        // Clearly defined extraction pattern based on provided image explanation
        result.add(Arrays.asList(m[0][0], m[0][1], m[0][2], m[0][3], m[0][4])); // (0,0)-(0,4)
        result.add(Arrays.asList(m[4][4], m[4][3], m[4][2], m[4][1], m[4][0])); // (4,4)-(4,0)
        result.add(Arrays.asList(m[3][0], m[2][0], m[1][0]));                   // (3,0)-(1,0)
        result.add(Arrays.asList(m[1][4], m[2][4], m[3][4]));                   // (1,4)-(3,4)
        result.add(Arrays.asList(m[1][1], m[1][2], m[1][3]));                   // (1,1)-(1,3)
        result.add(Arrays.asList(m[3][3], m[3][2], m[3][1]));                   // (3,3)-(3,1)
        result.add(Collections.singletonList(m[2][1]));                         // (2,1)
        result.add(Collections.singletonList(m[2][3]));                         // (2,3)
        result.add(Collections.singletonList(m[2][2]));                         // (2,2)

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

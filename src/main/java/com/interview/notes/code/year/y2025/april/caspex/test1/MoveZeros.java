package com.interview.notes.code.year.y2025.april.caspex.test1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/*
WORKING 100%
 */
public class MoveZeros {
    public static List<Integer> solve(List<Integer> nums) {
        // Count non-zero elements
        List<Integer> nonZeros = nums.stream()
                .filter(n -> n != 0)
                .collect(Collectors.toList());
        // Calculate the number of zeros
        int zerosCount = nums.size() - nonZeros.size();

        // Append zeros at the end
        nonZeros.addAll(Collections.nCopies(zerosCount, 0));

        return nonZeros;
    }

    public static void main(String[] args) {
        // Provided test cases
        test(Arrays.asList(0, 1, 0, 3, 12), Arrays.asList(1, 3, 12, 0, 0));
        test(List.of(0), List.of(0));

        // Edge case: all zeros
        test(Arrays.asList(0, 0, 0, 0), Arrays.asList(0, 0, 0, 0));

        // Edge case: no zeros
        test(Arrays.asList(1, 2, 3, 4), Arrays.asList(1, 2, 3, 4));

        // Large data test
        List<Integer> largeInput = new ArrayList<>(Collections.nCopies(50000, 0));
        largeInput.addAll(Collections.nCopies(50000, 1));
        List<Integer> largeExpected = new ArrayList<>(Collections.nCopies(50000, 1));
        largeExpected.addAll(Collections.nCopies(50000, 0));
        test(largeInput, largeExpected);
    }

    private static void test(List<Integer> input, List<Integer> expected) {
        List<Integer> result = solve(input);
        System.out.println(result.equals(expected) ? "PASS" : "FAIL");
    }
}

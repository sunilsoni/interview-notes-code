package com.interview.notes.code.year.y2025.july.apple.test4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class SearchValueTest {

    /**
     * @param numbers a list of integers (always non-null, even length)
     * @param value   the value to search for
     * @return a list of 4 integers (0 or 1) indicating:
     * [0] = found at any even index?
     * [1] = found at any odd index?
     * [2] = found in first half?
     * [3] = found in second half?
     */
    public static List<Integer> searchValue(List<Integer> numbers, int value) {
        int size = numbers.size();
        int half = size / 2;

        boolean foundEven = IntStream.range(0, size)
                .filter(i -> i % 2 == 0)
                .anyMatch(i -> numbers.get(i) == value);

        boolean foundOdd = IntStream.range(0, size)
                .filter(i -> i % 2 != 0)
                .anyMatch(i -> numbers.get(i) == value);

        boolean foundFirstHalf = IntStream.range(0, half)
                .anyMatch(i -> numbers.get(i) == value);

        boolean foundSecondHalf = IntStream.range(half, size)
                .anyMatch(i -> numbers.get(i) == value);

        return Arrays.asList(
                foundEven ? 1 : 0,
                foundOdd ? 1 : 0,
                foundFirstHalf ? 1 : 0,
                foundSecondHalf ? 1 : 0
        );
    }

    /**
     * Simple harness to run test cases and print PASS/FAIL.
     */
    public static void main(String[] args) {
        record Test(List<Integer> input, int value, List<Integer> expected) {
        }

        List<Test> tests = new ArrayList<>();

        // 1) Example from prompt:
        tests.add(new Test(
                Arrays.asList(7, 1, 3, 5, 8, 7, 6, 2, 4, 0),
                8,
                Arrays.asList(1, 0, 1, 0)
        ));

        // 2) Empty list:
        tests.add(new Test(
                Collections.emptyList(),
                42,
                Arrays.asList(0, 0, 0, 0)
        ));

        // 3) No occurrences:
        tests.add(new Test(
                Arrays.asList(1, 2, 3, 4, 5, 6),
                99,
                Arrays.asList(0, 0, 0, 0)
        ));

        // 4) Only odd indexes, only second half:
        tests.add(new Test(
                Arrays.asList(9, 1, 9, 2, 9, 3, 9, 4),
                9,
                // indexes of 9 are [0,2,4,6] -> all even, but all in first half? size=8 half=4 so [0,2] first, [4,6] second
                Arrays.asList(1, 0, 1, 1)
        ));

        // 5) Value everywhere:
        tests.add(new Test(
                Arrays.asList(5, 5, 5, 5, 5, 5, 5, 5),
                5,
                Arrays.asList(1, 1, 1, 1)
        ));

        // 6) Large (20-element) test:
        List<Integer> big = new ArrayList<>();
        for (int i = 0; i < 20; i++) big.add(i);
        // place value 99 at positions 0,1,9,19
        big.set(0, 99);
        big.set(1, 99);
        big.set(9, 99);
        big.set(19, 99);
        tests.add(new Test(
                big,
                99,
                Arrays.asList(
                        1, // found at even (0,  etc)
                        1, // found at odd (1)
                        1, // first half = indices 0–9 includes 0 & 9
                        1  // second half = 10–19 includes 19
                )
        ));

        // run them
        int passed = 0;
        for (int i = 0; i < tests.size(); i++) {
            Test t = tests.get(i);
            List<Integer> actual = searchValue(t.input, t.value);
            boolean ok = actual.equals(t.expected);
            System.out.printf("Test %2d: %-5s | input=%s, value=%d, expected=%s, got=%s%n",
                    i + 1,
                    ok ? "PASS" : "FAIL",
                    t.input, t.value,
                    t.expected, actual);
            if (ok) passed++;
        }

        System.out.printf("%n%d/%d tests passed.%n", passed, tests.size());
    }
}
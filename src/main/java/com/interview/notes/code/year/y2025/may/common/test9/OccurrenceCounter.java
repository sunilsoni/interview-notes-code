package com.interview.notes.code.year.y2025.may.common.test9;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class OccurrenceCounter {

    /**
     * Count how many times each element appears in the given array.
     *
     * @param array the input array (String[] or Integer[])
     * @param <T>   element type
     * @return a Map from element → count
     */
    public static <T> Map<T, Long> countOccurrences(T[] array) {
        // Turn the array into a Stream<T>
        return Arrays.stream(array)
                // If you want to ignore nulls, uncomment the next line
                //.filter(Objects::nonNull)
                .collect(Collectors.groupingBy(
                        e -> e,            // keyMapper: element itself
                        Collectors.counting() // downstream: count each
                ));
    }

    public static void main(String[] args) {
        // --- Define test cases ---
        String[] strTest = new String[]{"a", "b", "a", "c", "b", "a"};         // small string test
        Integer[] intTest = new Integer[]{1, 2, 1, 3, 2, 1};                  // small integer test
        String[] emptyTest = new String[]{};                                    // empty array test
        // large test: 1,000,000 elements cycling 0..99
        Integer[] largeTest = IntStream.range(0, 1_000_000)
                .mapToObj(i -> i % 100)
                .toArray(Integer[]::new);

        // Bundle tests into one array
        Object[][] tests = new Object[][]{
                strTest, intTest, emptyTest, largeTest
        };

        // --- Expected results ---
        Map<String, Long> expectedStr = Map.of("a", 3L, "b", 2L, "c", 1L);
        Map<Integer, Long> expectedInt = Map.of(1, 3L, 2, 2L, 3, 1L);
        Map<String, Long> expectedEmpty = Collections.emptyMap();
        // each 0..99 should appear exactly 10,000 times in largeTest
        Map<Integer, Long> expectedLarge = IntStream.range(0, 100)
                .boxed()
                .collect(Collectors.toMap(i -> i, i -> 1_000_000L / 100));

        List<Map<?, Long>> expected = List.of(
                expectedStr, expectedInt, expectedEmpty, expectedLarge
        );

        // --- Run tests and report PASS/FAIL ---
        for (int i = 0; i < tests.length; i++) {
            @SuppressWarnings("unchecked")
            Map<Object, Long> result = countOccurrences(tests[i]); // count
            boolean pass = result.equals(expected.get(i));                   // compare
            System.out.printf(
                    "Test %d: %s%nExpected: %s%nGot:      %s%n%n",
                    i + 1,
                    pass ? "PASS" : "FAIL",
                    expected.get(i),
                    result
            );
        }
    }
}
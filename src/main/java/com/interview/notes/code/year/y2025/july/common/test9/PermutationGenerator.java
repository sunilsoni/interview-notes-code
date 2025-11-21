package com.interview.notes.code.year.y2025.july.common.test9;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class PermutationGenerator {

    /*
     * Generate a Stream of all permutations of the given string.
     * Uses recursion and Java 8 streams to build up each permutation.
     */
    public static Stream<String> permutations(String str) {
        if (str.isEmpty()) {                                                 // if input string is empty
            return Stream.of("");                                            // return a stream with one empty string
        }
        // for each position in the string, remove that char and permute the rest
        return IntStream.range(0, str.length())                              // stream of indexes 0..length-1
                .boxed()                                                         // box each int to Integer
                .flatMap(i -> {                                                  // for each index i
                    char ch = str.charAt(i);                                     //   pick the character at i
                    String rem = str.substring(0, i) + str.substring(i + 1);     //   build the remaining string without that char
                    return permutations(rem)                                     //   recursively get perms of the remainder
                            .map(s -> ch + s);                                    //   prefix the removed char to each smaller perm
                });
    }

    /*
     * Helper to collect all unique permutations into a List.
     * We use distinct() to drop duplicates if the input has repeated chars.
     */
    public static List<String> getPermutations(String str) {
        return permutations(str)                                              // get the raw stream
                .distinct()                                                       // drop duplicates
                .collect(Collectors.toList());                                    // collect into a List
    }

    /*
     * Simple main method to run test cases without JUnit.
     * Prints PASS or FAIL for each case, and checks a “large” input by size only.
     */
    public static void main(String[] args) {
        // --------- Test 1: the example "ABC"  ---------
        String test1 = "ABC";                                                 // input string
        List<String> expected1 = Arrays.asList("ABC", "ACB", "BAC", "BCA", "CAB", "CBA");  // expected perms
        List<String> result1 = getPermutations(test1);                        // generate perms
        // compare as sets so order does not matter
        if (new HashSet<>(result1).equals(new HashSet<>(expected1))) {
            System.out.println("Test1 PASS");                                 // test passed
        } else {
            System.out.println("Test1 FAIL: expected " + expected1 + " but got " + result1);
        }

        // --------- Test 2: empty string ---------
        String test2 = "";                                                    // empty input
        List<String> expected2 = List.of("");                           // only one perm: the empty string
        List<String> result2 = getPermutations(test2);                        // generate perms
        if (new HashSet<>(result2).equals(new HashSet<>(expected2))) {
            System.out.println("Test2 PASS");                                 // should pass
        } else {
            System.out.println("Test2 FAIL: expected " + expected2 + " but got " + result2);
        }

        // --------- Test 3: single character ---------
        String test3 = "X";                                                   // one-char input
        List<String> expected3 = List.of("X");                          // only one perm
        List<String> result3 = getPermutations(test3);                        // generate perms
        if (new HashSet<>(result3).equals(new HashSet<>(expected3))) {
            System.out.println("Test3 PASS");                                 // should pass
        } else {
            System.out.println("Test3 FAIL: expected " + expected3 + " but got " + result3);
        }

        // --------- Test 4: two characters ---------
        String test4 = "AB";                                                  // two-char input
        List<String> expected4 = Arrays.asList("AB", "BA");                    // two permutations
        List<String> result4 = getPermutations(test4);                        // generate perms
        if (new HashSet<>(result4).equals(new HashSet<>(expected4))) {
            System.out.println("Test4 PASS");                                 // should pass
        } else {
            System.out.println("Test4 FAIL: expected " + expected4 + " but got " + result4);
        }

        // --------- Test 5: duplicate characters ---------
        String test5 = "AAB";                                                 // input with a repeat
        List<String> expected5 = Arrays.asList("AAB", "ABA", "BAA");            // unique perms only
        List<String> result5 = getPermutations(test5);                        // generate perms
        if (new HashSet<>(result5).equals(new HashSet<>(expected5))) {
            System.out.println("Test5 PASS");                                 // should pass
        } else {
            System.out.println("Test5 FAIL: expected " + expected5 + " but got " + result5);
        }

        // --------- Large input test: size check only ---------
        String testLarge = "ABCDE";                                           // 5-char input → 120 perms
        List<String> resultLarge = getPermutations(testLarge);                // generate perms
        int expectedSize = 120;                                               // 5! = 120
        if (resultLarge.size() == expectedSize) {
            System.out.println("Large Input Test PASS: size=" + resultLarge.size());
        } else {
            System.out.println("Large Input Test FAIL: expected size " + expectedSize
                    + " but got " + resultLarge.size());
        }
    }
}
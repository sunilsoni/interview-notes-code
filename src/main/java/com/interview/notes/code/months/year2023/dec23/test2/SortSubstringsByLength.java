package com.interview.notes.code.months.year2023.dec23.test2;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

/**
 * Sort the substrings in given string by number of character in them in
 * incremental order.
 * Example: Given String: "Welcome to this Interview" Output: "to this Welcome
 * Interview"
 */
public class SortSubstringsByLength {

    public static String sortSubstrings(String str) {
        return Arrays.stream(str.split("\\W+")) // Split string into words
                .sorted(Comparator.comparingInt(String::length)) // Sort by length
                .collect(Collectors.joining(" ")); // Join words back into a string
    }

    public static void main(String[] args) {
        String str = "Welcome to this Interview";
        String sortedStr = sortSubstrings(str);
        System.out.println(sortedStr); // Output: to this Welcome Interview
    }
}
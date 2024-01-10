package com.interview.notes.code.months.year2023.Sep23.test2;

import java.util.*;

/**
 * Given a string s, sort the string to the following rules:
 * <p>
 * a. All the characters in string should be sorted based on their asci value.
 * <p>
 * b. Print the all character in sorted order along with before sorting index position.
 * <p>
 * <p>
 * Input: s = "Test String!"
 * <p>
 * Output:  Char>>index>>  >> 4
 * <p>
 * Char>>index>>! >> 11
 * <p>
 * Char>>index>>S >> 5
 * <p>
 * Char>>index>>T >> 0
 * <p>
 * Char>>index>>e >> 1
 * <p>
 * Char>>index>>g >> 10
 * <p>
 * Char>>index>>i >> 8
 * <p>
 * Char>>index>>n >> 9
 * <p>
 * Char>>index>>r >> 7
 * <p>
 * Char>>index>>s >> 2
 * <p>
 * Char>>index>>t >> 3
 * <p>
 * Char>>index>>t >> 6
 */
public class SortStringAlternative {

    public static void main(String[] args) {
        String s = "Test String!";
        sortAndPrint(s);
    }

    public static void sortAndPrint(String s) {
        Map<Integer, List<Character>> positionMap = new TreeMap<>();

        for (int i = 0; i < s.length(); i++) {
            positionMap
                    .computeIfAbsent((int) s.charAt(i), k -> new ArrayList<>())
                    .add(s.charAt(i));
        }

        List<Map.Entry<Character, Integer>> sortedEntries = new ArrayList<>();

        for (Map.Entry<Integer, List<Character>> entry : positionMap.entrySet()) {
            for (char ch : entry.getValue()) {
                sortedEntries.add(new AbstractMap.SimpleEntry<>(ch, s.indexOf(ch)));
                s = s.substring(0, s.indexOf(ch)) + '\0' + s.substring(s.indexOf(ch) + 1);
            }
        }

        for (Map.Entry<Character, Integer> entry : sortedEntries) {
            System.out.println("Char>>index>>" + entry.getKey() + " >> " + entry.getValue());
        }
    }
}

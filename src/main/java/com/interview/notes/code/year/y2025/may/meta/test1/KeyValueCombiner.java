package com.interview.notes.code.year.y2025.may.meta.test1;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class KeyValueCombiner {

    public static Map<Character, Integer> combineValues(char[][] array) {
        Map<Character, Integer> result = new TreeMap<>();

        for (char[] pair : array) {
            char key = pair[0];
            int value = Character.getNumericValue(pair[1]);

            result.merge(key, value, Integer::sum);
        }

        return result;
    }

    public static void main(String[] args) {
        char[][] input = {
                {'a', '7'}, {'c', '6'}, {'d', '1'},
                {'b', '2'}, {'a', '1'}, {'e', '3'}
        };

        Map<Character, Integer> result = combineValues(input);

        System.out.println("Input array:");
        for (char[] pair : input) {
            System.out.print(Arrays.toString(pair) + " ");
        }
        System.out.println("\nOutput: " + result);
    }
}

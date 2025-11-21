package com.interview.notes.code.year.y2023.Oct23.test10;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MostCommonNumberFinder {
    public static void main(String[] args) {
        String sequence = "95667366399"; // Your sequence of numbers

        // Step 1: Collect the numbers
        char[] numbersArray = sequence.toCharArray();
        List<Character> numbersList = new ArrayList<>();
        for (char num : numbersArray) {
            numbersList.add(num);
        }

        // Step 2: Count the occurrences and find the most common number in order
        Map<Character, Integer> numberCount = new HashMap<>();
        char mostCommonNumber = ' ';
        int maxCount = 0;

        for (char num : numbersList) {
            int count = numberCount.getOrDefault(num, 0) + 1;
            numberCount.put(num, count);

            if (count > maxCount || (count == maxCount && num < mostCommonNumber)) {
                mostCommonNumber = num;
                maxCount = count;
            }
        }

        // Step 3: Generate the result
        StringBuilder result = new StringBuilder();
        for (char num : numbersList) {
            if (num == mostCommonNumber) {
                result.append(num);
            }
        }

        System.out.println("The most common number in order is: " + result);
    }
}

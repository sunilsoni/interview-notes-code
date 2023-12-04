package com.interview.notes.code.months.nov23.test7;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//Use Java8 Stream /Find the 3rd 4th and 5th highest valued integer
public class FindElements {
    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(2, 4, 5, 19, 18, 40, 13, 6);

        List<Integer> result = integers.stream()
                                       .sorted((a, b) -> b.compareTo(a))
                                       .skip(2)   // Skip the first two highest values
                                       .limit(3)  // Limit to the next three values
                                       .collect(Collectors.toList());

        System.out.println("3rd, 4th, and 5th highest values: " + result);
    }
}

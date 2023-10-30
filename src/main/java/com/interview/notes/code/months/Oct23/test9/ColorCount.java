package com.interview.notes.code.months.Oct23.test9;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Java8 stream write code to tegrt below outpu
 * <p>
 * <p>
 * Arrays.asList("RED","BLUE","RED","GREEN");
 * <p>
 * Output: {RED=2, BLUE=1, GREEN=1}
 */
public class ColorCount {
    public static void main(String[] args) {
        Map<String, Long> result = Arrays.asList("RED", "BLUE", "RED", "GREEN")
                .stream()
                .collect(Collectors.groupingBy(e -> e, Collectors.counting()));

        System.out.println(result);
    }
}

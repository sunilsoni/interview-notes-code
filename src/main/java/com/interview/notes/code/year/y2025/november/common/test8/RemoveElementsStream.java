package com.interview.notes.code.year.y2025.november.common.test8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RemoveElementsStream {
    public static void main(String[] args) {
        List<Integer> ints = Arrays.asList(1, 3, 5, 7, 8, 10, 2, 4, 6, 9);
        
        List<Integer> result = IntStream.range(0, ints.size())
            .filter(i -> {
                if (i == 0) return true;
                return (ints.get(i) - ints.get(i - 1)) != 2;
            })
            .mapToObj(ints::get)
            .collect(Collectors.toList());
        
        System.out.println("Result: " + result);
    }
}
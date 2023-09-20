package com.interview.notes.code.months.june23.test12;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class NumCube {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);

        Map<Integer, Integer> numberToCubeMap = numbers.stream()
                .filter(num -> Math.pow(num, 3) > 50)
                .collect(Collectors.toMap(num -> num, num -> (int) Math.pow(num, 3)));

        System.out.println(numberToCubeMap);
    }
}



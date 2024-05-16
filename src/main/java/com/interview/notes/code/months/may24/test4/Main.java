package com.interview.notes.code.months.may24.test4;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Integer> a = Arrays.asList(123, 345, 567, 789);

        List<Integer> reversed = a.stream()
                .map(number -> new StringBuilder(String.valueOf(number)).reverse().toString())
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        System.out.println("Reversed list a = " + reversed);
    }
}

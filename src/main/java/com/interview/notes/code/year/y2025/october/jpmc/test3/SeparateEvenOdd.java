package com.interview.notes.code.year.y2025.october.jpmc.test3;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SeparateEvenOdd {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 10; i++) numbers.add(i);

        List<Integer> even = numbers.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());

        List<Integer> odd = numbers.stream()
                .filter(n -> n % 2 != 0)
                .collect(Collectors.toList());

        System.out.println("Even: " + even);
        System.out.println("Odd: " + odd);
    }
}
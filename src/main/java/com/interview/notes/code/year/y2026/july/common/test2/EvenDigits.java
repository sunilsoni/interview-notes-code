package com.interview.notes.code.year.y2026.july.common.test2;

import java.util.List;

public class EvenDigits {
    public static void main(String[] args) {
        String str = "as84235ufg";

        List<Integer> evenNumbers = str.chars()
                .filter(Character::isDigit)
                .map(c -> c - '0')
                .filter(n -> n % 2 == 0)
                .boxed()
                .toList();

        System.out.println(evenNumbers);
    }
}
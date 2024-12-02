package com.interview.notes.code.year.y2024.nov24.test10;

import java.util.Arrays;
import java.util.List;

public class FindHighestNumber {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(10, 5, 8, 12, 3, 7, 15, 1);

        int highest = numbers.stream()
                .max(Integer::compare)
                .orElseThrow(() -> new IllegalStateException("List is empty"));

        System.out.println("The highest number is: " + highest);
    }
}

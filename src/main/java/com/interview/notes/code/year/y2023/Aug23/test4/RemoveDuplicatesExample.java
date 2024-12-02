package com.interview.notes.code.year.y2023.Aug23.test4;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RemoveDuplicatesExample {
    public static void main(String[] args) {
        List<Integer> numberListWithDuplicates = Arrays.asList(1, 2, 3, 4, 5, 1, 2, 3);

        List<Integer> distinctNumbers = numberListWithDuplicates.stream()
                .distinct()
                .collect(Collectors.toList());

        System.out.println("Distinct Numbers: " + distinctNumbers);
    }
}

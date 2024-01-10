package com.interview.notes.code.months.year2023.nov23.test7;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Find common elements between below 2
 * <p>
 * Integer[] integers1 = {2, 4, 7, 9, 10, 17};
 * Integer[] integers2 = {3, 5, 7, 10};
 */
public class CommonElements {
    public static void main(String[] args) {
        Integer[] integers1 = {2, 4, 7, 9, 10, 17};
        Integer[] integers2 = {3, 5, 7, 10};

        List<Integer> list1 = Arrays.asList(integers1);
        List<Integer> list2 = Arrays.asList(integers2);

        List<Integer> commonElements = list1.stream()
                .filter(list2::contains)
                .collect(Collectors.toList());

        System.out.println("Common elements: " + commonElements);
    }
}

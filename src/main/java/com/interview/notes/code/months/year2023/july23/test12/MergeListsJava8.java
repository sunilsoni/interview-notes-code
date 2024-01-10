package com.interview.notes.code.months.year2023.july23.test12;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MergeListsJava8 {
    public static void main(String[] args) {
        List<Integer> listA = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> listB = Arrays.asList(4, 5, 6, 7, 8);

        List<Integer> listC = mergeLists(listA, listB);

        System.out.println("List A: " + listA);
        System.out.println("List B: " + listB);
        System.out.println("Merged List C (with duplicates): " + listC);

        List<Integer> distinctListC = removeDuplicates(listC);
        System.out.println("Merged List C (distinct): " + distinctListC);
    }

    public static List<Integer> mergeLists(List<Integer> listA, List<Integer> listB) {
        return Stream.concat(listA.stream(), listB.stream())
                .collect(Collectors.toList());
    }

    public static List<Integer> removeDuplicates(List<Integer> list) {
        return list.stream().distinct().collect(Collectors.toList());
    }
}

package com.interview.notes.code.year.y2025.october.common.test;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 5, 8, 4, 9, 4, 3);

        Set<Integer> set = list.stream().collect(Collectors.toCollection(TreeSet::new));
        Set<Integer> set1 = list.stream().collect(Collectors.toCollection(LinkedHashSet::new));
        Set<Integer> set2 = list.stream().collect(Collectors.toCollection(HashSet::new));

        set.forEach(System.out::print);
        System.out.println();
        set1.forEach(System.out::print);
        System.out.println();
        set2.forEach(System.out::print);
        System.out.println();
    }
}

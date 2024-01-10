package com.interview.notes.code.months.year2023.july23.test3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ModifyCollectionUsingLambda {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));

        list.forEach(item -> list.set(list.indexOf(item), item * 2));

        System.out.println(list);  // Outputs: [2, 4, 6, 8, 10]
    }
}

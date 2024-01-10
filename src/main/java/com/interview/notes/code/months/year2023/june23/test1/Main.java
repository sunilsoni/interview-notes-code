package com.interview.notes.code.months.year2023.june23.test1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> values = new ArrayList<>(Arrays.asList(1, 25, 15, 54, 8, 24, 12));

        // Add 93 at index 2
        values.add(2, 93);

        System.out.println(values);  // Output: [1, 25, 93, 15, 54, 8, 24, 12]
    }
}
